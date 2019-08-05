<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Block_Search_Text extends Magenifieddebugging_Blockinformation_Block_Search_Abstract
{
    CONST FORM_TITLE = 'By entering a word that you see:';

    /**
     * Returns the form name
     * @return string
     */
    public function getFormName()
    {
        return Magenifieddebugging_Blockinformation_Form_Search_Text::FORM_NAME;
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