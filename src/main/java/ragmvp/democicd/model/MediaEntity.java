package ragmvp.democicd.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "media_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaEntity {

    @Id
    private UUID id;

    private Long tmdbId;

    @Column(nullable = false)
    private String type; // MOVIE или SERIAL

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String overview;

    // 🔹 Search context
    @Column(nullable = true)
    private String tagline;

    @Column(nullable = true)
    private Integer runtime;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> keywords;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "cast_members", columnDefinition = "jsonb", nullable = true)
    private List<String> cast;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> directors;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> writers;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> genres;

    @Column(nullable = true)
    private LocalDate releaseDate;

    @Column(nullable = true)
    private Integer releaseYear;

    @Column(nullable = true)
    private Double voteAverage;

    @Column(nullable = true)
    private String posterPath;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> productionCompanies;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> productionCountries;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<String> trailers;
}
