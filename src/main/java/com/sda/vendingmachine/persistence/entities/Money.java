package com.sda.vendingmachine.persistence.entities;

import com.sda.vendingmachine.service.Coin;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coin_id;

    @Column(name = "coin_type")
    private Coin coin;

    private int quantity;

    public Money(Coin coin, int quantity) {
        this.coin = coin;
        this.quantity = quantity;
    }

    public Money() {
    }

    public Coin getCoin() {
        return coin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public void setQuantity( int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Money{" +
                "coin=" + coin +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return quantity == money.quantity &&
                coin == money.coin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coin, quantity);
    }
}
