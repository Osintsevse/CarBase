package com.thirdwheel.carbase.dao.repositories.similaritytagservices;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class SimilarityTagFiltersService {
    private Map<SimilarityTag, ? extends SimilarityTagFilter> tagFilterMap;

    @Autowired
    private void setTagFilterMap(List<SimilarityTagFilter> tagFilterList) {
        tagFilterMap = tagFilterList.stream().collect(Collectors.toMap(SimilarityTagFilter::getSupportedTag, x -> x));
    }
}
