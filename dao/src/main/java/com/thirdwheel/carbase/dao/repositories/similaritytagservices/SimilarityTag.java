package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import com.thirdwheel.carbase.dao.excetions.CarbaseIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SimilarityTag {
    WD_TYPE,
    MAX_POWER,
    MAX_TORQUE,
    CHARGER_TYPE,
    COMPRATION_RATION,
    ENGINE,
    VOLUME,
    CYLINDER_COUNT,
    VALVE_PER_CYLINDER,
    ENGINE_TYPE,
    BORE,
    STROKE,
    TRANSMISSION_TYPE,
    ACCELERATION0100,
    MTR,
    DOOR_COUNT,
    WHEEL_BASE,
    FRONT_TRACK,
    REAR_TRACK,
    BODY_STYLE,
    FRONT_SUSPENSION,
    REAR_SUSPENSION,
    SQUARENESS_COEFFICIENT;

    public static SimilarityTag getByTagString(String tag) {
        Optional<SimilarityTag> any = Arrays.stream(SimilarityTag.values())
                .filter(x -> x.toString().replace("_", "")
                        .equalsIgnoreCase(tag.replace("_", "").trim()))
                .findAny();
        if (any.isEmpty()) {
            throw new CarbaseIllegalArgumentException("Tag does not exist: " + tag);
        }
        return any.get();
    }

    public static List<SimilarityTag> getByTagsStringArray(String[] tags) {
        return Arrays.stream(tags)
                .map(SimilarityTag::getByTagString)
                .collect(Collectors.toList());
    }
}
