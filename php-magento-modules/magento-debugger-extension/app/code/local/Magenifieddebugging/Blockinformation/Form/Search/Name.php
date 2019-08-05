<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Form_Search_Name extends Magenifieddebugging_Blockinformation_Form_Search_Abstract
{
    const FORM_NAME = 'SEARCH_BLOCK_BY_NAME';

    const DEBUG_HINT_NAME = 'block';

    const VALIDATION_ERROR_NO_INPUT = 'Please enter a value before hitting submit. Thank you. ';

    const VALIDATION_ERROR_NOT_FOUND = 'No block with that name could be found.';

    /**
     * Constructor
     */
    protected function _construct()
    {
        /* @var $service Magenifieddebugging_Blockinformation_Service_Match_Text */
        $service = Mage::getModel('mmdebugging_blockinformation_service/match_name', array('form' => $this));
        $this->_setService($service);
        parent::_construct();
    }

    /**
     * Determines if request is valid
     */
    public function isRequestValid()
    {
        $isRequestValid = false;

        $_fieldValue = $this->_getRequest()->getParam(self::FORM_NAME);
        if($this->_isCurrentFormSubmitted()) {
            if(strlen($_fieldValue) >= 0) {
                $isRequestValid = true;
            }
        }

        return $isRequestValid;
    }

    /**
     * Processing determines if there are any errors.
     */
    public function processForm()
    {
        if($this->_isCurrentFormSubmitted()) {
            $_fieldValue = $this->_getRequest()->getParam(self::FORM_NAME);
            if(strlen($_fieldValue) == 0) {
                $this->addValidationError(self::VALIDATION_ERROR_NO_INPUT);
            } elseif($this->_doesInputContainIllegalStrings($_fieldValue)) {
                $this->addValidationError(self::VALIDATION_ERROR_ILLEGAL_CHARACTER . " " . implode(",",$this->_getIllegalCharacters()));
                $this->_getRequest()->setParam(self::FORM_NAME,"");
            } elseif(!$this->getService()->getMatch()->isMatchValid()) {
                $this->addValidationError(self::VALIDATION_ERROR_NOT_FOUND);
            }
        }
    }

    /**
     * Returns the form's name
     * @return string
     */
    public function getFormName()
    {
        return self::FORM_NAME;
    }
}