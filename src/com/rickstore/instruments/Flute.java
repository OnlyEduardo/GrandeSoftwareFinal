package com.rickstore.instruments;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.specs.WindInstrument;

public class Flute extends WindInstrument {

    public Flute(long serial, float price, TradeMark tradeMark, Type instrumentType, Wood woodPart, Metal metalPart, int numRoles) {
        super(serial, price, tradeMark, instrumentType, woodPart, metalPart, numRoles);
    }

    @Override
    public String toString() {
        String material = (woodPart.equals(Wood.Nenhum) ? metalPart.toString() : woodPart.toString());

        return String.format("Flauta %s, %d entradas." +
                "\nMaterial: %s" +
                "\nPreço: R$%s", tradeMark, numRoles, material, formatter.format(price));
    }
}
