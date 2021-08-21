package com.thirdwheel.carbase.view.controller;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.view.model.ModificationsListResponse;
import com.thirdwheel.carbase.view.model.subelements.ModificationForListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;

@Validated
@Controller
@AllArgsConstructor
@Slf4j
public class ModificationSimilarityController {
    private final ModificationService modificationService;

    @Transactional(readOnly = true)
    @GetMapping(path = "/modifications/{modificationId}/similar")
    public ResponseEntity<List<ModificationForListResponse>> getSimilarByModification(
            @PathVariable(value = "modificationId") @Min(0) Integer modificationId,
            @RequestParam(value = "tag") String[] tags) {

        List<Modification> similar = modificationService.getSimilar(modificationId, tags);

        log.debug("Found " + similar.size() + " cars for ModificationId \""
                + modificationId + "\" and tags \"" + Arrays.stream(tags)
                .reduce("", (subRez, y) -> subRez + (subRez.isEmpty() ? "" : ", ") + y)
                + "\"");

        return ResponseEntity.ok(new ModificationsListResponse(similar).getModificationsResponse());
    }


}
