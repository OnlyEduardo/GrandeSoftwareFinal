package com.rickstore.instruments;

import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.StringInstrument;

public class Guitar extends StringInstrument {

    public Guitar(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood backWood, Wood topWood, int numStrings) {
        super(serial, price, tradeMark, instrumentType, backWood, topWood, numStrings);
    }

    @Override
    public String toString() {
        String iName = instrumentType.equals(Type.Electric) ? "Guitarra" : "Violão";

        return String.format(" %s %s, %d cordas." +
                "\nBackwood: %s" +
                "\nTopwood: %s" +
                "\nPreço: %s", iName, tradeMark, numStrings, backWood, topWood, formatter.format(price));
    }
}
