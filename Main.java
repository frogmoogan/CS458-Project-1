public class Main {

    public static void main(String[] args) {

        
        if (args.length != 4) {
            System.out.println("Usage: java Main <keySize> <key> <nonce> <plaintext>");
            return;
        }
        

        int keySize = Integer.parseInt(args[0]);
        byte[] key = args[1].getBytes();
        byte[] nonce = args[2].getBytes();
        byte[] plaintext = args[3].getBytes();

        salsa20 cipher = new salsa20(keySize, key, nonce, plaintext);
        //salsa20.testQuarterRound();
        //salsa20.testrowRound();
        //salsa20.testcolumnRound();
        //salsa20.testdoubleRound();

        //byte[] ciphertext = cipher.encrypt(plaintext);

        //System.out.println(new String(ciphertext));
    }
}
