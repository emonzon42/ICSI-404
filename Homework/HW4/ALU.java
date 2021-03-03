public class ALU {
    public static Longword doOp(Bit[] operation, Longword a, Longword b){ //does operation on two longwords based on bit[] array
        if (b == null)
            return doSoloOp(operation,a);

        if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(0)})){ //operation = 1000
            return a.and(b);
        }else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(1)}))
            return a.or(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(0)}))
            return a.xor(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(1)}))
            return a.not();
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(0)}))
            return a.leftShift(1);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(1)}))
            return a.rightShift(1);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(0)}))
            return a.plus(b);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(1)}))
            return a.minus(b);
        else if (areEqual(operation, new Bit[]{new Bit(0),new Bit(1),new Bit(1),new Bit(1)}))
            return a.times(b);
        
        return new Longword();
    }

    public static Longword doSoloOp(Bit[] operation, Longword a){ //same as doOp except its only for solo operations (less logic checks)
        if (areEqual(operation, new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(1)}))
            return a.not();
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(0)}))
            return a.leftShift(1);
        else if (areEqual(operation, new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(1)}))
            return a.rightShift(1);

        return new Longword();
    }

    private static boolean areEqual(Bit[] a, Bit[] b){ //Bit[] adaptation of Arrays.equals() method
        if (a==b)
            return true;
        if (a==null || b==null)
            return false;

        if (b.length != a.length)
            return false;
        
        for (int i = 0; i < b.length; i++) {
            if(a[i] == null || b[i] == null || a[i].getValue() != b[i].getValue())
                return false;
        }

        return true;
    }
}
