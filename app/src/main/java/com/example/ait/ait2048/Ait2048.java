package com.example.ait.ait2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by AitounaMohamed on 26/11/2017.
 */

public final class Ait2048 {
    static Hashtable squaresColors = new Hashtable();


    //initialization our  game Objects
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

        String firstColor=squaresColors.get(2).toString();
        tableOfTextViews[line1][colone1].setBackgroundColor(Color.parseColor(firstColor));
        tableOfTextViews[line2][colone2].setBackgroundColor(Color.parseColor(firstColor));

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
    static void setUpChanges(TextView[][] tableOfTextViews,TheSquare[][] mySquares,ArrayList<TheSquare> listOfSquares,int typeOfGame){
        for(int i=0;i<typeOfGame;i++){
            for(int j=0;j<typeOfGame;j++){
                int value=mySquares[i][j].getValue();
                if(value!=0){
                    tableOfTextViews[i][j].setText(""+value);
                    String chosenColor=squaresColors.get(value).toString();
                    tableOfTextViews[i][j].setBackgroundColor(Color.parseColor(chosenColor));
                    if(listOfSquares.contains(mySquares[i][j])){
                        listOfSquares.remove(mySquares[i][j]);
                    }
                }else{
                    tableOfTextViews[i][j].setText("");
                    tableOfTextViews[i][j].setBackgroundColor(Color.parseColor("#cdcec8"));
                    if(!listOfSquares.contains((mySquares[i][j]))){
                        listOfSquares.add(mySquares[i][j]);
                    }
                }
            }
        }
        int emptySquares=listOfSquares.size();
        if(emptySquares>0){
            ArrayList<Integer> intTab= new ArrayList<Integer>();
            for(int i=0;i<emptySquares;i++){
                if(listOfSquares.get(i).getLine()==0 || listOfSquares.get(i).getColone()==0){
                    intTab.add(i);
                }
            }
            Random r=new Random();
            int i,j;
            if(intTab.size()>0){
                j=r.nextInt(intTab.size());
                i=intTab.get(j);
            }else{
                i=r.nextInt(intTab.size());
            }
            listOfSquares.get(i).setValue(2);
            int line=listOfSquares.get(i).getLine();
            int colon=listOfSquares.get(i).getColone();
            tableOfTextViews[line][colon].setText(""+2);
            tableOfTextViews[line][colon].setBackgroundColor(Color.parseColor("#ef9207"));
            listOfSquares.remove(mySquares[line][colon]);
        }
    }

    static void initColor(){
        squaresColors.put(2   , "#ef9207");
        squaresColors.put(4   , "#f46f02");
        squaresColors.put(8   , "#e1ff05");
        squaresColors.put(16  , "#04ff00");
        squaresColors.put(32  , "#fa0df6");
        squaresColors.put(64  , "#1826f1");
        squaresColors.put(128 , "#9e3a3a");
        squaresColors.put(256 , "#98ac12");
        squaresColors.put(512 , "#2fbffe");
        squaresColors.put(1024, "#5cb601");
        squaresColors.put(2048, "#26bf15");
        squaresColors.put(4096, "#fffffe");
        squaresColors.put(8192, "#92ff16");
    }

    static boolean zeroEqual(TheSquare[][] mySquares,int typeOfGame){
        for(int i=0;i<typeOfGame-1;i++){
            for(int j=0;j<typeOfGame-1;j++){
                if(mySquares[i][j].getValue()==mySquares[i+1][j].getValue() || mySquares[i][j].getValue()==mySquares[i][j+1].getValue() )
                    return false;
            }
        }
        for(int i=0;i<typeOfGame-1;i++){
            if(mySquares[i][typeOfGame-1].getValue()==mySquares[i+1][typeOfGame-1].getValue())
                return false;
        }
        for(int i=0;i<typeOfGame;i++){
            if(mySquares[typeOfGame-1][i].getValue()==mySquares[typeOfGame-1][i+1].getValue())
            return false;
        }
        return  true;
    }
    static boolean notAllZero(int i,int j,TheSquare[][] mySquares ){
        for(int k=0;k<i;k++){
            if(mySquares[k][j].getValue()>0)
                return true;
        }
    return false;
    }

    static void goToDown(int i,int j,TheSquare[][] mySquares){
        if(i>0){
            if(mySquares[i][j].getValue()!=0){
                if(mySquares[i][j].getValue()==mySquares[i-1][j].getValue()){
                    mySquares[i][j].setValue(2*mySquares[i][j].getValue());
                    mySquares[i-1][j].setValue(0);
                    goToDown(i-1,j,mySquares);
                }else{
                    if(mySquares[i-1][j].getValue()==0){
                        if(i-1>0 && notAllZero(i-1,j,mySquares)) {//!!!!!!!
                        for(int k=i-1;k>0;k--){
                            mySquares[k][j].setValue(mySquares[k-1][j].getValue());
                        }
                            mySquares[0][j].setValue(0);
                            goToDown(i,j,mySquares);
                        }

                    }else{
                        goToDown(i-1,j,mySquares);
                    }

                }
            }else{
                goToDown(i-1,j,mySquares);
                for(int k=i;k>0;k--){
                    mySquares[k][j].setValue(mySquares[k-1][j].getValue());
                }
                mySquares[0][j].setValue(0);
            }
        }
    }

}

