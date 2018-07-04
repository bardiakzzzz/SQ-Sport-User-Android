package ir.sq.apps.squserside.models;


public class Transaction {

    private int id;
    private int debit;
    private int date;
    private int time;
    private int transactionMoney;

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(int transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

}
