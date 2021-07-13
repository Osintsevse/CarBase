package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.view.model.EntitiesWithNameListResponse;
import com.thirdwheel.carbase.view.model.EntityWithNameForResponse;
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
@AllArgsConstructor
@Deprecated
public class ModelController {
    private final ModelService vendorService;

    @RequestMapping(method = RequestMethod.GET, path = "/vendors/{vendorId}/models")
    public ResponseEntity<List<EntityWithNameForResponse>> getModels(
            @PathVariable(value = "vendorId") @Pattern(regexp = "[0-9]+") String vendorId,
            @RequestParam(value = "nameBeginning", required = false) String nameBeginning) {
        List<Model> models = vendorService.getByVendor(Integer.parseInt(vendorId), nameBeginning);
        return ResponseEntity.ok(new EntitiesWithNameListResponse(models).getEntities());
    }
}
