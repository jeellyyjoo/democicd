package ragmvp.democicd.dto;

import java.time.LocalDate;

public record MediaShortDto(
        String title,
        LocalDate releaseDate,
        String overview
) {
}
