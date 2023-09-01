package com.nuagike.demo.osiv.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nuagike.demo.osiv.dto.OrderDTO;
import com.nuagike.demo.osiv.service.OrderService;
import com.nuagike.demo.osiv.service.ProductService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrderDTO addOrder(@RequestBody(required = true) OrderDTO orderDTO) {
		final OrderDTO newOrderDTO = orderService.addOrder(orderDTO);
		return orderService.initInvoice(newOrderDTO.getId());
	}

	@GetMapping
	public List<OrderDTO> getAllOrders() {
		return orderService.getAll();
	}

}
