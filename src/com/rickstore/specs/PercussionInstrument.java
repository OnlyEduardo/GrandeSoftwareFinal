package com.rickstore.specs;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;

public class PercussionInstrument extends Instrument {

    protected final Wood bodyWood;
    protected final Metal bodyMetal;

    public PercussionInstrument(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood bodyWood, Metal bodyMetal) {
        super(serial, price, tradeMark, "Percussão", instrumentType);
        this.bodyWood = bodyWood;
        this.bodyMetal = bodyMetal;
    }

    public Wood getBodyWood() {
        return bodyWood;
    }

    public Metal getBodyMetal() {
        return bodyMetal;
    }
}
