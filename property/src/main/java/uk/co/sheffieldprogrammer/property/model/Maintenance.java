package uk.co.sheffieldprogrammer.property.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime maintenanceDate;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;
}
