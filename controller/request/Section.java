package com.etrivium.backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    private String type;
    private List<SectionDetail> keys;

    public String getSectionTitle() {
        Optional<SectionDetail> titleDetail = this.getKeys().stream()
                .filter(detail -> detail.getKey().equals("titulo"))
                .findFirst();
        return titleDetail.map(SectionDetail::getValue).orElse(this.getType());
    }
}
