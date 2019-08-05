<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Service_Match_Text_Eliminateduplicates
    extends Magenifieddebugging_Blockinformation_Service_Match_Abstract
{
    protected $_parentService;

    /**
     * Constructor
     * @throws Mage_Core_Exception
     */
    protected function _construct()
    {
        $parentService = $this->getData('parent_service');
        if(!$parentService || !$parentService instanceof Magenifieddebugging_Blockinformation_Service_Match_Abstract) {
            Mage::throwException('a required or invalid value was passed into the constructor');
        }

        $this->_parentService = $parentService;
        parent::_construct();
    }

    /**
     * Eliminates any duplicate parent blocks that match.
     */
    public function eliminateMatchingBlocks()
    {
        $layout = $this->_getLayout();
        foreach($this->_getAllMatches() as $blockName) {
            $block = $layout->getBlock($blockName);
            if($block && $block instanceof Mage_Core_Block_Abstract) {
                $this->_eliminateABlocksMatchingParents($block);
            }
        }
    }

    /**
     * @param Mage_Core_Block_Abstract $block
     */
    private function _eliminateABlocksMatchingParents(Mage_Core_Block_Abstract $block)
    {
        $blockHtml = $this->_getParentService()->getBlockHtml($block);
        /* @var $form Magenifieddebugging_Blockinformation_Form_Search_Text */
        $form = $this->getForm();
        $numberOccurrencesInChildBlock = substr_count($blockHtml, $form->getValueToSearchFor());

        /* @var $matchingParent Mage_Core_Block_Abstract */
        foreach($this->_getParentsWithSameMatchIfExists($block) as $matchingParent) {
            $numberOccurrencesInParentBlock = substr_count($this->_getParentService()->getBlockHtml($matchingParent), $form->getValueToSearchFor());
            if($numberOccurrencesInChildBlock == $numberOccurrencesInParentBlock) {
                if(($key = array_search($matchingParent->getNameInLayout(), $this->_getAllMatches())) !== false) {
                    $this->_getParentService()->removeMatch($key);
                    break;
                }
            }
        }
    }

    /**
     * Returns parents with same match if they exist
     * @param Mage_Core_Block_Abstract $block
     * @return array
     */
    private function _getParentsWithSameMatchIfExists(Mage_Core_Block_Abstract $block)
    {
        $matchingParents = array();

        /* @var $block Mage_Core_Block_Abstract */
        /*we already know that the current block is a match. We only need to check the parent
        the line below skips the parent and gets the next block. */
        $block = $block->getParentBlock();

        while($block && $block->getNameInLayout() != "root") {
            if(in_array($block->getNameInLayout(), $this->_getAllMatches())) {
                $matchingParents[] = $block;
            }

            $block = $block->getParentBlock();
        }

        return $matchingParents;
    }

    /**
     * Returns all the matches
     * @return array
     */
    private function _getAllMatches()
    {
        return $this->_getParentService()->getAllMatches();
    }

    /**
     * Returns the match
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch()
    {
        return $this->_getParentService()->getMatch();
    }

    /**
     * Returns the parent service
     * @return Magenifieddebugging_Blockinformation_Service_Match_Text
     */
    private function _getParentService()
    {
        return $this->_parentService;
    }
}