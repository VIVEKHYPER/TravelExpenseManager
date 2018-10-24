package com.travel.expence.entity;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class IndivBalanceSheet extends BalanceSheet {

    private Traveller traveller;

    private TotalBalanceSheet totalBalanceSheet;

    @Override
    public void viewBalanceSheet() {

	System.out.println("\nCurrent Balance: " + balanceAmount);

	if (Objects.isNull(history)) {
	    System.out.println("No transactions are performed");
	} else {
	    history.stream().collect(Collectors.collectingAndThen(Collectors.toList(), lst -> {
		Collections.reverse(lst);
		return lst.stream();
	    })).forEach(action -> {
		System.out.println("Transaction Type : " + action.getTransactionType());
		if (Objects.nonNull(action.getReceiver())) {
		    System.out.println("Counterparty: " + action.getReceiver().getName());
		}
		if (Objects.nonNull(action.getDescription())) {
		    System.out.println("Description :\n " + action.getDescription());
		}
		System.out.println("Amount : " + action.getAmount() + "\n");
	    });
	}
    }

    @Override
    public boolean deductBalance(Integer amount) {
	if (super.deductBalance(amount)) {
	    return totalBalanceSheet.deductBalance(amount);
	} else {
	    return false;
	}
    }

    public boolean deductIndivBalance(Integer amount) {
	return super.deductBalance(amount);
    }

    private IndivBalanceSheet() {
	super();
	super.setBalanceAmount(0);
	this.totalBalanceSheet = TotalBalanceSheet.getInstance();
    }

    public IndivBalanceSheet(Traveller traveller, Integer balanceAmount) {
	this();
	this.traveller = traveller;
	this.setBalanceAmount(balanceAmount);
	totalBalanceSheet.addBalance(balanceAmount);
    }
}
