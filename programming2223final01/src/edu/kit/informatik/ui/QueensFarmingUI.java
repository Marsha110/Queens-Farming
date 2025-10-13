package edu.kit.informatik.ui;

import edu.kit.informatik.model.*;

import java.util.*;

import static java.util.Collections.shuffle;

/**
 * The user interface of QueensFarming.
 *
 * @author uqeoc
 * @version 1.0
 */
public class QueensFarmingUI {
    private static final String STAR_PAGE = """
                                       _.-^-._    .--.   \s
                                    .-'   _   '-. |__|   \s
                                   /     |_|     \\|  |   \s
                                  /               \\  |   \s
                                 /|     _____     |\\ |   \s
                                  |    |==|==|    |  |   \s
              |---|---|---|---|---|    |--|--|    |  |   \s
              |---|---|---|---|---|    |==|==|    |  |   \s
            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            ^^^^^^^^^^^^^^^ QUEENS FARMING ^^^^^^^^^^^^^^^
            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^""";
    private static final String QUIT = "quit";
    private static final String QUESTION_NUMBER_OF_PLAYERS = "How many players?";
    private static final String QUESTION_NAME_ENTER = "Enter the name of player %d";
    private static final String QUESTION_START_GOLD = "With how much gold should each player start?";
    private static final String QUESTION_WIN_GOLD = "With how much gold should a player win?";
    private static final String QUESTION_SEED = "Please enter the seed used to shuffle the tiles";
    private static final String ERROR_EXPECTED_INT_POSITIVE = "Error:Expected positive data type, but got %s";
    private static final String ERROR_EXPECTED_SEED = "Error:Expected number in the Rang of "
            + "[-2147483648, 2147483647], but got %s";
    private static final String ERROR_EXPECTED_WIN_GOLD = "Error:Expected more than %d, but got %s. "
            + "The win gold should be higher than start gold";
    private static final String ERROR_EXPECTED_ALPHABET = "Error:Expected [A-Za-z]+, but got %s.";
    private static final String ERROR_INPUT_FORMAT ="Error:Expected format is sell+(the name of Vegetables), "
            + "but got %s";
    private static final String ERROR_PLANT_INPUT_FORMAT ="Error:Expected format is"
            + " plant [x-Coordinate] [y-Coordinate] [Vegetable name], but got %s.";
    private static final String ERROR_INPUT_CONTENT ="Error:The input content is not correct"
            + "in the barn.Please input the sell information again.";
    private static final String ERROR_NOT_ENOUGH_VEGETABLES_IN_THE_BARN ="Error:There is not enough vegetables "
            + "in the barn.Please input the sell information again.";
    private static final String ERROR_HARVEST_INPUT_FORMAT ="Error:Expected format is"
            + " harvest [x-Coordinate] [y-Coordinate] [quantity], but got %s.";
    private static final String ERROR_HARVEST_NO_SUCH_TILES ="Error:There is no such tiles."
            + " Please check your coordinate";
    private static final String ERROR_HARVEST_NOT_ENOUGH_VEGETABLE ="Error:The maximum vegetable you can harvest"
            + " is %d, but got %d";
    private static final String ERROR_BUY_FORMAT ="Error:Expected buy vegetable [vegetable name],but got %s";
    private static final String BUY_VEGETABLE ="You have bought a %s for %d gold";
    private static final String BUY_LAND ="You have bought a %s for %d gold";
    private static final String ERROR_BUY_LAND_FORMAT ="Error:Expected buy land [x-Coordinate] [y-Coordinate],"
            + "but got %s";
    private static final String ERROR_BUY_LAND_IS_OCCUPIED ="Error:This land is occupied, please choose another land.";
    private static final String ERROR_BUY_LAND_NOT_NEARBY_OTHER_LAND ="Error:This land need to be nearby other lands";

    private static final String HARVEST_SUCCEED ="You have harvested %s %s";
    private static final String NUMBER_OF_PLAYERS = "numberOfPlayers";
    private static final String START_GOLD = "startGold";
    private static final String WIN_GOLD = "winGold";
    private static final String SEED = "seed";
    private static final String BARN = "B";
    private static final String GARDEN = "G";
    private static final String FIELD = "Fi";
    private static final String LARGE_FIELD = "LFi";
    private static final String FOREST = "Fo";
    private static final String LARGE_FOREST = "LFo";
    private static final String TURN_INFO = "It is %s's turn!";
    private static final String VEGETABLE_HAS_GROWN = "%d vegetable has grown since your last turn";
    private static final String IS_SPOILED = "The vegetables in your barn are spoiled.";
    private static final String SHOW_BOARD = "show board";
    private static final String SHOW_BARN = "show barn";
    private static final String END_TURN = "end turn";
    private static final String SHOW_MARKET = "show market";
    private static final String SELL = "sell";
    private static final String PLANT = "plant";
    private static final String HARVEST = "harvest";
    private static final String BLANK = " ";
    private static final String CARROT = "carrot";
    private static final String SALAD = "salad";
    private static final String MUSHROOM = "mushroom";
    private static final String TOMATO = "tomato";
    private static final String SOLD_INFO = "You have sold %d vegetable%s for %d gold.";
    private static final String PLURAL_SUFFIX_S="s";
    private static final String NO_PLURAL_SUFFIX="";
    private static final String COMMA = ",";
    private static final String AND = " and ";
    private static final String WIN_INFO = "Player %d (%s): %d";
    private static final String HAS_WON = " has won!";
    private static final String HAVE_WON = " have won!";
    private static final String BUY = "buy";
    private static final String VEGETABLE = "vegetable";
    private static final String LAND = "land";
    private static final String NUMBER_OF_GROWN_VEGETABLE = "%d vegetable%s has grown since your last turn";
    private static final int NUMBER_OF_QUESTIONS = 5;
    private static final int CONST_NEGATIVE_1 = -1;
    private static final int CONST_0 = 0;
    private static final int CONST_1 = 1;
    private static final int CONST_2 = 2;
    private static final int CONST_3 = 3;
    private static final int CONST_4 = 4;
    private static final int CONST_5 = 5;
    private static final int CONST_10 = 10;
    private static final int SEED_MAX = 2147483647;
    private static final int SEED_MIN = -2147483648;
    private  int numberOfPlayers;
    private  Player[] player;
    private int startGold;
    private int winGold;
    private long seed;
    private int numberOfRestG;
    private int numberOfRestFi;
    private int numberOfRestLFi;
    private int numberOfRestFo;
    private int numberOfRestLFo;
    private boolean quit;
    private boolean matchGoingOn = false;
    private List<Tiles> tiles = new ArrayList<>();
    private boolean roundGoingOn=true;
    private Scanner scanner;
    int playerNumber;
    int xCoordinate=0;
    int yCoordinate=0;
    int quantityOfHarvest=0;

    Market market=new Market();
    private List<String> vegetableType=new LinkedList();

    public QueensFarmingUI(){
        vegetableType.add(CARROT);
        vegetableType.add(SALAD);
        vegetableType.add(MUSHROOM);
        vegetableType.add(TOMATO);
    }

    /**
     * Description: start the system.
     */
    public void start() {

        System.out.println(STAR_PAGE);
        this.scanner = new Scanner(System.in);
        questions();
        tilesNumberInitialize();
        mixTiles();
        if(!quit)
            matchBegin();
        scanner.close();
    }

    private void questions() {
        for (int i = 0; i < NUMBER_OF_QUESTIONS && !quit; i++) {
            switch (i) {
                case CONST_0 -> questionOfPlayerNumber();
                case CONST_1 -> questionOfPlayerName();
                case CONST_2 -> questionOfStartGold();
                case CONST_3 -> questionOfWinGold();
                case CONST_4 -> questionOfSeed();
            }
        }
        for(int i=0;i<numberOfPlayers && !quit;i++)
            player[i].setGold(startGold);
    }

    private void questionOfPlayerNumber() {
        System.out.println(QUESTION_NUMBER_OF_PLAYERS);
        checkIntUntilRightValue(NUMBER_OF_PLAYERS);
    }

    private void questionOfPlayerName() {
        boolean inputtedPlayerName;
        String nameAlphabet = "[A-Za-z]+";
        player = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers && !quit; i++) {
            inputtedPlayerName = false;
            System.out.println(String.format(QUESTION_NAME_ENTER, i + CONST_1));
            while (!inputtedPlayerName && !quit) {
                player[i] = new Player(scanner.nextLine());
                if (!player[i].getName().equals(QUIT)) {
                    if (player[i].getName().matches(nameAlphabet)) {
                        inputtedPlayerName = true;
                    }else {
                        System.err.println(String.format(ERROR_EXPECTED_ALPHABET, player[i]));
                    }
                } else {
                    quit = true;
                }
            }

        }
    }

    private void questionOfStartGold() {

        System.out.println(QUESTION_START_GOLD);
        checkIntUntilRightValue(START_GOLD);
    }

    private void questionOfWinGold() {

        System.out.println(QUESTION_WIN_GOLD);
        checkIntUntilRightValue(WIN_GOLD);
    }

    private void questionOfSeed() {

        System.out.println(QUESTION_SEED);
        checkIntUntilRightValue(SEED);
    }

    /**
     * Description: Check if the input format of numberOfPlayers,startGold, winGold is correct. If not passed, output
     * error information.
     *
     * @param input
     */
    private void checkIntUntilRightValue(String input) {

        String inputString;
        int temp = 0;
        boolean inputtedRightNumber = false;
        while (!inputtedRightNumber && !quit) {
            inputString = scanner.nextLine();
            if (!inputString.equals(QUIT)) {
                try {
                    temp = Integer.parseInt(inputString);
                    inputtedRightNumber = true;
                } catch (NumberFormatException exception) {
                    inputtedRightNumber = false;
                }
                if (inputtedRightNumber && (input.equals(NUMBER_OF_PLAYERS) || input.equals(START_GOLD)
                        || input.equals(WIN_GOLD)) && temp > 0) {
                    if (input.equals(NUMBER_OF_PLAYERS))
                        numberOfPlayers = temp;
                    else if (input.equals(START_GOLD))
                        startGold = temp;
                    else if (input.equals(WIN_GOLD))
                        winGold = temp;
                } else if (inputtedRightNumber && input.equals(SEED) && temp >= SEED_MIN && temp <= SEED_MAX) {
                    seed = temp;
                } else {
                    inputtedRightNumber = false;
                }
                if (inputtedRightNumber && input.equals(WIN_GOLD) && winGold <= startGold) {
                    inputtedRightNumber = false;
                    System.err.println(String.format(ERROR_EXPECTED_WIN_GOLD, startGold, inputString));
                } else if (!inputtedRightNumber && !input.equals(SEED)) {
                    System.err.println(String.format(ERROR_EXPECTED_INT_POSITIVE, inputString));
                } else if (!inputtedRightNumber && input.equals(SEED))
                    System.err.println(String.format(ERROR_EXPECTED_SEED, inputString));
            } else {
                quit = true;
            }
        }
    }

    private void tilesNumberInitialize() {
        numberOfRestG = CONST_2 * numberOfPlayers;
        numberOfRestFi = CONST_3 * numberOfPlayers;
        numberOfRestLFi = CONST_2 * numberOfPlayers;
        numberOfRestFo = CONST_2 * numberOfPlayers;
        numberOfRestLFo = numberOfPlayers;
    }

    private void mixTiles() {
        for (int i = 0; i < numberOfRestG; i++) {
            tiles.add(new Tiles(GARDEN));
        }
        for (int i = 0; i < numberOfRestFi; i++) {
            tiles.add(new Tiles(FIELD));
        }
        for (int i = 0; i < numberOfRestLFi; i++) {
            tiles.add(new Tiles(LARGE_FIELD));
        }
        for (int i = 0; i < numberOfRestFo; i++) {
            tiles.add(new Tiles(FOREST));
        }
        for (int i = 0; i < numberOfRestLFo; i++) {
            tiles.add(new Tiles(LARGE_FOREST));
        }
        shuffle(tiles, new Random(seed));
    }

    /**
     * Begin the match
     */
    private void matchBegin() {
        matchGoingOn = true;
        int startNumber = CONST_NEGATIVE_1;
        int turnCount =CONST_0;
        int roundCount;
        while (matchGoingOn) {

            roundGoingOn=true;
            int actionCount=0;
            int numOfGrownVegetable;
            playerNumber = ++startNumber % player.length;

            //show start information
            System.out.print(System.lineSeparator());
            System.out.println(String.format(TURN_INFO, player[playerNumber].getName()));
            numOfGrownVegetable=player[playerNumber].getNumberOfVegetableGrown();
            if(numOfGrownVegetable>CONST_0)
                System.out.println(String.format(NUMBER_OF_GROWN_VEGETABLE,numOfGrownVegetable
                    ,numOfGrownVegetable>CONST_1? PLURAL_SUFFIX_S:NO_PLURAL_SUFFIX));

            if (player[playerNumber].getIfSpoiled())
                System.out.println(IS_SPOILED);

            //begin the round
            while(roundGoingOn && matchGoingOn && actionCount<CONST_2) {
                String input;
                input = scanner.nextLine();

                String[] inputArray= input.split(BLANK);
                if(inputArray[CONST_0].equals(SELL)) {
                    sellVegetable(input);
                    actionCount++;
                }else if(inputArray[CONST_0].equals(PLANT)) {
                    plantVegetable(input);
                    actionCount++;
                }else if(inputArray[CONST_0].equals(HARVEST)){
                    harvestVegetable(input);
                    actionCount++;
                }else if(inputArray.length==CONST_3 && inputArray[CONST_0].equals(BUY)
                        && inputArray[CONST_1].equals(VEGETABLE)){
                    buyVegetable(input);
                } else if(inputArray.length==CONST_4 && inputArray[CONST_0].equals(BUY)
                        && inputArray[CONST_1].equals(LAND)){
                    buyLand(input);
                }else {
                    switch (input) {
                        case SHOW_BOARD -> System.out.println(player[playerNumber].showBoard());
                        case SHOW_BARN -> System.out.println(player[playerNumber].showBarn(player[playerNumber]
                                .getGold()));
                        case SHOW_MARKET -> System.out.println(market.showMarket());
                        case END_TURN -> endTurn();
                        case QUIT -> quit();
                    }
                }
            }

            //update the roundCount in the tiles.
            if(matchGoingOn) {
                turnCount++;
                roundCount = turnCount % numberOfPlayers;
                player[playerNumber].setClock();
                if (roundCount == CONST_0 && printWinInfo())
                    matchGoingOn = false;
            }
        }
    }

    private void buyVegetable(String input){
        String[] inputArray=input.split(BLANK);
        int soldGold;
        if(checkBuyVegetableInput(inputArray)){
            player[playerNumber].buyVegetable(new Vegetable(inputArray[CONST_2],CONST_1));
            soldGold=market.getPrice(new Vegetable(inputArray[CONST_2]));
            player[playerNumber].decreaseGold(soldGold);
            System.out.println(String.format(BUY_VEGETABLE,inputArray[CONST_2]
                    ,soldGold));
        }
    }
    private boolean checkBuyVegetableInput(String[] input){
        boolean isCorrect=true;
        if(!vegetableType.contains(input[CONST_2])) {
            isCorrect = false;
            System.err.println(ERROR_BUY_FORMAT);
        }
        return isCorrect;
    }

    private boolean buyLand(String input){
        String[] inputArray=input.split(BLANK);
        boolean formatIsCorrect=true;
        boolean positionIsOccupied;
        boolean positionNearByOtherLand;
        try {
            xCoordinate = Integer.parseInt(inputArray[CONST_2]);
            yCoordinate = Integer.parseInt(inputArray[CONST_3]);
        } catch (NumberFormatException exception) {
            formatIsCorrect = false;

        }
        Coordinate coordinate=new Coordinate(xCoordinate,yCoordinate);
        if(yCoordinate < CONST_0)
            formatIsCorrect=false;
        positionIsOccupied=player[playerNumber].tilesIsOccupied(coordinate);
        positionNearByOtherLand=player[playerNumber].isLandNearby(coordinate);
        //Output error information and buy land information if buy is succeed.
        if(!formatIsCorrect)
            System.err.println(String.format(ERROR_BUY_LAND_FORMAT,String.join(BLANK,inputArray)));
        else if(positionIsOccupied)
            System.err.println(ERROR_BUY_LAND_IS_OCCUPIED);
        else if(!positionNearByOtherLand)
            System.err.println((ERROR_BUY_LAND_NOT_NEARBY_OTHER_LAND));
        else {
            tiles.get(CONST_0).setPosition(coordinate);
            player[playerNumber].buyTiles(tiles.get(CONST_0));
            System.out.println(String.format(BUY_LAND,tiles.get(CONST_0).getFullName(),getLandPrice(coordinate)));
            tiles.remove(CONST_0);
        }

        return formatIsCorrect
                && player[playerNumber].tilesIsOccupied(new Coordinate(xCoordinate,xCoordinate))
                && positionNearByOtherLand;
    }
    private int getLandPrice(Coordinate coordinate){
        return CONST_10*(Math.abs(coordinate.getX())+Math.abs(coordinate.getY())-CONST_1);
    }
    private boolean printWinInfo(){
        StringBuilder nameBuilder=new StringBuilder();
        List<String> name =new LinkedList();
        boolean won =false;
        for(int i=0;i<numberOfPlayers;i++) {
            nameBuilder.append(String.format(WIN_INFO, i+CONST_1
                    , player[i].getName(), player[i].getGold()));
            nameBuilder.append(System.lineSeparator());
            if (player[i].getGold() >= winGold) {
                name.add(player[i].getName());
                won = true;
            }
        }
        if (won) {

            for (int i = 0; i < name.size(); i++) {
                if (i == CONST_0)
                    nameBuilder.append(name.get(i));
                else if (i < name.size() - CONST_1)
                    nameBuilder.append(COMMA + name.get(i));
                else if (i == name.size())
                    nameBuilder.append(AND + name.get(i));
            }
            if (name.size() == CONST_1)
                nameBuilder.append(HAS_WON);
            else if (name.size() > CONST_1)
                nameBuilder.append(HAVE_WON);
            System.out.println(nameBuilder.toString());
        }
        return won;
    }

    private void sellVegetable(String input){
        boolean succeedSold=false;
        int numOfSoldVegetable;
        int soldGold=0;
        if(sellInputCheck(input)) {
            String[] inputArray=input.split(BLANK);
            player[playerNumber].sellVegetable(input);
            numOfSoldVegetable = inputArray.length - CONST_1;
            for (int i = CONST_1; i < inputArray.length; i++) {
                if (inputArray[i].equals(CARROT))
                    soldGold += market.getCarrotPrice();
                else if (inputArray[i].equals(SALAD))
                    soldGold += market.getSaladPrice();
                else if (inputArray[i].equals(MUSHROOM))
                    soldGold += market.getMushroomPrice();
                else if (inputArray[i].equals(TOMATO))
                    soldGold += market.getTomatoPrice();
            }
            player[playerNumber].addGold(soldGold);
            System.out.println((String.format(SOLD_INFO,numOfSoldVegetable
                    ,numOfSoldVegetable>CONST_1?PLURAL_SUFFIX_S:NO_PLURAL_SUFFIX,soldGold)));
        }

    }
    private boolean sellInputCheck(String input){
        int numOfMushroom=0;
        int numOfCarrot=0;
        int numOfTomato=0;
        int numOfSalad=0;
        boolean inputFormatIsCorrect =true;
        boolean ifVegetableEnough=true;

        String[] inputArray = input.split(BLANK);
        if(inputArray[CONST_0].equals(SELL)){
            //check if the input words is correct.
            for(int i = CONST_1; i<inputArray.length && inputFormatIsCorrect; i++) {
                if (!vegetableType.contains(inputArray[i])) {
                    inputFormatIsCorrect = false;
                }else{
                    switch(inputArray[i]){
                        case CARROT-> numOfCarrot++;
                        case SALAD -> numOfSalad++;
                        case MUSHROOM -> numOfMushroom++;
                        case TOMATO -> numOfTomato++;
                    }
                }

            }
            //check if there is enough vegetable in the barn.
            for(int i = 0; i<CONST_4 && inputFormatIsCorrect; i++) {
                if (numOfMushroom != CONST_0
                        && player[playerNumber].getBarn().getNumOfMushroomInTheBarn() < numOfMushroom)
                    ifVegetableEnough = false;
                else if (numOfCarrot != CONST_0
                        && player[playerNumber].getBarn().getNumOfCarrotInTheBarn() < numOfCarrot)
                    ifVegetableEnough = false;
                else if (numOfTomato != CONST_0
                        && player[playerNumber].getBarn().getNumOfTomatoInTheBarn() < numOfTomato)
                    ifVegetableEnough = false;
                else if (numOfSalad != CONST_0
                        && player[playerNumber].getBarn().getNumOfSaladInTheBarn() < numOfSalad)
                    ifVegetableEnough = false;
            }
        }else inputFormatIsCorrect =false;

        if(!inputFormatIsCorrect)
            System.out.println(String.format(ERROR_INPUT_FORMAT,input));
        else if(!ifVegetableEnough)
            System.out.println(ERROR_NOT_ENOUGH_VEGETABLES_IN_THE_BARN);

        return inputFormatIsCorrect && ifVegetableEnough;
    }

    private void plantVegetable(String input){
        String[] inputArray= input.split(BLANK);
        if(checkPlantCommand(inputArray)){
            player[playerNumber].plantVegetable(new Vegetable(inputArray[CONST_3],CONST_1)
                    ,new Coordinate(xCoordinate,yCoordinate));
        }
    }

    private boolean checkPlantCommand(String[] inputArray){
        boolean formatIsCorrect=true;
        boolean tilesCanBeUsed=true;

        if(!inputArray[CONST_0].equals(PLANT) || inputArray.length!=CONST_4
                || !vegetableType.contains(inputArray[CONST_3]))
            formatIsCorrect=false;
        else {
            try {
                xCoordinate = Integer.parseInt(inputArray[CONST_1]);
                yCoordinate = Integer.parseInt(inputArray[CONST_2]);
            } catch (NumberFormatException exception) {
                formatIsCorrect = false;
            }
        }
        if(!formatIsCorrect)
            System.err.println(String.format(ERROR_PLANT_INPUT_FORMAT, String.join(BLANK,inputArray)));
        else if(!player[playerNumber].tilesCanBePlanted(new Vegetable(inputArray[CONST_3])
                    ,new Coordinate(xCoordinate,yCoordinate))) {
            tilesCanBeUsed=false;
            System.err.println(ERROR_INPUT_CONTENT);
        }
        return formatIsCorrect && tilesCanBeUsed;
    }

    private void harvestVegetable(String input){
        String[] inputArray=input.split(BLANK);
        if(checkHarvestCommand(inputArray)){
            int tilesIndex=player[playerNumber].getTilesIndex(new Coordinate(xCoordinate,yCoordinate));
            String vegetableName;
            if(quantityOfHarvest>CONST_1)
                vegetableName=player[playerNumber].getVegetableInTheTiles(tilesIndex).getPluralName();
            else
                vegetableName=player[playerNumber].getVegetableInTheTiles(tilesIndex).getName();

            player[playerNumber].harvest(new Coordinate(xCoordinate,yCoordinate),quantityOfHarvest);
            System.out.println(String.format(HARVEST_SUCCEED,inputArray[CONST_3]
                ,vegetableName));
        }
    }
    private boolean checkHarvestCommand(String[] inputArray){
        boolean formatIsCorrect=true;
        boolean tilesCanBeHarvested=true;


        if(inputArray.length!=CONST_4)
            formatIsCorrect=false;
        else {
            try {
                xCoordinate = Integer.parseInt(inputArray[CONST_1]);
                yCoordinate = Integer.parseInt(inputArray[CONST_2]);
                quantityOfHarvest=Integer.parseInt(inputArray[CONST_3]);
            } catch (NumberFormatException exception) {
                formatIsCorrect = false;
            }
        }
        if(quantityOfHarvest<=CONST_0)
            formatIsCorrect = false;
        if(!formatIsCorrect)
            System.err.println(String.format(ERROR_HARVEST_INPUT_FORMAT, String.join(BLANK,inputArray)));
        else{
            if(player[playerNumber].getTilesIndex(new Coordinate(xCoordinate,yCoordinate))==CONST_NEGATIVE_1) {
                tilesCanBeHarvested=false;
                System.err.println(String.format(ERROR_HARVEST_NO_SUCH_TILES,String.join(BLANK,inputArray)));
            }else{
                int tilesIndex=player[playerNumber].getTilesIndex(new Coordinate(xCoordinate,yCoordinate));
                int quantityCanBeHarvested=player[playerNumber].getBoard().get(tilesIndex).getNumOfVegetables();
                if(quantityCanBeHarvested<quantityOfHarvest){
                    tilesCanBeHarvested=false;
                    System.err.println(String.format(ERROR_HARVEST_NOT_ENOUGH_VEGETABLE
                            ,quantityCanBeHarvested,quantityOfHarvest));
                }
            }
        }
        return formatIsCorrect && tilesCanBeHarvested;
    }
    private void endTurn(){
        roundGoingOn=false;
    }
    private void quit(){
        matchGoingOn = false;
    }

}

