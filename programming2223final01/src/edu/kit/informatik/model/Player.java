package edu.kit.informatik.model;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    private static final String BARN = "B";
    private static final String GARDEN = "G";
    private static final String FIELD = "Fi";
    private static final String LARGE_FIELD = "LFi";
    private static final String FOREST = "Fo";
    private static final String LARGE_FOREST = "LFo";
    private static final String SLASH = "/";
    private static final String STRING_FIRST_LINE = "|%s %s|";
    private static final String BARN_OUTPUT_SECOND_LINE = " %s %s |";
    private static final String GARDEN_OUTPUT_FIRST_LINE = " %s %s |";
    private static final String FIELD_FOREST_OUTPUT_FIRST_LINE = " %s %s|";
    private static final String LARGE_FIELD_FOREST_OUTPUT_FIRST_LINE = "%s %s|";
    private static final String NOT_BARN_SECOND_LINE = "  %s  |";
    private static final String NOT_BARN_THIRD_LINE = " %s/%s |";
    private static final String BLANK = " ";
    private static final String BLANKS = "     ";
    private static final String ASTERISK = "*";
    private static final String VERTICAL_BAR = "|";
    private static final int CONST_0 = 0;
    private static final int CONST_1 = 1;
    private static final int CONST_3 = 3;
    private static final int CONST_6 = 6;

    private static final int CONST_NEGATIVE_1 = -1;
    private static final int X_MIN_INITIAL_VALUE = -1;
    private static final int X_MAX_INITIAL_VALUE = 1;
    private static final int Y_MIN_INITIAL_VALUE = 0;
    private static final int Y_MAX_INITIAL_VALUE = 1;
    private int numberOfVegetableGrown=0;
    private boolean ifSpoiled=false;
    private List<Tiles> board = new LinkedList<>();
    private int xMin;
    private int xMax;
    private final int yMin=Y_MIN_INITIAL_VALUE;
    private int yMax;

    String name;
    int gold=0;
    int startGold;

    public Player(String name){

        this.name= name;
        xMin=X_MIN_INITIAL_VALUE;
        xMax=X_MAX_INITIAL_VALUE;
        yMax=Y_MAX_INITIAL_VALUE;
        board.add(new Tiles(FIELD,new Coordinate(CONST_0, CONST_1)));
        board.add(new Tiles(GARDEN, new Coordinate(CONST_NEGATIVE_1, CONST_0)));
        board.add(new Tiles(BARN, new Coordinate(CONST_0, CONST_0)));
        board.add(new Tiles(GARDEN, new Coordinate(CONST_1, CONST_0)));
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public int getGold(){
        return gold;
    }
    public void setGold(int startGold){
        gold=startGold;
    }
    public List<Tiles> getBoard(){
        return board;
    }
    public Tiles getBarn(){
        Tiles barn=new Tiles(BARN);
        for(int i=0;i<board.size();i++)
            if(board.get(i).getType().equals(BARN))
                barn=board.get(i);
        return barn;
    }
    public boolean buyTiles(Tiles tiles){

        if(tiles.getPosition().getY()<CONST_0)
            return false;
        //search insertIndex
        int insertIndex=0;
        boolean insertIndexFound=false;
        boolean insertSucceed;

        for(int index=0;index<board.size() && !insertIndexFound;index++){
            if(board.get(index).getPosition().getY()<=tiles.getPosition().getY()) {
                int search;
                for (search = index; search < board.size() && !insertIndexFound
                        && board.get(index).getPosition().getY() == board.get(search).getPosition().getY(); search++)
                    if (board.get(search).getPosition().getX() >= tiles.getPosition().getX()) {
                        insertIndex = search;
                        insertIndexFound = true;
                    }
                if(!insertIndexFound && search<(board.size()-CONST_1))
                    insertIndex=search+CONST_1;
                else if(!insertIndexFound && search==(board.size()-CONST_1))
                    insertIndex=CONST_NEGATIVE_1;
            }
        }

        //judge if the new tiles is nearby the other tiles.If it passed, then insert the tiles according to insertIndex.
        if(isLandNearby(tiles.getPosition())) {

            if (insertIndex == CONST_NEGATIVE_1)
                board.add(tiles);
            else
                board.add(insertIndex, tiles);
            insertSucceed=true;
        }else
           insertSucceed= false;
        if(tiles.getPosition().getX()>xMax)
            xMax=tiles.getPosition().getX();
        else if(tiles.getPosition().getX()<xMin)
            xMin=tiles.getPosition().getX();
        else if(tiles.getPosition().getY()>yMax)
            yMax=tiles.getPosition().getY();

        return insertSucceed;
    }

    public boolean isLandNearby(Coordinate coordinate){
        if(!tilesIsOccupied(coordinate))
            for(int i=0;i< board.size();i++)
                if((board.get(i).getPosition().getX()==coordinate.getX()
                        && Math.abs(board.get(i).getPosition().getY()-coordinate.getY())==CONST_1)
                    ||(board.get(i).getPosition().getY()==coordinate.getY()
                        && (coordinate.getX()-board.get(i).getPosition().getX())==CONST_1))
                    return true;
        return false;
    }

    /**
     * Description: Scan every tile in the board. If the tiles have been sold, show the information if the tiles.
     * otherweise, use blank to represent this tiles.
     * @return
     */
    public String showBoard(){
        int index=CONST_0;
        StringJoiner joiner=new StringJoiner(System.lineSeparator());

        //save every line if the tiles in one row into three StringBuilder variables. Then use line break to connect
        //these three StringBuilders. Use this way to convert all the tiles in the board to string.
        for(int y=yMax;y>=yMin;y--) {
            boolean xBeforeBlank=true;
            StringBuilder firstLineBuilder = new StringBuilder();
            StringBuilder secondLineBuilder = new StringBuilder();
            StringBuilder thirdLineBuilder = new StringBuilder();
            for (int x = xMin; x <= xMax; x++) {
                if (board.get(index).getPosition().getX() == x && board.get(index).getPosition().getY() == y) {
                    if(xBeforeBlank){
                        firstLineBuilder.append(VERTICAL_BAR);
                        secondLineBuilder.append(VERTICAL_BAR);
                        thirdLineBuilder.append(VERTICAL_BAR);
                    }
                    switch (board.get(index).getType()) {
                        case BARN -> {
                            firstLineBuilder.append(BLANKS+VERTICAL_BAR);
                            secondLineBuilder.append(String.format(BARN_OUTPUT_SECOND_LINE
                                    , board.get(index).getType()
                                    , board.get(index).getCountDown()==CONST_NEGATIVE_1
                                            ? ASTERISK:board.get(index).getCountDown()));
                            thirdLineBuilder.append(BLANKS+VERTICAL_BAR);
                        }
                        case GARDEN -> firstLineBuilder.append(String.format(GARDEN_OUTPUT_FIRST_LINE
                                , board.get(index).getType()
                                , board.get(index).getCountDown()==CONST_NEGATIVE_1
                                        ? ASTERISK:board.get(index).getCountDown()));
                        case FIELD, FOREST -> firstLineBuilder.append(String.format(FIELD_FOREST_OUTPUT_FIRST_LINE
                                , board.get(index).getType()
                                , board.get(index).getCountDown()==CONST_NEGATIVE_1
                                        ? ASTERISK:board.get(index).getCountDown()));
                        case LARGE_FIELD, LARGE_FOREST -> firstLineBuilder
                                .append(String.format(LARGE_FIELD_FOREST_OUTPUT_FIRST_LINE
                                        , board.get(index).getType()
                                        , board.get(index).getCountDown()==CONST_NEGATIVE_1
                                                ? ASTERISK:board.get(index).getCountDown()));
                    }
                    if (!board.get(index).getType().equals(BARN)) {
                        secondLineBuilder.append(String.format(NOT_BARN_SECOND_LINE
                                , board.get(index).getVegetablePlantedName().toUpperCase().charAt(CONST_0)));
                        thirdLineBuilder.append(String.format(NOT_BARN_THIRD_LINE
                                , board.get(index).getNumOfVegetables()
                                , board.get(index).getCapacity()));
                    }
                    index++;
                    xBeforeBlank=false;
                } else {
                    if(xBeforeBlank) {
                        firstLineBuilder.append(BLANK);
                        secondLineBuilder.append(BLANK);
                        thirdLineBuilder.append(BLANK);
                    }
                    firstLineBuilder.append(BLANKS);
                    secondLineBuilder.append(BLANKS);
                    thirdLineBuilder.append(BLANKS);
                    xBeforeBlank=true;
                }
            }
            joiner.add(firstLineBuilder.toString() + System.lineSeparator()
                    +secondLineBuilder.toString() + System.lineSeparator()
                    +thirdLineBuilder.toString());
        }
        return joiner.toString();
    }
    public int getNumberOfVegetableGrown() {
        return numberOfVegetableGrown;
    }

    public boolean getIfSpoiled() {
        return ifSpoiled;
    }


    public String showBarn(int goldOfThePlayer){

        return getBarn().getVegetablesInfoInTheBarn(goldOfThePlayer);
    }
    public void sellVegetable(String input){

        String[] inputArray=input.split(BLANK);
        for(int i=CONST_1;i<inputArray.length;i++){
            getBarn().sellVegetableInTheBarn(new Vegetable(inputArray[i]));
        }
    }
    public void addGold(int increment){
        gold+=increment;
    }
    public void decreaseGold(int increment){
        gold-=increment;
    }
    public boolean tilesCanBePlanted(Vegetable vegetable,Coordinate coordinate){
        boolean tilesCanBePlanted=false;
        for(int i=0;i<board.size() && !tilesCanBePlanted;i++){
            if(board.get(i).getPosition().getX()==coordinate.getX()
                    && board.get(i).getPosition().getY()==coordinate.getY()
            && board.get(i).VegetableCanBePlanted(vegetable)
            && !board.get(i).getType().equals(BARN))
                if(!board.get(i).TilesHasVegetable())
                    tilesCanBePlanted=true;
        }
        return tilesCanBePlanted;
    }
    public void plantVegetable(Vegetable vegetable,Coordinate coordinate){
        if(board.get(getTilesIndex(coordinate)).plantVegetable(vegetable))
            getBarn().decreaseOneVegetableInTheBarn(vegetable);
    }
    public void buyVegetable(Vegetable vegetable){
        getBarn().addVegetablesIntoBarn(vegetable);
    }
    public int getTilesIndex(Coordinate coordinate){

        for(int i=0;i<board.size();i++)
            if(board.get(i).getPosition().getX()==coordinate.getX()
                    && board.get(i).getPosition().getY()==coordinate.getY()){
                return i;
            }
        return CONST_NEGATIVE_1;
    }
    public void setClock(){

        /*for(int i=0;i< board.size();i++){
            numberOfVegetableGrown+=board.get(i).addOneRound();
        }*/
    }
    public void harvest(Coordinate coordinate, int quantity){
        int tilesIndex=getTilesIndex(coordinate);
        getBarn().addVegetablesIntoBarn(board.get(tilesIndex).harvest(quantity));
    }
    public Vegetable getVegetableInTheTiles(int tilesIndex){
        return board.get(tilesIndex).getVegetableIsPlanted().get(CONST_0);
    }
    public boolean tilesIsOccupied(Coordinate coordinate){
        for(int i=0;i< board.size();i++)
            if(board.get(i).getPosition().getX()==coordinate.getX()
                    && board.get(i).getPosition().getY()==coordinate.getY())
                return true;
        return false;
    }
}
