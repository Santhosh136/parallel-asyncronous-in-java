package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.delay;

public class HPSortingHat {

    private static final String GRYFFINDOR = "Gryffindor";
    private static final String HUFFLEPUFF = "Hufflepuff";
    private static final String RAVENCLAW = "Ravenclaw";
    private static final String SLYTHERIN = "Slytherin";

    public String getHouse(String personality) {
        delay(1000);
        String house;
        switch (personality) {
            case "bravery" -> house = GRYFFINDOR;
            case "loyalty" -> house = HUFFLEPUFF;
            case "intelligence" -> house = RAVENCLAW;
            case "ambition" -> house = SLYTHERIN;
            default -> house = "";
        };
        return house;
    }

}
