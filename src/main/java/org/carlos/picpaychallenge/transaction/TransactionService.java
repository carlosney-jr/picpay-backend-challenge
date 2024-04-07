package org.carlos.picpaychallenge.transaction;

import org.carlos.picpaychallenge.authorization.AuthorizerService;
import org.carlos.picpaychallenge.notification.NotificationService;
import org.carlos.picpaychallenge.wallet.Wallet;
import org.carlos.picpaychallenge.wallet.WalletRepository;
import org.carlos.picpaychallenge.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository,
                              WalletRepository walletRepository,
                              AuthorizerService authorizerService,
                              NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        var payeeWallet = walletRepository.findById(transaction.payee()).orElseThrow(() -> new InvalidTransactionException("Payee not found!"));
        var payerWallet = walletRepository.findById(transaction.payer()).orElseThrow(() -> new InvalidTransactionException("Payer not found!"));

        validateTransaction(transaction, payerWallet, payeeWallet);

        var newTransaction = transactionRepository.save(transaction);

        walletRepository.save(payerWallet.debit(transaction.value()));

        walletRepository.save(payeeWallet.credit(transaction.value()));

        authorizerService.authorize(transaction);
        notificationService.notify(transaction);
        return newTransaction;
    }


    private void validateTransaction(Transaction transaction, Wallet payer, Wallet payee) {
        if (payer.balance().compareTo(transaction.value()) < 0) {
            throw new InvalidTransactionException("Not enough balance to transfer");
        }
        if (payer.equals(payee)) {
            throw new InvalidTransactionException("You can't transfer to the same Wallet");
        }
        if (payer.type().equals(WalletType.STORE)) {
            throw new InvalidTransactionException("You can't transfer from a Store Wallet");
        }
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
