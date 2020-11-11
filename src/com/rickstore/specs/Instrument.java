package com.rickstore.specs;

import com.rickstore.enumerators.Family;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Instrument {

    protected final NumberFormat formatter = new DecimalFormat("###,###,###.##");

    protected final long serial;
    protected final float price;
    protected final TradeMark tradeMark;
    protected final Family family;
    protected final Type instrumentType;

    public Instrument(long serial, float price, TradeMark tradeMark, Family family, Type instrumentType) {
        this.serial = serial;
        this.price = price;
        this.tradeMark = tradeMark;
        this.family = family;
        this.instrumentType = instrumentType;
    }

    public long getSerial() {
        return serial;
    }

    public float getPrice() {
        return price;
    }

    public TradeMark getTradeMark() {
        return tradeMark;
    }

    public Family getFamily() {
        return family;
    }

    public Type getInstrumentType() {
        return instrumentType;
    }

    public boolean matchRangeValue(float minimum, float maximum){
        return price >= minimum && price <= maximum;
    }

    public boolean matchFamily(Family family){
        return this.family == family;
    }

    public boolean matchTradeMark(TradeMark tradeMark){
        return this.tradeMark.equals(tradeMark);
    }
}
