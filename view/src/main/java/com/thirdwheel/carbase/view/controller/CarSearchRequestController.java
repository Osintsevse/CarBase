package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.service.carsearchservices.CarSearchRequestService;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import com.thirdwheel.carbase.view.model.CarSearchRequestResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class CarSearchRequestController {
    private final CarSearchRequestService carSearchRequestService;

    @GetMapping(path = "/vendors/{vendorId}/carsearchrequest")
    public ResponseEntity<CarSearchRequestResponse> getByVendorAndNameBeginning(
            @PathVariable(value = "vendorId") @Min(0) Integer vendorId,
            @RequestParam(value = "nameSubstring", required = false) String nameSubstring) {

        List<CarSearchResponseElement> carsByVendorAndNameSubstring =
                carSearchRequestService.getByVendorAndNameBeginning(vendorId, nameSubstring);

        log.debug("Found " + carsByVendorAndNameSubstring.size() + " cars for VendorId \""
                + vendorId + "\" and name beginning \"" + nameSubstring + "\"");

        return ResponseEntity.ok(new CarSearchRequestResponse(carsByVendorAndNameSubstring));
    }
}
