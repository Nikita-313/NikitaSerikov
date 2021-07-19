package com.tgbot.TgBot.Services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgbot.TgBot.Entity.ClientOrder;
import com.tgbot.TgBot.Entity.OrderProduct;
import com.tgbot.TgBot.Entity.Product;
import com.tgbot.TgBot.Entity.Сlient;
import com.tgbot.TgBot.Repository.ClientRepo;
import com.tgbot.TgBot.Repository.OrderProductRepo;
import com.tgbot.TgBot.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramService {

    @Autowired
    EntitiesService entitiesService;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    OrderProductRepo orderProductRepo;


    Сlient client;
    ClientOrder order;
    Long chatId;
    TelegramBot bot;


    public void getCallBack(TelegramBot bot,Update update){
        this.bot = bot;
        Long productId = Long.valueOf(update.callbackQuery().data());
        addProductInOrder(productId);

    }

    public void getMessage(TelegramBot bot, Update update){

        Long externalId = Long.valueOf(update.message().from().id());
        this.chatId = update.message().chat().id();
        this.bot = bot;

        if(entitiesService.getСlientByExternalId(externalId) == null){
            createClient(externalId, update.message().from().firstName());
            createOrder(this.client, 0.0, 1);
        }

        if (this.client == null){
            this.client = entitiesService.getСlientByExternalId(externalId);
            createOrder(this.client, 0.0, 1);
        }

        switch (update.message().text()) {
            case ("Пицца"): sendTgMessage("Пицца",getInlineKeyboardMarkup(3L));break;
            case ("Роллы"):  sendTgMessage("Роллы:",getReplyKeyboardMarkup(4L));break;
            case ("Бургеры"): sendTgMessage("Бургеры:",getReplyKeyboardMarkup(5L));break;
            case ("Напитки"): sendTgMessage("Напитки:",getReplyKeyboardMarkup(6L));break;

            case ("Классические роллы"): sendTgMessage("Классические роллы:",getInlineKeyboardMarkup(7L));break;
            case ("Запеченные роллы"):sendTgMessage("Запеченные роллы:",getInlineKeyboardMarkup(8L));break;
            case ("Сладкие роллы"): sendTgMessage("Сладкие роллы:",getInlineKeyboardMarkup(9L));break;
            case ("Наборы"): sendTgMessage("Наборы:",getInlineKeyboardMarkup(10L));break;

            case ("Классические бургеры"): sendTgMessage("Классические бургеры:",getInlineKeyboardMarkup(11L));break;
            case ("Острые бургеры"): sendTgMessage("Острые бургеры:",getInlineKeyboardMarkup(12L));break;

            case ("Газированные напитки"): sendTgMessage("Газированные напитки:",getInlineKeyboardMarkup(13L));break;
            case ("Энергетические напитки"): sendTgMessage("Энергетические напитки:",getInlineKeyboardMarkup(14L));break;
            case ("Соки"): sendTgMessage("Соки:",getInlineKeyboardMarkup(15L));break;
            case ("Другие напитки"): sendTgMessage("Другие напитки:",getInlineKeyboardMarkup(16L));break;

            case ("Оформить заказ"): placeOrder(); break;
            case ("В основное меню"): sendTgMessage("Основное меню:",getReplyKeyboardMarkup(0L));break;

            default: break;
        }


    }

    private void addProductInOrder(Long id){
        Product product = entitiesService.getProductById(id);
        OrderProduct orderProduct = entitiesService.getOrderProductByOrderIdAndProductId(order,product);
        if (orderProduct == null) saveOrderProduct(product,1);
        else {
            orderProduct.setCountProduct(orderProduct.getCountProduct()+1);
            orderProductRepo.save(orderProduct);
        }

        this.order.setTotal(this.order.getTotal() + product.getPrice());
        sendTgMessage("В заказ добавлен продукт: " + product.getName());
    }

    private void saveOrderProduct(Product product, Integer countProduct){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(this.order);
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(countProduct);
        orderProductRepo.save(orderProduct);
    }

    private void placeOrder(){
        if(order.getTotal() == 0.0) {
            sendTgMessage("Товары не выбраны");
        }else {
            List <OrderProduct> orderProducts = entitiesService.getOrderProductSByOrderId(this.order);
            sendTgMessage("Вы купили:");
            for(OrderProduct orderProduct:orderProducts){
                sendTgMessage(orderProduct.getProduct().getName()+ ": " + orderProduct.getCountProduct() + "x" +orderProduct.getProduct().getPrice() + " = " + orderProduct.getProduct().getPrice() * orderProduct.getCountProduct());
            }
            sendTgMessage("Итого: " + order.getTotal());
            sendTgMessage("Заказ №" + order.getId() + " подтвержден. Курьер уже едет к Вам по адресу " + client.getAddress()+". Приблизительное время доставки 90 мин.");
            order.setStatus(2);
            orderRepo.save(order);
            createOrder(this.client, 0.0, 1);
        }
    }

    private void sendTgMessage(String text, Keyboard keyboard){
        bot.execute(new SendMessage(chatId, text).replyMarkup(keyboard));
    }
    private void sendTgMessage(String text){
        bot.execute(new SendMessage(chatId, text));
    }

    private void createClient(Long externalId, String fullName){
        Сlient client = new Сlient();
        client.setExternalId(externalId);
        client.setAddress("Пр. Победы 21");
        client.setPhoneNumber("+79786755563");
        client.setFullName(fullName);
        clientRepo.save(client);
        this.client = client;
    }

    private void createOrder(Сlient client, Double total, Integer status){
        ClientOrder order = new ClientOrder();
        order.setClient(client);
        order.setTotal(total);
        order.setStatus(status);
        orderRepo.save(order);
        this.order = order;
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(Long parentId) {
        List<KeyboardButton> categories = entitiesService.getCategoriesByParentId(parentId)
                .stream()
                .map(category -> new KeyboardButton(category.getName())).collect(Collectors.toList());

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(categories.toArray(KeyboardButton[]::new));

        markup.resizeKeyboard(true);
        markup.addRow(new KeyboardButton("Оформить заказ"));
        markup.addRow(new KeyboardButton("В основное меню"));

        return markup;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(Long parentId) {

        List<Product> products = entitiesService.getCategoryProducts(parentId);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        for (Product product : products) {
            InlineKeyboardButton button = new
                    InlineKeyboardButton(String.format("Товар %s. Цена %.2f руб.",
                    product.getName(), product.getPrice()))
                    .callbackData(product.getId().toString());
            markup.addRow(button);
        }
        return markup;
    }


}
