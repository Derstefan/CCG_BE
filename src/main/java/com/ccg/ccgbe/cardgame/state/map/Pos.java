package com.ccg.ccgbe.cardgame.state.map;

public class Pos {

    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }






    public Pos plus(Pos pos){
        return new Pos(x+pos.getX(),y+pos.getY());
    }


    public String toString(){
        return "("+x+","+y+")";
    }

}
