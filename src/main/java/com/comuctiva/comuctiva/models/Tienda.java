package com.comuctiva.comuctiva.models;
//import java.util.ArrayList;
//import java.util.List;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.ManyToOne;
/*import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter

public class Tienda {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Tienda;

        @Column (length = 50)
        private String NombreT;
        
        @Column(length = 50)
        private String Log;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "Id_Direcc", nullable = false, foreignKey = @ForeignKey(name = "FK_Direcc"))
        private Direcciones direcciones;

}
