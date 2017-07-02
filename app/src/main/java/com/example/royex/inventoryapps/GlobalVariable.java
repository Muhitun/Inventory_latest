package com.example.royex.inventoryapps;

/**
 * Created by CWS on 6/22/2017.
 */

public class GlobalVariable {

    static int value=0;

    public GlobalVariable(){

    }
    public void setValue(int a){
        this.value = a;
    }

    public int getValue(){
        return this.value;
    }
}
