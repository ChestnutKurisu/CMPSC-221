package com.darksideoftherainbow.model;

public enum Genre {
    ROCK("Rock"), CLASSICAL("Classical"), POP("Pop"), JAZZ("Jazz"), JAPANESE_POP("J-Pop"),
    HINDUSTANI_CLASSICAL("Hindustani Classical"), CELTIC("Celtic"), ELECTRONICA("Electronica"),
    DARK_ROCK("Dark Rock"), POP_ROCK("Pop Rock"), DARKWAVE("Darkwave"), NO_GENRE("No Genre");

    private String name;

    private Genre(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
