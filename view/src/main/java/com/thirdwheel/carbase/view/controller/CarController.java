package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.view.model.CarForListResponse;
import com.thirdwheel.carbase.view.model.CarsListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
public class CarController {
    private final ModificationService modificationService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/models/{modelId}/cars")
    public ResponseEntity<List<CarForListResponse>> getCarsByModelAndYear(
            @PathVariable(value = "modelId") @Pattern(regexp = "[0-9]+") String modelId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {
        List<Modification> modifications = modificationService.getByVendor(Integer.parseInt(modelId), year);
        return ResponseEntity.ok(new CarsListResponse(modifications).getEntities());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/models/cars")
    public ResponseEntity<List<CarForListResponse>> getByVendorAndNameBeginning(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning") String nameBeginning) {
        List<Modification> modifications = modificationService.getByVendorAndNameBeginning(Integer.parseInt(vendorId), nameBeginning);
        System.out.println("Found " + modifications.size() + " cars for VendorId " + vendorId + " and name beginning " + nameBeginning);
        return ResponseEntity.ok(new CarsListResponse(modifications).getEntities());
    }
}
