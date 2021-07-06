package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
import com.thirdwheel.carbase.view.model.ModelRequest;
import com.thirdwheel.carbase.view.model.UniversalEntityForResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class ModelController {
    private final ModelService vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/models")
    public ResponseEntity<List<UniversalEntityForResponse>> getVendors
            (@RequestBody ModelRequest modelRequest) {
        List<Model> models = vendorService.getModels(modelRequest.getVendorId(),modelRequest.getNameBeginning());
        return ResponseEntity.ok(new EntitiesListResponse(models).getEntities());
    }
}
