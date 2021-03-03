public class Multiplier {
    public static Longword multiply (Longword a, Longword b){ //takes two longwords and multiplies them together
        if (isPowerOf2(b.getSigned()))
            return multiplyBy2(a,b);
        else if (isPowerOf2(a.getSigned()))
            return multiplyBy2(b, a);

        Longword c = new Longword();
        for (int i = b.LONGWORD_SIZE-1; i >= 0 ; i--) { //each digit in b
            Longword letsAdd = new Longword();// gets added on to c to create final longword
            for (int j = a.LONGWORD_SIZE-1; j >= 0; j--) { //each digit in a
                letsAdd.setBit(j, a.getBit(j).getValue() * b.getBit(i).getValue());
            }
            c = c.plus(letsAdd.leftShift(a.LONGWORD_SIZE-1-i));
        }
        return c;
    }

    private static Longword multiplyBy2(Longword a, Longword b){ //multiplies a by b, assuming b is a power of 2
        return a.leftShift((int)(Math.log(b.getSigned())/Math.log(2))); //left shifts a x times, where x is from 2^x = b
    }

    private static boolean isPowerOf2(int num){ //checks if num is a power of 2
        return (num != 0) && ((num & (num - 1)) == 0); 
    }
}
