<?php
/**
 * Adds a free item to the cart if necessary.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Total_Quote_Salesrule_Awardfreeproduct_Addtocart extends Varien_Object
{
    protected $_hasFreeProductBeenAddedToCart;

    /**
     * Sets if the free product has been added to the cart
     * @param $hasFreeProductBeenAddedToCart
     */
    public function setHasFreeProductBeenAddedToCart($hasFreeProductBeenAddedToCart)
    {
        $this->_hasFreeProductBeenAddedToCart = $hasFreeProductBeenAddedToCart;
    }

    /**
     * Returns if the free product has been added to the cart.
     * @return bool
     */
    public function hasFreeProductBeenAddedToCart()
    {
        return $this->_hasFreeProductBeenAddedToCart;
    }

    /**
     * Will only return true if the collector states that a new product needs to be added.
     * @return boolean
     */
    protected function _shouldAddFreegiftProduct()
    {
        return Mage::registry(Manger_Customgift_Model_Total_Quote_Salesrule_Awardfreeproduct_Validator::REGISTRY_KEY_ADD_PROMO_ITEM);
    }

    /**
     * Retrieve shopping cart model object
     *
     * @return Mage_Checkout_Model_Cart
     */
    protected function _getCart()
    {
        return Mage::getSingleton('checkout/cart');
    }

    /**
     * Returns the free item, if it exists
     * @return Mage_Sales_Model_Quote_Item
     */
    protected function _getFreeItem()
    {
        return $this->getFreeproductmodel()->getFreeItem();
    }

    /** Returns the free product
     * @return Mage_Sales_Model_Product
     */
    protected function _getFreeProduct()
    {
        return $this->getFreeproductmodel()->getFreeProduct();
    }

    /**
     * Checks to see if the free item is already in the cart
     * @return bool
     */
    protected function _isFreeProductAlreadyInCart()
    {
        $isFreeProductAlreadyInCart = false;
        $freeItem = $this->_getFreeItem();
        $freeProduct = $this->_getFreeProduct();

        if($freeItem instanceof Mage_Sales_Model_Quote_Item
            && $freeProduct instanceof Mage_Catalog_Model_Product) {
            $quote = $this->getQuote();
            $isFreeProductAlreadyInCart = $quote->getItemByProduct($this->_getFreeProduct()) ? true : false;
        }

        return $isFreeProductAlreadyInCart;
    }

    /**
     * Adds a free product to the cart.
     */
    public function addFreeProductToCart()
    {
        $this->setHasFreeProductBeenAddedToCart(false);
        if($this->_shouldAddFreegiftProduct() && !$this->_isFreeProductAlreadyInCart()) {
            $cart = $this->_getCart();
            $product = $this->_getFreeProduct();
            $parameters = $this->_getItemParameters();
            $cart->addProduct($product, $parameters);
            $freeQuoteItem = $cart->getQuote()->getItemByProduct($product);
            $freeQuoteItem->setIsCustomgiftProduct(true);
            $this->setHasFreeProductBeenAddedToCart(true);
        }
    }

    /**
     * Returns the item parameters
     * @return array
     */
    public function _getItemParameters()
    {
        $product = $this->_getFreeProduct();
        $parameters = null;

        if($product instanceof Mage_Catalog_Model_Product) {
            $session = Mage::getSingleton('core/session');
            $coreHelper = Mage::helper('core');
            $coreUrlHelper = Mage::helper('core/url');
            $uenc = $coreHelper->urlEncode($coreUrlHelper->getCurrentUrl());

            $parameters = array(
                    Mage_Core_Controller_Front_Action::PARAM_NAME_URL_ENCODED  => $uenc,
                    'product'   => $product->getId(),
                    Mage_Core_Model_Url::FORM_KEY  => $session->getFormKey(),
                    'related_product'   => '',
                    'qty'   => 1,
            );
        }

        return $parameters;
    }

}