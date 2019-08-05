<?php
/**
 * SalesRule Validator Model - Determines if a free gift needs to be added, adds it if necessary,
 * and utilizes other classes to perform all necessary processing associated.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Total_Quote_Salesrule_Awardfreeproduct_Validator extends Mage_SalesRule_Model_Validator
{
    const REGISTRY_KEY_ADD_PROMO_ITEM = 'add_promo_item';

    /**
     * Quote item discount calculation process
     *
     * @param   Mage_Sales_Model_Quote_Item_Abstract $item
     * @return  Mage_SalesRule_Model_Validator
     */
    public function findAndProcessFreeProductDiscount(Mage_Sales_Model_Quote_Item_Abstract $item)
    {
        $address    = $this->_getAddress($item);
        foreach ($this->_getRules() as $rule) {
            /* @var $rule Mage_SalesRule_Model_Rule */
            if (!$this->_canProcessRule($rule, $address)) {
                continue;
            }

            if (!$rule->getActions()->validate($item)) {
                continue;
            }

            if ($rule->getSimpleAction() == Manger_Customgift_Model_Observer_Promoedittab::AWARD_FREE_PRODUCT)  {
                $this->processFreeProduct($item, $rule);
                break; //this rule can only be applied once!!
            }
        }

        return $this;
    }

    /**
     * Processes the free products
     * @param $item
     * @param $rule
     */
    public function processFreeProduct($item, $rule)
    {
        $address = $this->_getAddress($item);
        Mage::register(self::REGISTRY_KEY_ADD_PROMO_ITEM, true, true); //marker let's us know a product needs to be added.

        Manger::getFactory(
            'customgift',
            'total_quote_salesrule_awardfreeproduct_process',
            array(
                'address' => $address,
                'quote'  => $quote = $address->getQuote(),
                'rule'    => $rule,
            ),
            $this
        );
    }

    /**
     * Returns number items a user should recieve free.
     * @param $item
     * @param $rule
     * @return mixed
     */
    public function getItemQty($item, $rule)
    {
        $qty = $item->getTotalQty();
        $ruleDiscountQuantity = $rule->getDiscountQty() ? $rule->getDiscountQty() : 1;
        return min($qty, $ruleDiscountQuantity);
    }
}
