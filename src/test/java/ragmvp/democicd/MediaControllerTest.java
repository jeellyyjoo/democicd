package ragmvp.democicd;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import ragmvp.democicd.dto.MediaShortDto;
import ragmvp.democicd.rest.MediaController;
import ragmvp.democicd.service.MediaService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MediaController.class)
class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MediaService mediaService; // now injected as a Spring bean

    // Provide a mock MediaService as a bean
    @TestConfiguration
    static class MediaServiceMockConfig {
        @Bean
        public MediaService mediaService() {
            return Mockito.mock(MediaService.class);
        }
    }

    private Page<MediaShortDto> mockPage;

    @BeforeEach
    void setUp() {
        List<MediaShortDto> movies = List.of(
                new MediaShortDto("Movie 1", LocalDate.of(2020,1,1), "Overview 1"),
                new MediaShortDto("Movie 2", LocalDate.of(2020,1,2), "Overview 2"),
                new MediaShortDto("Movie 3", LocalDate.of(2020,1,3), "Overview 3"),
                new MediaShortDto("Movie 4", LocalDate.of(2020,1,4), "Overview 4"),
                new MediaShortDto("Movie 5", LocalDate.of(2020,1,5), "Overview 5")
        );

        mockPage = new PageImpl<>(movies, PageRequest.of(1,5), 20);
    }

    @Test
    void testGetAllMovies() throws Exception {
        Mockito.when(mediaService.getAll(anyInt())).thenReturn(mockPage);

        mockMvc.perform(get("/api/v1/movies")
                        .param("page", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.content[0].title").value("Movie 1"))
                .andExpect(jsonPath("$.content[4].title").value("Movie 5"))
                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.size").value(5));
    }
}
