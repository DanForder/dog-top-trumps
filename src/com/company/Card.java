package com.company;

import java.util.UUID;

public class Card {
    private String name;
    private final UUID id;

    public Card(String name) {
        this.name = name;
        this.id = java.util.UUID.randomUUID();
    }

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
    //endregion

    public String GetDescription() {
        return "Card name: " + name + "\nCard id: " + this.id;
    }
}
