public class Multiplier {
    public static Longword multiply (Longword a, Longword b){
        if (isPowerOf2(b.getSigned()))
            return multiplyBy2(a,b);
        else if (isPowerOf2(a.getSigned()))
            return multiplyBy2(b, a);

        System.out.println(a);
        System.out.println(b);
        
        Longword c = new Longword();
        for (int i = b.LONGWORD_SIZE-1; i >= 0 ; i--) { //each digit in b
            Longword letsAdd = new Longword();// gets added on to c to create final longword
            System.out.print("i:" + i + " | b(i): "+ b.getBit(i) + " * a = ");
            for (int j = a.LONGWORD_SIZE-1; j >= 0; j--) { //each digit in a
                int multi = a.getBit(j).getValue() * b.getBit(i).getValue();
              //  System.out.println();
               // System.out.println(a.getBit(j).getValue() + "* " + b.getBit(i).getValue());
                //System.out.print(a + "\n*\n" + b + "\n--------------------------------------------------------------------");
                letsAdd.setBit(j, multi);
                /*
                0101
                1001

                0101
                0000
                00
                0
                */
               // System.out.println();
              //System.out.println(letsAdd);
            }
            System.out.println(letsAdd.leftShift(a.LONGWORD_SIZE-1-i));
            c = c.plus(letsAdd.leftShift(a.LONGWORD_SIZE-1-i));
            
        }
        System.out.println(c);
        System.out.println();
        return c;
    }

    private static Longword multiplyBy2(Longword a, Longword b){ //multiplies a by b, assuming b is a power of 2
        return a.leftShift((int)(Math.log(b.getSigned())/Math.log(2))); //left shifts a x times, where x is from 2^x = b
    }

    private static boolean isPowerOf2(int num){ //checks if num is a power of 2
        return (num != 0) && ((num & (num - 1)) == 0); 
    }
}
