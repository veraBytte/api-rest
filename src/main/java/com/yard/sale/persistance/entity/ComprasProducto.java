package com.yard.sale.persistance.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "compras_productos")
public class ComprasProducto {
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;
    private BigDecimal total;
    private BigDecimal estado;
}
