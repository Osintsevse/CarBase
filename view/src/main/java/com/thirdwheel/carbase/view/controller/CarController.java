package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.model.ModelOfCar;
import com.thirdwheel.carbase.service.modelofcarservices.ModelOfCarService;
import com.thirdwheel.carbase.view.model.CarForListResponse;
import com.thirdwheel.carbase.view.model.CarsListResponse;
import com.thirdwheel.carbase.view.model.CarsModelsListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class CarController {
    private final ModificationService modificationService;
    private final ModelOfCarService modelOfCarService;

    @Deprecated
    @RequestMapping(method = RequestMethod.GET, path = "/vendors/models/{modelId}/cars")
    public ResponseEntity<List<CarForListResponse>> getCarsByModelAndYear(
            @PathVariable(value = "modelId") @Pattern(regexp = "[0-9]+") String modelId,
            @RequestParam(value = "year", required = false) @Pattern(regexp = "[0-9]{4}") String year) {
        List<Modification> modifications = modificationService.getByVendor(Integer.parseInt(modelId), year);
        return ResponseEntity.ok(new CarsListResponse(modifications).getEntities());
    }

    @Deprecated
    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/models/cars")
    public ResponseEntity<List<CarForListResponse>> getModificationByVendorAndNameBeginning(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning") String nameBeginning) {
        List<Modification> modifications =
                modificationService.getByVendorAndNameBeginning(Integer.parseInt(vendorId), nameBeginning);
        log.info("Found " + modifications.size() + " cars for VendorId \""
                + vendorId + "\" and name beginning \"" + nameBeginning+"\"");
        return ResponseEntity.ok(new CarsListResponse(modifications).getEntities());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/carsmodels")
    public ResponseEntity<List<String>> getByVendorAndNameBeginning(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning", required = false) String nameBeginning) {
        Map<String, ModelOfCar> byVendorAndText =
                modelOfCarService.getByVendorAndText(Integer.parseInt(vendorId), nameBeginning);
        log.info("Found " + byVendorAndText.size() + " cars for VendorId \""
                + vendorId + "\" and name beginning \"" + nameBeginning+"\"");
        return ResponseEntity.ok(new CarsModelsListResponse(byVendorAndText).getCarsModels());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/cars")
    public ResponseEntity<List<String>> getByVendorsNameBeginningAndYear(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "carsModelName") String carsModelName,
            @RequestParam(value = "year", required = false) String year) {
        Map<String, ModelOfCar> byVendorAndText =
                modelOfCarService.getByVendorAndText(Integer.parseInt(vendorId), carsModelName);
        log.info("Found " + byVendorAndText.size() + " cars for VendorId \""
                + vendorId + "\" and name beginning \"" + carsModelName+"\"");
        return ResponseEntity.ok(new CarsModelsListResponse(byVendorAndText).getCarsModels());
    }
}
