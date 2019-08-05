<?php
/**
 * Static class is used to instantiate all factories and can be extended to include other
 * features in zend framework 2
 *
 * @category   Manger
 * @package    Mangeer
 * @author     Chris Manger <chris@manger4.com>
 */
abstract class Manger_Core_Factory_Abstract extends Varien_Object
{
    protected $_requiredVariables = array();

    protected $_modelInstance;

    public function initialize() {}

    public function getInstance($arguments = '') {}

    public function getModelInstance()
    {
        return $this->_modelInstance;
    }

    public function setModelInstance($modelInstance)
    {
        $this->_modelInstance = $modelInstance;
    }

    public function _construct()
    {
        $this->checkRequiredInjectedVariables();
    }

    /**
     * Checks to ensure required injected variables exist.
     */
    public function checkRequiredInjectedVariables()
    {
        foreach($this->_requiredVariables as $requiredVariable) {
            $requiredObject = $this->getData($requiredVariable);
            if(!$requiredVariable || !is_object($requiredObject)) {
                Mage::throwException('the required variable ' . $requiredVariable . ' was not injected or is invalid');
            }
        }
    }
}