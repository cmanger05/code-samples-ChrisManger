<?php
/**
 * @category    Blockinformation
 * @package     Magenifieddebugging
 * @author   	Chris Manger (chris-manger.com)
 */
class Magenifieddebugging_Blockinformation_Model_Match_Data extends Varien_Object
{
    const MESSAGE_NO_TEMPLATE_FILE = 'No template file exists.';

    const MESSAGE_NO_BLOCK_REWRITE_NODE = 'Xpath could not be determined.';

    const MESSAGE_ALREADY_REWRITTEN = 'This block has already successfully been rewritten.';

    const MESSAGE_ONLY_CORE_MODULES = 'This can only be determined for blocks in the core.';

    protected $_coreTemplatePrefixes = array(
        'frontend/enterprise/default',
        'frontend/rwd/enterprise',
        'frontend/rwd/default',
        'frontend/base/default',
    );

    protected $_block;

    protected $_form;

    /**
     * Constructor
     * @throws Mage_Core_Exception
     */
    protected function _construct()
    {
        $block = $this->getData('block');
        $form = $this->getData('form');
        if(!$block || !$block instanceof Mage_Core_Block_Abstract
            || !$form || !$form instanceof Magenifieddebugging_Blockinformation_Form_Search_Interface) {
            Mage::throwException('A required value was not passed into the constructor. Or, an invalid value was passed in.');
        }

        $this->_setBlock($block);
        $this->_setForm($form);
    }

    /**
     * Sets the block object
     * @param Mage_Core_Block_Abstract $block
     */
    private function _setBlock(Mage_Core_Block_Abstract $block)
    {
        $this->_block = $block;
    }

    /**
     * Returns the block object
     * @return Mage_Core_Block_Abstract
     */
    private function _getBlock()
    {
        return $this->_block;
    }

    /**
     * Sets the form object
     * @param Magenifieddebugging_Blockinformation_Form_Search_Interface $form
     */
    private function _setForm(Magenifieddebugging_Blockinformation_Form_Search_Interface $form)
    {
        $this->_form = $form;
    }

    /**
     * Gets the form object
     * @return Magenifieddebugging_Blockinformation_Form_Search_Interface
     */
    private function _getForm()
    {
        return $this->_form;
    }

    /**
     * Returns the name of the block
     */
    public function getBlockName()
    {
        return $this->_getBlock()->getNameInLayout();
    }

    /**
     * Returns the blocks class name
     * @return string
     */
    public function getClassName()
    {
        $_block = $this->_getBlock();
        $className = get_class($_block);
        return $className;
    }

    /**
     * Returns the filename
     * @return string
     */
    public function getTemplateFileName()
    {
        $_fileName = self::MESSAGE_NO_TEMPLATE_FILE;

        $_block = $this->_getBlock();
        if($_block instanceof Mage_Core_Block_Template && $_block->getTemplateFile()) {
            $_fileName = $_block->getTemplateFile();
            if($this->_isTemplateOverWritten($_fileName)) {
                $_fileName .= '<br /><span class="highlight-red">(core template file overwritten)</span>';
            }
        } elseif($_block instanceof Mage_Cms_Block_Page) {
            $page = $_block->getPage();
            if($page instanceof Mage_Cms_Model_Page) {
                $_fileName = 'This comes from the CMS Page block called "' . $page->getIdentifier() .'"';
            }
        } elseif($_block instanceof Mage_Cms_Block_Block) {
            $_fileName = 'This is a static block that is set in the Magento admin.';
        }

        return $_fileName;
    }

    /**
     * Determines if template file has been overwritten
     * @param $fileName
     * @return bool
     */
    private function _isTemplateOverWritten($fileName)
    {
        $hasBeenOverwritten = true;
        foreach($this->_coreTemplatePrefixes as $coreTemplatePrefix) {
            if(strpos($fileName, $coreTemplatePrefix) !== false) {
                $hasBeenOverwritten = false;
            }
        }

        return $hasBeenOverwritten;
    }

    /**
     * Returns the paths to the blocks layout xml files
     * @return string
     */
    public function getLayoutXmlFileNames()
    {
        /* @var $layoutXmlMapper Magenifieddebugging_Blockinformation_Model_Map_Block_Layoutxml */
        $layoutXmlMapper = Mage::getModel('mmdebugging_blockinformation/map_block_layoutxml', array(
            'module_name'   => $this->_getBlock()->getModuleName()
        ));

        $pathsToBlocksLayoutXmlFiles = $layoutXmlMapper->getPathsToBlocksLayoutXmlFiles();
        return $pathsToBlocksLayoutXmlFiles;
    }

    /**
     * Returns the name of the parent block
     * @return string
     */
    public function getParentBlockName()
    {
        $parentBlockName = '';

        $_parentBlock = $this->_getBlock()->getParentBlock();
        if($_parentBlock && $_parentBlock instanceof Mage_Core_Block_Abstract) {
            $parentBlockName = $_parentBlock->getNameInLayout();
        }

        return $parentBlockName;
    }

    /**
     * Returns the block Rewrite Node
     * @return string
     */
    public function getBlockRewriteNode()
    {
        $blockRewriteNode = self::MESSAGE_NO_BLOCK_REWRITE_NODE;

        $_block = $this->_getBlock();
        $moduleName = $_block->getModuleName();
        /* @var $fileConfig Magenifieddebugging_Base_Model_Xml_Load_File_Config */
        $fileConfig = Mage::getModel('magenifieddebugging_base/xml_load_file_config', array('module_name' => $moduleName));
        $blockGroupName = $fileConfig->getGroupNameByType('blocks');

        $codePool = (string)Mage::getConfig()->getModuleConfig($moduleName)->codePool;
        if($codePool != 'core') {
            return self::MESSAGE_ONLY_CORE_MODULES;
        }

        if(!$blockGroupName) {
            $blockGroupName = strtolower(substr($moduleName, strpos($moduleName,'age_') + 4));
        }

        if($blockGroupName) {
            $blockFullClassName = get_class($_block);
            $startPosition = strpos($blockFullClassName,'_Block_') + 7;
            $classSuffix = strtolower(substr($blockFullClassName, $startPosition));
            $blockRewriteNode = 'config/global/blocks/' . $blockGroupName . '/rewrite/' . $classSuffix;
        }

        return $blockRewriteNode;
    }

    /**
     * Determines if this is being stored in the block cache.
     * @return bool
     */
    public function isInBlockCache()
    {
        $isInBlockCache = false;
        /* @var $app Mage_Core_Model_App */
        $app = Mage::app();
        $useBlockCache = Mage::app()->useCache(Mage_Core_Block_Abstract::CACHE_GROUP);
        if($useBlockCache) {
            $cacheKey = $this->_getBlock()->getCacheKey();
            $cacheData = $app->loadCache($cacheKey);
            if($cacheData) {
                $isInBlockCache = true;
            }
        }

        return $isInBlockCache;
    }

    /**
     * Determines if the match is valid
     * @return bool
     */
    public function isMatchValid()
    {
        return ($this->_getBlock()->getNameInLayout() && !$this->_getForm()->hasValidationError()) ? true : false;
    }
}