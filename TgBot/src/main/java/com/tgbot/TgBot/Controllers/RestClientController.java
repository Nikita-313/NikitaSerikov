package com.tgbot.TgBot.Controllers;

import com.tgbot.TgBot.Entity.Сlient;
import com.tgbot.TgBot.Services.EntitiesService;
import com.tgbot.TgBot.Services.EntityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClientController {

    private final EntitiesService entitiesService;

    public  RestClientController(EntityServiceImpl entitiesService){
        this.entitiesService = entitiesService;
    }

    //возвращает список всех клиентов
    @GetMapping("/rest/clients")
    public List<Сlient> getAllClients() {
        return entitiesService.getAllClients();
    }

    //возвращает всю информацию о клиенте по его идентификатору
    @RequestMapping(value = "/rest/clients",params = {"id"})
    public Сlient getClientById(@RequestParam Long id){
        return entitiesService.getClientById(id);
    }

    //возвращает всю информацию о клиенте по его имени
    @RequestMapping(value = "/rest/clients",params = {"name"})
    public Сlient getClientByName(@RequestParam String name){
        return entitiesService.getClientByName(name);
    }


}
