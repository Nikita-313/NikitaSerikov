package com.tgbot.TgBot.Entity;


import javax.persistence.*;

@Entity
public class ClientOrder {

    @Id
    @GeneratedValue
    private Long id; // уникальный идентификатор

    @Column(nullable = false)
    private Integer status; // статус заказа

    @Column(nullable = false, length = 15,precision = 2)
    private Double total; //сумма по заказу

    @ManyToOne
    private Сlient client; // идентификатор клиента


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Сlient getClient() {
        return client;
    }

    public void setClient(Сlient client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }
}