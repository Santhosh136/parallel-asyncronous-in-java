package com.learnjava.completablefuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HPSortingCeremonyTest {

    static HPSortingCeremony hpS;

    @BeforeAll
    static void setUp() {
        hpS = new HPSortingCeremony(new HPSortingHat());
    }

    @Test
    void sortHouses() {
        List<String> personalities = new ArrayList<>();
        personalities.add("bravery");
        personalities.add("loyalty");
        personalities.add("intelligence");
        personalities.add("ambition");

        List<String> houses = hpS.sortHouses(personalities);

        System.out.println(houses);

        assertEquals(4, houses.size());
        assertTrue(houses.contains("Gryffindor"));
    }
}