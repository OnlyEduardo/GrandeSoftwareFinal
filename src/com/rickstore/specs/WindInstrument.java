package com.rickstore.specs;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;

public class WindInstrument extends Instrument {

    protected final Wood woodPart;
    protected final Metal metalPart;
    protected final int numRoles;

    public WindInstrument(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood woodPart, Metal metalPart, int numRoles) {
        super(serial, price, tradeMark, "Sopro", instrumentType);
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
}
