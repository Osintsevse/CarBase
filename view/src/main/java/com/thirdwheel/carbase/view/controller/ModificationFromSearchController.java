package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationFromSearchService;
import com.thirdwheel.carbase.view.model.ModificationForListResponse;
import com.thirdwheel.carbase.view.model.ModificationsListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class ModificationFromSearchController {
    private final ModificationFromSearchService modificationFromSearchService;

    @GetMapping(path = "/models/{modelId}/modificationsByModelName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModelNameAndYear(
            @PathVariable(value = "modelId") @Pattern(regexp = "[0-9]+") String modelId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        List<Modification> modifications = modificationFromSearchService
                .getByModelNameAndYear(Integer.parseInt(modelId), year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }


    @GetMapping(path = "/generations/{generationId}/modificationsByGenerationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByGenerationNameAndYear(
            @PathVariable(value = "generationId") @Pattern(regexp = "[0-9]+") String generationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        List<Modification> modifications = modificationFromSearchService
                .getByGenerationNameAndYear(Integer.parseInt(generationId), year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }

    @GetMapping(path = "/chassises/{chassisId}/modificationsByChassisName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByChassisNameAndYear(
            @PathVariable(value = "chassisId") @Pattern(regexp = "[0-9]+") String chassisId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        List<Modification> modifications = modificationFromSearchService
                .getByChassisNameAndYear(Integer.parseInt(chassisId), year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }

    @GetMapping(path = "/modifications/{modificationId}/modificationsByModificationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModificationNameAndYear(
            @PathVariable(value = "modificationId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        List<Modification> modifications = modificationFromSearchService
                .getByModificationNameAndYear(Integer.parseInt(modificationId), year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }
}
