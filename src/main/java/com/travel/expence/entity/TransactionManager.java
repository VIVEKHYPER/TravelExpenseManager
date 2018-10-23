package com.travel.expence.entity;

import java.util.Objects;

import com.travel.expence.config.Status;

public class TransactionManager {

    private static TotalBalanceSheet totalBalanceSheet;

    private static TransactionManager transactionManager;

    private TransactionManager() {
	totalBalanceSheet = TotalBalanceSheet.getInstance();
    }

    public static TransactionManager getInstance() {
	if (Objects.isNull(transactionManager)) {
	    transactionManager = new TransactionManager();
	}
	return transactionManager;
    }

    public boolean spend(Traveller initiator, Integer amount, String description) {
	Debit debit = new Debit(initiator, amount, description);
	BalanceSheet initIndivBalSheet = initiator.getIndivBalanceSheet();
	boolean transactionStatus = initIndivBalSheet.deductBalance(debit.getAmount());
	if (transactionStatus) {
	    debit.setStatus(Status.PASS);
	    initIndivBalSheet.addHistory(debit);
	    totalBalanceSheet.addHistory(debit);
	    System.out.println("Transaction complete \nYour balance amount: " + initIndivBalSheet.getBalanceAmount());
	    return transactionStatus;
	} else {
	    System.out.println("Insufficient funds");
	    return false;
	}
    }

    public void processTransaction(Transaction transaction) {
	switch (transaction.getTransactionType().toLowerCase()) {
	case "debit":
	    doDebit(transaction);
	    break;
	case "credit":
	    doCredit(transaction);
	    break;
	}

    }

    private void doDebit(Transaction transaction) {
	IndivBalanceSheet initIndivBalSheet = transaction.getInitiator().getIndivBalanceSheet();
	boolean transactionStatus = initIndivBalSheet.deductIndivBalance(transaction.getAmount());
	if (transactionStatus) {
	    transaction.setStatus(Status.PASS);
	    initIndivBalSheet.addHistory(transaction);
	    totalBalanceSheet.addHistory(transaction);
	    doPostDebit(transaction);
	    System.out.println("Transaction complete \nYour balance amount: " + initIndivBalSheet.getBalanceAmount());
	} else {
	    System.out.println("Insufficient funds");
	}
    }

    private static void doPostDebit(Transaction transaction) {
	IndivBalanceSheet recvIndivBalSheet = transaction.getReceiver().getIndivBalanceSheet();
	Transaction credit = Debit.creditTransform(transaction);
	recvIndivBalSheet.addBalance(transaction.getAmount());
	credit.setStatus(Status.PASS);
	recvIndivBalSheet.addHistory(credit);
    }

    private void doCredit(Transaction transaction) {
	IndivBalanceSheet initIndivBalSheet = transaction.getInitiator().getIndivBalanceSheet();
	IndivBalanceSheet recIndivBalSheet = transaction.getReceiver().getIndivBalanceSheet();

	if (recIndivBalSheet.getBalanceAmount() - transaction.getAmount() >= 0) {
	    initIndivBalSheet.addBalance(transaction.getAmount());
	    transaction.setStatus(Status.PASS);
	    initIndivBalSheet.addHistory(transaction);
	    totalBalanceSheet.addHistory(transaction);
	    doPostCredit(transaction);
	    System.out.println("Transaction complete \nYour balance amount: " + initIndivBalSheet.getBalanceAmount());
	} else {
	    System.out.println("Insufficient funds in creditor");
	}
    }

    private static void doPostCredit(Transaction transaction) {
	IndivBalanceSheet recvIndivBalSheet = transaction.getReceiver().getIndivBalanceSheet();
	Transaction debit = Credit.debitTransform(transaction);
	recvIndivBalSheet.deductIndivBalance(transaction.getAmount());
	debit.setStatus(Status.PASS);
	recvIndivBalSheet.addHistory(debit);
    }

}
