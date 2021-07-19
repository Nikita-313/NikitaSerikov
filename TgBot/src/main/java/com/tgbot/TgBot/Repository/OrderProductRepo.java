package com.tgbot.TgBot.Repository;
import com.tgbot.TgBot.Entity.ClientOrder;
import com.tgbot.TgBot.Entity.OrderProduct;
import com.tgbot.TgBot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderProduct", path = "orderProduct")
public interface OrderProductRepo  extends JpaRepository<OrderProduct,Long> {
    String GET_ORDERPRODUCT_BY_ORDER_ID_AND_PRODUCT_ID_QUERY = "select o from OrderProduct as o where o.order = ?1 and o.product = ?2";
    String GET_ORDERPRODUCTS_BY_ORDER_ID_QUERY = "select o from OrderProduct as o where o.order = ?1";

    @Query(GET_ORDERPRODUCT_BY_ORDER_ID_AND_PRODUCT_ID_QUERY)
    OrderProduct getOrderProductByOrderIdAndProductId(ClientOrder order, Product product);

    @Query(GET_ORDERPRODUCTS_BY_ORDER_ID_QUERY)
    List<OrderProduct> getOrderProductSByOrderId(ClientOrder order);

}
