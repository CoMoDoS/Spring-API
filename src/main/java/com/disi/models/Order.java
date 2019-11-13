//package com.disi.models;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name="orderr")
//public class Order implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user", nullable = false)
//    private User user;
//
//    @Column(name = "total_price")
//    private Double total_price;
//
//    @Column(name = "order_date")
//    private Timestamp order_date;
//
//    @Column(name = "status")
//    private String status;
//
//    public Order(){
//    }
//
//    public Order(User user, Double total_price, Timestamp order_date, String status) {
//        this.user = user;
//        this.total_price = total_price;
//        this.order_date = order_date;
//        this.status = status;
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
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Double getTotal_price() {
//        return total_price;
//    }
//
//    public void setTotal_price(Double total_price) {
//        this.total_price = total_price;
//    }
//
//    public Timestamp getDate() {
//        return order_date;
//    }
//
//    public void setDate(Timestamp order_date) {
//        this.order_date = order_date;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", user=" + user +
//                ", total_price=" + total_price +
//                ", order_date=" + order_date +
//                ", status='" + status + '\'' +
//                '}';
//    }
//}
