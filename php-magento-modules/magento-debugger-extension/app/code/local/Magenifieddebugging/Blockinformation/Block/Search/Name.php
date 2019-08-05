<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Block_Search_Name extends Magenifieddebugging_Blockinformation_Block_Search_Abstract
{
    CONST FORM_TITLE = 'By entering the name of the block:';

    /**
     * Returns the form name
     * @return string
     */
    public function getFormName()
    {
        return Magenifieddebugging_Blockinformation_Form_Search_Name::FORM_NAME;
    }

    /**
     * Returns the forms title
     * @return string
     */
    public function getFormTitle()
    {
        return self::FORM_TITLE;
    }
}