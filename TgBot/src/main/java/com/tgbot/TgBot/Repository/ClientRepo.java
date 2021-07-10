package com.tgbot.TgBot.Repository;


import com.tgbot.TgBot.Entity.Сlient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepo extends JpaRepository<Сlient,Long> {

}
