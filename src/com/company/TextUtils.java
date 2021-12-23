package com.company;

public class TextUtils {
    private static final String ANSI_RESET = "\u001B[0m";

    public static String GetColoredText(String text, ConsoleColours color) {
        return color.getLabel() + text + ANSI_RESET;
    }
}
