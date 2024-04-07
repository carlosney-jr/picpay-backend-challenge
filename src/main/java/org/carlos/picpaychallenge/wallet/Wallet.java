package org.carlos.picpaychallenge.wallet;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "WALLETS")
public final class Wallet {
    @Id
    private Long id;
    private String fullName;
    private Long cpf;
    private String email;
    private String password;
    private WalletType type;
    private BigDecimal balance;

    public Wallet() {
    }

    public Wallet(
            Long id,
            String fullName,
            Long cpf,
            String email,
            String password,
            WalletType type,
            BigDecimal balance
    ) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance;
    }

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.add(value));
    }

    @Id
    public Long id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public Long cpf() {
        return cpf;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public WalletType type() {
        return type;
    }

    public BigDecimal balance() {
        return balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Wallet) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.fullName, that.fullName) &&
                Objects.equals(this.cpf, that.cpf) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, cpf, email, password, type, balance);
    }

    @Override
    public String toString() {
        return "Wallet[" +
                "id=" + id + ", " +
                "fullName=" + fullName + ", " +
                "cpf=" + cpf + ", " +
                "email=" + email + ", " +
                "password=" + password + ", " +
                "type=" + type + ", " +
                "balance=" + balance + ']';
    }

}
