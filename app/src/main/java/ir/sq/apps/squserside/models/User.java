package ir.sq.apps.squserside.models;

import java.util.ArrayList;
import java.util.List;

public class User {


    private Long id;
    private String name = "";
    private String userName = "";
    private String passWord = "";
    private String email = "";
    private List<Receipt> receiptList;

    private List<Transaction> transaction;
    private double credit;

    public User(Long id, String name, String userName, String passWord, String email) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        receiptList = new ArrayList<>();
        this.id = id;
    }

    public User() {
        receiptList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public void addReceipt(Receipt receipt) {
        receiptList.add(receipt);
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }
}
