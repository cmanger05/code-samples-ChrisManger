<?php
/**
 * Static class is used to instantiate all factories and can be extended to include other
 * features in zend framework 2
 *
 * @category   Manger
 * @package    Manger
 * @author     Chris Manger <chris@manger4.com>
 */
final class Manger
{
    const FACTORY_POSTFIX = '_factory/';

    static private $_objects = array();

    /**
     * Creates an instance of a model using a factory.
     * @param $factoryClass
     * @param array $arguments
     * @param $callingObject
     * @return null
     */
    public static function getFactory($module, $className, $arguments = array(), $callingObject = '', $persistent = true)
    {
        $factoryClass = $module. self::FACTORY_POSTFIX . $className;

        if(!isset(self::$_objects[$factoryClass]))  {
            $arguments['classname'] = $module . "/" . $className ;

            if($callingObject) {
                $arguments['calling_object']= $callingObject;
            }

            $factory = Mage::getModel($factoryClass, $arguments);
            $factory->checkRequiredInjectedVariables(); //an exception is thrown if required variables are not passed in.

            $modelInstance = null;
            if($factory instanceof Manger_Core_Factory_Abstract) {
                $argumentsAsObject = new Varien_Object($arguments);
                $modelInstance = $factory->createInstance($argumentsAsObject);
                $factory->setModelInstance($modelInstance);
                $factory->initialize($arguments);
            }

            self::$_objects[$factoryClass] = $modelInstance;
        }

        return self::$_objects[$factoryClass];
    }
}