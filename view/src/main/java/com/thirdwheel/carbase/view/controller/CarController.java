package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.CarService;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.view.model.ModificationDetailedResponse;
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
import java.util.TreeSet;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class CarController {
    private final ModificationService modificationService;
    private final CarService carService;

    @GetMapping(path = "/vendors/{vendorId}/cars")
    public ResponseEntity<List<ModificationForListResponse>> getByVendorAndCarsModelNameAndYear(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "carsModelName") String carsModelName,
            @RequestParam(value = "year") @Pattern(regexp = "[0-9]{4}") String year) {
        TreeSet<Modification> byVendorAndCarsModelAndYear =
                carService.getByVendorAndCarsModelAndYear(Integer.parseInt(vendorId), carsModelName, year);
        log.debug("Found " + byVendorAndCarsModelAndYear.size() + " cars for VendorId \""
                + vendorId + "\", car's model name \"" + carsModelName + "\" and year \"" + year + "\"");
        return ResponseEntity.ok(new ModificationsListResponse(byVendorAndCarsModelAndYear).getModificationsResponse());
    }

    @GetMapping(path = "/vendors/cars/{modificationId}/similar")
    public ResponseEntity<List<ModificationForListResponse>> getSimilarByModification(
            @PathVariable(value = "modificationId") @Pattern(regexp = "[0-9]+") String modificationId,
            @RequestParam(value = "tags") String tags) {
        List<Modification> similar = modificationService.getSimilar(Integer.parseInt(modificationId), tags);
        log.debug("Found " + similar.size() + " cars for ModificationId \""
                + modificationId + "\" and tags \"" + tags + "\"");
        return ResponseEntity.ok(new ModificationsListResponse(similar).getModificationsResponse());
    }

    @GetMapping(path = "/vendors/cars/{modificationId}")
    public ResponseEntity<ModificationDetailedResponse> getById(
            @PathVariable(value = "modificationId") @Pattern(regexp = "[0-9]+") String modificationId) {
        Modification byId = modificationService.getById(Integer.parseInt(modificationId));
        return ResponseEntity.ok(new ModificationDetailedResponse(byId));
    }
}
