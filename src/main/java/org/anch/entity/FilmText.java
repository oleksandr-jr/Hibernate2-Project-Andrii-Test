package org.anch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film_text", catalog = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title", nullable = false, length = 255)
    private String firstName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "staff")
    private Store store;

}
