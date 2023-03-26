package com.get.businesslayer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String prod_id;
    private String name;
    private String image;

    private String pdp_url;
    private String isTested;
    @JsonProperty("Brand")
    private String brand;

    @Column(name = "sku")
    private String displayProdNo;
    private String isClearance;
}
