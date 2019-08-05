<?php
/**
* Factory class that creates an instance of the award free product process model.
*
* @category   Customgift
* @package    Mangeer_Customgift
* @author     Chris Manger <chris@manger4.com>
*/
class Manger_Customgift_Factory_Total_Quote_Salesrule_Awardfreeproduct_Process extends Manger_Core_Factory_Abstract
{
    protected $_requiredVariables = array(
        'address',
        'rule',
    );

    /**
     * Creates an instance of the object
     * @param $arguments
     * @return Mage_Core_Model_Abstract
     */
    public function createInstance($arguments)
    {
        $freeProductModel = $this->_initializeFreeproductModel($arguments);

        //Nothing needs to be returned this follows the decorator pattern and thus updates necessary item values.
        $processModel = Mage::getSingleton('customgift/total_quote_salesrule_awardfreeproduct_process', array(
            'address'   => $arguments->getAddress(),
            'rule'  => $arguments->getRule(),
            'item'  => $freeProductModel->getFreeItem(),
            'product'   => $freeProductModel->getFreeProduct(),
            'discount_qty'   => $freeProductModel->getDiscountQty(),
        ));

        return $processModel;
    }

    /**
     * Initializes the free product model.
     * @param $arguments
     */
    protected function _initializeFreeproductModel($arguments)
    {
        $freeProductModel = Mage::getSingleton('customgift/total_quote_salesrule_awardfreeproduct_freeproduct', array(
            'quote' => $arguments->getQuote(),
            'rule'  => $arguments->getRule(),
        ));

        $freeItem = $freeProductModel->getFreeItem();

        if($freeItem instanceof Mage_Sales_Model_Quote_Item) {
            $itemQty = $arguments->getCallingObject()->getItemQty($freeItem, $arguments->getRule());
            $freeProductModel->setDiscountQty($itemQty);
        }

        return $freeProductModel;
    }
}