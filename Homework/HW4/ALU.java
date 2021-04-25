public class ALU {
    public static Longword doOp(Bit[] operation, Longword a, Longword b){ //does operation on two longwords based on bit[] array
        Longword lastFive = new Longword(b.leftShift(27).rightShift(27)); //the value of the last 5 bits of b

        if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(0)})){ //1000
            return a.and(b);
        }else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(1)}))//1010
            return a.or(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(0)})) //1010
            return a.xor(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(1)})) //1011
            return a.not();
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(0)})) //1100
            return a.leftShift(lastFive.getSigned());
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(1)})) //1101
            return a.rightShift(lastFive.getSigned());
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(0)})) //1110
            return a.plus(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(1)})) //1111
            return a.minus(b);
        else if (areEqual(operation, new Bit[]{new Bit(0),new Bit(1),new Bit(1),new Bit(1)})) //0111
            return a.times(b);
        
        return new Longword();
    }

    public static boolean areEqual(Bit[] a, Bit[] b){ //Bit[] adaptation of Arrays.equals() method
        if (a==b)
            return true;
        if (a==null || b==null)
            return false;

        if (b.length != a.length)
            return false;
        
        for (int i = 0; i < b.length; i++) {
            if(a[i] == null || b[i] == null || !a[i].equals(b[i]))
                return false;
        }

        return true;
    }
}
