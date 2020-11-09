package net.expense.exception;

public class ExpenseNotFoundException extends Exception {

    private static final long serialVersionUID = 4464805435791446256L;

    public ExpenseNotFoundException(String message) {
        super(message);
    }

}
