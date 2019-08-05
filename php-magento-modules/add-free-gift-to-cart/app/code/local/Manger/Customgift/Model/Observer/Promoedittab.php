<?php
/**
 * Observer adds or edit fields on the Shopping Cart Price rule's action tab.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Model_Observer_Promoedittab
{
    const AWARD_FREE_PRODUCT = 'award_free_product';

    /**
     * @param Varien_Event_Observer $observer
     */
    public function editShoppingCartPriceRulesActionTab(Varien_Event_Observer $observer)
    {
        $form = $observer->getForm();
        $elements = $form->getElements();
        $fieldset = $elements->searchById('action_fieldset');

        //Whatever product the user selects is the one that is awarded to the customer for free (only if discount conditions are true)
        $fieldset->addField('free_product_ids', 'select', array(
            'label'     => Mage::helper('salesrule')->__('Free Product'),
            'title'     => Mage::helper('salesrule')->__('Free Product'),
            'name'      => 'free_product_ids',
            'values'    => Mage::getSingleton('customgift/source_listallproducts')->toOptionArray(),
            'note'      => Mage::helper('salesrule')->__('Only has an effect if receive a free product is selected.')
        ),'discount_qty');

        $fieldsetElements = $fieldset->getElements();
        $simpleActionElement = $fieldsetElements->searchById('simple_action');

        $simpleActionElement->setOptions(array(
            Mage_SalesRule_Model_Rule::BY_PERCENT_ACTION => Mage::helper('salesrule')->__('Percent of product price discount'),
            Mage_SalesRule_Model_Rule::BY_FIXED_ACTION => Mage::helper('salesrule')->__('Fixed amount discount'),
            Mage_SalesRule_Model_Rule::CART_FIXED_ACTION => Mage::helper('salesrule')->__('Fixed amount discount for whole cart'),
            Mage_SalesRule_Model_Rule::BUY_X_GET_Y_ACTION => Mage::helper('salesrule')->__('Buy X get Y free (discount amount is Y)'),
            self::AWARD_FREE_PRODUCT => Mage::helper('salesrule')->__('Receive a free product'),
        ));

        $values = $simpleActionElement->getValues();
        $values[] = array(
            'value' => self::AWARD_FREE_PRODUCT,
            'label' => 'Receive a free product',
        );
        $simpleActionElement->setValues($values); //adds the 'Receive a free product' option to drop down menu.
    }
}