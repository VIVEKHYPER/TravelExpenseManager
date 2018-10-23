package com.travel.expence.entity;

public class Debit extends Transaction {

    public Debit(Traveller initiator, Traveller receiver, Integer amount, String description) {
	super(initiator, receiver, amount, description);
    }

    public Debit(Traveller traveller, Integer amount, String description) {
	super(traveller, amount, description);
    }

    public static Transaction creditTransform(Transaction debit) {
	Transaction credit = new Credit(debit.getReceiver(), debit.getInitiator(), debit.getAmount(),
		debit.getDescription());
	return credit;
    }

}
