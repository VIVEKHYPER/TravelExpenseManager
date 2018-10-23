package com.travel.expence.service;

import java.util.Scanner;

import com.travel.expence.entity.TotalBalanceSheet;
import com.travel.expence.entity.Traveller;

public class Client {
    public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);

	boolean exit = false;

	do {
	    System.out.println("**************   Travel Expence Management System   ********************");
	    System.out.println("# Please select any of following options");
	    if (TotalBalanceSheet.getInstance().getTravellerList().isEmpty()) {
		System.out.println("1 -> New Traveller");
		System.out.println("2 -> Stop Trip");
		switch (sc.nextLine()) {
		case "1":
		    createTraveller();
		    break;
		case "2":
		    exit = true;
		    break;
		default:
		    continue;
		}
	    } else {
		System.out.println("1 -> New Traveller");
		System.out.println("2 -> Select Traveller");
		System.out.println("3 -> Total Balance sheet");
		System.out.println("4 -> Stop Trip");
		switch (sc.nextLine()) {
		case "1":
		    createTraveller();
		    break;
		case "2":
		    Traveller traveller = Traveller.selectTraveller();
		    makeTransactions(traveller);
		    break;
		case "3":
		    TotalBalanceSheet.getInstance().viewBalanceSheet();
		    break;
		case "4":
		    exit = true;
		    break;
		default:
		    continue;
		}
	    }
	} while (exit == false);

    }

    private static void makeTransactions(Traveller traveller) {
	Scanner sc = new Scanner(System.in);
	boolean exit = false;
	do {
	    System.out.println("***** " + traveller.getName() + " *****");
	    System.out.println("1 -> Spend money");
	    System.out.println("2 -> Borrow");
	    System.out.println("3 -> Lend");
	    System.out.println("4 -> View balancesheet");
	    System.out.println("5 -> Go back");
	    switch (sc.nextLine()) {
	    case "1":
		traveller.spendMoney();
		break;
	    case "2":
		traveller.borrowMoney();
		break;
	    case "3":
		traveller.lendMoney();
		break;
	    case "4":
		traveller.viewBalanceSheet();
		break;
	    case "5":
		exit = true;
	    default:
		continue;
	    }
	} while (exit == false);

    }

    private static void createTraveller() {

	boolean exit = false;
	String name = "none";
	Integer balance = 0;
	Scanner sc = new Scanner(System.in);

	do {
	    System.out.println("*****  Creating traveller  *****");
	    System.out.println("1 -> Set name");
	    System.out.println("2 -> Set balance");
	    System.out.println("3 -> Create traveller");
	    System.out.println("4 -> Go back");
	    switch (sc.nextLine()) {
	    case "1":
		name = sc.nextLine();
		break;
	    case "2":
		balance = Integer.valueOf(sc.nextLine());
		break;
	    case "3":
		new Traveller(name, balance);
		exit = true;
		break;
	    case "4":
		exit = true;
		break;
	    default:
		continue;
	    }
	} while (exit == false);
    }
}
