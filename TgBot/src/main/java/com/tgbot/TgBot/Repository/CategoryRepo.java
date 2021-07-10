package com.tgbot.TgBot.Repository;

import com.tgbot.TgBot.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
