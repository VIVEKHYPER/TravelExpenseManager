package com.travel.expence.entity;

public class Credit extends Transaction {

    public Credit(Traveller initiator, Traveller receiver, Integer amount, String description) {
	super(initiator, receiver, amount, description);
    }

    public static Transaction debitTransform(Transaction credit) {
	Transaction debit = new Debit(credit.getReceiver(), credit.getInitiator(), credit.getAmount(),
		credit.getDescription());
	return debit;
    }
}
