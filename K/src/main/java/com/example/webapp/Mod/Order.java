package com.example.webapp.Mod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "ModelOrder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;

    private int Num;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "order")
    private List<com.example.webapp.Mod.List> lists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    public void addListToOrder(com.example.webapp.Mod.List list) {
        list.setOrder(this);
        lists.add(list);
    }
    public void deleteListFromOrder(com.example.webapp.Mod.List t){

        lists.remove(t);
    }

    public boolean FindList(Long ID){
        for(com.example.webapp.Mod.List L : this.getLists()){
            if(L.getModel().getID().equals(ID)) return true;
        }
        return false;
    }

}
