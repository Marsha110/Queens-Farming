package edu.kit.informatik.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Tiles {
    private static final String SHORT_LINE = "-";
    private static final String BARN = "B";
    private static final String BARN_FULL_NAME = "Barn";
    private static final int BARN_CAPACITY = -1;
    private static final String BARN_Vegetable_TYPE = "-1";
    private static final String GARDEN = "G";
    private static final String GARDEN_FULL_NAME = "Garden";
    private static final int GARDEN_CAPACITY = 2;
    private static final String FIELD = "Fi";
    private static final String FIELD_FULL_NAME= "Field";
    private static final int FIELD_CAPACITY = 4;
    private static final String LARGE_FIELD = "LFi";
    private static final String LARGE_FIELD_FULL_NAME = "Large Field";
    private static final int LARGE_FIELD_CAPACITY = 8;
    private static final String FOREST = "Fo";
    private static final String FOREST_FULL_NAME = "Forest";
    private static final int FOREST_CAPACITY = 4;
    private static final String LARGE_FOREST = "LFo";
    private static final String LARGE_FOREST_FULL_NAME = "Large Forest";
    private static final int LARGE_FOREST_CAPACITY = 8;
    private static final String CARROT = "carrot";
    private static final String SALAD = "salad";
    private static final String TOMATO = "tomato";
    private static final String MUSHROOM = "mushroom";
    private static final String NO_COUNTDOWN = "*";
    private static final String NO_VEGETABLE_PLANTED = " ";
    private static final String HYPE = "-";
    private static final int COUNTDOWN_INITIAL_VALUE = -1;
    private static final int COUNTDOWN_BARN_INITIAL_VALUE = 6;
    private static final int CONST_0 = 0;
    private static final int CONST_1 = 1;
    private static final int CONST_2 = 2;
    private static final int LENGTH_OF_COLON = 1;
    private static final String COLON = ":";
    private static final int LENGTH_OF_ONE_BLANK = 1;
    private static final int CONST_NEGATIVE_1 = -1;
    private static final String SHOW_BARN_SPOILS = "Barn (spoils in %d turns)";
    private static final String BLANK = " ";
    private static final String PERCENTAGE = "%";
    private static final String ALPHABET_D = "d";
    private static final String SUM = "Sum:";
    private static final String GOLD = "Gold:";
    private String type;
    private List<Vegetable> vegetableCanBePlanted=new LinkedList<>();
    private List<Vegetable> vegetableInTheBarn=new LinkedList<>();
    private  List<Vegetable> vegetableIsPlanted =new LinkedList<>();
    private int capacity;
    private Coordinate position;
    private int countDown;
    private String fullName;

    public Tiles(String type){

        initialize(type);
    }

    public Tiles(String type, Coordinate position){

        initialize(type);
        this.position=new Coordinate(position.getX(),position.getY());

    }

    private void initialize(String type){

        vegetableInTheBarn.add(new Vegetable(CARROT,1));
        vegetableInTheBarn.add(new Vegetable(SALAD,1));
        vegetableInTheBarn.add(new Vegetable(TOMATO,1));
        vegetableInTheBarn.add(new Vegetable(MUSHROOM,1));
        this.type=type;
        switch(type){
            case BARN-> {
                assignProperty(BARN_CAPACITY,BARN_FULL_NAME);
            }
            case GARDEN-> {
                assignProperty(GARDEN_CAPACITY,GARDEN_FULL_NAME);
                vegetableCanBePlanted.add(new Vegetable(CARROT));
                vegetableCanBePlanted.add(new Vegetable(SALAD));
                vegetableCanBePlanted.add(new Vegetable(TOMATO));
                vegetableCanBePlanted.add(new Vegetable(MUSHROOM));
            }
            case FIELD-> {
                assignProperty(FIELD_CAPACITY,FIELD_FULL_NAME);
                vegetableCanBePlanted.add(new Vegetable(CARROT));
                vegetableCanBePlanted.add(new Vegetable(SALAD));
                vegetableCanBePlanted.add(new Vegetable(TOMATO));
            }
            case LARGE_FIELD-> {
                assignProperty(LARGE_FIELD_CAPACITY,LARGE_FIELD_FULL_NAME);
                vegetableCanBePlanted.add(new Vegetable(CARROT));
                vegetableCanBePlanted.add(new Vegetable(SALAD));
                vegetableCanBePlanted.add(new Vegetable(TOMATO));
            }
            case FOREST-> {
                assignProperty(FOREST_CAPACITY,FOREST_FULL_NAME);
                vegetableCanBePlanted.add(new Vegetable(CARROT));
                vegetableCanBePlanted.add(new Vegetable(MUSHROOM));
            }
            case LARGE_FOREST-> {
                assignProperty(LARGE_FOREST_CAPACITY,LARGE_FOREST_FULL_NAME);
                vegetableCanBePlanted.add(new Vegetable(CARROT));
                vegetableCanBePlanted.add(new Vegetable(MUSHROOM));
            }
        }
        if(type.equals(BARN))
            countDown =COUNTDOWN_BARN_INITIAL_VALUE;
        else countDown =3;
                //COUNTDOWN_INITIAL_VALUE;
    }
    public String getFullName(){
        return fullName;
    }
    private void assignProperty(int capacity, String fullName){
            this.capacity = capacity;
            this.fullName=fullName;
    }
    public Coordinate getPosition(){
        return position;
    }
    public void setPosition(Coordinate coordinate){
        position=new Coordinate(coordinate.getX(), coordinate.getY());
    }
    public int getCountDown(){
        return countDown;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }
    public int getNumOfVegetables(){
        return vegetableIsPlanted.isEmpty()? CONST_0: vegetableIsPlanted.get(CONST_0).getQuantity();
    }
    public String getType(){
        return type;
    }
    public String getVegetablePlantedName(){
        return vegetableIsPlanted.isEmpty()?BLANK: vegetableIsPlanted.get(CONST_0).getName();
    }
    public void decreaseOneVegetableInTheBarn(Vegetable vegetable){
        int vegetableIndex =searchVegetableIndexInTheBarn(vegetable);
        vegetableInTheBarn.get(vegetableIndex).quantityMinus1();
        if(vegetableInTheBarn.get(vegetableIndex).getQuantity() == CONST_0)
            vegetableInTheBarn.remove(vegetableIndex);
    }
    public String getVegetablesInfoInTheBarn(int goldOfThePlayer) {

        int lineLength=0; //the length of one row from the second line of information of the barn.
        int quantityOfAllVegetable=0;

        //sort the vegetables in the barn according to the length of plural name and then get the maximum length
        // of the plural name of the vegetable.
        Collections.sort(vegetableInTheBarn, new Comparator<Vegetable>() {
            @Override
            public int compare(Vegetable o1, Vegetable o2) {

                return o2.getPluralName().length()-o1.getPluralName().length();
            }
        });

        //calculate the sum of the quantity of the vegetable in the barn.
        lineLength= lineLength+vegetableInTheBarn.get(CONST_0).getPluralName().length();
        for(Vegetable vegetable: vegetableInTheBarn )
            quantityOfAllVegetable+=vegetable.getQuantity();

        //add the max length between quantityOfAllVegetable and goldOfThePlayer to lineLength.
        if(Integer.toString(quantityOfAllVegetable).length()>=Integer.toString(goldOfThePlayer).length())
            lineLength+=Integer.toString(quantityOfAllVegetable).length();
        else
            lineLength+=Integer.toString(goldOfThePlayer).length();

        lineLength+= LENGTH_OF_ONE_BLANK+LENGTH_OF_COLON;

        //sort the vegetables in the barn.
        Comparator<Vegetable> byQuantity = Comparator.comparing(Vegetable::getQuantity);
        Comparator<Vegetable> byName = Comparator.comparing(Vegetable::getName);
        Collections.sort(vegetableInTheBarn, byQuantity.thenComparing(byName));

        //convert all the information to string.
        StringBuilder barnInformation = new StringBuilder();
        if(getCountDown() !=CONST_0){
            barnInformation.append(String.format(SHOW_BARN_SPOILS, getCountDown()));
            barnInformation.append(System.lineSeparator());
        }

        for(Vegetable vegetable: vegetableInTheBarn ) {
            barnInformation.append(String.format(vegetable.getPluralName()+COLON+PERCENTAGE
                    +(lineLength-LENGTH_OF_COLON-vegetable.getPluralName().length())+ALPHABET_D
                    ,vegetable.getQuantity()));
            barnInformation.append(System.lineSeparator());
        }
        for(int i=0;i<lineLength;i++)
            barnInformation.append(HYPE);
        barnInformation.append(System.lineSeparator());
        barnInformation.append(String.format(SUM+PERCENTAGE+(lineLength-SUM.length())+ALPHABET_D
                            , quantityOfAllVegetable));
        barnInformation.append(System.lineSeparator());
        barnInformation.append(System.lineSeparator());
        barnInformation.append(String.format(GOLD+PERCENTAGE+(lineLength-GOLD.length())+ALPHABET_D
                , goldOfThePlayer));
        return barnInformation.toString();
    }
    public boolean sellVegetableInTheBarn(Vegetable vegetable){
        boolean isSold=false;
        Vegetable barnVegetables;
        for(int i=0;i<vegetableInTheBarn.size() && !isSold;i++){
            barnVegetables=vegetableInTheBarn.get(i);
            if(barnVegetables.getName().equals(vegetable.getName())) {
                barnVegetables.quantityMinus1();

                isSold=true;
                if(barnVegetables.getQuantity() == CONST_0)
                    vegetableInTheBarn.remove(i);
            }
        }
        return isSold;
    }
    public void addVegetablesIntoBarn(Vegetable vegetable){
        boolean vegetableFound=false;
        for(int i=0;i<vegetableInTheBarn.size() && !vegetableFound;i++)
            if(vegetableInTheBarn.get(i).getName().equals(vegetable.getName())) {
                vegetableInTheBarn.get(i).quantityPlus1();
                vegetableFound=true;
            }
        if(!vegetableFound)
            vegetableInTheBarn.add(vegetable);
    }
    public int getNumOfCarrotInTheBarn(){
        int carrotIndex= searchVegetableIndexInTheBarn(new Vegetable(CARROT));
        if(carrotIndex>=CONST_0)
            return vegetableInTheBarn.get(carrotIndex).getQuantity();
        else return CONST_0;
    }
    public int getNumOfMushroomInTheBarn(){
        int carrotIndex= searchVegetableIndexInTheBarn(new Vegetable(MUSHROOM));
        if(carrotIndex>=CONST_0)
            return vegetableInTheBarn.get(carrotIndex).getQuantity();
        else return CONST_0;
    }
    public int getNumOfSaladInTheBarn(){
        int carrotIndex= searchVegetableIndexInTheBarn(new Vegetable(SALAD));
        if(carrotIndex>=CONST_0)
            return vegetableInTheBarn.get(carrotIndex).getQuantity();
        else return CONST_0;
    }
    public int getNumOfTomatoInTheBarn(){
        int carrotIndex= searchVegetableIndexInTheBarn(new Vegetable(TOMATO));
        if(carrotIndex>=CONST_0)
            return vegetableInTheBarn.get(carrotIndex).getQuantity();
        else return CONST_0;
    }
    private int searchVegetableIndexInTheBarn(Vegetable vegetable){
        for(int i=0;i<vegetableInTheBarn.size();i++){
            if(vegetableInTheBarn.get(i).getName().equals(vegetable.getName()))
                return i;
        }
        return CONST_NEGATIVE_1;
    }
    public boolean VegetableCanBePlanted(Vegetable vegetable){
        if(searchVegetableIndexInTheTiles(vegetable)!=CONST_NEGATIVE_1)
            return true;
        else return false;
    }
    private int searchVegetableIndexInTheTiles(Vegetable vegetable){
        for(int i=0;i<vegetableCanBePlanted.size();i++)
            if(vegetableCanBePlanted.get(i).getName().equals(vegetable.getName()))
                return i;
        return CONST_NEGATIVE_1;
    }
    public boolean ifTilesIsFull(){
        return vegetableIsPlanted.isEmpty()? false:vegetableIsPlanted.get(CONST_0).getQuantity()==capacity;
    }
    public boolean TilesHasVegetable(){
        return !vegetableIsPlanted.isEmpty();
    }
    public boolean plantVegetable(Vegetable vegetable){
        if(!TilesHasVegetable()) {
            vegetableIsPlanted.add(vegetable);
            countDown = vegetable.getGrowthCycle();
            return true;
        }else
            return false;
    }
    //update roundCountOutside, roundCountInner,return how many vegetables are grown
    /*public int addOneRound(){
        int quantityOfGrownVegetable=CONST_0;
        if(countDown !=CONST_NEGATIVE_1) {
                countDown =(countDown ==CONST_0? countDown + vegetableIsPlanted.get(CONST_0).getGrowthCycle(): countDown);
                countDown--;
                if (countDown == CONST_0)
                    if (!ifTilesIsFull()) {
                        int originalQuantity=vegetableIsPlanted.get(CONST_0).getQuantity();
                        for(int i=0;i<vegetableIsPlanted.get(CONST_0).getQuantity()
                                && vegetableIsPlanted.get(CONST_0).getQuantity()<capacity;i++)
                            vegetableIsPlanted.get(CONST_0).quantityPlus1();
                        quantityOfGrownVegetable=vegetableIsPlanted.get(CONST_0).getQuantity()-originalQuantity;
                        countDownReset();
                    } else
                        countDown = CONST_NEGATIVE_1;
        }
        return quantityOfGrownVegetable;
    }*/

    public Vegetable harvest(int quantity){
        for(int i=0;i<quantity;i++)
            vegetableIsPlanted.get(CONST_0).quantityMinus1();
        countDownReset();
        return (new Vegetable(vegetableIsPlanted.get(CONST_0).getName(),quantity));
    }
    private void countDownReset(){
        countDown =vegetableIsPlanted.get(CONST_0).getGrowthCycle();
    }
    public List<Vegetable> getVegetableIsPlanted(){
        return vegetableIsPlanted;
    }

}
