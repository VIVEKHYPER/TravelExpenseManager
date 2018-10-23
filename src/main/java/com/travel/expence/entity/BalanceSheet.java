package com.travel.expence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BalanceSheet {

    protected Integer balanceAmount;

    protected List<Transaction> history;

    public abstract void viewBalanceSheet();

    public Integer getBalanceAmount() {
	return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
	this.balanceAmount = balanceAmount;
    }

    public boolean deductBalance(Integer amount) {
	if (balanceAmount < amount) {
	    return false;
	} else {
	    this.balanceAmount -= amount;
	    return true;
	}
    }

    public void addBalance(Integer amount) {
	this.balanceAmount += amount;
    }

    public List<Transaction> getHistory() {
	return history;
    }

    public List<Transaction> getDebitList() {
	return history.stream().filter(predicate -> predicate.getTransactionType().equalsIgnoreCase("Debit"))
		.collect(Collectors.toList());
    }

    public List<Transaction> getCreditList() {
	return history.stream().filter(predicate -> predicate.getTransactionType().equalsIgnoreCase("Credit"))
		.collect(Collectors.toList());
    }

    public void addHistory(Transaction transaction) {
	if (Objects.isNull(this.history)) {
	    this.history = new ArrayList<Transaction>();
	}
	history.add(transaction);
    }

}
