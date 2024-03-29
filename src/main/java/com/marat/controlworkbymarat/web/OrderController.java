package com.marat.controlworkbymarat.web;

import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.dto.OrderDTO;
import com.marat.controlworkbymarat.dto.OrderResponse;
import com.marat.controlworkbymarat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO, Principal principal){
        return orderService.createOrder(orderDTO,principal);
    }

    @GetMapping("/get/{id}")
    public OrderResponse getOrderById(@PathVariable long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/get/all")
    public List<OrderResponse> getAllOrders(Principal principal){
        return orderService.getAllOrders(principal);
    }

    @PostMapping("/change/{id}")
    public Message getAllOrders(@PathVariable long id){
        return orderService.changeStatus(id);
    }

}
