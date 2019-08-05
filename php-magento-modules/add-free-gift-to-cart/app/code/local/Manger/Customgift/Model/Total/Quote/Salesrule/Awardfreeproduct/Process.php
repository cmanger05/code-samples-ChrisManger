<?php
/**
 * Adjusts the subotal, grand total, and item totals only when a free gift has been applied to the order.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Total_Quote_Salesrule_Awardfreeproduct_Process extends Varien_Object
{

    protected $_hasBeenProcessed = false;

    /**
     * Sets has been processed variable
     * @param $hasBeenProcessed
     */
    public function setHasBeenProcessed($hasBeenProcessed)
    {
        $this->_hasBeenProcessed = $hasBeenProcessed;
    }

    /**
     * Returns the has been processed variable.
     * @return bool
     */
    public function hasBeenProcessed()
    {
        return $this->_hasBeenProcessed;
    }

    /**
     * Constructor
     */
    public function _construct()
    {
        parent::_construct();

        if(!$this->hasBeenProcessed()) {
            $this->processFreeGift();
        }
    }

    /**
     * Performs all necessary processing associated with the free gift.
     */
    public function processFreeGift()
    {
        $this->applyDiscountToTotals();
        $this->setHasBeenProcessed(true);
    }

    /**
     * Applies the discount to the totals.
     */
    public function applyDiscountToTotals()
    {
        $product = $this->getProduct();
        $item = $this->getItem();
        $quantityFree = $this->getDiscountQty();

        if($product && $item && $quantityFree) {
            $address = $this->getAddress();
            $productPrice = $product->getPrice();

            //Calculating and setting Row Totals for discounted product
            $current_quantity = $item->getQty();
            $quantityRequiresPayment = $current_quantity - $quantityFree;
            $amountDue = $quantityRequiresPayment * $productPrice;
            $item->setRowTotal($amountDue)->setBaseRowTotal($amountDue);

            //Adjusting subtotals, since discounts applied subtotals must be updated.
            $rowTotalWithoutDiscount = $current_quantity * $productPrice;
            $baseRowTotalWithoutDiscount = $current_quantity * $productPrice;
            $rowTotalWithoutDiscountDifference = $rowTotalWithoutDiscount - $amountDue;
            $baseRowTotalWithoutDiscountDifference = $baseRowTotalWithoutDiscount - $amountDue;
            $adjustedSubtotal = $address->getSubtotal() - $rowTotalWithoutDiscountDifference;
            $adjustedBaseSubtotal = $address->getBaseSubtotal() - $baseRowTotalWithoutDiscountDifference;
            $address->setSubtotal($adjustedSubtotal);
            $address->setBaseSubtotal($adjustedBaseSubtotal);

            //Adjusting grand totals, since discounts applied subtotals, grandtotals must be adjusted too.
            $adjustedGrandTotal = $address->getGrandTotal() - $rowTotalWithoutDiscountDifference;
            $adjustedBaseGrandTotal = $address->getBaseGrandTotal() - $baseRowTotalWithoutDiscountDifference;
            $address->setGrandTotal($adjustedGrandTotal);
            $address->setBaseGrandTotal($adjustedBaseGrandTotal);
        }
    }
}