package com.thirdwheel.carbase.dao.models.common;

public class UnknownOrEquals {
    public static <T extends Enum<T>> boolean compare(T t1, T t2) {
        if ((t1.name().equals("Unknown")) || (t2.name().equals("Unknown"))) {
            return true;
        } else return t1.equals(t2);
    }
}
