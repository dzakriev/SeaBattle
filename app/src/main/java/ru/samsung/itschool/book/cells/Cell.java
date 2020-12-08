package ru.samsung.itschool.book.cells;

public class Cell {
    private int x;
    private int y;
    private boolean belongsToPlayer;
    private int status;
    int SEA_CELL = 0;// океан, не была атакована
    int EMPTY_CELL = 1;// атакована, пусто
    int SHIP_CELL = 2; // стоит корабль, не атакована
    int SHIP_HIT_CELL = 3;// стоит корабль, атакована
    public Cell(int x, int y, int status, boolean belongsToPlayer) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.belongsToPlayer = belongsToPlayer;
    }

    public int getStatus() {
        return status;
    }

    public void hit() {
        if (status == SHIP_CELL) status = SHIP_HIT_CELL;
        else if (status == SEA_CELL) status = EMPTY_CELL;
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isBelongsToPlayer(){ return belongsToPlayer; }
    public void setStatus(int status) {
        this.status = status;
    }
}
