package com.example.webapp.Mod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class models {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "author")
    private String author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "products")
    private java.util.List<Image> images = new ArrayList<>();
    private Long previewImageId;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "model")
    private java.util.List<List> Lists = new ArrayList<>();

    public void addImageToProduct(Image image) {
        image.setProducts(this);
        images.add(image);
    }
    public void addListToProduct(List list) {
        list.setModel(this);
        Lists.add(list);
    }
    public void deleteListFromModel(com.example.webapp.Mod.List t){
        Lists.remove(t);
    }
}