<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Form_Search_Text extends Magenifieddebugging_Blockinformation_Form_Search_Abstract
{
    const FORM_NAME = 'SEARCH_BLOCK_BY_TEXT';

    const MIN_INPUT_LENGTH = 4;

    const VALIDATION_ERROR_NO_INPUT = 'No results returned. Please enter a value before pressing the submit button.';

    const VALIDATION_ERROR_INPUT_TOO_SHORT = 'The value entered is invalid. It must have at least 6 characters. ';

    const VALIDATION_ERROR_TOO_MANY_MATCHES = 'The value you entered was found in more than one block.';

    const VALIDATION_ERROR_NO_MATCH = 'No match was found.  Please note, the search is case sensitive.';

    const VALIDATION_ERROR_BLOCK_CACHE = 'The block you are looking for is stored in the "Block Cache".<br />
        Please login to the Magento admin and turn off the "Blocks HTML Output" cache.';

    /**
     * Constructor
     */
    protected function _construct()
    {
        /* @var $service Magenifieddebugging_Blockinformation_Service_Match_Text */
        $service = Mage::getModel('mmdebugging_blockinformation_service/match_text', array('form' => $this));
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
        if($this->_isCurrentFormSubmitted() && strlen($_fieldValue) >= self::MIN_INPUT_LENGTH) {
            $isRequestValid = true;
        }

        return $isRequestValid;
    }

    /**
     * Processes the form, and finds validation errors
     */
    public function processForm()
    {
        if($this->_isCurrentFormSubmitted()) {
            /* @var $service Magenifieddebugging_Blockinformation_Service_Match_Text */
            $service = $this->getService();
            $service->eliminateMatchingBlocks(); //removes duplicate matches.

            $_fieldValue = $this->_getRequest()->getParam(self::FORM_NAME);
            /* @var $service Magenifieddebugging_Blockinformation_Service_Match_Text */
            $service = $this->getService();
            $blocksMatch = $service->getAllMatches();
            if(strlen($_fieldValue) == 0) {
                $this->addValidationError(self::VALIDATION_ERROR_NO_INPUT);
            } elseif($this->_doesInputContainIllegalStrings($_fieldValue)) {
                $this->addValidationError(self::VALIDATION_ERROR_ILLEGAL_CHARACTER . " " . implode(",",$this->_getIllegalCharacters()));
                $this->_getRequest()->setParam(self::FORM_NAME,"");
            } elseif(strlen($_fieldValue) < self::MIN_INPUT_LENGTH) {
                $this->addValidationError(self::VALIDATION_ERROR_INPUT_TOO_SHORT);
            } elseif(count($blocksMatch) > 1) {
                $matchingBlocks = implode(',', $blocksMatch);
                $message = self::VALIDATION_ERROR_TOO_MANY_MATCHES . '<br />' .
                    'These are the names of the blocks that match: ' . $matchingBlocks . '<br />' .
                    'Please enter the name of one of these blocks into the
                    "By entering the name of the block" box.';
                $this->addValidationError($message);
            } elseif($service->getMatch()->isInBlockCache()) {
                $this->addValidationError(self::VALIDATION_ERROR_BLOCK_CACHE);
            } elseif(count($blocksMatch) == 0) {
                $this->addValidationError(self::VALIDATION_ERROR_NO_MATCH);
            }
        }
    }

    /**
     * Returns the value we are looking for
     * @return mixed
     */
    public function getValueToSearchFor()
    {
        return $this->_getRequest()->getParam(Magenifieddebugging_Blockinformation_Form_Search_Text::FORM_NAME);
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