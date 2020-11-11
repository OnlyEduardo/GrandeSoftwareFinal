package com.rickstore.specs;

import com.rickstore.enumerators.*;

public class PercussionInstrument extends Instrument {

    protected final Wood bodyWood;
    protected final Metal bodyMetal;

    public PercussionInstrument(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood bodyWood, Metal bodyMetal) {
        super(serial, price, tradeMark, Family.Percurssao, instrumentType);
        this.bodyWood = bodyWood;
        this.bodyMetal = bodyMetal;
    }

    public Wood getBodyWood() {
        return bodyWood;
    }

    public Metal getBodyMetal() {
        return bodyMetal;
    }

    public boolean matchParts(Wood wood, Metal metal){
        if(wood.equals(Wood.Nenhum))
            wood = getBodyWood();
        if(metal.equals(Metal.Nenhum))
            metal = getBodyMetal();

        return getBodyWood().equals(wood) && getBodyMetal().equals(metal);
    }
}
