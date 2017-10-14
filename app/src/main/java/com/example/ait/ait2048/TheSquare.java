package com.example.ait.ait2048;

/**
 * Created by AitounaMohamed on 14/10/2017.
 */

public class TheSquare {
    private int line;
    private int colone;
    private int value;
    public TheSquare(int line,int colone){
        this.line=line;
        this.colone=colone;
        this.value=0;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setLine(int line){
        this.line=line;
    }
    public void setColone(int colone){
        this.colone=colone;
    }
    public int getValue(){
        return  this.value;
    }
    public int getLine(){
        return this.line;
    }
    public int getColone(){
        return  this.colone;
    }
}
