package edu.kit.informatik.model;

import java.util.List;

/**
 * The user interface of perseverance.
 *
 * @author uqeoc
 * @version 1.0
 */
public class Vegetable {
    private static final String CARROT = "carrot";
    private static final String CARROT_PLURAL = "carrots";
    private static final String SALAD = "salad";
    private static final String SALAD_PLURAL = "salads";
    private static final String MUSHROOM = "mushroom";
    private static final String MUSHROOM_PLURAL = "mushrooms";
    private static final String TOMATO = "tomato";
    private static final String TOMATO_PLURAL = "tomatoes";
    private static final int CONST_1 = 1;
    private static final int CONST_2 = 2;
    private static final int CONST_3 = 3;
    private static final int CONST_4 = 4;
    private String name;
    private int quantity;
    private String pluralName;
    private int growthCycle;

    public Vegetable(String name,int quantity){
        this.quantity=quantity;
        initialize(name);


    }
    public Vegetable(String name){
        this.name=name;
        initialize(name);

    }
    private void initialize(String name){
        this.name=name;
        if(name.equals(CARROT)) {
            pluralName = CARROT_PLURAL;
            growthCycle=CONST_1;
        } else if(name.equals(SALAD)) {
            pluralName = SALAD_PLURAL;
            growthCycle=CONST_2;
        }else if(name.equals(TOMATO)) {
            pluralName = TOMATO_PLURAL;
            growthCycle=CONST_3;
        } else if(name.equals(MUSHROOM)) {
            pluralName = MUSHROOM_PLURAL;
            growthCycle=CONST_4;
        }

    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
   public String getName(){
       return name;
   }
    public void setName(String name){
        this.name=name;
    }
    public void deepCopy(Vegetable vegetable){
        this.name=vegetable.getName();
        this.quantity=vegetable.getQuantity();
        this.pluralName= vegetable.getPluralName();
        this.growthCycle= vegetable.getGrowthCycle();
    }
   public int getQuantity(){
       return quantity;
   }
   public void quantityMinus1(){
        quantity--;
   }
    public void quantityPlus1(){
        quantity++;
    }
   public String getPluralName(){
        return pluralName;
   }
   public int getGrowthCycle(){
        return growthCycle;
   }

}
