package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.view.model.subelements.ModificationForListResponse;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ModificationsListResponse {
    private final List<ModificationForListResponse> modificationsResponse;

    public ModificationsListResponse(Collection<Modification> modifications) {
        modificationsResponse =
                modifications.stream().map(ModificationForListResponse::new).collect(Collectors.toList());
    }
}
