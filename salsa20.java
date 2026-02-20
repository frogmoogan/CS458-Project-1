public class salsa20 {

    //making these arrays for easier access//
    private int keySize;
    private int[] key;
    private int[] nonce;
    private int[] plaintext;

    //constructor//
    public salsa20(int keySize, int[] key, int[] nonce, int[] plaintext) {
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


    //testing functions for each individual function//
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
    
    

    public static void testlittleendian() {
        int[] input = {0,0,0,0};
        long output = littleendian(input);

        System.out.println("Input: ");
        for (int i : input){
            System.out.printf("%08x ", i);
        }
        System.out.printf("\n%08x ", output);
       
    }
        
    
    public static void testhash() {
        int[] input = {6,124, 83,146, 38,191, 9, 50, 4,161, 47,222,122,182,223,185,
75, 27, 0,216, 16,122, 7, 89,162,104,101,147,213, 21, 54, 95,
225,253,139,176,105,132, 23,116, 76, 41,176,207,221, 34,157,108,
94, 94, 99, 52, 90,117, 91,220,146,190,239,143,196,176,130,186};
        int[] output = hash(input);

        System.out.println("\nInput: ");
        for (int i = 0; i < input.length; i++) {
        System.out.print(input[i]);
        if (i < input.length - 1) {
            System.out.print(", ");
        }
        }

        System.out.println("\nOutput: ");
        for (int i = 0; i < output.length; i++) {
        System.out.print(output[i]);
        if (i < output.length - 1) {
            System.out.print(", ");
        }
        }
       
    }

    

     public static void testexpansion() {
        int[] k0 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        int[] k1 = {201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216}; 
        int[] n = {101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116};
        int[] output = expansion(k0,n);

        System.out.println("\nOutput: ");
        for (int i = 0; i < output.length; i++) {
        System.out.print(output[i]);
        if (i < output.length - 1) {
            System.out.print(", ");
        }
        }
    }
    */
    

    //backend functions//
    private static int[] quarterRound(int[] input) {
        //output matrix//
        int[] z = new int[4];

        int y0 = input[0];
        int y1 = input[1];
        int y2 = input[2];
        int y3 = input[3];

        //using addition which is mod 2^32//
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
        //output matrix//
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
        //output matrix//
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

    private static int littleendian(int[] input) {
        long b0 = input[0] & 0xff;
        long b1 = input[1] & 0xff;
        long b2 = input[2] & 0xff;
        long b3 = input[3] & 0xff;

        //long to keep all the components//
        //nvm not necesssary, keep int for consistancy//
        int b = (int) (b0 + (b1 * 256) + (b2 * 65536) + (b3 * 16777216));
        return b;
    }

    /* 
    private static int[] bigendian(int word) {
        int[] res = new int[4];

        res[0] = word & 0xff;
        res[1] = (word / 256) & 0xff;
        res[2] = (word / 65536) & 0xff;
        res[3] = (word / 16777216) & 0xff;

        return res;
        
    }
        */
    
    private static int[] hash(int[] input) {
        //convert to 16 words//
        int[] x = new int[16];
        int[] temparr = new int[4];
        for (int i = 0; i < 16; i++) {

            temparr[0] = input[4 * i];
            temparr[1] = input[4 * i + 1];
            temparr[2] = input[4 * i + 2];
            temparr[3] = input[4 * i + 3];
            
            x[i] = (int) littleendian(temparr);
            
        }

        //10 doublerounds//
        //maybe 8 rounds //
        int[] z = x.clone();
        for (int i = 0; i < 8; i++) {
            z = doubleRound(z);
        }
        
        //add words//
        for (int i = 0; i < 16; i++) {
            z[i] += x[i];
        }

        //convert back to 64 bites//
        int[] output = new int[64];
        for (int i = 0; i < 16; i++) {
            int word = z[i];

            output[4*i] =  word & 0xff;
            output[4*i + 1] = (word >>> 8) & 0xff;
            output[4*i + 2] = (word >>> 16) & 0xff;
            output[4*i + 3] = (word >>> 24) & 0xff;

        }

        return output;
    }
    
    //first version//
    private static int[] expansion(int[] k0, int[] k1, int[] n) {
    
        int[] t0 = {101, 120, 112, 97};
        int[] t1 = {110, 100, 32, 51};
        int[] t2 = {50, 45, 98, 121};
        int[] t3 = {116, 101, 32, 107};

        int[] input = new int[64];
        int pos = 0;

        int[][] word = {t0, k0, t1, n, t2, k1, t3};
        for (int[] w : word) {
            System.arraycopy(w, 0, input, pos, w.length);
            pos += w.length;
        }
    
        return hash(input);


    }

    //second version//
    private static int[] expansion(int[] k, int[] n) {
    
        int[] t0 = {101, 120, 112, 97};
        int[] t1 = {110, 100, 32, 49};
        int[] t2 = {54, 45, 98, 121};
        int[] t3 = {116, 101, 32, 107};

        int[] input = new int[64];
        int pos = 0;

        int[][] word = {t0, k, t1, n, t2, k, t3};
        for (int[] w : word) {
            System.arraycopy(w, 0, input, pos, w.length);
            pos += w.length;
        }
    
        return hash(input);


    }

    int[] encryption(int[] key, int[] nonce, int[] plaintxt) {
        //int k = key.length;
        //System.out.printf("keylength", k);
    int[] ciphertext = new int[plaintxt.length];
    long blocks = (plaintxt.length + 63) / 64;
    
    for (long blockNum = 0; blockNum < blocks; blockNum++) {
        int[] counterBytes = new int[8];
        long counter = blockNum;
        for (int j = 0; j < 8; j++) {
            counterBytes[j] = (int)((counter >> (8 * j)) & 0xFF);
        }

        //combine into 48 bytes//
        //send into expansion function after//
        int[] keystreamBlock;
        //for 32byte key case//
        if (key.length == 32) {
            System.out.println("32 byte key taken");
            // Split 32-byte key
            int[] k0 = new int[16];
            int[] k1 = new int[16];
            System.arraycopy(key, 0, k0, 0, 16);
            System.arraycopy(key, 16, k1, 0, 16);

            int[] temp = new int[16];
            System.arraycopy(nonce, 0, temp, 0, 8);
            System.arraycopy(counterBytes, 0, temp, 8, 8);

            //implicitly calling hash 
            keystreamBlock = expansion(k0, k1, temp);
        } else{
            //must be 16bytekey then//
            System.out.println("16 byte key taken\n");
            int[] temp = new int[16];
            System.arraycopy(nonce, 0, temp, 0, 8);
            System.arraycopy(counterBytes, 0, temp, 8, 8);

            //implicitly calling hash 
            keystreamBlock = expansion(key, key, temp);
        } 

        //keystream xor plaintext step/
        for (int i = 0; i < 64; i++) {
            int messageIndex = (int)(blockNum * 64 + i);
            //handle edge case error//
            if (messageIndex >= plaintxt.length){
                break;
            }
            ciphertext[messageIndex] = plaintxt[messageIndex] ^ keystreamBlock[i];
        }
    }

    return ciphertext;
    
    }

}



