package org.anch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "inventory", catalog = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", columnDefinition = "mediumint")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    public Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    public Store store;

    @Column(name = "last_update", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER)
    private Set<Rental> rentals;

}
