package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.service.VendorService;
import com.thirdwheel.carbase.view.model.EntitiesWithNameListResponse;
import com.thirdwheel.carbase.view.model.EntityWithNameForResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @GetMapping(path = "/vendors")
    public ResponseEntity<List<EntityWithNameForResponse>> getVendors
            (@RequestParam(value = "nameBeginning", required = false) String nameBeginning) {
        List<Vendor> vendors = vendorService.getByNameBeginning(nameBeginning);
        return ResponseEntity.ok(new EntitiesWithNameListResponse(vendors).getEntities());
    }

}
