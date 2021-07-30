package com.thirdwheel.carbase.dao.models.enums;

import java.util.EnumSet;

public enum CarSearchDomain {
    MODEL(1),
    GENERATION(2),
    CHASSIS(4),
    MODIFICATION(8);

    private final int bitmask;

    CarSearchDomain(int bitmask) {
        this.bitmask = bitmask;
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

    public static int toInt(EnumSet<CarSearchDomain> carSearchDomains) {
        if (carSearchDomains == null) {
            throw new IllegalArgumentException("Null carSearchDomains");
        }
        int returnValue = 0;
        for (CarSearchDomain code : carSearchDomains) {
            returnValue |= code.intValue();
        }
        return returnValue;
    }

    public int intValue() {
        return bitmask;
    }
}