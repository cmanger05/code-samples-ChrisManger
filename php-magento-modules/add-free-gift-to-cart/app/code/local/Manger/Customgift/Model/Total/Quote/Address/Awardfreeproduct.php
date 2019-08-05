<?php
/**
 * This is the collector class that utilizes other classes to add and process free gifts.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Total_Quote_Address_Awardfreeproduct extends Mage_Sales_Model_Quote_Address_Total_Abstract
{

    protected $_calculator;

    const CODE = 'customgift';

    public function __construct()
    {
        $this->setCode(self::CODE);
        $this->_calculator = Mage::getSingleton('customgift/total_quote_salesrule_awardfreeproduct_validator');
    }

    /**
     * Collects data pertaining to free products.
     * @param Mage_Sales_Model_Quote_Address $address
     * @return $this|Mage_Sales_Model_Quote_Address_Total_Abstract
     */
    public function collect(Mage_Sales_Model_Quote_Address $address)
    {
        parent::collect($address);

        $items = $this->_getAddressItems($address);
        if (!count($items)) {
            return $this;
        }

        $this->processFreeProductDiscounts($items, $address);

        return $this;
    }

    /**
     * Perform all processing associated with the free product discount
     * @param $items
     */
    public function processFreeProductDiscounts($items, $address)
    {
        $this->initializeFreeProductProcessing($address);
        foreach($items as $item) {
            if ($item->getParentItemId() || $item->getHasChildren() || $item->isChildrenCalculated()) {
                continue;
            }

            //this finds all products that should be awarded for free, they are added later, not now!
            $this->_calculator->findAndProcessFreeProductDiscount($item);
        }
    }

    /**
     * Performs operations necessary for free product processing.
     */
    public function initializeFreeProductProcessing($address)
    {
        $quote = $address->getQuote();
        $store = Mage::app()->getStore($quote->getStoreId());
        $this->_calculator->reset($address);
        $this->_calculator->init($store->getWebsiteId(), $quote->getCustomerGroupId(), $quote->getCouponCode()); //Gathers a list of all free product discounts that apply to this order.
    }

    /**
     * No totals are calcaulated for this collector.
     * Instead, quote price of valid discount items are set to zero.
     * @param Mage_Sales_Model_Quote_Address $address
     * @return array|void
     */
    public function fetch(Mage_Sales_Model_Quote_Address $address)
    {

    }
}