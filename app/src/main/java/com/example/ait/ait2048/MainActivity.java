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

import static com.example.ait.ait2048.Ait2048.goToDown;
import static com.example.ait.ait2048.Ait2048.initColor;
import static com.example.ait.ait2048.Ait2048.initTheGame;
import static com.example.ait.ait2048.Ait2048.lose;
import static com.example.ait.ait2048.Ait2048.setUpChanges;
import static com.example.ait.ait2048.Ait2048.zeroEqual;

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

        myTableOfTheGame=(TableLayout)findViewById(R.id.tableLayoutGame);
        scoreTextView=(TextView)findViewById(R.id.score);
        for(int i=0;i<4;i++){
            TableRow row=(TableRow)myTableOfTheGame.getChildAt(i);
            for(int j=0;j<4;j++){
                tableOfTextViews[i][j]=(TextView)row.getChildAt(j);
            }
        }
        replayButton=(Button)findViewById(R.id.replay);
        replayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                listOfSquares.clear();
                                                initTheGame(tableOfTextViews,mySquares,listOfSquares,4);
                                            }
                                        }

        );

        initColor();
        initTheGame(tableOfTextViews,mySquares,listOfSquares,4);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

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
                Log.d("Move Direction", "onTouchEvent: Down->Up");
            }else if(xX<0 && yY<-xX && yY>xX){
                Log.d("Move Direction", "onTouchEvent: Down->Up");
            }else if(yY<0 && xX<-yY && xX>yY){
                Log.d("Move Direction", "onTouchEvent: Down->Up");
                isChang();
            }else if(yY>0 && xX<yY && xX>-yY){
                Log.d("Move Direction", "onTouchEvent: Down->Up");
            }
        }

        return true;
    }
    protected  void  isChang(){
        isChanged=0;
        for (int i=0;i<4;i++){
            if(isChanged==0){
                squaresValue[0]=mySquares[0][i].getValue();
                squaresValue[1]=mySquares[1][i].getValue();
                squaresValue[2]=mySquares[2][i].getValue();
                squaresValue[3]=mySquares[3][i].getValue();
            }
            goToDown(3,i,mySquares);
            if( squaresValue[0]==mySquares[0][i].getValue() ||
                squaresValue[1]==mySquares[1][i].getValue() ||
                 squaresValue[2]==mySquares[2][i].getValue() ||
                squaresValue[3]==mySquares[3][i].getValue()){
                isChanged=1;
            }
            if(isChanged!=0){
                setUpChanges( tableOfTextViews,mySquares,listOfSquares,4);
            }
            if(listOfSquares.size()==0){
                if(zeroEqual(mySquares,4)==true){
                    lose(this);
                }

            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }



}
