import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;
public class passwordManager{
    public static void main(String[] args) {
        Scanner ui = new Scanner(System.in);
        String userInput = "";
        File creds = new File("credentials.csv");
        
        
        while(!userInput.equals("q")){   
            System.out.println("would you like to login(L) or create an account?(C)");
            userInput=ui.nextLine();
            if(userInput.equals("C")){
                WriteToFile();
            }
            else if(userInput.equals("L")){
                try {
                    File myObj = new File("credentials.csv");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                      String data = myReader.nextLine();
                      System.out.println("Username: ");
                      userInput=ui.nextLine();
                      String username = userInput;
                      System.out.println("password: ");
                      userInput=ui.nextLine();
                      String password = userInput;
                      if(username.equals(data.substring(0, data.indexOf(","))) && password.equals(data.substring(data.indexOf(",")+1, data.length()))){
                        System.out.println("logged in");
                    }
                }
                    myReader.close();
                  } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
                }
              
            }
        System.out.println("bye bye");
    }



    public static void WriteToFile(){
            Scanner ui = new Scanner(System.in);
            String userInput = "";
          try {
            FileWriter myWriter = new FileWriter("credentials.csv",true);
            System.out.println("Create an account.");
            System.out.println("Username: ");
            userInput=ui.nextLine();
            myWriter.write(userInput + ",");
            System.out.println("Password: ");
            userInput=ui.nextLine();
            myWriter.write(userInput + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}