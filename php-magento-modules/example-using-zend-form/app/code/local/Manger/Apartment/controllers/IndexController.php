<?php
/**
 * Displays the apartment form.
 *
 * @category    Apartment
 * @package     Manger
 * @author   	Chris Manger (chris-manger.com)
 */
class Manger_Apartment_IndexController extends Mage_Core_Controller_Front_Action
{
    public function indexAction()
    {
        $apartmentService = Mage::getSingleton('apartment_service/apartment');
        $apartmentService->processForm();

        $this->loadLayout();
        $this->renderLayout();
    }
}