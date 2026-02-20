public class Main {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("Usage: java Main <keySize> <key> <nonce> <plaintext>");
            return;
        }

        int keySize = Integer.parseInt(args[0]);

        int[] key = hexStringToIntArray(args[1]);
        int[] nonce = hexStringToIntArray(args[2]);
        int[] plaintext = hexStringToIntArray(args[3]);

        salsa20 cipher = new salsa20(keySize, key, nonce, plaintext);

        //individual function test cases//
        //salsa20.testQuarterRound();
        //salsa20.testrowRound();
        //salsa20.testcolumnRound();
        //salsa20.testdoubleRound();
        //salsa20.testlittleendian();
        //salsa20.testhash();
        //salsa20.testexpansion();
        
        int[] ciphertext = cipher.encryption(key, nonce, plaintext);

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (int b : ciphertext) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        sb.append("\"");

        System.out.println(sb.toString());
    }

    private static int[] hexStringToIntArray(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }
        int[] bytes = new int[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}

     

        


