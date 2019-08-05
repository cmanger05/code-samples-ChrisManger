<?php
/**
 * @category    Productinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Productinformation_Helper_Data extends Mage_Core_Helper_Abstract
{
    private $_storeId;

    /**
     * Returns the url to view the product in the admin
     * @param Mage_Catalog_Model_Product $product
     * @return string
     */
    public function getProductUrl(Mage_Catalog_Model_Product $product)
    {
        /* @var $adminUrlModel Mage_Adminhtml_Model_Url */
        $adminUrlModel = Mage::getModel('adminhtml/url');
        $params = array(
            'id'        => $product->getId(),
            'store'     => $this->_getStoreId(),
        );

        $urlRaw = $adminUrlModel->getUrl("admin/catalog_product/edit", $params);
        $url = str_replace("//catalog_product","/admin/catalog_product", $urlRaw);

        return $url;
    }

    /**
     * Returns the store id
     * @return int
     */
    private function _getStoreId()
    {
        if(is_null($this->_storeId)) {
            $this->_storeId = Mage::app()->getStore()->getId();
        }

        return $this->_storeId;
    }
}