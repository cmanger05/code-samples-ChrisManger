<?php
/**
 * Adds a free item to the quote if necessary
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Observer_Freeitem
{
    /**
     * Adds the free item to the quote.
     * This doesn't always add a free product.
     * It only adds one if shopping cart price rule is valid, and the product has not already been added.
     */
    public function addFreeItemToQuote(Varien_Event_Observer $observer)
    {
        $quote = $observer->getQuote();
        $addToCartModel = Manger::getFactory('customgift','total_quote_salesrule_awardfreeproduct_addtocart', array('quote'  => $quote));
        $addToCartModel->addFreeProductToCart(); //will only run if collector sets a registry variable saying item needs to be added.

        if($addToCartModel->hasFreeProductBeenAddedToCart()) {
            $quote->collectTotals();
        }
    }

    /**
     * Prevents magento's default discount collector from processing free products.
     * Additional processing done for free products can make the discount amount incorrect
     * @param Varien_Event_Observer $observer
     */
    public function disableMagentoDefaultDiscountForFreeproducts(Varien_Event_Observer $observer)
    {
        $rule = $observer->getRule();

        if ($rule->getSimpleAction() == Manger_Customgift_Model_Observer_Promoedittab::AWARD_FREE_PRODUCT)  {
            $freeProductModel = Mage::getSingleton('customgift/total_quote_salesrule_awardfreeproduct_freeproduct', array(
                'quote' => $observer->getQuote(),
                'rule'  => $observer->getRule(),
            ));

            if($freeItem = $freeProductModel->getFreeItem()) {
                $item = $observer->getItem();
                $itemProductId = $item->getProduct()->getId();
                $freeItemProductId = $freeItem->getProduct()->getId();

                if($itemProductId == $freeItemProductId) {
                    $result = $observer->getResult();
                    $result->setDiscountAmount(0);
                    $result->setBaseDiscountAmount(0);
                    $item->setDiscountAmount(0);
                    $item->setBaseDiscountAmount(0);
                }
            }
        }
    }

    /**
     * @param Varien_Event_Observer $observer
     */
    public function disallowFreeproductQuantitiesTobeUpdated(Varien_Event_Observer $observer)
    {
        $cart = $observer->getCart();
        $updatedItemInfo = $observer->getInfo();

        foreach($cart->getQuote()->getAllItems() as $item) {
            if($item->getIsCustomgiftProduct()) {
                foreach($updatedItemInfo as $quoteItemId => $updatedItem) {
                    if($quoteItemId == $item->getId()) {
                        if($item->getQty() != $updatedItem['qty']) {
                            Mage::throwException('Changing the quantity of the free item is not allowed. Your change(s) were not saved.');
                        }
                    }
                }
            }
        }
    }

    /**
     * Informs user that they not allowed to delete a gift item.
     * @param Varien_Event_Observer $observer
     */
    public function notifyUserNotAllowedTodeleteFreecustomgift(Varien_Event_Observer $observer)
    {
        $quoteItem = $observer->getQuoteItem();
        if($quoteItem->getIsCustomgiftProduct()) {
            $coreSession = Mage::getSingleton('core/session');
            $coreSession->addError('Customers are not permitted to delete a free item from the cart.');
        }

    }
}