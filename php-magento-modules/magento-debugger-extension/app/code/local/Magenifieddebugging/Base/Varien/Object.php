<?php
/**
 * @category    Base
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Base_Varien_Object extends Varien_Object
{
    /**
     * Returns the request object
     * @return Mage_Core_Controller_Request_Http
     */
    protected function _getRequest()
    {
        return Mage::app()->getRequest();
    }

    /**
     * Returns the layout object
     * @return Mage_Core_Model_Layout
     */
    protected function _getLayout()
    {
        return Mage::app()->getLayout();
    }
}