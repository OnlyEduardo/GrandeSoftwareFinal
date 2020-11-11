package com.rickstore.specs;

import com.rickstore.enumerators.*;

public class WindInstrument extends Instrument {

    protected final Wood woodPart;
    protected final Metal metalPart;
    protected final int numRoles;

    public WindInstrument(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood woodPart, Metal metalPart, int numRoles) {
        super(serial, price, tradeMark, Family.Wind, instrumentType);
        this.woodPart = woodPart;
        this.metalPart = metalPart;
        this.numRoles = numRoles;
    }

    public Wood getWoodPart() {
        return woodPart;
    }

    public Metal getMetalPart() {
        return metalPart;
    }

    public int getNumRoles() {
        return numRoles;
    }

    public boolean matchParts(Wood wPart, Metal mPart){
        if(woodPart.equals(Wood.Nenhum)){
            return metalPart.equals(mPart);
        } else if(metalPart.equals(Metal.NONE)){
            return  woodPart.equals(wPart);
        }

        return false;
    }
}
