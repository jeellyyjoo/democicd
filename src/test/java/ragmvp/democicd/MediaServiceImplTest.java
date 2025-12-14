package ragmvp.democicd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ragmvp.democicd.dto.MediaShortDto;
import ragmvp.democicd.model.MediaEntity;
import ragmvp.democicd.repository.MediaRepository;
import ragmvp.democicd.service.MediaServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MediaServiceImplTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    @Test
    void shouldReturnPageWithFiveElements() {
        // given
        PageRequest pageable = PageRequest.of(0, 5);

        List<MediaEntity> entities = List.of(
                media("Movie 1"),
                media("Movie 2"),
                media("Movie 3"),
                media("Movie 4"),
                media("Movie 5")
        );

        Page<MediaEntity> page = new PageImpl<>(entities, pageable, 10);

        when(mediaRepository.findAll(pageable)).thenReturn(page);

        // when
        Page<MediaShortDto> result = mediaService.getAll(0);

        // then
        assertThat(result.getContent()).hasSize(5);
        assertThat(result.getTotalElements()).isEqualTo(10);
        assertThat(result.getContent().get(0).title()).isEqualTo("Movie 1");
    }

    private MediaEntity media(String title) {
        return MediaEntity.builder()
                .id(UUID.randomUUID())
                .title(title)
                .overview("Overview")
                .releaseDate(LocalDate.now())
                .type("MOVIE")
                .build();
    }
}
