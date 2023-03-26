package com.get.businesslayer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class IdType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prod_id", nullable = false)
    private String prodId;

    String type;

    public IdType(String prod_id, String type) {
        this.prodId = prod_id;
        this.type = type;
    }
}
