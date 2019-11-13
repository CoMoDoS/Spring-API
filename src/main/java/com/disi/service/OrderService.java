//package com.disi.service;
//import com.disi.dto.NameAndQuantityDTO;
//import com.disi.dto.OrderDetailsDTO;
//import com.disi.dto.OrderMenuDTO;
//import com.disi.errorHandler.ResourceNotFoundException;
//import com.disi.models.Menu;
//import com.disi.models.Order;
//import com.disi.models.OrderMenu;
//import com.disi.models.User;
//import com.disi.repository.MenuRepository;
//import com.disi.repository.OrderMenuRepository;
//import com.disi.repository.OrderRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class OrderService {
//    @Autowired
//    private final OrderRepository orderRepository;
//    @Autowired
//    private final OrderMenuRepository orderMenuRepository;
//    @Autowired
//    private final MenuRepository menuRepository;
//    private final ModelMapper modelMapper;
//
//    public OrderService( OrderRepository orderRepository, OrderMenuRepository orderMenuRepository, MenuRepository menuRepository, ModelMapper modelMapper ){
//        this.modelMapper = modelMapper;
//        this.orderRepository = orderRepository;
//        this.orderMenuRepository = orderMenuRepository;
//        this.menuRepository = menuRepository;
//    }
//
//    public Order findById(int id) {
//        Optional<Order> order = orderRepository.findById(id);
//        if ( order.isPresent() ){
//            return order.get();
//        }
//        return new Order();
//    }
//
//    public Order addOrder(OrderMenuDTO  orderMenuDTO){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        MenuService menuService = new MenuService(this.menuRepository, this.modelMapper);
//        Menu menu = menuService.findById(orderMenuDTO.getMenuID());
//        Order order = orderRepository.findByUserAndStatus(currentUser.getId(), "new");
//
//        if ( order != null )
//        {
//            OrderMenu orderMenu = new OrderMenu();
//            orderMenu.setQuantity(orderMenuDTO.getQuantity());
//            orderMenu.setMenu(menu);
//            orderMenu.setOrder(order);
//            orderMenuRepository.save(orderMenu);
//            return order;
//        } else {
//            // create new Order
//            Order order1 = new Order();
//            order1.setUser(currentUser);
//            order1.setDate(new Timestamp(System.currentTimeMillis()));
//            order1.setStatus("new");
//            Order newOrder = orderRepository.save(order1);
//            // add orderMenu with orderID created
//            OrderMenu orderMenu = new OrderMenu();
//            orderMenu.setOrder(newOrder);
//            orderMenu.setMenu(menu);
//            orderMenu.setQuantity(orderMenuDTO.getQuantity());
//            orderMenuRepository.save(orderMenu);
//            return newOrder;
//
//        }
//    }
//
//    public Order confirmOrder(){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Order order = orderRepository.findByUserAndStatus(currentUser.getId(), "new");
//        List<OrderMenu> orderMenus = orderMenuRepository.findAllByIdOrder(order.getId());
//        double total = 0;
//        for ( OrderMenu om : orderMenus ){
//            total += (om.getQuantity() * om.getMenu().getPrice());
//        }
//        order.setStatus("in progress");
//        order.setTotal_price(total);
//
//        return orderRepository.save(order);
//    }
//
//    public Order finalizeOrder(int id){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Order> aux = orderRepository.findById(id);
//        if ( aux.isPresent() ){
//            Order order = aux.get();
//            order.setStatus("done");
//            sendMail(order);
//            return orderRepository.save(order);
//        }else{
//            throw new ResourceNotFoundException(String.format("No order with id %s was found", id));
//        }
//
//    }
//
//    public OrderDetailsDTO getCurrentOrderDetails(){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Order order = orderRepository.findByUserAndStatus(currentUser.getId(), "new");
//        List<OrderMenu> orderMenus = orderMenuRepository.findAllByIdOrder(order.getId());
//        List<NameAndQuantityDTO> list = buildNAQList(orderMenus);
//        OrderDetailsDTO orderDetailsDTO = buildOD(list,order);
//
//        return orderDetailsDTO;
//    }
//
//    public List<OrderDetailsDTO> getAllOrderDetails(String status){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<OrderDetailsDTO> listOD = new ArrayList<>();
////        List<Order> orders = orderRepository.findAllByUserAndStatus(currentUser.getId(), status);
//        List<Order> orders = orderRepository.findAllByStatus(status);
//        List<OrderMenu> orderMenus = new ArrayList<>();
//        List<NameAndQuantityDTO> listNAQ = new ArrayList<>();
//
//        for ( Order o : orders ){
//            orderMenus = orderMenuRepository.findAllByIdOrder(o.getId());
//            listNAQ = buildNAQList(orderMenus);
//            OrderDetailsDTO orderDetailsDTO = buildOD(listNAQ,o);
//            listOD.add(orderDetailsDTO);
//        }
//        return listOD;
//
//    }
//
//    private List<NameAndQuantityDTO> buildNAQList(List<OrderMenu> orderMenus){
//        List<NameAndQuantityDTO> list = new ArrayList<>();
//        for ( OrderMenu om : orderMenus ){
//            NameAndQuantityDTO nameAndQuantity = new NameAndQuantityDTO();
//            nameAndQuantity.setId(om.getId());
//            nameAndQuantity.setMenuName(om.getMenu().getName());
//            nameAndQuantity.setQuantity(om.getQuantity());
//            nameAndQuantity.setPrice(om.getMenu().getPrice());
//            list.add(nameAndQuantity);
//        }
//        return list;
//    }
//
//    private OrderDetailsDTO buildOD(List<NameAndQuantityDTO> listNAQ,Order order ){
//        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
//        orderDetailsDTO.setNamesAndQuantities(listNAQ);
//        orderDetailsDTO.setOrderDate(order.getDate());
//        orderDetailsDTO.setOrderId(order.getId());
//        orderDetailsDTO.setStatus(order.getStatus());
//        orderDetailsDTO.setTotalPrice(order.getTotal_price());
//        orderDetailsDTO.setUsername(order.getUser().getEmail());
//        orderDetailsDTO.setUser_id(order.getUser().getId());
//        return orderDetailsDTO;
//    }
//
//    public int sendMail(Order order) {
//        final MailService mailService = new MailService("comodos.n346@gmail.com","ParolaGoogle123");
//        Object object = order.toString();
//
//        mailService.sendMail("ilie.neag96@gmail.com","Dummy Mail Title",  object.toString());
//        return 1;
//    }
//}
