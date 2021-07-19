package com.tgbot.TgBot.Services;


import com.tgbot.TgBot.Entity.*;
import com.tgbot.TgBot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.tgbot.TgBot.Services.EntitiesService;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntityServiceImpl implements EntitiesService{

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OrderProductRepo orderProductRepo;

    @Override
    public List<Сlient> getAllClients(){
        return clientRepo.findAll();
    }

    @Override
    public Сlient getClientById(Long id){
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    public Сlient getClientByName(String name) {
        Сlient client = new Сlient();
        client.setFullName(name);
        return clientRepo.findOne(Example.of(client)).orElse(null);
    }

    @Override
    public List<ClientOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public ClientOrder getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<ClientOrder> getOrdersByStatus(Integer status) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setStatus(status);
        return orderRepo.findAll(Example.of(clientOrder));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product getProductsByName(String name) {
        Product product = new Product();
        product.setDescription(name);
        return productRepo.findOne(Example.of(product)).orElse(null);
    }

    @Override
    public List<Product> getCategoryProducts(Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        Product product = new Product();
        product.setCategory(category);
        return productRepo.findAll(Example.of(product));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<ClientOrder> getClientOrdersByName(String name) {
        Сlient client = new Сlient();
        client.setFullName(name);
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        return orderRepo.findAll(Example.of(clientOrder));
    }

    @Override
    public List<Product> getClientProducts(Long id) {
        return productRepo.getClientProducts(id);
    }

    @Override
    public List<Product> getTopPopularProducts(Integer top) {
        return productRepo.getTopPopular().stream().limit(top).collect(Collectors.toList());
    }

    @Override
    public Сlient getСlientByExternalId(Long externalId) {
        Сlient client = new Сlient();
        client.setExternalId(externalId);
        return clientRepo.findOne(Example.of(client)).orElse(null);
    }

    @Override
    public List<Category> getCategoriesByParentId(Long parentId) {
        Category category = new Category();
        category.setParent(parentId);
        return categoryRepo.findAll(Example.of(category));
    }

    @Override
    public List<OrderProduct> getOrderProductSByOrderId(ClientOrder order) {
        return orderProductRepo.getOrderProductSByOrderId(order);
    }

    @Override
    public OrderProduct getOrderProductByOrderIdAndProductId(ClientOrder order, Product product) {
        return orderProductRepo.getOrderProductByOrderIdAndProductId(order,product);
    }


}
