<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Service_Match_None extends Magenifieddebugging_Blockinformation_Service_Match_Abstract
{
    /**
     * Returns the matching block
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch()
    {
        /* @var $block Mage_Core_Block_Abstract */
        $block = new Mage_Core_Block_Template();
        /* @var $match Magenifieddebugging_Blockinformation_Model_Match_Data */
        $match = Mage::getModel('mmdebugging_blockinformation/match_data', array(
            'block' => $block,
            'form' => $this->getForm()
        ));

        return $match;
    }
}