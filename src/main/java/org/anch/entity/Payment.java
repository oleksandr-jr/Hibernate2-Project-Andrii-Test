package org.anch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment", catalog = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder (setterPrefix = "with")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Short id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    public Staff staff;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "amount", nullable = false, columnDefinition = "decimal(5,2)")
    @Type(type = "org.hibernate.type.DoubleType")
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime paymentDate;

    @Column(name = "last_update", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

}
