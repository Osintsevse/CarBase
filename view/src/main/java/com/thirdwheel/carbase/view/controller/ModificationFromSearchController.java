package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationFromSearchService;
import com.thirdwheel.carbase.view.model.ModificationsListResponse;
import com.thirdwheel.carbase.view.model.subelements.ModificationForListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class ModificationFromSearchController {
    private final ModificationFromSearchService modificationFromSearchService;

    @GetMapping(path = "/models/{modelId}/modificationsByModelName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModelNameAndYear(
            @PathVariable(value = "modelId") @Min(0) Integer modelId,
            @RequestParam(value = "year", required = false) @Min(1000) @Max(9999) Integer year) {

        List<Modification> modifications = modificationFromSearchService
                .getByModelNameAndYear(modelId, year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }


    @Transactional(readOnly = true)
    @GetMapping(path = "/generations/{generationId}/modificationsByGenerationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByGenerationNameAndYear(
            @PathVariable(value = "generationId") @Min(0) Integer generationId,
            @RequestParam(value = "year", required = false) @Min(1000) @Max(9999) Integer year) {

        List<Modification> modifications = modificationFromSearchService
                .getByGenerationNameAndYear(generationId, year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/chassises/{chassisId}/modificationsByChassisName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByChassisNameAndYear(
            @PathVariable(value = "chassisId") @Min(0) Integer chassisId,
            @RequestParam(value = "year", required = false) @Min(1000) @Max(9999) Integer year) {

        List<Modification> modifications = modificationFromSearchService
                .getByChassisNameAndYear(chassisId, year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/modifications/{modificationId}/modificationsByModificationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModificationNameAndYear(
            @PathVariable(value = "modificationId") @Min(0) Integer modificationId,
            @RequestParam(value = "year", required = false) @Min(1000) @Max(9999) Integer year) {

        List<Modification> modifications = modificationFromSearchService
                .getByModificationNameAndYear(modificationId, year);

        List<ModificationForListResponse> modificationsResponse =
                new ModificationsListResponse(modifications).getModificationsResponse();

        return ResponseEntity.ok(modificationsResponse);
    }
}

