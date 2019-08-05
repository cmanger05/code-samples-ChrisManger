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
$installer->run("ALTER TABLE salesrule ADD free_product_ids VARCHAR( 100 ) NULL AFTER  product_ids");
$installer->endSetup();
