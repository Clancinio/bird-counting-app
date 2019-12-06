package com.example.birdspotting;

public class Bird {

    // Variables
    private String scientificName;
    private String commonName;
    private String description;
    private String numberImage;

    // Defaults Constructor
    public Bird() {

    }

    // Constructor with parameters
    public Bird(String scientificName, String commonName, String description, String numberImage) {
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.description = description;
        this.numberImage = numberImage;
    }

    // Getters
    public String getScientificName() {
        return scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getDescription() {
        return description;
    }

    public String getNumberImage() {
        return numberImage;
    }


    // Setters
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberImage(String numberImage) {
        this.numberImage = numberImage;
    }




}

