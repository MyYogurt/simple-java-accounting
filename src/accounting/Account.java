package accounting;

/**
 * Holds information such as type, balance, and name
 */
public class Account {
    /**
     * Name of account
     */
    private String name, balanceString;
    /**
     * Balance is multiplied by 100. Example: $54.87 is represented by 5487
     */
    private int balance;

    private final AccountType accountType;

    /**
     * Creates a new account
     *
     * @param name    Name of account
     * @param balance Balance of account
     */
    public Account(String name, int balance, AccountType accountType) {
        this.name = name;
        this.balance = balance;
        this.accountType = accountType;
        this.balanceString = new String("$" + balance / 100 + "." + balance % 100);
    }

    /**
     * Returns the name of the account
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the balance of account
     *
     * @return balance
     */
    public int getBalanceRaw() {
        return balance;
    }

    /**
     * Returns a formatted string of the current balance
     * @return balanceString
     */
    public String getBalanceString() {
        return balanceString;
    }

    /**
     * Sets the balance of the account
     *
     * @param balance New account balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
        this.balanceString = new String("$" + balance / 100 + "." + balance % 100);
    }

    /**
     * Sets the name of the account
     *
     * @param name Account name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the account type
     * @return account type
     */
    public AccountType getType() {
        return accountType;
    }
}
