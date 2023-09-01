package com.nuagike.demo.osiv.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String reference;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Product> products;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Invoice invoice;

}
