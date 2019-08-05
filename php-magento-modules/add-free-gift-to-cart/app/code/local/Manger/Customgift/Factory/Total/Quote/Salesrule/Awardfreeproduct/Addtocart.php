<?php
/**
 * Factory class that creates an instance of the add to cart model.
 *
 * @category   Customgift
 * @package    Mangeer_Customgift
 * @author     Chris Manger <chris@manger4.com>
 */
class Manger_Customgift_Factory_Total_Quote_Salesrule_Awardfreeproduct_Addtocart extends Manger_Core_Factory_Abstract
{
    protected $_requiredVariables = array(
        'quote',
    );

    /**
     * Creates an instance of the mode.
     * @param $arguments
     * @return Mage_Core_Model_Abstract
     */
    public function createInstance($arguments)
    {
        $freeProductModel = Mage::getSingleton('customgift/total_quote_salesrule_awardfreeproduct_freeproduct');
        $addToCartModel = Mage::getSingleton($arguments->getClassname(), array(
            'quote' => $arguments->getQuote(),
            'freeproductmodel'  => $freeProductModel,
        ));

        return $addToCartModel;
    }
}