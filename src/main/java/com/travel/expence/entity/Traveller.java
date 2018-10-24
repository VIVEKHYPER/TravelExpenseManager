package com.travel.expence.entity;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.travel.expence.service.TransactionManager;

import lombok.Data;

@Data
public class Traveller {

    private String name;
    private IndivBalanceSheet indivBalanceSheet;

    public Traveller(String name, Integer balanceAmount) {
	this.name = name;
	if (Objects.isNull(this.indivBalanceSheet)) {
	    this.indivBalanceSheet = new IndivBalanceSheet(this, balanceAmount);
	    TotalBalanceSheet.getInstance().addTraveller(this);
	} else {
	    this.indivBalanceSheet.addBalance(balanceAmount);
	    TotalBalanceSheet.getInstance().addTraveller(this);
	}
    }

    public void viewBalanceSheet() {
	indivBalanceSheet.viewBalanceSheet();
    }

    public void spendMoney() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter amount to spend : ");
	Integer amount = Integer.valueOf(sc.nextLine());
	System.out.println("Enter Description: ");
	String description = sc.nextLine();

	TransactionManager transactionManager = TransactionManager.getInstance();
	transactionManager.spend(this, amount, description);
    }

    public void borrowMoney() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Select traveller : ");
	Traveller payer = selectTraveller();
	System.out.println("Enter amount to borrrow : ");
	Integer amount = Integer.valueOf(sc.nextLine());
	System.out.println("Enter Description: ");
	String description = sc.nextLine();

	Transaction credit = new Credit(this, payer, amount, description);
	TransactionManager transactionManager = TransactionManager.getInstance();
	transactionManager.processTransaction(credit);
    }

    public void lendMoney() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Select traveller : ");
	Traveller payer = selectTraveller();
	System.out.println("Enter amount to lend : ");
	Integer amount = Integer.valueOf(sc.nextLine());
	System.out.println("Enter Description: ");
	String description = sc.nextLine();

	Transaction debit = new Debit(this, payer, amount, description);
	TransactionManager transactionManager = TransactionManager.getInstance();
	transactionManager.processTransaction(debit);
    }

    public static Traveller selectTraveller() {
	Traveller traveller = null;
	boolean exit = false;
	Scanner sc = new Scanner(System.in);

	System.out.println("*****  Select traveller  *****");
	List<Traveller> travellerList = TotalBalanceSheet.getInstance().getTravellerList();
	travellerList.stream().forEach(action -> {
	    System.out.println("-> " + String.valueOf(travellerList.indexOf(action) + 1) + " " + action.getName() + " "
		    + action.getIndivBalanceSheet().getBalanceAmount());
	});

	do {
	    String travellerIndex = sc.nextLine();
	    if (Integer.valueOf(travellerIndex) > travellerList.size() || Integer.valueOf(travellerIndex) < 1) {
		System.out.println("Invalid input !! , try again");
	    } else {
		traveller = travellerList.get(Integer.valueOf(travellerIndex) - 1);
		exit = true;
	    }
	} while (exit == false);

	return traveller;
    }
}
