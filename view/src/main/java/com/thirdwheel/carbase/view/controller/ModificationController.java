package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.view.model.ModificationDetailedResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Min;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class ModificationController {
    private final ModificationService modificationService;

    @Transactional(readOnly = true)
    @GetMapping(path = "/modifications/{modificationId}")
    public ResponseEntity<ModificationDetailedResponse> getById(
            @PathVariable(value = "modificationId") @Min(0) Integer modificationId) {

        Modification byId = modificationService.getById(modificationId);

        return ResponseEntity.ok(new ModificationDetailedResponse(byId));
    }
}
