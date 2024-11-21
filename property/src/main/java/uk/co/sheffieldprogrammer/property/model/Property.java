package uk.co.sheffieldprogrammer.property.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ManyToOne
    @JoinColumn(name="landlord_id", nullable=false)
    private Landlord landlord;

    @OneToMany(mappedBy = "property")
    private List<Apartment> apartments;


}
