<?php
/**
 * @category    Requestinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Requestinformation_Block_Container extends Mage_Core_Block_Template
{
    const ACTION_NAME_SUFFIX = 'Action';

    /**
     * Returns the class name of the controller
     * @return string
     */
    public function getControllerClassName()
    {
        return get_class($this->_getController());
    }

    /**
     * Returns the name of the action method the controller calls
     * @return string
     * @throws Exception
     */
    public function getControllerActionName()
    {
        return $this->getRequest()->getActionName() . self::ACTION_NAME_SUFFIX;
    }

    /**
     * Returns an array of all the handles
     * @return array
     */
    public function getHandles()
    {
        return $this->_getLayout()->getUpdate()->getHandles();
    }

    /**
     * Returns the package name
     * @return string
     */
    public function getPackageName()
    {
        return $this->_getDesign()->getPackageName();
    }

    /**
     * Returns the skin template
     * @return mixed
     */
    public function getSkinTheme()
    {
        return $this->_getDesign()->getTheme('skin');
    }

    /**
     * Returns the main theme
     * @return string
     */
    public function getMainTheme()
    {
        return $this->_getDesign()->getTheme('default');
    }

    /**
     * Returns the full action name
     * @return string
     */
    public function getFullActionName()
    {
        return $this->_getController()->getFullActionName();
    }

    /**
     * Returns the layout theme
     * @return string
     */
    public function getLayoutTheme()
    {
        return $this->_getDesign()->getTheme('layout');
    }

    /**
     * Returns the template theme
     * @return string
     */
    public function getTemplateTheme()
    {
        return $this->_getDesign()->getTheme('template');
    }

    /**
     * Returns the template type
     * @param $type
     * @return bool
     */
    public function shouldShowThemeType($type)
    {
        $shouldShowTemplateType = false;

        $themeType = $this->_getDesign()->getTheme($type);
        $mainTheme = $this->_getDesign()->getTheme('default');

        if($themeType != $mainTheme) {
            $shouldShowTemplateType = true;
        }

        return $shouldShowTemplateType;
    }

    /**
     * Returns the locale theme
     * @return string
     */
    public function getLocaleTheme()
    {
        return Mage::getDesign()->getTheme('locale');
    }

    /**
     * Determines if all themes need to be displayed
     * @return bool
     */
    public function shouldShowOtherThemes()
    {
        $shouldShowOtherThemes = false;

        $themeTemplate = $this->getTemplateTheme();
        $themeLayout = $this->getLayoutTheme();
        $themeLocale = $this->getLocaleTheme();

        if($themeTemplate != $themeLayout || $themeTemplate != $themeLocale || $themeLayout != $themeLocale) {
            $shouldShowOtherThemes = true;
        }

        return $shouldShowOtherThemes;
    }

    /**
     * Returns the controller class
     * @return Mage_Core_Controller_Varien_Action
     */
    private function _getController()
    {
        /* @var $app Mage_Core_Model_App */
        $app = Mage::app();
        /* @var $frontController Mage_Core_Controller_Varien_Front */
        $frontController = $app->getFrontController();
        /* @var $controller Mage_Core_Controller_Varien_Action */
        $controller = $frontController->getData('action');
        return $controller;
    }

    /**
     * Returns the layout object
     * @return Mage_Core_Model_Layout
     */
    private function _getLayout()
    {
        return Mage::app()->getLayout();
    }

    /**
     * Returns the design
     * @return Mage_Core_Model_Design_Package
     */
    private function _getDesign()
    {
        return Mage::getDesign();
    }
}