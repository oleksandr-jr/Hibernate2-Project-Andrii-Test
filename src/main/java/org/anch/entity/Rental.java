package org.anch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "rental", catalog = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder (setterPrefix = "with")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;

    @Column(name = "rental_date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime rentalDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    public Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer customer;

    @Column(name = "return_date")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    public Staff staff;

    @Column(name = "last_update", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "rental")
    private Payment payment;

}
