package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "duenio")
public class Duenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rut;

    private String nombre;

    private String apellido;

    private String email;

    private int telefono;

    // Duenio.java  (lado "uno"; Mascota y Servicio son idénticas)
    @JsonIgnore
    @OneToMany(mappedBy = "duenio", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}
