<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Service_Match_Name extends Magenifieddebugging_Blockinformation_Service_Match_Abstract
{
    protected $_match;

    /**
     * Returns the matching block
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch()
    {
        if(is_null($this->_match)) {
            /* @var $block Mage_Core_Block_Abstract */
            $block = new Mage_Core_Block_Template();

            $_fieldValue = $this->_getRequest()->getParam(Magenifieddebugging_Blockinformation_Form_Search_Name::FORM_NAME);
            if($_fieldValue) {
                $blockCandidate = $this->_getLayout()->getBlock($_fieldValue);
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
}