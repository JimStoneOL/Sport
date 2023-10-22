package com.marat.controlworkbymarat.web;

import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.dto.OrderedProductDTO;
import com.marat.controlworkbymarat.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/op")
@CrossOrigin(origins = "*")
public class OrderedProductController {

    @Autowired
    OrderedProductService orderedProductService;

    @PostMapping("/create")
    public Message createOP(@RequestBody OrderedProductDTO orderedProductDTO){
        return orderedProductService.createOP(orderedProductDTO);
    }

    @GetMapping("/get/{id}")
    public OrderedProductDTO getOPById(@PathVariable long id){
        return orderedProductService.getOrderedProductById(id);
    }

    @GetMapping("/get/all")
    public List<OrderedProductDTO> getAllOPS(){
        return orderedProductService.getAllOrderedProducts();
    }
}
