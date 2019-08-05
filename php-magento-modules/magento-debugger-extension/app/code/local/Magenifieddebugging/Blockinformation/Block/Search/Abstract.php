<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
abstract class Magenifieddebugging_Blockinformation_Block_Search_Abstract extends Mage_Core_Block_Template
{
    /**
     * Returns the name of the form
     * @return string
     */
    public abstract function getFormName();

    public abstract function getFormTitle();

    /**
     * Returns the model with the match data
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch()
    {
        return $this->_router()->getForm()->getService()->getMatch();
    }

    /**
     * @return bool
     */
    public function isMatchValid()
    {
        $isMatchValid = false;
        if($this->_isCurrentForm()) {
            $isMatchValid = $this->getMatch()->isMatchValid();
        }

        return $isMatchValid;
    }

    /**
     * Returns the validation errors
     * @return array
     */
    public function getValidationErrors()
    {
        $validationErrors = array();

        if($this->_isCurrentForm()) {
            $validationErrors = $this->_router()->getForm()->getValidationErrors();
        }

        return $validationErrors;
    }

    /**
     * Determines if there is a validation error
     * @return bool
     */
    public function hasValidationError()
    {
        $hasValidationErrors = false;

        if($this->_isCurrentForm()) {
            $hasValidationErrors = $this->_router()->getForm()->hasValidationError();
        }

        return $hasValidationErrors;
    }

    /**
     * Determines whether the form is current
     * @return bool
     */
    protected function _isCurrentForm()
    {
        $isCurrentForm = false;
        if($this->_router()->getForm()->getFormName() == $this->getFormName()) {
            $isCurrentForm = true;
        }

        return $isCurrentForm;
    }

    /**
     * Returns the name of the field containing the form name
     * @return string
     */
    public function getFieldWithFormName()
    {
        return Magenifieddebugging_Blockinformation_Form_Search_Abstract::FIELD_IDENTIFY_FORM_NAME;
    }

    /**
     * Returns the posted value
     * @return mixed
     * @throws Exception
     */
    public function getPostedValue()
    {
        $postedValue = $this->getRequest()->getParam($this->getFormName());
        $valueEscaped = $this->escapeHtml($postedValue);
        return $valueEscaped;
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