package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.service.CarService;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.view.model.ModificationForListResponse;
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
    private final ModificationService modificationService;
    private final CarService carService;

    @GetMapping(path = "/models/{modelId}/modificationsByModelName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModelName(
            @PathVariable(value = "modelId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        return null;
    }


    @GetMapping(path = "/generations/{generationId}/modificationsByGenerationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByGenerationName(
            @PathVariable(value = "generationId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        return null;
    }

    @GetMapping(path = "/chassises/{chassisId}/modificationsByChassisName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByChassisName(
            @PathVariable(value = "chassisId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        return null;
    }

    @GetMapping(path = "/modifications/{modificationId}/modificationsByModificationName")
    public ResponseEntity<List<ModificationForListResponse>> getModificationsByModificationName(
            @PathVariable(value = "modificationId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {

        return null;
    }
}
