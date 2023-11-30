import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.security.SecureRandom;

public class PasswordManager {
    public static void main(String[] args) {
        Scanner ui = new Scanner(System.in);
        String userInput = "";
        // File to store user credentials
        File creds = new File("credentials.csv");

        // Main loop for the UI(user Interaction)
        while (!userInput.equals("Q")) {
            System.out.println("Would you like to login (L), create an account (C), delete account (D), generate a password (G), or quit (Q)?");
            userInput = ui.nextLine();

            // Branch based on user input so each one contains a method like for example c Calls to the WriteToFile method to create a new account, L calls for login method etc
            if (userInput.equals("C")) {
                
                WriteToFile();
            } else if (userInput.equals("L")) {
                login();
            } else if (userInput.equals("G")) {
                // Call's the generatePassword method as well as it  display's the generated password
                System.out.println("Generated Password: " + generatePassword());
            } else if (userInput.equals("D")) {

                deleteAccount();
            }
        }
        System.out.println("Logging Off");
    }

    // Method to delete user account
    public static void deleteAccount() {
        Scanner ui = new Scanner(System.in);
        String usernameToDelete;
        try {
            File inputFile = new File("credentials.csv");
            File tempFile = new File("temp.csv");
    
            FileWriter writer = new FileWriter(tempFile);
            Scanner reader = new Scanner(inputFile);
    
            System.out.println("Enter the username to delete: ");
            usernameToDelete = ui.nextLine();
    
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                String username = parts[0];
    
                if (!username.equals(usernameToDelete)) {
                    // Write user information to temporary file if the username does not match
                    writer.write(line + "\n");
                } else {
                    System.out.println("Account deleted: " + line);
                }
            }
    
            writer.close();
            reader.close(); // Close the Scanner before attempting to delete the file
    
            // Rename the temporary file to the original file
            if (tempFile.renameTo(inputFile)) {
                System.out.println("Account deletion successful.");
            } else {
                System.out.println("Error deleting account.");
            }
    
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method to write user information to file
    public static void WriteToFile() {
        Scanner ui = new Scanner(System.in);
        String userInput = "";
        try {
            FileWriter myWriter = new FileWriter("credentials.csv", true);
            System.out.println("Create an account.");
            System.out.println("Username: ");
            userInput = ui.nextLine();
            myWriter.write(userInput + ",");

            System.out.println("Would you like to generate a password(G) or create your own?(C)");
            userInput = ui.nextLine();
            if (userInput.equals("G")) {
                // Call the generatePassword method and display the generated password
                System.out.println("Generated Password: " + generatePassword());
                myWriter.write(generatePassword() + "\n");
                myWriter.close();
            } else if (userInput.equals("C")) {
                System.out.println("Password: ");
                userInput = ui.nextLine();
                // Write user-input password to file
                myWriter.write(userInput + "\n");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    // Method for user login
    public static void login() {
        Scanner ui = new Scanner(System.in);
        try {
            File myObj = new File("credentials.csv");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("Username: ");
                String username = ui.nextLine();
                System.out.println("Password: ");
                String password = ui.nextLine();
                // Checks to see  if the entered username and password match the stored credentials
                if (username.equals(data.substring(0, data.indexOf(","))) && password.equals(data.substring(data.indexOf(",") + 1, data.length()))) {
                    System.out.println("Logged in");
                    return; // Exit the method and logs off
                }
            }
            System.out.println("Login failed. Please check your username and password.");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method to generate random passwords
    public static String generatePassword() {
        // Defines the characters that you can use for  a password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        // Set the length of the password to 12
        int length = 12;
        // Use SecureRandom to generate a random password
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
