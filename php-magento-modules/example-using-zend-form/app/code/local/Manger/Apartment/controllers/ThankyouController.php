<?php
/**
 * Controller called to display thank you page.
 *
 * @category    Apartment
 * @package     Manger
 * @author   	Chris Manger (chris-manger.com)
 */
class Manger_Apartment_ThankyouController extends Mage_Core_Controller_Front_Action
{
    public function indexAction()
    {
        $this->loadLayout();
        $this->renderLayout();
    }
}