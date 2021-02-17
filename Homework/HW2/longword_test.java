public class longword_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests() {
        if ( testSetGet())
            System.out.println("SetBit/Get Passed!");
        else
            System.out.println("SetBit/Get failed! :(");

        if ( testSetCopy())
            System.out.println("Set/Copy Passed!");
        else
            System.out.println("Set/Copy failed! :(");
        
        if (testAnd())
            System.out.println("AND Passed!");
        else
            System.out.println("AND failed! :(");

        if (testOr())
            System.out.println("OR Passed!");
        else
            System.out.println("OR failed! :(");
        
        if (testXor())
            System.out.println("XOR Passed!");
        else
            System.out.println("XOR failed! :(");

        if (testNot())
            System.out.println("NOT Passed!");
        else
            System.out.println("NOT failed! :(");

        if (testLeftShift())
            System.out.println("LeftShift Passed!");
        else
            System.out.println("LeftShift failed! :(");
        
        if (testRightShift())
            System.out.println("RightShift Passed!");
        else
            System.out.println("RightShift failed! :(");

        if (testToString())
            System.out.println("ToString Passed!");
        else
            System.out.println("ToString failed! :(");

        if (testGetSigned())
            System.out.println("getSigned Passed!");
        else
            System.out.println("getSigned failed! :(");

        if (testGetUnsigned())
            System.out.println("getUnsigned Passed!");
        else
            System.out.println("getUnsigned failed! :(");
    }
    public static boolean testSetGet(){
        Longword l = new Longword();
        l.setBit(0, new Bit(1));
        if (l.getBit(0).getValue() != 1) return false;

        l.setBit(1, new Bit(67));
        if (l.getBit(1).getValue() != 1) return false;

        l.setBit(0, new Bit(0));
        if (l.getBit(0).getValue() != 0) return false;

        return true;
    }
    
    public static boolean testSetCopy(){
        Longword l = new Longword();
        Longword l2;
        l.set(56);
        if (l.getSigned() != 56) return false;
        l2 = new Longword(l);
        if (l2.getSigned() != l.getSigned()) return false;

        l.set(-58329);
        if (l.getSigned() != -58329) return false;
        l2.copy(l);
        if (l2.getSigned() != l.getSigned()) return false;

        l.set(1);
        if (l.getSigned() != 1) return false;
        l2.copy(l);
        if (l2.getSigned() != l.getSigned()) return false;

        return true;
    }

    public static boolean testAnd(){
        if (new Longword(0).and(new Longword(0)).getSigned() != 0) return false;
        if (new Longword(-2).and(new Longword(99)).getSigned() != 98) return false;
        if (new Longword(23).and(new Longword(-23)).getSigned() != 1) return false;
        if (new Longword(2327837).and(new Longword(-242913)).getSigned() != 2097437) return false;
        return true;
    }

    public static boolean testOr(){
        if (new Longword(0).or(new Longword(1)).getSigned() != 1) return false;
        if (new Longword(-2).or(new Longword(99)).getSigned() != -1) return false;
        if (new Longword(23).or(new Longword(-23)).getSigned() != -1) return false;
        if (new Longword(56).or(new Longword(604)).getSigned() != 636) return false;
        return true;
    }

    public static boolean testXor(){
        if (new Longword(1).xor(new Longword(3)).getSigned() != 2) return false;
        if (new Longword(-2).xor(new Longword(99)).getSigned() != -99) return false;
        if (new Longword(23).xor(new Longword(-23)).getSigned() != -2) return false;
        if (new Longword(56).xor(new Longword(604)).getSigned() != 612) return false;

        return true;
    }
    public static boolean testNot(){
        if (new Longword(0).not().getSigned() != -1) return false;
        if (new Longword(1).not().getSigned() != -2) return false;

        return true;
    }

    public static boolean testRightShift(){
        Longword l = new Longword(-1); //...1111111...
        l = l.rightShift(1);
        if (l.getSigned() != 2147483647) return false;

        l = new Longword(-1);
        l = l.rightShift(3);
        if (l.getSigned() != 536870911) return false;

        l = new Longword(-1);
        l = l.rightShift(5);
        if (l.getSigned() != 134217727) return false;

        return true;
    }

    public static boolean testLeftShift(){
        Longword l = new Longword(-1); //...1111111...
        l = l.leftShift(1);
        if (l.getSigned() != -2) return false;

        l = new Longword(-1);
        l = l.leftShift(3);
        if (l.getSigned() != -8) return false;

        l = new Longword(-1);
        l = l.leftShift(5);
        if (l.getSigned() != -32) return false;
        
        return true;
    }

    public static boolean testToString(){
        if (! new Longword(0).toString().equalsIgnoreCase("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")) return false;
        if (! new Longword(1).toString().equalsIgnoreCase("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1")) return false;
        if (! new Longword(-1).toString().equalsIgnoreCase("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1")) return false;
        return true;
    }

    public static boolean testGetSigned(){
        if (new Longword(-1).getSigned() != -1) return false;
        if (new Longword(53).getSigned() != 53) return false;
        if (new Longword(218430).getSigned() != 218430) return false;
        return true;
    }

    public static boolean testGetUnsigned(){
        if (new Longword(-1).getUnsigned() != 4294967295L) return false;
        if (new Longword(53).getUnsigned() != 53) return false;
        if (new Longword(218430).getUnsigned() != 218430) return false;
        return true;
    }
}
