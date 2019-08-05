<?php
/**
 * Source model lists all products in the store.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Source_Listallproducts
{
    /**
     * Returns an array of all the products
     * @return array
     */
    public function toOptionArray()
    {
        //This creates drop down menu options that lets user choose what product should be awarded for free.
        $products = Mage::getModel('catalog/product')->getCollection()
            ->addAttributeToSelect('name')
            ->addAttributeToFilter('status', Mage_Catalog_Model_Product_Status::STATUS_ENABLED);

        Mage::getSingleton('catalog/product_status')->addSaleableFilterToCollection($products);

        $options = array();

        foreach($products as $product)
        {
            $options[] = array(
              'label' => Mage::helper('adminhtml')->__($product->getName()),
              'value' => $product->getId(),
            );
        }

        return $options;
    }
}