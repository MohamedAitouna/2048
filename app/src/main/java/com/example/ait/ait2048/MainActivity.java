package com.example.ait.ait2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView[][] tableOfTextViews=new TextView[4][4];
    private TextView scoreTextView;
    private Button replayButton;
    private TableLayout myTableOfTheGame;

    private TheSquare[][] mySquares=new TheSquare[4][4];
    private ArrayList<TheSquare> listOfSquares=new ArrayList<TheSquare>();

    private HashMap theColorsOfTheSquares=new HashMap();

    private int[] squaresValue=new int[4];
    private int theScore=0;
    private int isOnPause=0;
    private int isChanged=0;
    private int isDown=0;
    private float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("AITGAMES 4x4");
        setContentView(R.layout.activity_main);


    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
