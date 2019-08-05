<?php
/**
 * This service class is reusable, can be used for other forms with minimum customization
 * This class validates, saves, or redirects the user depending on the situation.
 *
 * @category    Apartment
 * @package     Manger
 * @author   	Chris Manger (chris-manger.com)
 */
class Manger_Apartment_Service_Apartment
{
    /**
     * Processes the form.  If successful, this saves the data and redirects.
     */
    public function processForm()
    {
        $isSubmitted = $this->getRequest()->getPost('is_submitted');
        $isValid = $this->isFormValid();

        if($isSubmitted && $isValid) {
            $this->saveApartment();
            $this->redirectToThankYoupage();
        }
    }

    /**
     * Saves the submitted apartment form data.
     */
    public function saveApartment()
    {
        $data = $this->getRequest()->getPost();
        if($data) {
            $apartment = Mage::getModel('apartment/apartment');
            $apartment->addData($data);
            $apartment->save();
        }
    }

    /**
     * Redirects the user to the thank you page.
     */
    public function redirectToThankYoupage()
    {
        Mage::app()->getFrontController()->getAction()->setRedirectWithCookieCheck('apartment/thankyou', array());
    }

    /**
     * Determines if the form is valid; this utilizes zend form.
     * @return boolean
     */
    public function isFormValid()
    {
        return $this->_getForm()->isFormValid();
    }

    /**
     * Returns a zend form object that can validate the form.
     * @return Mage_Core_Model_Abstract
     */
    public function _getForm()
    {
        return Mage::getSingleton('apartment_form/apartment');
    }

    /**
     * Returns the request object.
     * @return Mage_Core_Controller_Request_Http
     */
    public function getRequest()
    {
        return Mage::app()->getRequest();
    }
}