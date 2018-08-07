package com.processingcenter.processingcenter.entity;

import javax.persistence.*;

/**
 * Created by davlet on 1/31/18.
 * Таблица "транзакции" представляет собой переводы денег между счетами пользователей.
 */

@Entity
@Table(name = "transaction")
public class Transaction {
    private Long trxId;
    private Long from_id;
    private Long to_id;
    private Integer amount;

    protected Transaction() {
    }

    public Transaction(Long from_id, Long to_id, Integer amount) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
    }

    public Long getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Long from_id) {
        this.from_id = from_id;
    }


    public Long getTo_id() {
        return to_id;
    }

    public void setTo_id(Long to_id) {
        this.to_id = to_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trxId=" + trxId +
                ", from_id=" + from_id +
                ", to_id=" + to_id +
                ", amount=" + amount +
                '}';
    }
}
