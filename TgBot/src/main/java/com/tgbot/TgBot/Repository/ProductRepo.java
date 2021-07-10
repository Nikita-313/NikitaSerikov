package com.tgbot.TgBot.Repository;

import com.tgbot.TgBot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepo extends JpaRepository<Product,Long> {
}
