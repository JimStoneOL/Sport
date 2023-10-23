package com.marat.controlworkbymarat.facade;

import com.marat.controlworkbymarat.dto.OrderDTO;
import com.marat.controlworkbymarat.dto.OrderResponse;
import com.marat.controlworkbymarat.entity.Order;
import com.marat.controlworkbymarat.entity.OrderedProduct;
import com.marat.controlworkbymarat.entity.enums.EStatus;
import com.marat.controlworkbymarat.repository.IssueRepository;
import com.marat.controlworkbymarat.repository.OrderedProductRepository;
import com.marat.controlworkbymarat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderFacade {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private UserRepository userRepository;

    public Order orderDTOToOrder(OrderDTO orderDTO){
        Order order=new Order();
        if(orderDTO.getOrderedProducts()!=null) {
            List<OrderedProduct> orderedProducts = new ArrayList<>();
            for (Long orderedProductId : orderDTO.getOrderedProducts()) {
                OrderedProduct orderedProduct = orderedProductRepository.findById(orderedProductId).orElse(null);
                if (orderedProduct != null) {
                    orderedProducts.add(orderedProduct);
                }
            }
            order.setOrderedProductList(orderedProducts);
        }
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCode(orderDTO.getCode());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        if(orderDTO.getUserId()!=null){
            order.setUser(userRepository.findById(orderDTO.getUserId()).orElse(null));
        }
        order.setId(orderDTO.getId());
        order.setEStatus(orderDTO.getEStatus());
        if(orderDTO.getIssue()!=null){
            order.setIssue(issueRepository.findById(orderDTO.getIssue()).orElse(null));
        }
        return order;
    }

    public OrderDTO orderToOrderDTO(Order order){

        OrderDTO orderDTO=new OrderDTO();
        if(order.getOrderedProductList()!=null) {
            List<Long> orderedProductsListId = new ArrayList<>();
            for (OrderedProduct orderedProduct : order.getOrderedProductList()) {
                orderedProductsListId.add(orderedProduct.getId());
            }
            orderDTO.setOrderedProducts(orderedProductsListId);
        }
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCode(order.getCode());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        if(order.getUser()!=null) {
            orderDTO.setUserId(order.getUser().getId());
        }
        orderDTO.setId(order.getId());
        orderDTO.setEStatus(order.getEStatus());

        if(order.getIssue()!=null){
            orderDTO.setIssue(order.getIssue().getId());
        }

        return orderDTO;
    }


    public List<OrderDTO> orderListToOrderDTOList(List<Order> orderList){
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(Order order:orderList){
            orderDTOList.add(orderToOrderDTO(order));
        }
        return orderDTOList;
    }

    public OrderResponse orderToOrderResponse(Order order){

        OrderResponse orderDTO=new OrderResponse();
        if(order.getOrderedProductList()!=null) {
            String orderedProductsListId = "";
            for (OrderedProduct orderedProduct : order.getOrderedProductList()) {
                orderedProductsListId+=", "+orderedProduct.getProduct().getName();
            }
            orderDTO.setOrderedProducts(orderedProductsListId);
        }
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCode(order.getCode());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        if(order.getUser()!=null) {
            orderDTO.setUser(order.getUser().getFio());
        }
        orderDTO.setId(order.getId());
        if(order.getEStatus()== EStatus.NEW){
            orderDTO.setEStatus("Новый");
        }else {
            orderDTO.setEStatus("Завершенный");
        }


        if(order.getIssue()!=null){
            orderDTO.setIssue(order.getIssue().getName());
        }

        return orderDTO;
    }


    public List<OrderResponse> orderListToOrderResponseList(List<Order> orderList){
        List<OrderResponse> orderDTOList=new ArrayList<>();
        for(Order order:orderList){
            orderDTOList.add(orderToOrderResponse(order));
        }
        return orderDTOList;
    }
}
