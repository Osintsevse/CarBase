package com.thirdwheel.carbase.dao.models.enums;

import java.util.EnumSet;

public enum CarSearchDomain {
    MODEL(1),
    GENERATION(2),
    CHASSIS(4),
    MODIFICATION(8);

    private int v;

    private CarSearchDomain(int v) {
        this.v = v;
    }

    public static EnumSet<CarSearchDomain> fromInt(int codeId) {
        EnumSet<CarSearchDomain> codesList = EnumSet.noneOf(CarSearchDomain.class);
        for (CarSearchDomain code : values()) {
            if ((codeId & code.intValue()) != 0) {
                codesList.add(code);
            }
        }
        return codesList;
    }

    public static int toInt(EnumSet<CarSearchDomain> codesList) {
        if (codesList == null) {
            throw new IllegalArgumentException("Null EnumSet<VrSuspensionReasonCode>");
        }
        int returnValue = 0;
        for (CarSearchDomain code : codesList) {
            returnValue |= code.intValue();
        }
        return returnValue;
    }

    public int intValue() {
        return v;
    }
}