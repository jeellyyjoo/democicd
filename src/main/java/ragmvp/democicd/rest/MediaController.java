package ragmvp.democicd.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ragmvp.democicd.service.MediaService;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(mediaService.getAll(page));
    }
}
