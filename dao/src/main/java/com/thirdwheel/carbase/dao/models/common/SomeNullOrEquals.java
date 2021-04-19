package com.thirdwheel.carbase.dao.models.common;

public class SomeNullOrEquals {
    public static <T> boolean compare(T t1, T t2){
        if ((t1 == null) || (t2 == null)){
            return true;
        } else return t1.equals(t2);
    }
}
