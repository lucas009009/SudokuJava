package com.example.lucas.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Chronometer;

public class GameGrid extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_grid);
        Chronometer chronometer = findViewById(R.id.Chrono);
        chronometer.start();
    }
}
