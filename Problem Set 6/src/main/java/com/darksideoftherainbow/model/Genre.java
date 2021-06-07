package com.darksideoftherainbow.model;

public enum Genre {
    ROCK("Rock", 1), CLASSICAL("Classical", 2), POP("Pop", 3), JAZZ("Jazz", 4), JAPANESE_POP("J-Pop", 5),
    HINDUSTANI_CLASSICAL("Hindustani Classical", 6), CELTIC("Celtic", 7), ELECTRONICA("Electronica", 8),
    DARK_ROCK("Dark Rock", 9), POP_ROCK("Pop Rock", 10), DARKWAVE("Darkwave", 11), NO_GENRE("No Genre", 0);

    private String name;
    private Integer id;

    Genre(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static Genre getGenreById(int id){
        for(Genre genre: values()){
            if(id == genre.id)
                return genre;
        }
        return Genre.NO_GENRE;
    }
}
