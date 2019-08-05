<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Helper_Data extends Magenifieddebugging_Base_Helper_Data
{
    const XML_NODE_FEATURE_ENABLED = 'mm_debugging/debugging/feature_block_information';

    /**
     * Determines if debugging feature is enabled
     * @return bool
     */
    public function isFeatureEnabled()
    {
        $isFeatureEnabled = false;

        $isDebuggingEnabled = $this->isDebuggingRequestValid();
        $isBlockInformationModuleEnabled = $this->_helperCore()->isModuleEnabled(self::MODULE_NAME_BLOCK_INFORMATION);

        if($isDebuggingEnabled && $isBlockInformationModuleEnabled) {
            $isFeatureEnabled = true;
        }

        return $isFeatureEnabled;
    }
}