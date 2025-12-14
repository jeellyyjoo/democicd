package ragmvp.democicd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ragmvp.democicd.dto.MediaShortDto;
import ragmvp.democicd.model.MediaEntity;
import ragmvp.democicd.repository.MediaRepository;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<MediaShortDto> getAll(int page) {
        PageRequest pageable  = PageRequest.of(page, 5);

        return mediaRepository.findAll(pageable)
                .map(this::toDto);
    }

    private MediaShortDto toDto(MediaEntity entity) {
        return new MediaShortDto(
                entity.getTitle(),
                entity.getReleaseDate(),
                entity.getOverview()
        );
    }
}
