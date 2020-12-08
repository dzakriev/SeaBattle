package ru.samsung.itschool.book.cells;

public class Ship {

    private int size;
    private int health;
    private Cell[] ship;
    public Ship(int size) {
        this.size = size;
        this.health = size;
    }
}
