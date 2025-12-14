package ragmvp.democicd.service;

import org.springframework.data.domain.Page;
import ragmvp.democicd.dto.MediaShortDto;

public interface MediaService {
    Page<MediaShortDto> getAll(int page);
}
