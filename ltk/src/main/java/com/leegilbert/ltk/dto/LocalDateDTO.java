package com.leegilbert.ltk.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class LocalDateDTO {
    @NonNull @Getter
    private Long epoch;

    @NonNull @Getter
    private LocalDate dt;
}
