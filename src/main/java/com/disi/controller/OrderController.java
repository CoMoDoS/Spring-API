//package com.disi.controller;
//
//
//import com.disi.dto.OrderDetailsDTO;
//import com.disi.dto.OrderMenuDTO;
//import com.disi.models.Order;
//import com.disi.service.OrderService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderServ) {
//        this.orderService = orderServ;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Order> addOrder(@RequestBody OrderMenuDTO orderMenuDTO ){
//
//        Order order = orderService.addOrder(orderMenuDTO);
//        return ok().body(order);
//    }
//
//    @PutMapping("/confirm")
//    public ResponseEntity<Order> confirmOrder(){
//        Order order = orderService.confirmOrder();
//        return ok().body(order);
//    }
//
//    @PutMapping("/finalize/{id}")
//    public ResponseEntity<Order> finalizeOrder(@PathVariable("id") Integer id){
//        Order order = orderService.finalizeOrder(id);
//        return ok().body(order);
//    }
//
//    @GetMapping("/currentOrder")
//    public ResponseEntity<OrderDetailsDTO> getCurrentOrderDetails(){
//        OrderDetailsDTO orderDetailsDTO = orderService.getCurrentOrderDetails();
//        return ok().body(orderDetailsDTO);
//    }
//
//    @GetMapping("/sentOrders")
//    public ResponseEntity<List<OrderDetailsDTO>> getSentOrderDetails(){
//        List<OrderDetailsDTO> orderDetailsDTOs = orderService.getAllOrderDetails("in progress");
//        return ok().body(orderDetailsDTOs);
//    }
//
//    @GetMapping("/doneOrders")
//    public ResponseEntity<List<OrderDetailsDTO>> getDoneOrderDetails(){
//        List<OrderDetailsDTO> orderDetailsDTOs = orderService.getAllOrderDetails("done");
//        return ok().body(orderDetailsDTOs);
//    }
//}
