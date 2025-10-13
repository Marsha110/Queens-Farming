package edu.kit.informatik.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The user interface of perseverance.
 *
 * @author uqeoc
 * @version 1.0
 */
public class Price {
    private Vegetable vegetableOne;
    private int priceOfVegetableOne;
    private Vegetable vegetableTwo;
    private int priceOfVegetableTwo;

    public Price(String vegetableOneName, int priceOfVegetableOne, String vegetableTwoName, int priceOfVegetableTwo){
        this.vegetableOne=new Vegetable(vegetableOneName);
        this.priceOfVegetableOne=priceOfVegetableOne;
        this.vegetableTwo=new Vegetable(vegetableTwoName);
        this.priceOfVegetableTwo=priceOfVegetableTwo;
    }

    public int getPriceOfVegetableOne() {
        return priceOfVegetableOne;
    }
    public int getPriceOfVegetableTwo(){
        return priceOfVegetableTwo;
    }
    public Vegetable getVegetableOne(){
        return vegetableOne;
    }
    public Vegetable getVegetableTwo(){
        return vegetableTwo;
    }
}
