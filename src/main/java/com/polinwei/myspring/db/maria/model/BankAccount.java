package com.polinwei.myspring.db.maria.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    private long id;
    private double balance;
    private String fullName;

    public BankAccount() {
    }

    public BankAccount(long id, double balance, String fullName) {
        this.id = id;
        this.balance = balance;
        this.fullName = fullName;
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 128)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, balance, fullName);
    }
}
