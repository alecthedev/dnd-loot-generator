package edu.bsu.cs;

import java.util.*;

public class ItemGenerator {

    protected static List<Item> filteredItemList = new ArrayList<>();

    protected List<Item> generateAmountOfItems(int numberOfItemsToGenerate) {
        List<Item> displayItemList = new ArrayList<>();
        if (filteredItemList.isEmpty()) {
            GUI.displayNoItemsWithCurrentFilters();
        }

        for (int i = 0; i < numberOfItemsToGenerate; i++) {
            displayItemList.add(generateItem(filteredItemList));
        }

        return displayItemList;
    }

    private Item generateItem(List<Item> filteredItemList) {
        int randomItemIndex = new Random().nextInt(filteredItemList.size());
        randomItemIndex = weightGeneratedItem(filteredItemList, randomItemIndex);
        return filteredItemList.get(randomItemIndex);
    }

    private int weightGeneratedItem(List<Item> filteredItemList, int randomItemIndex) {
        int weight = new ItemFilter().fetchWeight();
        for (int i = 0; i < weight; i++) {
            if (!filteredItemList.get(randomItemIndex).getRarity().equals("Mundane")) {
                randomItemIndex = new Random().nextInt(filteredItemList.size());
            }
        }
        return randomItemIndex;
    }

}
