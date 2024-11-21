package uk.co.sheffieldprogrammer.property.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "landlord")
    private List<Property> properties;
}
