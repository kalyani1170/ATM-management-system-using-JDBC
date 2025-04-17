import java.util.HashMap;
import java.util.Scanner;

class User {
    private final String pin;
    private double balance;

    public User(String pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class ATMManagementSystem {
    // Using final for variables that are initialized once and not reassigned
    private static final HashMap<String, User> users = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);  // Marked as final

    public static void main(String[] args) {
        // Initialize some dummy users (userId, pin, balance)
        users.put("user1", new User("1234", 5000));
        users.put("user2", new User("5678", 3000));
        
        System.out.println("Welcome to the ATM Management System");
        
        // Authentication
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        if (users.containsKey(userId)) {
            User currentUser = users.get(userId);
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            
            if (currentUser.getPin().equals(enteredPin)) {
                atmOperations(currentUser);
            } else {
                System.out.println("Incorrect PIN. Access Denied.");
            }
        } else {
            System.out.println("User ID not found.");
        }
    }

 
private static void atmOperations(User user) {
    int option;
    do {
        System.out.println("\nATM Operations:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Fund Transfer");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        option = scanner.nextInt();
        
        switch (option) {
            case 1 -> checkBalance(user);
            case 2 -> withdrawCash(user);
            case 3 -> fundTransfer(user);
            case 4 -> System.out.println("Thank you for using the ATM.");
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (option != 4);
}

    private static void checkBalance(User user) {
        System.out.println("Your current balance is: " + user.getBalance());
    }

    private static void withdrawCash(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            System.out.println("Transaction successful. Your new balance is: " + user.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private static void fundTransfer(User user) {
        System.out.print("Enter recipient User ID: ");
        scanner.nextLine(); // To consume newline after nextInt()
        String recipientId = scanner.nextLine();
        if (users.containsKey(recipientId)) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            User recipient = users.get(recipientId);
            
            if (amount > 0 && amount <= user.getBalance()) {
                user.setBalance(user.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                System.out.println("Transfer successful. Your new balance is: " + user.getBalance());
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Recipient User ID not found.");
        }
    }
}
