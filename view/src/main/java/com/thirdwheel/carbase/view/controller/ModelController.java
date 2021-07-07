package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.view.model.EntitiesListResponse;
import com.thirdwheel.carbase.view.model.UniversalEntityForResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
public class ModelController {
    private final ModelService vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/models")
    public ResponseEntity<List<UniversalEntityForResponse>> getVendors(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning", required = false) String nameBeginning) {
        List<Model> models = vendorService.getByVendor(Integer.parseInt(vendorId), nameBeginning);
        return ResponseEntity.ok(new EntitiesListResponse(models).getEntities());
    }
}
