package com.example.webapp.Mod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;

@Entity
@Table (name = "List")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")//прописывать необязательно
    private Long ID;

    private LocalDateTime OrderDate1;
    private LocalDateTime OrderDate2;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)//cascade-как повлияет деййствие с фото на сущ товаров
    private Order order;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)//cascade-как повлияет деййствие с фото на сущ товаров
    private models model;

}
