package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.service.VendorService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
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

    @RequestMapping(method = RequestMethod.GET, path = "/getallvendors")
    public ResponseEntity<EntitiesListResponse> getAllVendors() {
        List<Vendor> allVendors = vendorService.getAllVendors();
        return ResponseEntity.ok(new EntitiesListResponse(allVendors));
    }
}
