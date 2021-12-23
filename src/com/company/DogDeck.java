package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DogDeck {
    private static ArrayList<DogCard> deck = new ArrayList<>();

    public DogDeck(String filePath) {
        deck = createDeckFromCsv(filePath);

        //TODO: abstract shuffling from constructor?
        Collections.shuffle(deck);
    }

    // region Getters & Setters
    public ArrayList<DogCard> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<DogCard> deck) {
        DogDeck.deck = deck;
    }

    public void describeDeck() {
        for (DogCard card : deck) {
            System.out.println(card.getDescription());
        }
    }
    //endregion

    private ArrayList<DogCard> createDeckFromCsv(String filePath) {
        ArrayList<DogCard> tempArr = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                // create an array from the whole CSV file
                String[] values = line.split(",");

                // create doggo data item and add to data array
                DogCard dogCardToAdd = new DogCard(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
                tempArr.add(dogCardToAdd);

                // move on to next line in CSV
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempArr;
    }
}