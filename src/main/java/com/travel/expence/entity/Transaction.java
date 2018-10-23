package com.travel.expence.entity;

import com.travel.expence.config.Status;

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
    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public Integer getAmount() {
	return amount;
    }

    public void setAmount(Integer amount) {
	this.amount = amount;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Traveller getInitiator() {
	return initiator;
    }

    public void setInitiator(Traveller initiator) {
	this.initiator = initiator;
    }

    public Traveller getReceiver() {
	return receiver;
    }

    public void setReceiver(Traveller receiver) {
	this.receiver = receiver;
    }

}
