package com.tgbot.TgBot.Repository;


import com.tgbot.TgBot.Entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepo extends JpaRepository<ClientOrder,Long> {

}
