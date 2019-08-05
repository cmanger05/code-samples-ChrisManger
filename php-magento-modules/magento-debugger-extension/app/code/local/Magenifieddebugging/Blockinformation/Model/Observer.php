<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Model_Observer extends Magenifieddebugging_Base_Varien_Object
{
    const ROOT_BLOCK_NAME = 'root';

    protected $_isFeatureEnabled;

    protected $_isSearchByTextRequestValid;

    protected $_hasProcessedDebuggingForms = false;

    /**
     * Determines if debugging forms have been processed
     * @return bool
     */
    private function _hasProcessedDebugginForms()
    {
        return $this->_hasProcessedDebuggingForms;
    }

    /**
     * Marks debugging forms as have been processed
     * @param $hasProcessed
     */
    private function _setHasProcessedDebuggingForms($hasProcessed)
    {
        $this->_hasProcessedDebuggingForms = $hasProcessed;
    }

    /**
     * Determines what block information form has been submitted, if any
     * @param Varien_Event_Observer $observer
     */
    public function determineSubmittedForm(Varien_Event_Observer $observer)
    {
        if($this->_isFeatureEnabled()) {
            $this->_router()->determineAndSetSubmittedForm();
        }
    }

    /**
     * Processes whatever form has been submitted. Observer executes right before blocks are rendered.
     * @param Varien_Event_Observer $observer
     */
    public function processDebuggingForm(Varien_Event_Observer $observer)
    {
        if($this->_isFeatureEnabled()) {
            /* @var $block Mage_Core_Block_Abstract */
            $block = $observer->getData('block');
            if ($block->getNameInLayout() == self::ROOT_BLOCK_NAME && !$this->_hasProcessedDebugginForms()) {
                $this->_router()->getForm()->processForm();
                $this->_setHasProcessedDebuggingForms(true);
            }
        }
    }

    /**
     * Adds all of the html to an array
     * @param Varien_Event_Observer $observer
     */
    public function determineIfBlockContainsSearchText(Varien_Event_Observer $observer)
    {
        if($this->_isFeatureEnabled() && $this->_isSearchByTextRequestValid()) {
            /* @var $_form Magenifieddebugging_Blockinformation_Form_Search_Text */
            $_form = $this->_router()->getForm();
            /* @var $_service Magenifieddebugging_Blockinformation_Service_Match_Text */
            $_service = $_form->getService();
            /* @var $block Mage_Core_Block_Abstract */
            $block = $observer->getData('block');

            $valueToSearchFor = $_form->getValueToSearchFor();
            /* @var $transport Varien_Object */
            $transport = $observer->getData('transport');
            $html = $transport->getData('html');
            if($valueToSearchFor && $html && strpos('.'.$html, $valueToSearchFor) > 0 && $block instanceof Mage_Core_Block_Abstract) {
                $_service->addMatch($block, $html);
            }
        }
    }

    /**
     * Determines if the search by text request is valid.
     * @return bool
     */
    private function _isSearchByTextRequestValid()
    {
        if(is_null($this->_isSearchByTextRequestValid)) {
            $isSearchByTextRequestValid = false;

            $_form = $this->_router()->getForm();
            if($this->_isFeatureEnabled() && $_form instanceof Magenifieddebugging_Blockinformation_Form_Search_Text) {
                if($_form->isRequestValid()) {
                    $isSearchByTextRequestValid = true;
                }
            }

            $this->_isSearchByTextRequestValid = $isSearchByTextRequestValid;
        }

        return $this->_isSearchByTextRequestValid;
    }

    /**
     * Determines if the feature is enabled
     * @return bool
     */
    private function _isFeatureEnabled()
    {
        if(is_null($this->_isFeatureEnabled)) {
            $this->_isFeatureEnabled = $this->_helper()->isFeatureEnabled();
        }

        return $this->_isFeatureEnabled;
    }

    /**
     * Returns the main helper
     * @return Magenifieddebugging_Blockinformation_Helper_Data
     */
    private function _helper()
    {
        return Mage::helper('mmdebugging_blockinformation');
    }

    /**
     * Returns the router (the router helps to process and validate the form)
     * @return Magenifieddebugging_Blockinformation_Router_Standard
     */
    private function _router()
    {
        return Mage::getSingleton('mmdebugging_blockinformation_router/standard');
    }
}