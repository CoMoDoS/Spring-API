//package com.disi.repository;
//
//import com.disi.models.Order;
//import com.disi.models.OrderMenu;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
//    @Query(value = "select * from order_menu where order_id = ?1", nativeQuery = true)
//    List<OrderMenu> findAllByIdOrder(int id);
//}
