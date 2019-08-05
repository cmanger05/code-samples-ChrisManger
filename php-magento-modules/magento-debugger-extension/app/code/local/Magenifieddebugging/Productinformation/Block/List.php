<?php
/**
 * @category    Productinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Productinformation_Block_List extends Mage_Core_Block_Template
{
    CONST PRODUCT_LIST_PAGE_MAIN_HANDLE = "catalog_category_view";


    /**
     * This block is only output if the page requested is a product list page
     * @return string
     */
    protected function _toHtml()
    {
        $html = "";

        if($this->_isCategoryPage()) {
           $html = parent::_toHtml();
        }

        return  $html;
    }

    /**
     * Returns the product collection
     * @return Mage_Eav_Model_Entity_Collection_Abstract
     */
    public function getProductCollection()
    {
        /* @var $blockProductList Mage_Catalog_Block_Product_List */
        $blockProductList = $this->_getLayout()->getBlock('product_list');
        $productCollection = $blockProductList->getLoadedProductCollection();
        return $productCollection;
    }

    /**
     * Determines if page being requested is the category page
     * @return bool
     */
    private function _isCategoryPage()
    {
        $isCategoryPage = false;
        $handles = $this->_getLayout()->getUpdate()->getHandles();
        foreach($handles as $handle) {
            if($handle == self::PRODUCT_LIST_PAGE_MAIN_HANDLE) {
                $isCategoryPage = true;
            }
        }

        return $isCategoryPage;
    }

    /**
     * Renders the child products, if there are any
     * @param Mage_Catalog_Model_Product $product
     * @return string
     */
    public function renderChildProducts(Mage_Catalog_Model_Product $parentProduct)
    {
        $rendererChild = $this->_getLayout()->createBlock('magenifieddebugging_productinformation/list_children','',
            array('parent_product' => $parentProduct));

        return $rendererChild->toHtml();
    }

    /**
     * Returns the layout object
     * @return Mage_Core_Model_Layout
     */
    private function _getLayout()
    {
        return Mage::app()->getLayout();
    }
}