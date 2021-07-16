package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.service.carsmodelservices.CarsModelService;
import com.thirdwheel.carbase.service.model.CarsModel;
import com.thirdwheel.carbase.view.model.CarsModelsAsStringListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class CarsModelController {
    private final CarsModelService carsModelService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/carsmodels")
    public ResponseEntity<List<String>> getByVendorAndNameBeginning(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning", required = false) String nameBeginning) {
        Map<String, CarsModel> byVendorAndText =
                carsModelService.getByVendorAndNameBeginning(Integer.parseInt(vendorId), nameBeginning);
        log.info("Found " + byVendorAndText.size() + " cars for VendorId \""
                + vendorId + "\" and name beginning \"" + nameBeginning + "\"");
        return ResponseEntity.ok(new CarsModelsAsStringListResponse(byVendorAndText).getCarsModels());
    }
}
