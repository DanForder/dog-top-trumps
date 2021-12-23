package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/com/company/data/top-trumps";
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager(filePath, scanner);
        
        gameManager.runGame();
    }
}
