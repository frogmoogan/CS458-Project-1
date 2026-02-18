public class Main {
    int keysize = 0;
    String stringkey = "";
    String nonce = "";
    String plaintxt = "";
    //not needed necessarily
    //String ciphertxt = "";

    /* 
    static int quarterround(y0,y1,y2,y3) {
        //whatever quarterround does
        //come back to this later
         
  }
  */

  public static void main(String[] args) {
        Main myObj = new Main();
        // Checking if length of args array is
        // greater than 0
        if (args.length > 0) {

            // Print statements
            /* 
            System.out.println("The command line arguments are:");

            // using for each loop
            for (String val : args)
                System.out.println(val);
            */
        }
        else{

            System.out.println("No command line arguments found.");
        }

        //reassigning values
        myObj.keysize = Integer.valueOf(args[0]);
        myObj.stringkey = args[1];
        myObj.nonce = args[2];
        myObj.plaintxt = args[3];
        //myObj.ciphertxt = args[4];

        //print check reassigning workability
        /*
        System.out.println("keysize : " + myObj.keysize);
        System.out.println("stringkey : " + myObj.stringkey);
        System.out.println("nonce : " + myObj.nonce);
        System.out.println("plaintxt : " + myObj.plaintxt);
        */
        //System.out.println("ciphertxt : " + myObj.ciphertxt);

    }
 }
