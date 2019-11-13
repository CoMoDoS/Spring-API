//package com.disi.models;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name="menu")
//public class Menu implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "ingredients")
//    private String ingredients;
//
//    @Column(name = "price")
//    private Double price;
//
//    public Menu() {
//    }
//
//    public Menu(String name, String ingredients, Double price) {
//        this.name = name;
//        this.ingredients = ingredients;
//        this.price = price;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(String ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Menu{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", ingredients='" + ingredients + '\'' +
//                ", price=" + price +
//                '}';
//    }
//}
