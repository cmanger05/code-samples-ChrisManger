<?php
/**
 * Creates the apartment table.
 *
 * @category    Apartment
 * @package     Manger
 * @author   	Chris Manger (chris-manger.com)
 */
$installer = $this;
$installer->startSetup();

$table = $installer->getConnection()
    ->newTable($installer->getTable('apartment/apartment'))
    ->addColumn('apartment_id', Varien_Db_Ddl_Table::TYPE_INTEGER, null, array(
        'identity'  => true,
        'unsigned'  => true,
        'nullable'  => false,
        'primary'   => true,
    ), 'unique id')
    ->addColumn('apartment_name', Varien_Db_Ddl_Table::TYPE_VARCHAR,
        255, array('nullable'  => true), 'Apartment Name')
    ->addColumn('tier', Varien_Db_Ddl_Table::TYPE_VARCHAR,
        255, array('nullable'  => true), 'tier')
    ->addColumn('email_primary', Varien_Db_Ddl_Table::TYPE_VARCHAR,
        255, array('nullable'  => true), 'Email Primary')
    ->addColumn('year_established', Varien_Db_Ddl_Table::TYPE_INTEGER, null, array(
        'unsigned'  => true,
        'nullable'  => true,
        'default'   => '0',
    ), 'year established')
    ->addColumn('number_current_residents', Varien_Db_Ddl_Table::TYPE_INTEGER, null, array(
        'unsigned'  => true,
        'nullable'  => true,
        'default'   => '0',
    ), 'number residents')
    ->addColumn('max_occupants', Varien_Db_Ddl_Table::TYPE_INTEGER, null, array(
        'unsigned'  => true,
        'nullable'  => true,
        'default'   => '0',
    ), 'max occupants');

$installer->getConnection()->createTable($table);
$installer->endSetup();