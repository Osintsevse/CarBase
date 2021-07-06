package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.service.VendorService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
import com.thirdwheel.carbase.view.model.UniversalEntityForResponse;
import com.thirdwheel.carbase.view.model.VendorRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors")
    public ResponseEntity<List<UniversalEntityForResponse>> getVendors
            (@RequestBody(required = false) VendorRequest vendorRequest) {
        List<Vendor> vendors;
        if (vendorRequest == null) {
            vendors = vendorService.getAllVendors();
        } else {
            vendors = vendorService.getVendorsByFirstLetter(vendorRequest.getNameBeginning());
        }
        return ResponseEntity.ok(new EntitiesListResponse(vendors).getEntities());
    }

}
