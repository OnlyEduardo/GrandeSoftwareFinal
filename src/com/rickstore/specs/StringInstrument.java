package com.rickstore.specs;;

import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;

public class StringInstrument extends Instrument {

    protected final Wood backWood;
    protected final Wood topWood;
    protected final int numStrings;

    public StringInstrument(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood backWood, Wood topWood, int numStrings) {
        super(serial, price, tradeMark, "Corda", instrumentType);
        this.backWood = backWood;
        this.topWood = topWood;
        this.numStrings = numStrings;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }

    public int getNumStrings() {
        return numStrings;
    }
}
