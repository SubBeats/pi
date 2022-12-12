package com.example.webapp.Mod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")//прописывать необязательно
    private Long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "size")
    private  Long size;
    @Column(name = "ContentType")
    private String ContentType;
    @Column(name = "IsPreviewImage")
    private boolean IsPreviewImage;
    @Column(name = "bytes")
    @Lob//
    private byte[] bytes;

    //отношение таблиц к фото в товару
    //много фото к 1 тоару
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)//cascade-как повлияет деййствие с фото на сущ товаров
    //fetch способ загрузки (спооб подгрузки всех сещностей фото)
    //Lazy не быстро EAGER - моментально
    private models products;


}
