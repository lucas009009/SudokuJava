package com.example.lucas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class GridChoice extends Activity{
    ListView choiceList;
    private String[] grid = new String[]{
      "Grille 1","Grille 2","Grille 3", "Grille 4","Grille 5",
            "Grille 6", "Grille 7","Grille 8","Grille 9", "Grille 10",
            "Grille 11", "Grille 12", "Grille 13", "Grille 14"
    };
    int percent=0;
    private int[] randoms = new int[grid.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_choice);


        ListView listView = this.findViewById(R.id.GridList);
        for (int i=0;i<grid.length;i++) {
            percent = new Random().nextInt(100);
            grid[i] = grid[i] +" - " + percent+"%";
            randoms[i]=percent;
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GridChoice.this,
                android.R.layout.simple_list_item_1, grid){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                if (randoms[position]<50){
                    tv.setTextColor(Color.RED);
                }else{
                    tv.setTextColor(Color.GREEN);
                }
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(GridChoice.this, GameGrid.class);
                startActivity(intent);
            }
        });
    }


}
