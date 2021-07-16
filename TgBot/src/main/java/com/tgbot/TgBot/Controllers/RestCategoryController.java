package com.tgbot.TgBot.Controllers;

import com.tgbot.TgBot.Entity.Category;
import com.tgbot.TgBot.Services.EntitiesService;
import com.tgbot.TgBot.Services.EntityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCategoryController {

    private final EntitiesService entitiesService;

    public  RestCategoryController(EntityServiceImpl entitiesService){
        this.entitiesService = entitiesService;
    }

    //возвращает список всех категорий
    @GetMapping("/rest/categories")
    public List<Category> getAllCategories() {
        return entitiesService.getAllCategories();
    }

}
