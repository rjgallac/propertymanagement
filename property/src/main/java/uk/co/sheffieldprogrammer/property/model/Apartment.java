package uk.co.sheffieldprogrammer.property.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Property property;

    private double rent;

    @OneToMany(mappedBy = "apartment")
    private List<Maintenance> maintenances;
}
