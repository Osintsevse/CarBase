package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.service.GeneralService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
import com.thirdwheel.carbase.view.model.UniversalEntityForResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class VendorController {
    private final GeneralService<Vendor> vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors")
    public ResponseEntity<List<UniversalEntityForResponse>> getVendors
            (@RequestParam(value="nameBeginning",required = false) String nameBeginning) {
        List<Vendor> vendors;
        if (nameBeginning == null) {
            vendors = vendorService.getAll();
        } else {
            vendors = vendorService.getByNameBeginnig(nameBeginning);
        }
        return ResponseEntity.ok(new EntitiesListResponse(vendors).getEntities());
    }

}
