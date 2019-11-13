//package com.disi.repository;
//
//import com.disi.models.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//@Repository
//public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @Query(value = "select * from orderr where user = ?1 and status = ?2 LIMIT 1", nativeQuery = true)
//    Order findByUserAndStatus(int userId, String status);
//    @Query(value = "select * from orderr where user = ?1 and status = ?2 ", nativeQuery = true)
//    List<Order> findAllByUserAndStatus(int userId, String status);
//
//    List<Order> findAllByStatus(String status);
//
//}
