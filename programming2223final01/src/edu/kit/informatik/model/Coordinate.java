package edu.kit.informatik.model;

/**
 * The user interface of perseverance.
 *
 * @author uqeoc
 * @version 1.0
 */
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
