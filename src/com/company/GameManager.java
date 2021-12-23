package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    private Player player;
    private Player computer;
    private int maxCardsPerHand;
    private DogDeck deck;
    private final Scanner scanner;
    private boolean isWin;
    private int playerScore;
    private int computerScore;

    public GameManager(String dataFilePath, Scanner scanner) {
        this.deck = new DogDeck(dataFilePath);
        this.maxCardsPerHand = deck.getDeck().size() / 2;
        this.scanner = scanner;
        this.isWin = false;
        this.playerScore = 0;
        this.computerScore = 0;
    }

    //region Getters & Setters
    public Player getComputer() {
        return computer;
    }

    public void setComputer(Player computer) {
        this.computer = computer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMaxCardsPerHand() {
        return maxCardsPerHand;
    }

    public void setMaxCardsPerHand(int maxCardsPerHand) {
        this.maxCardsPerHand = maxCardsPerHand;
    }

    public DogDeck getDeck() {
        return deck;
    }

    public void setDeck(DogDeck deck) {
        this.deck = deck;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }
    //endregion

    public void runGame() {
        System.out.println("Welcome to Dog Top Trumps!\n");

        // create the players' hands of cards
        int cardsPerPlayer = getCardsPerHand();
        createPlayerHands(cardsPerPlayer);

        // initiate the game loop until someone has won
        while (!isWin) {
            takePlayerTurn();
        }
    }

    public int getCardsPerHand() {
        System.out.println("How many cards per player?");

        int cardsPerPlayer;
        while (true) {
            cardsPerPlayer = scanner.nextInt();
            if (cardsPerPlayer >= 1 && cardsPerPlayer <= getMaxCardsPerHand()) {
                break;
            }
            System.out.println("There are " + deck.getDeck().size() + " total cards, please choose a number between 1 and " + getMaxCardsPerHand());
        }
        return cardsPerPlayer;
    }

    public void createPlayerHands(int cardsPerHand) {
        System.out.println("Dealing cards to you and your opponent...");

        // deal two hands from the top deck
        ArrayList<DogCard> playerHand = new ArrayList<>(deck.getDeck().subList(0, cardsPerHand));
        ArrayList<DogCard> computerHand = new ArrayList<>(deck.getDeck().subList(cardsPerHand, cardsPerHand + cardsPerHand));

        // create player and computer objects with these hands
        player = new Player(false, playerHand); // TODO: setPlayer
        computer = new Player(true, computerHand);
    }

    //TODO: should getChosenCardIndex and getChosenAttributeIndex become one generic "get user int input" method?
    private int getChosenCardIndex() {
        int cardChoiceIndex;
        while (true) {
            // index is one fewer than user input
            cardChoiceIndex = scanner.nextInt() - 1;
            if (cardChoiceIndex >= 0 && cardChoiceIndex < player.hand.size()) {
                break;
            }
            System.out.println("Invalid choice! Choose a card by entering its number");
            player.describeHand();
        }
        return cardChoiceIndex;
    }

    private int getChosenAttributeIndex(DogCard chosenCard) {
        int attributeChoiceIndex;
        while (true) {
            attributeChoiceIndex = scanner.nextInt();
            //TODO: 6 is a magic number, get the length of all playable attributes dynamically
            if (attributeChoiceIndex > 0 && attributeChoiceIndex < 6) {
                break;
            }
            System.out.println("Invalid choice! Choose an attribute by entering its number");
            System.out.println(chosenCard.getAttributeChoices());
        }
        return attributeChoiceIndex;
    }

    private void takePlayerTurn() {
        // tell player the cards they can choose from
        System.out.println("\nYour cards...");
        String message = player.getHandDescription();
        System.out.println(message);
        System.out.println("Which card would you like to play?");

        // get player card choice
        int cardIndex = getChosenCardIndex();
        DogCard chosenCard = player.hand.get(cardIndex);

        // tell player their chosen card and attributes to choose from
        System.out.println("You've chosen " + chosenCard.getName() + "!");
        System.out.println(chosenCard.getAttributeChoices());
        System.out.println("Which attribute would you like use?");

        // get player attribute choice
        int attributeChoiceIndex = getChosenAttributeIndex(chosenCard);
        String chosenAttribute = getAttributeNameFromIndex(attributeChoiceIndex);

        // both players play a card using same attribute
        int playerCardScore = player.playCard(cardIndex, chosenAttribute);
        int computerCardScore = computer.playCard(0, chosenAttribute);

        // add to the winning player's score
        handleIncrementScore(playerCardScore, computerCardScore);

        // once the player has no cards left, end the game
        if (player.getHand().size() == 0) {
            endGame();
        }
    }

    private void handleIncrementScore(int playerCardScore, int computerCardScore) {
        if (playerCardScore > computerCardScore) {
            playerScore++;
            System.out.println(TextUtils.GetColoredText("You win this round!", ConsoleColours.GREEN));
            return;
        }

        if (playerCardScore < computerCardScore) {
            computerScore++;
            System.out.println(TextUtils.GetColoredText("Opponent wins this round!", ConsoleColours.RED));
            return;
        }

        System.out.println(TextUtils.GetColoredText("They have the same score!", ConsoleColours.YELLOW));
    }

    // TODO: move this logic to DogDeck/ DogCard/ a new constants file?
    private String getAttributeNameFromIndex(int attributeChoiceIndex) {
        switch (attributeChoiceIndex) {
            case 1:
                return "size";
            case 2:
                return "rarity";
            case 3:
                return "goodTemper";
            case 4:
                return "cuteness";
            case 5:
                return "rating";
            default:
                System.out.println("invalid choice");
                return "error";
        }
    }

    private void endGame() {
        isWin = true;

        System.out.println("\nThe game is over!");
        System.out.println("\nYour score was:         " + playerScore);
        System.out.println("Opponent's score was:   " + computerScore + "\n");

        if (playerScore == computerScore) {
            System.out.println("It was a tie!");
            return;
        }

        if (playerScore > computerScore) {
            System.out.println("Congratulations, you won!");
            return;
        }

        System.out.println("Better luck next time, the computer won!");
    }
}
