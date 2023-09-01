package com.nuagike.demo.osiv.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nuagike.demo.osiv.dto.OrderDTO;
import com.nuagike.demo.osiv.model.Invoice;
import com.nuagike.demo.osiv.model.Order;
import com.nuagike.demo.osiv.model.Product;
import com.nuagike.demo.osiv.repository.OrderRepository;
import com.nuagike.demo.osiv.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * permet de rajouter une commande
	 * 
	 */
	@Transactional
	public OrderDTO addOrder(final OrderDTO orderDTO) {
		Assert.notNull(orderDTO, "orderDTO should not be null");
		final Set<Product> products = //
				orderDTO.getProducts().stream()//
						.map(productDTO -> {
							return productRepository.findOneByName(productDTO.getName())//
									.orElseThrow(() -> new RuntimeException(//
											String.format("product with given name %s not found",
													productDTO.getName())));
						}).collect(Collectors.toSet());

		final Order order = new Order();
		order.setProducts(products);
		order.setReference(UUID.randomUUID().toString());
		orderRepository.save(order);
		return modelMapper.map(order, OrderDTO.class);
	}

	/**
	 * permet d'ajouter une facture pour une commande
	 */
	@Transactional
	public OrderDTO initInvoice(final Long orderId) {
		Assert.notNull(orderId, "orderId should not be null");

		final Order order = orderRepository.findById(orderId)//
				.orElseThrow(() -> new RuntimeException(String.format("order with given id %s not found", orderId)));

		final Long total = order.getProducts().stream()//
				.collect(Collectors.summingLong(Product::getPrice));

		final Invoice invoice = new Invoice();	
		invoice.setTotal(total);
		order.setInvoice(invoice);
		orderRepository.save(order);
		return modelMapper.map(order, OrderDTO.class);
	}

	/**
	 * 
	 * retourne toute la liste des commandes
	 */
	@Transactional
	public List<OrderDTO> getAll() {
		return modelMapper.map(orderRepository.findAll(), //
				new TypeToken<List<Order>>() {
				}.getType());
	}

}
