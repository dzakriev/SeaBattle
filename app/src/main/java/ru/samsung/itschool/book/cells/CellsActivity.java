package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import java.util.Random;

public class CellsActivity extends Activity implements OnClickListener{
    public static final Random random = new Random();
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    Map playerMap;
    Map botMap;
    Button[][] playerCells;
    Button[][] botCells;
//    private boolean isGameOver;
    private boolean isPlayerMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();
        generate();

    }

    void generate() {
//        isGameOver = false;
        isPlayerMove = true;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;
        int x = getX(tappedCell);
        int y = getY(tappedCell);
        if (isPlayerMove){
            int status = botMap.getCell(x,y).getStatus();
            if(status == 0){
                botCells[y][x].setBackgroundColor(Color.BLUE);
                botMap.getCell(x,y).setStatus(1);
                isPlayerMove = false;
                enemyTurn();
                return;
            }
            if(status == 2){
                botCells[y][x].setBackgroundColor(Color.RED);
                botMap.getCell(x,y).setStatus(3);
            }
        }
    }

    private void enemyTurn() {
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        if(playerMap.getCell(x, y).getStatus() == 0 || playerMap.getCell(x, y).getStatus() == 2) {
            //status = 0 - empty cell; 1 - attacked empty cell; 2 - ship; 3 - attacked ship
            int status = playerMap.getCell(x,y).getStatus();
            if(status == 0) {
                playerCells[y][x].setBackgroundColor(Color.BLUE);
                isPlayerMove = true;
                playerMap.getCell(x,y).setStatus(1);
                return;
            }
            else {
                playerMap.getCell(x,y).setStatus(3);
                playerCells[y][x].setBackgroundColor(Color.RED);
            }
            playerMap.getCell(x, y).hit();
        }
        else enemyTurn();
    }
    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        playerMap = new Map(HEIGHT,WIDTH, true);
        playerCells = new Button[playerMap.getSizeY()][playerMap.getSizeX()];
        GridLayout cellsLayout = findViewById(R.id.PlayerMap);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                playerCells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                playerCells[i][j].setTag(i + "," + j);
                cellsLayout.addView(playerCells[i][j]);
            }
        botMap = new Map(HEIGHT,WIDTH, false);
        botCells = new Button[HEIGHT][WIDTH];
        cellsLayout = findViewById(R.id.BotMap);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                botCells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                botCells[i][j].setOnClickListener(this);
                botCells[i][j].setTag(i + "," + j);
                cellsLayout.addView(botCells[i][j]);
            }
    }

}