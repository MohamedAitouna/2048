package com.example.ait.ait2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AitounaMohamed on 26/11/2017.
 */

public final class Ait2048 {

    //initialize our  Square Objects
    //initialize the screen Square
    //Also initialize our List Of All Square Objects
    static void initTheGame(TextView[][] tableOfTextViews,TheSquare[][] mySquares,ArrayList<TheSquare> listOfSquares,int typeOfGame){
        for (int i=0;i<typeOfGame;i++){
            for(int j=0;j<typeOfGame;j++){
                mySquares[i][j]=new TheSquare(i,j);
                tableOfTextViews[i][j].setText("");
                tableOfTextViews[i][j].setBackgroundColor(Color.parseColor("#cdcec8"));
                listOfSquares.add(mySquares[i][j]);
            }
        }
        Random r=new Random();
        int line1=r.nextInt(3);
        int colone1=r.nextInt(3);
        int line2=r.nextInt(3);
        int colone2=r.nextInt(3);
        tableOfTextViews[line1][colone1].setText(""+2);
        tableOfTextViews[line2][colone2].setText(""+2);
        mySquares[line1][colone1].setValue(2);
        mySquares[line2][colone2].setValue(2);
        listOfSquares.remove(mySquares[line1][colone1]);
        listOfSquares.remove(mySquares[line2][colone2]);
    }
    static void lose(MainActivity mainActivity){
        AlertDialog.Builder adb=new AlertDialog.Builder(mainActivity);
        adb.setTitle("GAME OVER :D");
        adb.setMessage("The Score :");
        adb.setPositiveButton("End", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        adb.show();
    }

}

