<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Base_Model_Observer extends Varien_Object
{
    const HANDLE_DEBUGGING_FORMS = 'magemagenified_debugging_forms';

    /**
     * Displays the forms at the very bottom of the page.
     * It accomplishes this by adding a handle to retrieve layout XML that adds block at bottom of page
     * @param Varien_Event_Observer $observer
     */
    public function displayDebuggingForms(Varien_Event_Observer $observer)
    {
        if($this->_helper()->isDebuggingRequestValid()) {
            /* @var $layout Mage_Core_Model_Layout */
            $layout = $observer->getData('layout');
            $layout->getUpdate()->addHandle(self::HANDLE_DEBUGGING_FORMS);
        }
    }

    /**
     * Returns the main helper
     * @return Magenifieddebugging_Base_Helper_Data
     */
    private function _helper()
    {
        return Mage::helper('magenifieddebugging_base');
    }
}