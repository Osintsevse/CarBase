package com.thirdwheel.carbase.dao.models.common;

public class SpecificEquals {

    public static <T> boolean someNullOrEquals(T t1, T t2) {
        if ((t1 == null) || (t2 == null)) {
            return true;
        } else return t1.equals(t2);
    }

    public static <T extends Enum<T>> boolean unknownOrEquals(T t1, T t2) {
        if ((t1.name().equals("Unknown")) || (t2.name().equals("Unknown"))) {
            return true;
        } else return t1.equals(t2);
    }
}
