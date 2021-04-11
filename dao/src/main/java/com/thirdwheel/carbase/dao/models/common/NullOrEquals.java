package com.thirdwheel.carbase.dao.models.common;

public class NullOrEquals {
    public static <T> boolean compare(T t1, T t2){
        if ((t1 == null) && (t2 == null)){
            return true;
        } else if ((t1 == null) || (t2 == null)){
            return false;
        } else return t1.equals(t2);
    }
}
