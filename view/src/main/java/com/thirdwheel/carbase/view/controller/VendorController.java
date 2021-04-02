package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @RequestMapping(method = RequestMethod.POST, path = "/createvendor")
    public ResponseEntity<Boolean> saveVendor(@RequestBody String name) {
        vendorService.save(name);
        return ResponseEntity.ok(true);
    }
}
