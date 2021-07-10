package com.tgbot.TgBot;


import com.tgbot.TgBot.Entity.Category;
import com.tgbot.TgBot.Entity.Product;
import com.tgbot.TgBot.Entity.Сlient;
import com.tgbot.TgBot.Repository.CategoryRepo;
import com.tgbot.TgBot.Repository.ClientRepo;
import com.tgbot.TgBot.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FillingTests {

    @Autowired
    private ClientRepo clientRepository;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;


    private void saveClient(Long externalId,  String fullName, String phoneNumber,String address){
        Сlient client = new Сlient();
        client.setExternalId(externalId);
        client.setFullName(fullName);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);
        clientRepository.save(client);
    }

    private Category saveCategory(String name, Long parent){
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepo.save(category);
        return category;
    }

    private void saveProduct(String name, String description, Double price, Category category){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        productRepo.save(product);
    }


    @Test
    public void createTwoClients(){
        saveClient(1L,"fullName1","+79780844456","address1");
        saveClient(2L,"fullName2","+79780844457","address2");
    }

    @Test
    public void createCategoriesAndProducts(){

        //Основные категории
        Category pizza = saveCategory("Пицца",0L);
        Category rolls = saveCategory("Роллы",0L);
        Category burgers = saveCategory("Бургеры",0L);
        Category drinks = saveCategory("Напитки",0L);

        //Подкатегории (Роллы)
        Category classicRolls = saveCategory("Классические роллы",rolls.getId());
        Category bakedRolls = saveCategory("Запеченные роллы",rolls.getId());
        Category sweetRolls = saveCategory("Сладкие роллы",rolls.getId());
        Category setsRolls = saveCategory("Наборы",rolls.getId());

        //Подкатегории (Бургеры)
        Category classicBurgers = saveCategory("Классические бургеры",burgers.getId());
        Category spicyBurgers = saveCategory("Острые бургеры",burgers.getId());

        //Подкатегории (Напитки)
        Category carbonatedDrinks = saveCategory("Газированные напитки",drinks.getId());
        Category energyDrinks = saveCategory("Энергетические напитки",drinks.getId());
        Category juices = saveCategory("Соки",drinks.getId());
        Category otherDrinks = saveCategory("Другие напитки",drinks.getId());

        //Товары (Пицца)
        saveProduct("Cицилийская","Пицца Сицилийская",380.00,pizza);
        saveProduct("4 сезона","Пицца 4 сезона",340.00,pizza);
        saveProduct("4 сыра","Пицца 4 сыра",300.00,pizza);

        //Товары (Роллы)
        //(Классические роллы)
        saveProduct("Ахи","Классические роллы Ахи",250.00,classicRolls);
        saveProduct("Хамачи","Классические роллы Хамачи",240.00,classicRolls);
        saveProduct("Кани","Классические роллы Кани",290.00,classicRolls);

        //(Запеченные роллы)
        saveProduct("Саба","Запеченные роллы Саба",350.00,bakedRolls);
        saveProduct("Ти","Запеченные роллы Ти",340.00,bakedRolls);
        saveProduct("Магиро","Запеченные роллы Магиро",370.00,bakedRolls);

        //(Сладкие роллы)
        saveProduct("Томаго","Запеченные роллы Саба",310.00,sweetRolls);
        saveProduct("Торо","Запеченные роллы Ти",320.00,sweetRolls);
        saveProduct("Магуро","Запеченные роллы Магиро",360.00,sweetRolls);

        //(Наборы роллы)
        saveProduct("Антикризисный сет №6","Антикризисный сет №6",1510.00,setsRolls);
        saveProduct("Антикризисный сет №7","Антикризисный сет №7",1320.00,setsRolls);
        saveProduct("Антикризисный сет №8","Антикризисный сет №8",1260.00,setsRolls);

        //Товары (Бургеры)
        //(Классические бургеры)
        saveProduct("Чикенбургер","Классический бургер Чикенбургер",100.00,classicBurgers);
        saveProduct("Фишбургер","Классический бургер Фишбургер",120.00,classicBurgers);
        saveProduct("Чизбургер","Классический бургер Чизбурге",90.00,classicBurgers);

        //(Острые бургеры)
        saveProduct("Спайстибургер","Острый бургер Спайстибургер",150.00,spicyBurgers);
        saveProduct("Спайстибургер №2","Острый бургер Спайстибургер №2",160.00,spicyBurgers);
        saveProduct("Спайстибургер №3","Острый бургер Спайстибургер №3",170.00,spicyBurgers);

        //Товары (Напитки)
        //(Газированные напитки)
        saveProduct("Газированная вода","Газированные напитки Газированная вода",40.00,carbonatedDrinks);
        saveProduct("Coca-Cola","Газированные напитки Coca-Cola",80.00,carbonatedDrinks);
        saveProduct("Фанта","Газированные напитки Фанта",70.00,carbonatedDrinks);

        //(Энергетические напитки)
        saveProduct("Red Bull","Энергетический напиток Red Bull",110.00,energyDrinks);
        saveProduct("Gorilla Energy","Энергетический напиток Gorilla Energy",60.00,energyDrinks);
        saveProduct("Tornado Energy","Энергетический напиток Tornado Energy",70.00,energyDrinks);

        //(Соки)
        saveProduct("Яблочный сок","Соки Яблочный сок",70.00,juices);
        saveProduct("Манговый сок","Соки Манговый сок",90.00,juices);
        saveProduct("Банановый сок","Соки Банановый сок",90.00,juices);

        //(Другие напитки)
        saveProduct("Белое вино Селлар Мастер Шардоне","Напиток Белое вино Селлар Мастер Шардоне",370.00,otherDrinks);
        saveProduct("Пиво Spaten, Munchen","Напиток Пиво Spaten, Munchen",100.00,otherDrinks);
        saveProduct("Tequila Mixta","Напиток Tequila Mixta",500.00,otherDrinks);

    }

}

