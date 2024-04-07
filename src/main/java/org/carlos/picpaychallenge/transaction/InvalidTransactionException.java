package org.carlos.picpaychallenge.transaction;

public class InvalidTransactionException extends RuntimeException {

    public InvalidTransactionException(String message) {
        super(message);
    }

    public InvalidTransactionException(Transaction transaction) {
        super("Invalid Transaction - %s".formatted(transaction));
    }
}
