package com.tgbot.TgBot.Services;

import java.util.List;

import com.tgbot.TgBot.Entity.Category;
import com.tgbot.TgBot.Entity.ClientOrder;
import com.tgbot.TgBot.Entity.Product;
import com.tgbot.TgBot.Entity.Сlient;

/**
 * Сервис для работы с сущностями телеграмм-бота
 */
public interface EntitiesService
{
    /**
     * Возвращает список всех клиентов
     */
    List<Сlient> getAllClients();
    /**
     * Возвращает клиента по его идентификатору
     * @param id идентификатор клиента
     */
    Сlient getClientById(Long id);
    /**
     * Возвращает клиента по его имени
     * @param name имя клиента
     */
    Сlient getClientByName(String name);
    /**
     * Возвращает список всех заказов
     */
    List<ClientOrder> getAllOrders();
    /**
     * Возвращает заказ по его идентификатору
     * @param id идентификатор заказа
     */ClientOrder getOrderById(Long id);
    /**
     * Возвращает заказ по его статусу
     * @param status статус заказа
     */
    List<ClientOrder> getOrdersByStatus(Integer status);
    /**
     * Возвращает список всех товаров
     */
    List<Product> getAllProducts();
    /**
     * Возвращает товар по его идентификатору
     * @param id идентификатор товар
     */
    Product getProductById(Long id);
    /**
     * Возвращает товар по его описанию
     * @param name описание товара
     */
    Product getProductsByName(String name);
    /**
     * Возвращает список товаров в категории
     * @param id идентификатор категории
     */
    List<Product> getCategoryProducts(Long id);
    /**
     * Возвращает список всех категорий
     */
    List<Category> getAllCategories();/**
 * Возвращает список заказов клиента по его имени
 * @param name имя клиента
 */
List<ClientOrder> getClientOrdersByName(String name);
    /**
     * Возвращает список всех товаров купленных когда-либо клиентом
     * @param id идентификатор клиента
     */
    List<Product> getClientProducts(Long id);
    /**
     * Возвращает указанное кол-во самых популярных товаров среди
     клиентов
     * @param top кол-во товаров
     */
    List<Product> getTopPopularProducts(Integer top);
}
