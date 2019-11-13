//package com.disi.models;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name="order_menu")
//public class OrderMenu implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "menu_id", nullable = false)
//    private Menu menu;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    public OrderMenu(){
//    }
//
//    public OrderMenu(Order order, Menu menu, int quantity) {
//        this.order = order;
//        this.menu = menu;
//        this.quantity = quantity;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public Menu getMenu() {
//        return menu;
//    }
//
//    public void setMenu(Menu menu) {
//        this.menu = menu;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//}
