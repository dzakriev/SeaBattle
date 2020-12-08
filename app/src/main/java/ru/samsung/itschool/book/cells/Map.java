package ru.samsung.itschool.book.cells;

public class Map {
    final private int mapHeight;
    final private int mapWidth;



    private Cell[][] map;
    public Map(int height, int width, boolean belongsToPlayer) {
        mapHeight = height;
        mapWidth = width;

        map = new Cell[height][width];

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                map[i][j] = new Cell(j, i, 0, belongsToPlayer);
            }
        }

    }
    public Cell getCell(int x, int y) {
        return map[y][x];
    }
    public int getSizeX(){ return mapWidth;}
    public int getSizeY(){ return mapHeight;}
}
