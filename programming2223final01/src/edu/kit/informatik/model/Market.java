package edu.kit.informatik.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The user interface of perseverance.
 *
 * @author uqeoc
 * @version 1.0
 */
public class Market {
    private static final String CARROT = "carrot";
    private static final String SALAD = "salad";
    private static final String MUSHROOM = "mushroom";
    private static final String TOMATO = "tomato";
    private static final int INITIAL_PRICE = 2;
    private static final String MUSHROOMS_AND_COLON = "mushrooms:";
    private static final String COLON = ":";
    private static final String PERCENTAGE = "%";
    private static final String ALPHABET_D = "d";
    private static final int LENGTH_OF_COLON = 1;
    private static final int CONST_NEGATIVE_1=-1;
    private static final int PRICE_CONST_1=1;
    private static final int PRICE_CONST_2=2;
    private static final int PRICE_CONST_3=3;
    private static final int PRICE_CONST_4=4;
    private static final int PRICE_CONST_5=5;
    private static final int PRICE_CONST_6=6;
    private static final int PRICE_CONST_7=7;
    private static final int PRICE_CONST_9=9;
    private static final int PRICE_CONST_12=12;
    private static final int PRICE_CONST_15=15;
    private static final int PRICE_CONST_16=16;
    private static final int PRICE_CONST_17=17;
    private static final int PRICE_CONST_20=20;
    private static final int LENGTH_IN_ONE_ROW=13;
    private static final int MARKET_INFO_COLUMNS=4;
    private int currentPriceMAndC = INITIAL_PRICE;
    private int currentPriceTAndS = INITIAL_PRICE;
    private List<Price> priceListMAndC = new LinkedList<>();
    private List<Price> priceListTAndS = new LinkedList<>();
    Vegetable mushroom;
    Vegetable carrot;
    Vegetable tomato;
    Vegetable salad;
    int mushroomPrice;
    int carrotPrice;
    int tomatoPrice;
    int saladPrice;
    public Market(){
        priceListMAndC.add(new Price(MUSHROOM,PRICE_CONST_12,CARROT,PRICE_CONST_3));
        priceListMAndC.add(new Price(MUSHROOM,PRICE_CONST_15,CARROT,PRICE_CONST_2));
        priceListMAndC.add(new Price(MUSHROOM,PRICE_CONST_16,CARROT,PRICE_CONST_2));
        priceListMAndC.add(new Price(MUSHROOM,PRICE_CONST_17,CARROT,PRICE_CONST_2));
        priceListMAndC.add(new Price(MUSHROOM,PRICE_CONST_20,CARROT,PRICE_CONST_1));
        priceListTAndS.add(new Price(TOMATO,PRICE_CONST_3,SALAD,PRICE_CONST_6));
        priceListTAndS.add(new Price(TOMATO,PRICE_CONST_5,SALAD,PRICE_CONST_5));
        priceListTAndS.add(new Price(TOMATO,PRICE_CONST_6,SALAD,PRICE_CONST_4));
        priceListTAndS.add(new Price(TOMATO,PRICE_CONST_7,SALAD,PRICE_CONST_3));
        priceListTAndS.add(new Price(TOMATO,PRICE_CONST_9,SALAD,PRICE_CONST_2));
        mushroom=priceListMAndC.get(currentPriceMAndC).getVegetableOne();
        mushroomPrice=priceListMAndC.get(currentPriceMAndC).getPriceOfVegetableOne();
        carrot=priceListMAndC.get(currentPriceMAndC).getVegetableTwo();
        carrotPrice=priceListMAndC.get(currentPriceMAndC).getPriceOfVegetableTwo();
        tomato=priceListTAndS.get(currentPriceTAndS).getVegetableOne();
        tomatoPrice=priceListTAndS.get(currentPriceTAndS).getPriceOfVegetableOne();
        salad=priceListTAndS.get(currentPriceTAndS).getVegetableTwo();
        saladPrice=priceListTAndS.get(currentPriceTAndS).getPriceOfVegetableTwo();
    }
    public String showMarket(){

        StringBuilder marketInformation = new StringBuilder();
        marketInformation.append(String.format(mushroom.getPluralName() +COLON+PERCENTAGE
                +(LENGTH_IN_ONE_ROW-LENGTH_OF_COLON-mushroom.getPluralName().length())+ALPHABET_D, mushroomPrice));
        marketInformation.append(System.lineSeparator());
        marketInformation.append(String.format(carrot.getPluralName() +COLON+PERCENTAGE
                +(LENGTH_IN_ONE_ROW-LENGTH_OF_COLON-carrot.getPluralName().length())+ALPHABET_D, carrotPrice));
        marketInformation.append(System.lineSeparator());
        marketInformation.append(String.format(tomato.getPluralName() +COLON+PERCENTAGE
                +(LENGTH_IN_ONE_ROW-LENGTH_OF_COLON-tomato.getPluralName().length())+ALPHABET_D, tomatoPrice));
        marketInformation.append(System.lineSeparator());
        marketInformation.append(String.format(salad.getPluralName() +COLON+PERCENTAGE
                +(LENGTH_IN_ONE_ROW-LENGTH_OF_COLON-salad.getPluralName().length())+ALPHABET_D, saladPrice));

        return marketInformation.toString();
    }

    public int getMushroomPrice(){
        return mushroomPrice;
    }
    public int getCarrotPrice(){
        return carrotPrice;
    }
    public int getTomatoPrice(){
        return tomatoPrice;
    }
    public int getSaladPrice(){
        return saladPrice;
    }
    public int getPrice(Vegetable vegetable){
        switch(vegetable.getName()){
            case CARROT -> {return carrotPrice;}
            case TOMATO -> {return tomatoPrice;}
            case SALAD -> {return saladPrice;}
            case MUSHROOM -> {return mushroomPrice;}
            default -> {return CONST_NEGATIVE_1;}
        }
    }
}
