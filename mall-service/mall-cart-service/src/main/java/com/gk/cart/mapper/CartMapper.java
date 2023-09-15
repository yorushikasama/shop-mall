package com.gk.cart.mapper;

import com.gk.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartMapper extends MongoRepository<Cart,String> {
}
