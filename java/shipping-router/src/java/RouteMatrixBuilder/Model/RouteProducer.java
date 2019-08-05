/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteMatrixBuilder.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author chris
 */
public class RouteProducer {
    
    private RouteProducer _topmostParent;
    
    private RouteProducer _parent;
    
    private RouteProducer _child;
    
    private Integer _routerBegin;
    
    private Integer _routerEnd;
    
    private Integer _currentStop;
    
    private Integer _numberStops;
    
    private Boolean _uniqueRoutesOnly;
    
    private final ArrayList<ArrayList<Integer>> _routesRegistered = new ArrayList<>();
    
    private final ArrayList<IterationSimulator> _iterationSimulators = new ArrayList<>();
    
    /**
     * Standard constructor
     * @param numberStops 
     * @param isTopMostParent 
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public RouteProducer(Integer numberStops, Boolean isTopMostParent)
    {
        this._initialize(numberStops, 1, numberStops, true);
        
        if(isTopMostParent) {
            this._setTopmostParent(this);
        }
    }
    
    /**
     * Constructor main
     * @param numberStops
     * @param routerBegin
     * @param routerEnd 
     * @param uniqueRoutesOnly 
     */
    public RouteProducer(Integer numberStops, Integer routerBegin, Integer routerEnd, Boolean uniqueRoutesOnly)
    {
        this._initialize(numberStops, routerBegin, routerEnd, uniqueRoutesOnly);
    }
    
    private void _initialize(Integer numberStops, Integer routerBegin, Integer routerEnd, Boolean uniqueRoutesOnly)
    {
        this._setNumberStops(numberStops);
        this._setRouterBegin(routerBegin);
        this._setRouterEnd(numberStops);
        this._setIsUniqueRoutesOnly(uniqueRoutesOnly);
        this._setCurrentStop(routerBegin);
        
        for(int i = 1; i <= numberStops; i++) {
            this._addIterationSimulator(new IterationSimulator());
        }
    }
    
    private RouteProducer() {}
    
    /**
     * Adds a child route producer
     * @param numberStops 
     */
    public void addChild(Integer numberStops)
    {
        RouteProducer routeProducer = new RouteProducer(numberStops, this._getRouterBegin(), this._getRouterEnd(), this._getIsUniqueRoutesOnly());
        routeProducer._setParent(this);
        routeProducer._setTopmostParent(this.getTopmostParent());
        this._setChild(routeProducer);
    }

    /**
     * Determines if a child exists.
     * @return 
     */
    public boolean hasChild()
    {
        Boolean hasChild = false;
        RouteProducer child = this.getChild();
        if(child != null) {
            hasChild = true;
        }
        
        return hasChild;
    }
    
    /**
     * Determines if parent exists
     * @return 
     */
    public boolean hasParent()
    {
        Boolean hasParent = false;
        RouteProducer parent = this.getParent();
        if(parent != null) {
            hasParent = true;
        }
        
        return hasParent;
    }
    
    /**
     * @return the _routerBegin
     */
    private Integer _getRouterBegin() 
    {
        return _routerBegin;
    }

    /**
     * @return the _routerEnd
     */
    private Integer _getRouterEnd() 
    {
        return _routerEnd;
    }

    /**
     * @return the _uniqueRoutesOnly
     */
    private Boolean _getIsUniqueRoutesOnly() 
    {
        return _uniqueRoutesOnly;
    }

    /**
     * @param _routerBegin the _routerBegin to set
     */
    private void _setRouterBegin(Integer _routerBegin) 
    {
        this._routerBegin = _routerBegin;
    }

    /**
     * @param _routerEnd the _routerEnd to set
     */
    private void _setRouterEnd(Integer _routerEnd) 
    {
        this._routerEnd = _routerEnd;
    }

    /**
     * @param _numberStops the _numberStops to set
     */
    private void _setNumberStops(Integer _numberStops) 
    {
        this._numberStops = _numberStops;
    }

    /**
     * Returns total number of stops
     * @return 
     */
    public Integer getNumberStops()
    {
        return this._numberStops;
    }
    
    /**
     * @param _uniqueRoutesOnly the _uniqueRoutesOnly to set
     */
    private void _setIsUniqueRoutesOnly(Boolean _uniqueRoutesOnly) 
    {
        this._uniqueRoutesOnly = _uniqueRoutesOnly;
    }

    /**
     * @return the _parent
     */
    public RouteProducer getParent() {
        return _parent;
    }

    /**
     * @return the _child
     */
    public RouteProducer getChild() {
        return _child;
    }

    /**
     * @param _parent the _parent to set
     */
    private void _setParent(RouteProducer _parent) {
        this._parent = _parent;
    }

    /**
     * @param _child the _child to set
     */
    private void _setChild(RouteProducer _child) {
        this._child = _child;
    }

    /**
     * Sets the current stop
     * @param currentStop 
     */
    private void _setCurrentStop(Integer currentStop)
    {
        this._currentStop = currentStop;
    }
    
    /**
     * @return the _currentStop
     */
    public Integer getCurrentStop() 
    {
        return _currentStop;
    }

    /**
     * Increments the current stop
     */
    public void incrementCurrentStop()
    {
        this._currentStop++;
    }
    
    /**
     * Resets the current stop
     */
    public void resetCurrentStop()
    {
        this._currentStop = this._getRouterBegin();
    }
    
    /**
     * Adds an iteration simulator
     * @param iterationSimulator 
     */
    private void _addIterationSimulator(IterationSimulator iterationSimulator)
    {
        this._iterationSimulators.add(iterationSimulator);
    }
    
    /**
     * Returns the iteration simulators
     * @return 
     */
    public ArrayList<IterationSimulator> getIterationSimulators()
    {
        return this._iterationSimulators;
    }
    
    /**
     * Adds the route
     * @param route 
     */
    public void addRoute(ArrayList<Integer> route)
    {
        if(this._shouldAddRoute(route)) {
            this._routesRegistered.add(route);
        }
    }
    
    /**
     * Determines if a new route needs to be added.
     * @param routeString
     * @return 
     */
    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    private Boolean _shouldAddRoute(ArrayList<Integer> route)
    {
        Boolean shouldAddRoute = true;
        if(this._getIsUniqueRoutesOnly()) {
            if(this._routesRegistered.contains(route)) {
                shouldAddRoute = false;
            } else {
                Collections.reverse(route);
                if(this._routesRegistered.contains(route)) {
                    shouldAddRoute = false;
                }
                Collections.reverse(route);
            }
        } 
        
        if(shouldAddRoute) {
            ArrayList<Integer> routeSorted = (ArrayList<Integer>) route.clone();
            Collections.sort(routeSorted);
            Integer lastSpot = 0;
            for(Integer currentSpot : routeSorted) {
                if(currentSpot == lastSpot) {
                    shouldAddRoute = false;
                }
                
                lastSpot = currentSpot;
            }
        }
        
        return shouldAddRoute;
    }
    
    /**
     * Generates all the possible routes!
     */
    public void generateRouteMatrix()
    {
        if(this.hasChild()) { //executes every time except when current class instance is bottom most class
            RouteProducer child = this.getChild();
            for(IterationSimulator iterationSimulatorParent : this.getIterationSimulators()) {
                for(IterationSimulator iterationSimulatorChild : child.getIterationSimulators()) {
                    child.generateRouteMatrix();
                }
                
                child.resetCurrentStop();
                this.incrementCurrentStop();
            }
            
            this.resetCurrentStop();
        } else if(this.hasParent()) { //only executes when bottom most child is called.
            ArrayList<Integer> currentRoute = this._determineCurrentRoute();
            this.getTopmostParent().addRoute(currentRoute);
            this.incrementCurrentStop();
        }
    }
    
    /**
     * Determines the current route
     * @return 
     */
    private ArrayList<Integer> _determineCurrentRoute()
    {
        ArrayList<Integer> currentRoute = new ArrayList<>();
        if(this.hasParent()) {
            boolean parentExists = true;
            RouteProducer currentRoutePart = this;
            while(parentExists) {
                if(currentRoutePart.hasParent()) {
                    currentRoute.add(currentRoutePart.getCurrentStop());
                    if(currentRoutePart.hasParent()) {
                        currentRoutePart = currentRoutePart.getParent();
                    } else {
                        parentExists = false;
                    }
                } else {
                    parentExists = false; //when current object is topmost parent this is called.
                }
            }
        }
        
        Collections.reverse(currentRoute);
        return currentRoute;
    }
    
    /**
     * Returns all the routes
     * @return 
     */
    public ArrayList<ArrayList<Integer>> getAllRoutes()
    {
        return this._routesRegistered;
    }

    /**
     * @return the _topmostParent
     */
    public RouteProducer getTopmostParent() 
    {
        return _topmostParent;
    }

    /**
     * @param _topmostParent the _topmostParent to set
     */
    private void _setTopmostParent(RouteProducer _topmostParent) 
    {
        this._topmostParent = _topmostParent;
    }
}
