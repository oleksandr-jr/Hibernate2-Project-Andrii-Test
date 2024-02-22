package org.anch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.anch.converter.RatingConverter;
import org.anch.converter.YearAttributeConverter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film", catalog = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year", columnDefinition = "YEAR")
    @Convert(converter = YearAttributeConverter.class)
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "original_language_id")
    private Byte originalLanguage;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "tinyint default '3'")
    private Integer rentalDuration;

    @Column(name = "rental_rate", nullable = false, columnDefinition = "decimal(4,2) default 4.99")
    @Type(type = "org.hibernate.type.DoubleType")
    private Double rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false, columnDefinition = "decimal(5,2) default 19.99")
    @Type(type = "org.hibernate.type.DoubleType")
    private Double replacementCost;

    /* It's highly recommended to avoid @Enumerated(EnumType.STRING) annotation,
     because it hides the @Convert and the data truncate occurs!!! */

    @Column(name = "rating", nullable = false, length = 5, columnDefinition = "enum('G','PG','PG-13','R','NC-17') default 'G'")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String specialFeaturesStr;

    @Column(name = "last_update", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor", catalog = "movie",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_category", catalog = "movie",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    private Set<Inventory> inventories;

    public Set<String> getSpecialFeaturesStr() {
        if (specialFeaturesStr == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(
                    new HashSet<String>(Arrays.asList(specialFeaturesStr.split(",")))
            );
    }

    public void setSpecialFeatures(Set<String> specialFeatures) {
        if (specialFeatures == null)
            specialFeaturesStr = null;
        else
            specialFeaturesStr = String.join(",", specialFeatures);
    }
}
