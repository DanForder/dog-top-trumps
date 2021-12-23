package com.company;

import java.util.HashMap;

public class DogCard extends Card {
    private final HashMap<String, String> attributes;

    public DogCard(String breed, String size, String rarity, String goodTemper, String cuteness, String rating, String country) {
        // set the name of this card to the breed
        super(breed);

        //populate the attributes
        attributes = new HashMap<String, String>();
        attributes.put("size", size);
        attributes.put("rarity", rarity);
        attributes.put("goodTemper", goodTemper);
        attributes.put("cuteness", cuteness);
        attributes.put("rating", rating);
        attributes.put("country", country);
    }

    public String getDescription() {
        return String.format("Breed: %s. Size: %s. Rarity: %s. Good Temper: %s. Cuteness: %s. Rating: %s. Country: %s.%n",
                getName(),
                attributes.get("size"),
                attributes.get("rarity"),
                attributes.get("goodTemper"),
                attributes.get("cuteness"),
                attributes.get("rating"),
                attributes.get("country"));
    }

    public String getAttributeValue(String attribute) {
        return attributes.get(attribute);
    }

    public String getAttributeChoices() {
        return String.format("[1] Size: %s\n[2] Rarity: %s\n[3] Good Temper: %s\n[4] Cuteness: %s\n[5] Rating: %s%n",
                attributes.get("size"),
                attributes.get("rarity"),
                attributes.get("goodTemper"),
                attributes.get("cuteness"),
                attributes.get("rating"));
    }

}
