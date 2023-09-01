package com.nuagike.demo.osiv.service;

import com.nuagike.demo.osiv.dto.ProductDTO;
import com.nuagike.demo.osiv.model.Product;
import com.nuagike.demo.osiv.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository articleRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * permet d'ajouter un nouveau article
     */
    @Transactional
    public ProductDTO addProduct(final ProductDTO articleDTO) {
        Assert.notNull(articleDTO, "articleDTO should not be null");

        final Product article = articleRepository.save(modelMapper.map(articleDTO, Product.class));
        // Par défaut, la quantité en stock d'un article est 5
        article.setStock(5L);

        return modelMapper.map(article, ProductDTO.class);
    }

    @Transactional
    public List<ProductDTO> getAll() {
        return modelMapper.map(articleRepository.findAll(), //
                new TypeToken<List<Product>>() {
                }.getType());
    }
}
