package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class ModificationsListResponse {
    private final List<ModificationForListResponse> modificationsResponse;

    public ModificationsListResponse(Set<Modification> modifications) {
        modificationsResponse = new ArrayList<>();
        modifications.forEach(x -> {
            modificationsResponse.add(new ModificationForListResponse(x));
        });
    }

    public ModificationsListResponse(List<Modification> modifications) {
        modificationsResponse = new ArrayList<>();
        modifications.forEach(x -> {
            modificationsResponse.add(new ModificationForListResponse(x));
        });
    }
}
