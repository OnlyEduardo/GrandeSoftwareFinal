package com.rickstore.enumerators;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Type {
    Nenhum,
    Acoustic,
    Electric

    ;

    private static final List<Type> VALUES = Arrays.asList(Acoustic,
            Electric);
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Type getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
