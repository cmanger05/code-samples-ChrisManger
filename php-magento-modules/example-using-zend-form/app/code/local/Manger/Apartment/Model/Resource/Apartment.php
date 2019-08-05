<?php
/**
 * @category    Apartment
 * @package     Manger
 * @author   	Chris Manger (chris-manger.com)
 */
class Manger_Apartment_Model_Resource_Apartment extends Mage_Core_Model_Resource_Db_Abstract
{
    public function _construct()
    {
        $this->_init('apartment/apartment','apartment_id');
    }
}