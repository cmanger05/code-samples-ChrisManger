<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Base_Helper_Data extends Mage_Core_Helper_Abstract
{
    const XML_NODE_DEBUGGING_ENABLED = 'mm_debugging/debugging/is_debugging_enabled';

    const PARAM_NAME_DEBUG_TYPE = 'debughint';

    const MODULE_NAME_BASE = 'Magenifieddebugging_Base';

    const MODULE_NAME_BLOCK_INFORMATION = 'Magenifieddebugging_Blockinformation';

    const MODULE_NAME_REQUEST_INFORMATION = 'Magenifieddebugging_Requestinformation';

    const ERROR_FPC_MUST_BE_OFF = "Please turn off the full page cache. The debugging module will
            not work unless this is turned off.";

    protected $_isDebuggingRequestValid;

    /**
     * Determines if debugging feature is enabled
     * @return bool
     */
    public function isDebuggingEnabled()
    {
        $isDebuggingEnabled = false;

        $isBaseModuleEnabled = $this->_helperCore()->isModuleEnabled(self::MODULE_NAME_BASE);
        $isBlockInformationModuleEnabled = $this->_helperCore()->isModuleEnabled(self::MODULE_NAME_BLOCK_INFORMATION);
        $isRequestInformationModuleEnabled = $this->_helperCore()->isModuleEnabled(self::MODULE_NAME_REQUEST_INFORMATION);
        $isDebuggingSettingEnabled = Mage::getStoreConfig(self::XML_NODE_DEBUGGING_ENABLED);

        if($isDebuggingSettingEnabled && $isBaseModuleEnabled &&
            ($isBlockInformationModuleEnabled || $isRequestInformationModuleEnabled)) {
            $isDebuggingEnabled = true;
        }

        return $isDebuggingEnabled;
    }

    /**
     * Returns if the debugging request is valid
     * @return bool
     */
    public function isDebuggingRequestValid()
    {
        if(is_null($this->_isDebuggingRequestValid)) {
            $isDebuggingRequestValid = false;

            $isDebuggingEnabled = $this->isDebuggingEnabled();
            $didUserRequest = $this->_getRequest()->getParam(self::PARAM_NAME_DEBUG_TYPE);
            $isFpcEnabled = (Mage::app()->useCache('full_page')) ? true : false;

	        if($isDebuggingEnabled && $didUserRequest && !$isFpcEnabled) {
                $isDebuggingRequestValid = true;
            }

            $this->_isDebuggingRequestValid = $isDebuggingRequestValid;
        }

        return $this->_isDebuggingRequestValid;
    }

    /**
     * Returns the core helper
     * @return Mage_Core_Helper_Abstract
     */
    protected function _helperCore()
    {
        return Mage::helper('core');
    }
}