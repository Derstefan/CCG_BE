package com.ccg.ccgbe.cardgame.state.map;


import com.ccg.ccgbe.cardgame.rules.element.Element;

public class Map {

    private Cell[][] mapData;

    private int width;
    private int height;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.mapData = new Cell[width][height];
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[0].length; j++) {
                mapData[i][j] = new Cell(new Pos(i,j));
            }
        }
    }

    public Map(Cell[][] mapData){
        this.width = mapData.length;
        this.height = mapData[0].length;
        this.mapData = mapData;
    }

    public Map(Map map){
        this.width = map.getWidth();
        this.height = map.getHeight();
        Cell[][] mapData = new Cell[this.width][this.height];
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[0].length; j++) {
                Pos pos = new Pos(i,j);
                mapData[i][j] = map.getCell(pos).clone();
            }
        }
        this.mapData = mapData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void put(Element element, Pos pos){
        mapData[pos.getX()][pos.getY()].setElement(element);
    }

    public Cell getCell(Pos pos) {
        return mapData[pos.getX()][pos.getY()];
    }

    public Element getElement(Pos pos) {
        return mapData[pos.getX()][pos.getY()].getElement();
    }

    public Element getElement(int i, int j) {
        return mapData[i][j].getElement();
    }

    public Cell[][] getMapData() {
        return mapData;
    }


    public Map clone(){
        Map newMap = new Map(this);
        return newMap;
    }


    public String toString(){
        String str = " ~ ";
        for(int i=0;i<getWidth();i++){
            str+= " "+i+" ";
        }
        str+="\n";

        for(int i=0;i<getWidth();i++){
            str+= " "+i+" ";
            for(int j=0;j<getHeight();j++){
                str+=getElement(i,j)!=null?" "+getElement(i,j).toString()+" ":" - ";
            }
            str+="\n";
        }
        return str;
    }
}
