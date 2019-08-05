<?php
/**
 * @category    Base
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Base_Block_Maincontainer extends Mage_Core_Block_Template
{
    CONST HANDLE_ONEPAGE_CHECKOUT_PREFIX = "checkout_onepage_";

    /**
     * Constructor
     */
    protected function _construct()
    {
        parent::_construct(); //this sets the default template.
        /* if the page being requested is onepage checkout. This page displays an error message stating this feature
        does not work in the one page checkout.*/
        if(!$this->_isPageValid()) {
            $this->setTemplate("magemagenified/debugging/base/formscontainer/invalidrequest/checkout.phtml");
        } else {
            $this->setTemplate("magemagenified/debugging/base/forms-container.phtml"); //this is the default template.
        }
    }

    /**
     * The debugging feature does not work on all pages.
     * @return bool
     */
    private function _isPageValid()
    {
        return !$this->_isOnePageCheckout();
    }

    /**
     * Determines if the page request is for the one page checkout.
     */
    private function _isOnePageCheckout()
    {
        $isOnePageCheckout = false;
        $handles = $this->_getLayout()->getUpdate()->getHandles();
        foreach($handles as $handle) {
            if(strpos($handle,self::HANDLE_ONEPAGE_CHECKOUT_PREFIX) !== false) {
                $isOnePageCheckout = true;
            }
        }

        return $isOnePageCheckout;
    }

    /**
     * Returns the layout object
     * @return Mage_Core_Model_Layout
     */
    private function _getLayout()
    {
        return Mage::app()->getLayout();
    }
}