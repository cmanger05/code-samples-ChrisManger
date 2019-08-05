<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Router_Standard extends Magenifieddebugging_Base_Varien_Object
{
    protected $_form;

    /**
     * Returns the form object
     * @return Magenifieddebugging_Blockinformation_Form_Search_Abstract
     */
    public function getForm()
    {
        return $this->_form;
    }

    /**
     * Sets the form object
     * @param Magenifieddebugging_Blockinformation_Form_Search_Abstract $form
     */
    private function _setForm(Magenifieddebugging_Blockinformation_Form_Search_Abstract $form)
    {
        $this->_form = $form;
    }

    public function determineAndSetSubmittedForm()
    {
        $_submittedFormName = $this->_getSubmittedFormName();
        if($_submittedFormName == Magenifieddebugging_Blockinformation_Form_Search_Name::FORM_NAME) {
            /* @var $form Magenifieddebugging_Blockinformation_Form_Search_Name */
            $form = Mage::getModel('mmdebugging_blockinformation_form/search_name');
            $this->_setForm($form);
        } elseif($_submittedFormName == Magenifieddebugging_Blockinformation_Form_Search_Text::FORM_NAME) {
            /* @var $form Magenifieddebugging_Blockinformation_Form_Search_Text */
            $form = Mage::getModel('mmdebugging_blockinformation_form/search_text');
            $this->_setForm($form);
        } else {
            /* @var $form Magenifieddebugging_Blockinformation_Form_Search_None */
            $form = Mage::getModel('mmdebugging_blockinformation_form/search_none');
            $this->_setForm($form);
        }
    }

    /**
     * Returns the name of the submitted form
     * @return string
     */
    private function _getSubmittedFormName()
    {
        return $this->_getRequest()->getParam(Magenifieddebugging_Blockinformation_Form_Search_Abstract::FIELD_IDENTIFY_FORM_NAME);
    }
}