<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Form_Search_None extends Magenifieddebugging_Blockinformation_Form_Search_Abstract
{
    const FORM_NAME = 'None';

    /**
     * Constructor
     */
    protected function _construct()
    {
        /* @var $service Magenifieddebugging_Blockinformation_Service_Match_None */
        $service = Mage::getModel('mmdebugging_blockinformation_service/match_none', array('form' => $this));
        $this->_setService($service);
        parent::_construct();
    }

    /**
     * Request is always valid, this is a dummy request
     * @return bool
     */
    public function isRequestValid()
    {
        return true;
    }

    /**
     * Form is always valid, this is a dummy form
     * @return bool
     */
    public function isFormValid()
    {
        return true;
    }

    /**
     * No processing is required.
     */
    public function processForm() {}

    /**
     * Returns the form's name
     * @return string
     */
    public function getFormName()
    {
        return self::FORM_NAME;
    }
}