package com.thirdwheel.carbase.dao.models.enums;

import java.util.EnumSet;

public enum SearchFieldForVendor {
    SEARCH_IN_MODELS(1),
    SEARCH_IN_GENERATIONS(2),
    SEARCH_IN_CHASSIS(4),
    SEARCH_IN_MODIFICATIONS(8);

    private int v;

    private SearchFieldForVendor(int v) {
        this.v = v;
    }

    public static EnumSet<SearchFieldForVendor> fromInt(int codeId) {
        EnumSet<SearchFieldForVendor> codesList = EnumSet.noneOf(SearchFieldForVendor.class);
        for (SearchFieldForVendor code : values()) {
            if ((codeId & code.intValue()) != 0) {
                codesList.add(code);
            }
        }
        return codesList;
    }

    public static int toInt(EnumSet<SearchFieldForVendor> codesList) {
        if (codesList == null) {
            throw new IllegalArgumentException("Null EnumSet<VrSuspensionReasonCode>");
        }
        int returnValue = 0;
        for (SearchFieldForVendor code : codesList) {
            returnValue |= code.intValue();
        }
        return returnValue;
    }

    public int intValue() {
        return v;
    }
}