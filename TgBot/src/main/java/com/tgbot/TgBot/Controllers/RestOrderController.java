package com.tgbot.TgBot.Controllers;

import com.tgbot.TgBot.Entity.ClientOrder;
import com.tgbot.TgBot.Services.EntitiesService;
import com.tgbot.TgBot.Services.EntityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestOrderController {

    private final EntitiesService entitiesService;

    public  RestOrderController(EntityServiceImpl entitiesService){
        this.entitiesService = entitiesService;
    }

    //возвращает список всех заказов
    @GetMapping("/rest/orders")
    public List<ClientOrder> getAllOrders() {
        return entitiesService.getAllOrders();
    }

    //возвращает всю информацию о заказе по его идентификатору
    @RequestMapping(value = "/rest/orders",params = {"id"})
    public ClientOrder getOrderById(@RequestParam Long id){
        return entitiesService.getOrderById(id);
    }

    //возвращает все заказы в определенном статусе
    @RequestMapping(value = "/rest/orders",params = {"status"})
    public List<ClientOrder> getOrdersByStatus(@RequestParam Integer status){
        return entitiesService.getOrdersByStatus(status);
    }

    //возвращает список всех заказов клиента по имени клиента
    @RequestMapping(value = "/rest/listClientOrders",params = {"clientName"})
    public List<ClientOrder> getClientOrdersByName(@RequestParam String clientName){
        return entitiesService.getClientOrdersByName(clientName);
    }


}
