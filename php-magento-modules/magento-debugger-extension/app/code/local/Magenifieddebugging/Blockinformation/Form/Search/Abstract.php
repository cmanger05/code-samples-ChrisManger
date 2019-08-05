<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
abstract class Magenifieddebugging_Blockinformation_Form_Search_Abstract extends Magenifieddebugging_Base_Varien_Object
    implements Magenifieddebugging_Blockinformation_Form_Search_Interface
{
    const PARAM_NAME_DEBUG_TYPE = 'debughint';

    const FIELD_IDENTIFY_FORM_NAME = 'form_name';

    const VALIDATION_ERROR_ILLEGAL_CHARACTER = "The value you entered contains an illegal character.
        The following characters are illegal: ";

    protected $_service;

    protected $_validationErrors = array();

    protected $_illegalCharacters = array(">","<","™","©","&");

    /**
     * Constructor
     * @throws Mage_Core_Exception
     */
    protected function _construct()
    {
        if(!$this->getService()) {
            Mage::throwException('service class must be set in the constructor');
        }
    }

    /**
     * Returns the name of the form
     * @return string
     */
    public abstract function getFormName();

    /**
     * Sets the service class
     * @param $service
     */
    protected function _setService($service)
    {
        $this->_service = $service;
    }

    /**
     * Gets the service class
     * @return Magenifieddebugging_Blockinformation_Service_Match_Interface
     */
    public function getService()
    {
        return $this->_service;
    }

    /**
     * Adds an error message
     * @param string $errorMessage
     */
    public function addValidationError($errorMessage = '')
    {
        if($errorMessage) {
            $this->_validationErrors[] = $errorMessage;
        }
    }

    /**
     * Returns all the validation errors
     * @return array
     */
    public function getValidationErrors()
    {
        return array_unique($this->_validationErrors);
    }

    /**
     * Determines if the form has a validation error
     * @return bool
     */
    public function hasValidationError()
    {
        return (count($this->getValidationErrors()) > 0) ? true : false;
    }

    /**
     * Determines if the current form has been submitted
     * @return bool
     */
    protected function _isCurrentFormSubmitted()
    {
        $isCurrentFormSubmitted = false;

        $formFieldName = Magenifieddebugging_Blockinformation_Form_Search_Abstract::FIELD_IDENTIFY_FORM_NAME;
        $formFieldNamePosted = $this->_getRequest()->getParam($formFieldName);
        if($formFieldNamePosted == $this->getFormName()) {
            $isCurrentFormSubmitted = true;
        }

        return $isCurrentFormSubmitted;
    }

    /**
     * Determines if the string contains an illegal character
     * @param $userInput
     * @return bool
     */
    protected function _doesInputContainIllegalStrings($userInput)
    {
        $doesInputContainIllegalStrings = false;

        foreach($this->_getIllegalCharacters() as $illegalCharacter) {
            if(strpos($userInput, $illegalCharacter) !== false) {
                $doesInputContainIllegalStrings = true;
                break;
            }
        }

        return $doesInputContainIllegalStrings;
    }

    /**
     * Returns a list of illegal characters
     * @return array
     */
    protected function _getIllegalCharacters()
    {
        return $this->_illegalCharacters;
    }

    /**
     * Determines if the form is valid
     * @return bool
     */
    public function isFormValid()
    {
        $isFormValid = false;

        if($this->isRequestValid()) {
            /* @var $match Magenifieddebugging_Blockinformation_Model_Match_Data */
            if($this->getService()->getMatch()->isMatchValid()) {
                $isFormValid = true;
            }
        }

        return $isFormValid;
    }
}