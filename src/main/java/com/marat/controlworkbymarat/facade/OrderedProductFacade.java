package com.marat.controlworkbymarat.facade;

import com.marat.controlworkbymarat.dto.OrderedProductDTO;
import com.marat.controlworkbymarat.entity.OrderedProduct;
import com.marat.controlworkbymarat.repository.OrderRepository;
import com.marat.controlworkbymarat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderedProductFacade {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public OrderedProduct orderedProductDTOToOrderedProduct(OrderedProductDTO orderedProductDTO){

        OrderedProduct orderedProduct=new OrderedProduct();
        orderedProduct.setOrder(orderRepository.findById(orderedProductDTO.getOrder()).orElse(null));
        orderedProduct.setProduct(productRepository.findById(orderedProductDTO.getProduct()).orElse(null));
        orderedProduct.setCount(orderedProductDTO.getCount());

        return orderedProduct;
    }

    public OrderedProductDTO orderedProductToOrderedProductDTO(OrderedProduct orderedProduct){

        OrderedProductDTO orderedProductDTO=new OrderedProductDTO();
        orderedProductDTO.setOrder(orderedProduct.getOrder().getId());
        orderedProductDTO.setProduct(orderedProduct.getProduct().getArticle());
        orderedProductDTO.setCount(orderedProduct.getCount());
        orderedProductDTO.setId(orderedProduct.getId());

        return orderedProductDTO;
    }

    public List<OrderedProductDTO> orderedProductListToOrderedProductDTOList(List<OrderedProduct> orderedProductList){

        List<OrderedProductDTO> orderedProductDTOList=new ArrayList<>();
        for(OrderedProduct orderedProduct:orderedProductList){
            orderedProductDTOList.add(orderedProductToOrderedProductDTO(orderedProduct));
        }

        return orderedProductDTOList;
    }
}
