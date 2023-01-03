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

    public static Pos IPos(int index){
        switch (index){
            case 0: return new Pos(-1,-1);
            case 1: return new Pos(0,-1);
            case 2: return new Pos(1,-1);

            case 3: return new Pos(1,0);
            case 4: return new Pos(1,1);
            case 5: return new Pos(0,1);
            case 6: return new Pos(-1,1);
            default: return new Pos(-1,0);
        }
    }

}
