package com.company;

public enum ConsoleColours {
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m");

    private final String label;

    ConsoleColours(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
