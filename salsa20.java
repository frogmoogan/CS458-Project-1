public class salsa20 {

    //making these arrays for easier access
    private int keySize;
    private byte[] key;
    private byte[] nonce;
    private byte[] plaintext;

    //constructor//
    public salsa20(int keySize, byte[] key, byte[] nonce, byte[] plaintext) {
        //some sort of validation (?)

        this.keySize = keySize;
        this.key = key.clone();
        this.nonce = nonce.clone();
        this.plaintext = plaintext.clone();
    }

    //helper functions//
    private int rotateLeft(int value, int bits) {
        return Integer.rotateLeft(value, bits);
    }

    
    //general functions//
    /* 
    public byte[] encrypt(byte[] plaintext) {
        return process(plaintext);
    }

    public byte[] decrypt(byte[] ciphertext) {
        // Salsa20 is symmetric
        return process(ciphertext);
    }

    private byte[] process(byte[] input) {
        // 1. Split into 64-byte blocks
        // 2. Generate keystream blocks
        // 3. XOR keystream with input
        // 4. Increment counter per block
        return null; // placeholder
    }

    private byte[] generateKeystreamBlock(long counter) {
        // 1. Build initial 16-word state
        // 2. Run salsa20Hash
        // 3. Convert words to bytes
        return null;
    }
    */

    //testing functions//
    /*
    public static void testQuarterRound() {
        int[] input = {0x00000000, 0x00000000, 0x00000001, 0x00000000};
        int[] output = quarterRound(input);

        System.out.println("Input: ");
        for (int i : input) System.out.printf("%08x ", i);
        System.out.println("\nOutput: ");
        for (int i : output) System.out.printf("%08x ", i);
    }
    

    public static void testrowRound() {
        int[] input = {0x00000001, 0x00000000, 0x00000000, 0x00000000,
0x00000001, 0x00000000, 0x00000000, 0x00000000,
0x00000001, 0x00000000, 0x00000000, 0x00000000,
0x00000001, 0x00000000, 0x00000000, 0x00000000};
        int[] output = rowRound(input);

        System.out.println("Input: ");
        for (int i : input) System.out.printf("%08x ", i);
        System.out.println("\nOutput: ");
        for (int i : output) System.out.printf("%08x ", i);
    }
        

    public static void testcolumnRound() {
        int[] input = {0x08521bd6, 0x1fe88837, 0xbb2aa576, 0x3aa26365,
0xc54c6a5b, 0x2fc74c2f, 0x6dd39cc3, 0xda0a64f6,
0x90a2f23d, 0x067f95a6, 0x06b35f61, 0x41e4732e,
0xe859c100, 0xea4d84b7, 0x0f619bff, 0xbc6e965a};
        int[] output = columnRound(input);

        System.out.println("Input: ");
        for (int i : input) System.out.printf("%08x ", i);
        System.out.println("\nOutput: ");
        for (int i : output) System.out.printf("%08x ", i);
    }

    

    public static void testdoubleRound() {
        int[] input = {0xde501066, 0x6f9eb8f7, 0xe4fbbd9b, 0x454e3f57,
0xb75540d3, 0x43e93a4c, 0x3a6f2aa0, 0x726d6b36,
0x9243f484, 0x9145d1e8, 0x4fa9d247, 0xdc8dee11,
0x054bf545, 0x254dd653, 0xd9421b6d, 0x67b276c1};
        int[] output = doubleRound(input);

        System.out.println("Input: ");
        for (int i : input){
         System.out.printf("%08x ", i);
        }
        System.out.println("\nOutput: ");
        for (int i : output) System.out.printf("%08x ", i);
    }
    
    */

    public static void testlittleendian() {
        int[] input = {0,0,0,0};
        long output = littleendian(input);

        System.out.println("Input: ");
        for (int i : input){
            System.out.printf("%08x ", i);
        }
        System.out.printf("\n%08x ", output);
       
    }
    

    //backend functions//
    private static int[] quarterRound(int[] input) {
        //output matrix
        int[] z = new int[4];

        int y0 = input[0];
        int y1 = input[1];
        int y2 = input[2];
        int y3 = input[3];

        //using addition which is mod 2^32
        int z1 = y1 ^ Integer.rotateLeft(y0 + y3, 7);
        int z2 = y2 ^ Integer.rotateLeft(z1 + y0, 9);
        int z3 = y3 ^ Integer.rotateLeft(z2 + z1, 13);
        int z0 = y0 ^ Integer.rotateLeft(z3 + z2, 18);

        z[0] = z0;
        z[1] = z1;
        z[2] = z2;
        z[3] = z3;

        return z;
    }

    private static int[] rowRound(int[] input) {
        //output matrix
        int[] z = new int[16];

        int[] qr0 = quarterRound(new int[]{input[0], input[1], input[2], input[3]});
        z[0] = qr0[0];
        z[1] = qr0[1];
        z[2] = qr0[2];
        z[3] = qr0[3];

        int[] qr1 = quarterRound(new int[]{input[5], input[6], input[7], input[4]});
        z[5] = qr1[0];
        z[6] = qr1[1];
        z[7] = qr1[2];
        z[4] = qr1[3];

        int[] qr2 = quarterRound(new int[]{input[10], input[11], input[8], input[9]});
        z[10] = qr2[0];
        z[11] = qr2[1];
        z[8] = qr2[2];
        z[9] = qr2[3];

        int[] qr3 = quarterRound(new int[]{input[15], input[12], input[13], input[14]});
        z[15] = qr3[0];
        z[12] = qr3[1];
        z[13] = qr3[2];
        z[14] = qr3[3];

        return z;
    }

    private static int[] columnRound(int[] input) {
        //output matrix
        int[] z = new int[16];

        int[] qr0 = quarterRound(new int[]{input[0], input[4], input[8], input[12]});
        z[0] = qr0[0];
        z[4] = qr0[1];
        z[8] = qr0[2];
        z[12] = qr0[3];

        int[] qr1 = quarterRound(new int[]{input[5], input[9], input[13], input[1]});
        z[5] = qr1[0];
        z[9] = qr1[1];
        z[13] = qr1[2];
        z[1] = qr1[3];

        int[] qr2 = quarterRound(new int[]{input[10], input[14], input[2], input[6]});
        z[10] = qr2[0];
        z[14] = qr2[1];
        z[2] = qr2[2];
        z[6] = qr2[3];

        int[] qr3 = quarterRound(new int[]{input[15], input[3], input[7], input[11]});
        z[15] = qr3[0];
        z[3] = qr3[1];
        z[7] = qr3[2];
        z[11] = qr3[3];

        return z;
    }

    private static int[] doubleRound(int[] input) {
        return rowRound(columnRound(input));
    }

    private static long littleendian(int[] input) {
        long b0 = input[0];
        long b1 = input[1];
        long b2 = input[2];
        long b3 = input[3];

        //long to keep all the components
        long b = (long) (b0 + (b1 * Math.pow(2,8)) + (b2 * Math.pow(2,16)) + (b3 * Math.pow(2,24)));
        return b;
    }

    /* 
    private static int[] hash(int[] input) {
        //first section,
       

    }
    */
    

}


