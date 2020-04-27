package com.jjdev.persistence.dao;

import com.jjdev.persistence.entity.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDao implements BaseDao<Product> {

    @Override
    @Transactional
    public void create(Product entity) {
        entity.persist();
    }

    @Override
    public List<Product> readAll() {
        return Product.listAll();
    }

    @Override
    public Optional<Product> readById(Long id) {
        return Product.findByIdOptional(id);
    }

    @Override
    @Transactional
    public void update(Product entity, Long id) {
        Optional<Product> optProduct = readById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.setName(entity.getName());
            product.setValue(entity.getValue());
            product.persist();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Product> optProduct = readById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.delete();
        } else {
            throw new NotFoundException();
        }
    }
}
