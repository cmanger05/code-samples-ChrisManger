<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Service_Match_Text extends Magenifieddebugging_Blockinformation_Service_Match_Abstract
{
    protected $_blocksMatch = array();

    protected $_excludedBlocks = array('content','root');

    protected $_match;

    protected $_blockHtml = array();

    /**
     * Adds block html to the array
     * @param $html
     * @param $block
     */
    public function addMatch(Mage_Core_Block_Abstract $block, $html = '')
    {
        $blockName = $block->getNameInLayout();
        if($blockName && !in_array($blockName, $this->_excludedBlocks) && !$block instanceof Mage_Core_Block_Text_List
            && !$block instanceof Mage_Page_Block_Html_Wrapper) {
            $this->_blocksMatch[] = $blockName;
            $this->_blockHtml[$blockName] = $html;
        }
    }

    /**
     * Returns the matching block
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch()
    {
        if(is_null($this->_match)) {
            /* @var $block Mage_Core_Block_Abstract */
            $block = new Mage_Core_Block_Template();

            if(count($this->getAllMatches()) == 1) {
                $matchingBlockName = array_pop($this->getAllMatches());
                $blockCandidate = $this->_getLayout()->getBlock($matchingBlockName);
                if($blockCandidate && $blockCandidate instanceof Mage_Core_Block_Abstract) {
                    $block = $blockCandidate;
                }
            }

            /* @var $match Magenifieddebugging_Blockinformation_Model_Match_Data */
            $this->_match = Mage::getModel('mmdebugging_blockinformation/match_data', array(
                'block' => $block,
                'form' => $this->getForm()
            ));
        }

        return $this->_match;
    }

    /**
     * Eliminates any duplicate parent blocks that match.
     */
    public function eliminateMatchingBlocks()
    {
        $this->_serviceEliminateDuplicates()->eliminateMatchingBlocks();
    }

    /**
     * Return block html
     * @param Mage_Core_Block_Abstract $block
     * @return string
     */
    public function getBlockHtml(Mage_Core_Block_Abstract $block)
    {
        $blockHtml = "";

        $blockName = $block->getNameInLayout();
        if($blockName && isset($this->_blockHtml[$blockName]) && $this->_blockHtml[$blockName]) {
            $blockHtml = $this->_blockHtml[$blockName];
        }

        return $blockHtml;
    }

    /**
     * Returns array containing all html
     * @return array
     */
    public function getAllMatches()
    {
        return $this->_blocksMatch;
    }

    /**
     * Removes a match
     * @param $key
     */
    public function removeMatch($key)
    {
        if(isset($this->_blocksMatch[$key]) && $this->_blocksMatch[$key]) {
            unset($this->_blocksMatch[$key]);
        }
    }

    /**
     * Creates service for eliminating duplicates
     * @return Magenifieddebugging_Blockinformation_Service_Match_Text_Eliminateduplicates
     */
    private function _serviceEliminateDuplicates()
    {
        return Mage::getModel('mmdebugging_blockinformation_service/match_text_eliminateduplicates', array(
            'form'              => $this->getForm(),
            'parent_service'    => $this,
        ));
    }
}