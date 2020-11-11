package com.rickstore.instruments;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.WindInstrument;

public class Saxophone extends WindInstrument {

    public Saxophone(long serial, float price, TradeMark tradeMark, Type instrumentType, Metal metalPart) {
        super(serial, price, tradeMark, instrumentType, Wood.NONE, metalPart, 8);
    }

    @Override
    public String toString() {
        return String.format("Saxofone %s, %d entradas." +
                        "\nMaterial: %s" +
                        "\nPreço: %s", tradeMark, numRoles, metalPart, formatter.format(price));
    }
}
