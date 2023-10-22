package com.marat.controlworkbymarat.service;

import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.dto.OrderedProductDTO;
import com.marat.controlworkbymarat.entity.OrderedProduct;
import com.marat.controlworkbymarat.facade.OrderedProductFacade;
import com.marat.controlworkbymarat.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductService {


    private final OrderedProductRepository orderedProductRepository;
    private final OrderedProductFacade orderedProductFacade;

    @Autowired
    public OrderedProductService(OrderedProductRepository orderedProductRepository, OrderedProductFacade orderedProductFacade) {
        this.orderedProductRepository = orderedProductRepository;
        this.orderedProductFacade = orderedProductFacade;
    }

    public Message createOP(OrderedProductDTO orderedProductDTO){
        orderedProductRepository.save(orderedProductFacade.orderedProductDTOToOrderedProduct(orderedProductDTO));
        return new Message("Сохранилось");
    }

    public OrderedProductDTO getOrderedProductById(Long id){
        return orderedProductFacade.orderedProductToOrderedProductDTO(orderedProductRepository.findById(id).orElse(null));
    }

    public List<OrderedProductDTO> getAllOrderedProducts(){
        return orderedProductFacade.orderedProductListToOrderedProductDTOList(orderedProductRepository.findAll());
    }
}
