package com.travel.expence.entity;

import com.travel.expence.config.Status;

import lombok.Data;

@Data
public abstract class Transaction {
    private Integer amount;
    private String description;
    public Status status;

    private Traveller receiver;
    private Traveller initiator;

    public String getTransactionType() {
	return this.getClass().getSimpleName().toString();
    }

    public Transaction(Traveller initiator, Traveller receiver, Integer amount, String description) {
	this.amount = amount;
	this.description = description;
	this.initiator = initiator;
	this.receiver = receiver;
	status = Status.NEW;
    }

    public Transaction(Traveller initiator, Integer amount, String description) {
	this.amount = amount;
	this.description = description;
	this.initiator = initiator;
	status = Status.NEW;
    }

}
