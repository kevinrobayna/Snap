package com.kevinrobayna.snap;

public record Card(String suit, String value) {
    @Override
    public String toString() {
        return value + " of " + suit;
    }
}