package com.rickstore.instruments;

import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.StringInstrument;

public class Mandolin extends StringInstrument {

    public Mandolin(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood backWood, Wood topWood) {
        super(serial, price, tradeMark, instrumentType, backWood, topWood, 6);
    }

    @Override
    public String toString() {
        var iType = instrumentType.equals(Type.Electric) ? "elétrico" : "";

        return String.format("""
                Bandolin %s %s.
                Backwood: %s
                Topwood: %s
                Preço: %s""", iType, tradeMark, backWood, topWood, formatter.format(price));
    }
}
