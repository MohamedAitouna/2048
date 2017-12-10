package com.example.ait.ait2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.ait.ait2048.Ait2048.initColor;
import static com.example.ait.ait2048.Ait2048.initTheGame;
import static com.example.ait.ait2048.Ait2048.isChangRL;
import static com.example.ait.ait2048.Ait2048.isChangTD;

public class Main2Activity extends AppCompatActivity {

    private TextView[][] tableOfTextViews=new TextView[5][5];
    private TextView scoreTextView;
    private Button replayButton;
    private TableLayout myTableOfTheGame;

    private TheSquare[][] mySquares=new TheSquare[5][5];
    private ArrayList<TheSquare> listOfSquares=new ArrayList<TheSquare>();

    private HashMap theColorsOfTheSquares=new HashMap();


    private int theScore=0;
    private int isOnPause=0;
    private int isChanged=0;
    private int isDown=0;
    private float x,y;
    private int  typeOfGame=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("AITGAMES 5x5");
        setContentView(R.layout.activity_main2);

        myTableOfTheGame=(TableLayout)findViewById(R.id.tableLayoutGame);
        scoreTextView=(TextView)findViewById(R.id.score);
        for(int i=0;i<typeOfGame;i++){
            TableRow row=(TableRow)myTableOfTheGame.getChildAt(i);
            for(int j=0;j<typeOfGame;j++){
                tableOfTextViews[i][j]=(TextView)row.getChildAt(j);
            }
        }
        replayButton=(Button)findViewById(R.id.replay);
        replayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                listOfSquares.clear();
                                                initTheGame(tableOfTextViews,mySquares,listOfSquares,typeOfGame);
                                            }
                                        }

        );

        initColor();
        initTheGame(tableOfTextViews,mySquares,listOfSquares,typeOfGame);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN && isDown==0){
            isDown=1;
            x=event.getX();
            y=event.getY();
        }else if(event.getAction()==MotionEvent.ACTION_MOVE && isDown==1){
            isDown=2;
        }else if(event.getAction()==MotionEvent.ACTION_UP && isDown==2){
            isDown=0;
            float xX=x-event.getX();
            float yY=y-event.getY();
            Log.i("Move","Good "+xX+" , "+yY);
            if(xX>0 && yY<xX && yY>-xX){
                Log.d("Move Direction", "onTouchEvent: RiGHT->Left");
                isChangRL(this,tableOfTextViews,mySquares,listOfSquares,typeOfGame,1);
            }else if(xX<0 && yY<-xX && yY>xX){
                Log.d("Move Direction", "onTouchEvent: Left->Right");
                isChangRL(this,tableOfTextViews,mySquares,listOfSquares,typeOfGame,-1);
            }else if(yY<0 && xX<-yY && xX>yY){
                Log.d("Move Direction", "onTouchEvent: Down->Up");
                //isChang();
                isChangTD(this,tableOfTextViews,mySquares,listOfSquares,typeOfGame,1);
            }else if(yY>0 && xX<yY && xX>-yY){
                Log.d("Move Direction", "onTouchEvent: Up->Down");
                isChangTD(this,tableOfTextViews,mySquares,listOfSquares,typeOfGame,-1);
            }
        }

        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
