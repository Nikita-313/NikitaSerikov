package com.tgbot.TgBot.Controllers;


import com.tgbot.TgBot.Entity.Product;
import com.tgbot.TgBot.Services.EntitiesService;
import com.tgbot.TgBot.Services.EntityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestProductController {

    private final EntitiesService entitiesService;

    public  RestProductController(EntityServiceImpl entitiesService){
        this.entitiesService = entitiesService;
    }

    //возвращает список всех товаров
    @GetMapping("/rest/products")
    public List<Product> getAllProducts() {
        return entitiesService.getAllProducts();
    }

    //возвращает всю информацию о товаре по его идентификатору
    @RequestMapping(value = "/rest/products",params = {"id"})
    public Product getProductById(@RequestParam Long id){
        return entitiesService.getProductById(id);
    }

    //возвращает всю информацию о товаре по его описанию
    @RequestMapping(value = "/rest/products",params = {"description"})
    public Product getProductByDescription(@RequestParam String description){
        return entitiesService.getProductsByName(description);
    }

    //возвращает список всех товаров определенной категории
    @RequestMapping(value = "/rest/products",params = {"categoryId"})
    public List<Product> getCategoryProducts(@RequestParam Long categoryId){
        return entitiesService.getCategoryProducts(categoryId);
    }

    //возвращает список всех товаров купленных когда-либо клиентом по идентификатору клиента
    @RequestMapping(value = "/rest/listClientProducts",params = {"clientId"})
    public List<Product> getClientProducts(@RequestParam Long clientId){
        return entitiesService.getClientProducts(clientId);
    }

    //возвращает {top} самых популярных товаров среди клиентов
    @RequestMapping(value = "/rest/topPopularProducts",params = {"top"})
    public List<Product> getTopPopularProducts(@RequestParam Integer top){
        return entitiesService.getTopPopularProducts(top);
    }


}
