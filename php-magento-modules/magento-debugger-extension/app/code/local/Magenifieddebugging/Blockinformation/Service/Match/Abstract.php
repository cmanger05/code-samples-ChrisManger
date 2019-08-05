<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
abstract class Magenifieddebugging_Blockinformation_Service_Match_Abstract extends Magenifieddebugging_Base_Varien_Object
    implements Magenifieddebugging_Blockinformation_Service_Match_Interface
{
    protected $_form;

    /**
     * Constructor
     * @throws Mage_Core_Exception
     */
    protected function _construct()
    {
        $form = $this->getData('form');
        if(!$form || !$form instanceof Magenifieddebugging_Blockinformation_Form_Search_Interface) {
            Mage::throwException('a required or invalid value was passed into the constructor');
        }

        $this->_setForm($form);
    }

    /**
     * Sets the form object
     * @param Magenifieddebugging_Blockinformation_Form_Search_Interface $form
     */
    protected function _setForm(Magenifieddebugging_Blockinformation_Form_Search_Interface $form)
    {
        $this->_form = $form;
    }

    /**
     * Returns the form object
     * @return Magenifieddebugging_Blockinformation_Form_Search_Interface
     */
    public function getForm()
    {
        return $this->_form;
    }
}