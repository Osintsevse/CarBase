package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
import com.thirdwheel.carbase.view.model.UniversalEntityForResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ModelController {
    private final ModelService vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorID}/models")
    public ResponseEntity<List<UniversalEntityForResponse>> getVendors
            (@PathVariable(value="vendorID") String vendorID,@RequestParam(value="nameBeginning",required = false) String nameBeginning ) {
        List<Model> models = vendorService.getModels(Integer.parseInt(vendorID), nameBeginning);
        return ResponseEntity.ok(new EntitiesListResponse(models).getEntities());
    }
}
