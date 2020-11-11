package com.rickstore.enumerators;

public enum Family {
    NONE, Percurssion, String, Wind;

    @Override
    public String toString() {
        switch (this){
            case Percurssion: return "Percurssão";
            case String: return "Corda";
            case Wind: return "Sopro";
            case NONE: return "Nenhum";
        }

        return super.toString();
    }
}
