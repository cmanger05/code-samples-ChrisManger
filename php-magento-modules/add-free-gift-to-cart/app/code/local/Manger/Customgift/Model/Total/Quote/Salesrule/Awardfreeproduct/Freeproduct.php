<?php
/**
 * Determines if a free product has been added to the cart
 * If it has, it stores the product object and item object
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Total_Quote_Salesrule_Awardfreeproduct_Freeproduct extends Varien_Object
{
    protected $_freeProduct = null;

    protected $_freeItem;

    protected $_discountQuantity;

    protected $_requiredVariables = array(
        'quote',
    );

    /**
     * Constructor
     */
    public function _construct()
    {
        parent::_construct();
        $this->getFreeItem();
    }
    /**
     * Returns the free product object
     * @param string $rule
     * @return Mage_Catalog_Model_Product
     */
    public function getFreeProduct()
    {
        if(is_null($this->_freeProduct) && $this->getRule()) {
            $freeProductId = $this->getRule()->getFreeProductIds();
            $product = $this->_initProductById($freeProductId);
            if($product && $product instanceof Mage_Catalog_Model_Product) {
                $this->_freeProduct = $product;
            }
        }

        return $this->_freeProduct;
    }

    /**
     * Retrives the exixting quote item from the product object
     * @return Mage_Sales_Model_Quote_Item
     */
    public function getFreeItem()
    {
        $freeItem = null;
        if(is_null($this->_freeItem)) {
            $freeProduct = $this->getFreeProduct();

            if($freeProduct) {
                $item = $this->getQuote()->getItemByProduct($freeProduct); //if item has  already been added to cart, it is loaded.
                if($item instanceof Mage_Sales_Model_Quote_Item) {
                    $this->_freeItem = $item;
                }
            }
        }

        return $this->_freeItem;
    }

    /**
     * Sets the discount quantity
     */
    public function setDiscountQty($discountQty)
    {
        $this->_discountQuantity = $discountQty;
    }

    /**
     * Retrieves the discount quantity
     */
    public function getDiscountQty()
    {
        return $this->_discountQuantity;
    }

    /**
     * Instantiates a product based on the id.
     * @param string $productId
     * @return bool
     */
    protected function _initProductById($productId = '')
    {
        if ($productId) {
            $product = Mage::getModel('catalog/product')
                ->setStoreId(Mage::app()->getStore()->getId())
                ->load($productId);
            if ($product->getId()) {
                return $product;
            }
        }
        return false;
    }
}