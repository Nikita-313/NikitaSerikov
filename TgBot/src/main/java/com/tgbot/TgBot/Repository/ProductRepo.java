package com.tgbot.TgBot.Repository;

import com.tgbot.TgBot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepo extends JpaRepository<Product,Long> {
    String GET_TOP_POPULAR_QUERY = "select o.product from OrderProduct as o group by o.product.id order by sum(o.countProduct) desc";

    String GET_CLIENT_PRODUCTS_QUERY = "select o.product from OrderProduct as o " +
            "inner join ClientOrder as co on co.id = o.order " +
            "inner join Сlient as c on c.id = co.client " +
            "where c.id = ?1";


    @Query(GET_TOP_POPULAR_QUERY)
    List<Product> getTopPopular();

    @Query(GET_CLIENT_PRODUCTS_QUERY)
    List<Product> getClientProducts(Long id);
}
