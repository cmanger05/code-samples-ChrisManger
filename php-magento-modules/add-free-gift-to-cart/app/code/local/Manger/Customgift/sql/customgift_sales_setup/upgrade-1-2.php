<?php
/**
 * This adds a column to the salesrule type for the purpose of saving the new discount type.
 * This discount type makes it possible for a free product to be added to the cart.
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
$installer = $this;
$installer->startSetup();

$typeBoolean = Varien_Db_Ddl_Table::TYPE_BOOLEAN;

foreach (array('quote_item','order_item') as $entityType) {
    $installer->addAttribute($entityType, 'is_customgift_product', array('type' => $typeBoolean));
}

$installer->endSetup();