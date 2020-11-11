package com.rickstore.instruments;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.PercussionInstrument;

public class Battery extends PercussionInstrument {

    public Battery(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood bodyWood, Metal bodyMetal) {
        super(serial, price, tradeMark, instrumentType, bodyWood, bodyMetal);
    }

    @Override
    public String toString() {
        return "Flauta " + tradeMark + "." +
                "\nbodyWood: " + bodyWood +
                "\nbodyMetal: " + bodyMetal +
                "\nPreço: " + formatter.format(price);
    }

}
