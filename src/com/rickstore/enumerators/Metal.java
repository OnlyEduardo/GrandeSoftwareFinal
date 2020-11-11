package com.rickstore.enumerators;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Metal {
    Nenhum,
    Latao,
    Bronze,
    Prata,
    Ouro,
    ;

    private static final List<Metal> VALUES = Arrays.asList(Latao,
            Bronze,
            Prata,
            Ouro);
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Metal getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}