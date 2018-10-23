package com.travel.expence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TotalBalanceSheet extends BalanceSheet {

    private static List<Traveller> travellerList;

    private static TotalBalanceSheet totalBalanceSheet = null;

    private TotalBalanceSheet() {
	super();
	setBalanceAmount(0);
    }

    public static TotalBalanceSheet getInstance() {
	if (Objects.isNull(totalBalanceSheet)) {
	    totalBalanceSheet = new TotalBalanceSheet();
	}
	return totalBalanceSheet;
    }

    public List<Traveller> getTravellerList() {
	if (Objects.isNull(travellerList)) {
	    travellerList = new ArrayList<Traveller>();
	}
	return travellerList;
    }

    public void addTraveller(Traveller traveller) {
	if (Objects.isNull(travellerList)) {
	    travellerList = new ArrayList<Traveller>();
	    travellerList.add(traveller);
	} else {
	    travellerList.add(traveller);
	}
    }

    @Override
    public void viewBalanceSheet() {

	System.out.println("\nTotal Balance: " + balanceAmount);

	if (Objects.isNull(history)) {
	    System.out.println("No transactions are performed");
	} else {
	    history.stream().collect(Collectors.collectingAndThen(Collectors.toList(), lst -> {
		Collections.reverse(lst);
		return lst.stream();
	    })).forEach(action -> {
		System.out.println("Transaction Type : " + action.getTransactionType());
		System.out.println("Initiator: " + action.getInitiator().getName());

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

}
