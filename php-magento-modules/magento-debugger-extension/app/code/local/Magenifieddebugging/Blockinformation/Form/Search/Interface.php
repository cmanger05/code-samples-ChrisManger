<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
interface Magenifieddebugging_Blockinformation_Form_Search_Interface
{
    /**
     * Determines if form is valid.
     * @return bool
     */
    public function isFormValid();

    /**
     * Determines if request is valid
     * @return mixed
     */
    public function isRequestValid();

    /**
     * Processes the form
     * @return mixed
     */
    public function processForm();

    /**
     * Returns the name of the form
     * @return string
     */
    public function getFormName();

    /**
     * Determines if there is a validation error
     * @return bool
     */
    public function hasValidationError();
}