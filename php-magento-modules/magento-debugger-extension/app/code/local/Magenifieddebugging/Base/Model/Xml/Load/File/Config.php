<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Base_Model_Xml_Load_File_Config extends Magenifieddebugging_Base_Model_Xml_Load_File_Abstract
{
    CONST BLOCK_SUFFIX = '_Block';

    /**
     * Constructor
     */
    protected function _construct()
    {
        $this->setData('file_type','etc');
        $this->setData('file_name','config.xml');
        parent::_construct();
    }

    /**
     * Returns the models group name
     */
    public function getGroupNameByType($type)
    {
        $modelGroupName = null;

        if(in_array($type,array('models','groups','blocks','helpers'))) {
            $nodeArgument = 'global/' . $type;

            $modelsNode = $this->getNodeAsArray($nodeArgument);
            $classPrefix = $this->getModuleName() . '_' . ucwords(substr($type, 0, strlen($type) - 1));

            foreach ($modelsNode as $modelGroupNameCandidate => $modelNode) {
                if (!empty($modelNode['class'])) {
                    if ($modelNode['class'] == $classPrefix) {
                        $modelGroupName = $modelGroupNameCandidate;
                    }
                }
            }
        }

        return $modelGroupName;
    }
}