package com.ccg.ccgbe.cardgame.rules.element;

import java.util.Random;

public class EType {
    private String name;

    private String description;

    private String color = null;

    private String[] tags = new String[0];

    private boolean isBasic = false;

    public EType(String name) {
        this.name = name;
        int rand_num = new Random().nextInt(0xffffff + 1);
        this.color = String.format("#%06x", rand_num);
    }

    public EType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    //Basic ETypes are startelements of the map to avoid empty cells
    public EType(String name, String color,String description, boolean isBasic) {
        this.name = name;
        this.color = color;
        this.description = description;
        this.isBasic = isBasic;

    }
    public EType(String name, String color,String description,boolean isBasic, String ... tags) {
        this.name = name;
        this.color = color;
        this.description = description;
        this.tags = tags;
        this.isBasic = isBasic;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public boolean equals(EType type) {
        return type.getName().equals(name);
    }


    public String[] getTags() {
        return tags;
    }

    public String toString(){
        return name;
    }


}
