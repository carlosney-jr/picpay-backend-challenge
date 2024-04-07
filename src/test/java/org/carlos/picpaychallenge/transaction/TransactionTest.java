package org.carlos.picpaychallenge.transaction;

import org.carlos.picpaychallenge.wallet.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionTest {

    private TransactionRepository transactionRepository;

    private WalletRepository walletRepository;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        walletRepository = mock(WalletRepository.class);
        when(transactionRepository.save(any())).thenReturn(new Transaction(1L, 1L, 2L, new BigDecimal(100), LocalDateTime.now()));
    }

    @Test
    void testarSalvaTransacao() {
        TransactionService transacaoService = new TransactionService(transactionRepository, walletRepository, null, null);

        Transaction transaction = new Transaction(null, 1L, 2L, new BigDecimal(100), null);
        Transaction newTransaction = transacaoService.createTransaction(transaction);

        assertThat(new Transaction(1L, 1L, 2L, new BigDecimal(100), null)).isEqualTo(newTransaction);
    }
}
