package com.company;

import java.util.ArrayList;

public class Player {
    boolean isComputer;
    ArrayList<DogCard> hand;

    public Player(boolean isComputer, ArrayList<DogCard> hand) {
        this.isComputer = isComputer;
        this.hand = hand;
    }

    // region Getters & Setters
    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setHand(ArrayList<DogCard> hand) {
        this.hand = hand;
    }

    public ArrayList<DogCard> getHand() {
        return hand;
    }
    //endregion

    public void describeHand() {
        for (int i = 0; i < hand.size(); i++) {
            DogCard card = hand.get(i);
            System.out.println("[" + (i + 1) + "] " + card.getDescription());
        }
    }

    public String getHandDescription() {
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            DogCard card = hand.get(i);
            desc.append("[" + (i + 1) + "] " + card.getDescription() + " \n");
        }
        return desc.toString();
    }

    public int playCard(int cardIndex, String attribute) {
        DogCard selectedCard = hand.get(cardIndex);
        String chosenAttributeValue = selectedCard.getAttributeValue(attribute);

        System.out.printf("%s a %s with a %s of %s%n",
                isComputer ? "Opponent plays" : "You play", selectedCard.getName(), attribute, chosenAttributeValue);
        hand.remove(selectedCard);

        return Integer.parseInt(chosenAttributeValue);
    }
}
