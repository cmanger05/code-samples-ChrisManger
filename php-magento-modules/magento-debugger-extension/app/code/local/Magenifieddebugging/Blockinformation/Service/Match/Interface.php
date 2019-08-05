<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
interface Magenifieddebugging_Blockinformation_Service_Match_Interface
{
    /**
     * Returns a class containing all the data about the match
     * @return Magenifieddebugging_Blockinformation_Model_Match_Data
     */
    public function getMatch();

    public function getForm();
}