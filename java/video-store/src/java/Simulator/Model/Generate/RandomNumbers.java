/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Generate;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author chris
 */
public class RandomNumbers {
    
    private final ArrayList<Integer> random_numbers_chosen;
    
    Integer minimumRandomNumber;
    
    Integer maximumRandomNumber;
    
    private RandomNumbers() {
        this.random_numbers_chosen = new ArrayList<>();
    }
    
    public RandomNumbers(Integer minRandomNumber, Integer maxRandomNumber)
    {
        this.random_numbers_chosen = new ArrayList<>();
        this.minimumRandomNumber = minRandomNumber;
        this.maximumRandomNumber = maxRandomNumber;
    }
    
    /**
     * Returns the minimum random number
     * @return 
     */
    public Integer getMinimumRandomNumber()
    {
        return this.minimumRandomNumber;
    }
    
    /**
     * Returns the maximum random number
     * @return 
     */
    public Integer getMaximumRandomNumber()
    {
        return this.maximumRandomNumber;
    }
    
    /**
     * Marks random number as selected
     * @param randomNumber 
     */
    public void markRandomNumberAsSelected(Integer randomNumber)
    {
        this.random_numbers_chosen.add(randomNumber);
    }
    
    /**
     * Determines if number has already been chosen
     * @param intCandidate
     * @return 
     */
    private boolean _isNumberAlreadyChosen(Integer intCandidate)
    {
        return this.random_numbers_chosen.contains(intCandidate);
    }
    
    /**
     * Returns the random numbers that have been chosen
     * @return 
     */
    private ArrayList<Integer> _getRandomNumbersChosen()
    {
        return this.random_numbers_chosen;
    }
    
    /**
     * Generates a random number in a range.
     * @param shouldRegenerate
     * @return 
     */
    public Integer generateRandom(Boolean shouldRegenerate)
    {
        boolean hasRandomNumberBeenSelected = false;
        
        Integer min = this.getMinimumRandomNumber();
        Integer max = this.getMaximumRandomNumber();
        
        Integer randomNumber = 0;
        if(max > min) {
            Integer numbersInRange = max - min + 1;
            Integer numberOfnumbersChosen;
            
            while(!hasRandomNumberBeenSelected) {
                numberOfnumbersChosen = this._getRandomNumbersChosen().size();
                if(numbersInRange > numberOfnumbersChosen) {
                    Random random = new Random();
                    randomNumber = random.nextInt(max - min + 1) + min;
                    if(!this._isNumberAlreadyChosen(randomNumber)) {
                        hasRandomNumberBeenSelected = true;
                        this.markRandomNumberAsSelected(randomNumber);
                    }
                } else {
                    if(shouldRegenerate) {
                        this._getRandomNumbersChosen().clear();
                    } else {
                        hasRandomNumberBeenSelected = true; //prevents an infinite loop from occurring
                    }
                }
            }
        }
        
        return randomNumber;
    }
}
