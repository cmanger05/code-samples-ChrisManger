<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Model_Map_Block_Layoutxml extends Magenifieddebugging_Base_Model_Xml_Load_File_Config
{
    CONST LAYOUT_XML_FILE_NOT_FOUND = "could not determine the block's layout xml file";

    CONST PATH_START_IDENTIFIER = 'app/design';

    /**
     * Determine the blocks layout xml filename
     * @return string
     */
    public function getPathsToBlocksLayoutXmlFiles()
    {
        $layoutXmlFilenames = $this->_getModulesLayoutXmlFilename();
        if(count($layoutXmlFilenames) > 0) {
            $fullPaths = $this->_getFullPathsEachFile($layoutXmlFilenames);
            $fullPathsToLayoutXmlFiles = implode(',', $fullPaths);
        } else {
            $fullPathsToLayoutXmlFiles = self::LAYOUT_XML_FILE_NOT_FOUND;
        }

        return $fullPathsToLayoutXmlFiles;
    }

    /**
     * Returns the modules layout xml filename
     * @return string
     */
    private function _getModulesLayoutXmlFilename()
    {
        $layoutXmlFiles = array();

        $matchingNodesRoot = $this->getNode('frontend/layout/updates');
        if($matchingNodesRoot && $matchingNodesRoot instanceof Mage_Core_Model_Config_Element) {
            $matchingNodes = $matchingNodesRoot->asArray();

            foreach ($matchingNodes as $updateNode) {
                if (!empty($updateNode['file'])) {
                    $layoutXmlFiles[] = $updateNode['file'];
                }
            }
        }

        return $layoutXmlFiles;
    }

    /**
     * Returns the full path to each file
     * @param array $layoutXmlFileNames
     * @return array
     */
    private function _getFullPathsEachFile(array $layoutXmlFileNames)
    {
        $fullPathsToFiles = array();

        foreach($layoutXmlFileNames as $layoutXmlFilename) {
            $fullPathToFileNameRaw = Mage::getDesign()->getLayoutFilename($layoutXmlFilename);
            $positionStartPath = strpos($fullPathToFileNameRaw,self::PATH_START_IDENTIFIER);
            if($positionStartPath && $positionStartPath > 0) {
                $fullPathsToFiles[] =  substr($fullPathToFileNameRaw, $positionStartPath);
            }
        }

        return $fullPathsToFiles;
    }
}