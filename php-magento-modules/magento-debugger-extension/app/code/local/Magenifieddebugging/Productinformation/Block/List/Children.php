<?php
/**
 * @category    Base
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Productinformation_Block_List_Children extends Mage_Core_Block_Template
{
    private $_parentProduct;

    /**
     * Constructor
     */
    protected function _construct()
    {
        $parentProduct = $this->getData('parent_product');

        if($parentProduct && $parentProduct instanceof Mage_Catalog_Model_Product && $parentProduct->getId()) {
            $this->_parentProduct = $parentProduct;
        }

        $this->setTemplate("magemagenified/debugging/productinformation/list/children.phtml");
        parent::_construct();

    }

    /**
     * This block is only output if a parent product exists
     * @return string
     */
    protected function _toHtml()
    {
        $html = "";

        if($this->_getParentProduct()) {
            $html = parent::_toHtml();
        }

        return $html;
    }

    /**
     * Returns collection of child products
     * @return Mage_Catalog_Model_Resource_Eav_Mysql4_Product_Link_Product_Collection|Mage_Catalog_Model_Resource_Eav_Mysql4_Product_Type_Configurable_Product_Collection|Varien_Data_Collection
     */
    public function getChildProductsCollection()
    {
        $childProductsCollection = new Varien_Data_Collection();

        $_parentProduct = $this->_getParentProduct();

        if($_parentProduct) {
            $_parentProductTypeInstance = $this->_getParentProduct()->getTypeInstance();
            if($_parentProductTypeInstance instanceof Mage_Catalog_Model_Product_Type_Grouped) {
                $childProductsCollection = $_parentProductTypeInstance->getAssociatedProductCollection();
            } elseif($_parentProductTypeInstance instanceof Mage_Catalog_Model_Product_Type_Configurable) {
                $childProductsCollection = $_parentProductTypeInstance->getUsedProductCollection();
            }
        }

        return $childProductsCollection;
    }

    /**
     * Returns the quantity
     * @param Mage_Catalog_Model_Product $product
     * @return float|string
     */
    public function getQty(Mage_Catalog_Model_Product $product)
    {
        $qty = "";

        /* @var $stockItem Mage_CatalogInventory_Model_Stock_Item*/
        $stockItem = $product->getData('stock_item');

        if($stockItem && $stockItem instanceof Mage_CatalogInventory_Model_Stock_Item) {
            $qty = round($stockItem->getQty());
        }

        return $qty;
    }

    /**
     * Returns the parent product
     * @return Mage_Catalog_Model_Product
     */
    private function _getParentProduct()
    {
        return $this->_parentProduct;
    }
}