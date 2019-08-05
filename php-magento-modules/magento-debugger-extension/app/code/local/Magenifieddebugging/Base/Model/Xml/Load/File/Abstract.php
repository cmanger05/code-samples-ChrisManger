<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 *
 * @method string getFileType()
 * @method string getModuleName()
 * @method string getFileName()
 */
abstract class Magenifieddebugging_Base_Model_Xml_Load_File_Abstract extends Varien_Object
{
    protected $_xml;

    /**
     * Constructor
     * @throws Mage_Core_Exception
     */
    protected function _construct()
    {
        $fileType = $this->getData('file_type');
        $moduleName = $this->getData('module_name');
        $fileName = $this->getData('file_name');
        if(!$fileType || !$moduleName || !$fileName) {
            Mage::throwException('A required variable was not passed into the objects constructor');
        }

        $this->_loadXmlInOneFileOnly($fileType, $moduleName, $fileName);
    }

    /**
     * Returns the xml
     * @return Mage_Core_Model_Config_Base
     */
    protected function _getXml()
    {
        return $this->_xml;
    }

    /**
     * Sets the xml
     * @param Mage_Core_Model_Config_Base $xml
     */
    protected function _setXml(Mage_Core_Model_Config_Base $xml)
    {
        $this->_xml = $xml;
    }

    /**
     * Returns the config XML for the requested module only
     * @param $fileType
     * @param $moduleName
     * @param $fileName
     */
    private function _loadXmlInOneFileOnly($fileType, $moduleName, $fileName)
    {
        $configFile = Mage::getModuleDir($fileType, $moduleName) . DS . $fileName;
        $mergeModelCandidate = new Mage_Core_Model_Config_Base();
        $configXmlCandidate = $mergeModelCandidate->loadFile($configFile);
        if($configXmlCandidate) {
            $this->_setXml($mergeModelCandidate); //we want the merge model, the layout xml has been loaded to that.
        }
    }

    /**
     * Returns the node
     * @param $nodePath
     * @return Varien_Simplexml_Element|null
     */
    public function getNode($nodePath)
    {
        $node = null;
        if($this->_isObjectValid()) {
            $node = $this->_getXml()->getNode($nodePath);
        }

        return $node;
    }

    /**
     * Return node as array
     * @param $nodePath
     * @return array
     */
    public function getNodeAsArray($nodePath)
    {
        $nodeAsArray = array();
        $node = $this->getNode($nodePath);
        if($node) {
            $nodeAsArray = $node->asArray();
        }

        return $nodeAsArray;
    }

    /**
     * Is object valid
     * @return bool
     */
    private function _isObjectValid()
    {
        $isObjectValid = false;
        if($this->_getXml()) {
            $isObjectValid = true;
        }

        return $isObjectValid;
    }
}