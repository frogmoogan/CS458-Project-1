public class Main {
  public static void main(String[] args) {
    // Checking if length of args array is
        // greater than 0
        if (args.length > 0) {

            System.out.println("Enter your key length as a hexadecimal string, nonce(IV) represented as a hexadecimal string, and the textstring to be encrypted/decrypted in hexadecimal format:");
            // Print statements
            System.out.println("The command line"
                               + " arguments are:");

            // using for each loop
            for (String val : args)

                System.out.println(val);
        }
        else{

            System.out.println("No command line "
                               + "arguments found.");
        }
    }
 }
