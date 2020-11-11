package com.rickstore.instruments;

import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.StringInstrument;

public class Violin extends StringInstrument {

    public Violin(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood backWood, Wood topWood) {
        super(serial, price, tradeMark, instrumentType, backWood, topWood, 4);
    }

    @Override
    public String toString() {
        var iType = instrumentType.equals(Type.Electric) ? "elétrico" : "";

        return String.format("""
                Violino %s %s.
                Backwood: %s
                Topwood: %s
                Preço: %s""", iType, tradeMark, backWood, topWood, formatter.format(price));
    }
}