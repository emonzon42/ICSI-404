public class bit_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests() {
        if ( testSetClearToggle())
            System.out.println("Set/Clear/Toggle Passed!");
        else
            System.out.println("Set/Clear/Toggle failed! :(");
        
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

    }
    
    public static boolean testSetClearToggle(){
        if (new Bit(1).getValue() != 1) return false;
        if (new Bit(67).getValue() != 1) return false;
        if (new Bit(0).getValue() != 0) return false;

        Bit b = new Bit();
        b.set(79);
        if (b.getValue() != 1) return false;

        b.clear();
        if (b.getValue() != 0) return false;

        b.toggle();
        if (b.getValue() != 1) return false;
        
        return true;
    }

    public static boolean testAnd(){
        if (new Bit(0).and(new Bit(0)).getValue() != 0) return false;
        if (new Bit(0).and(new Bit(1)).getValue() != 0) return false;
        if (new Bit(1).and(new Bit(0)).getValue() != 0) return false;
        if (new Bit(1).and(new Bit(1)).getValue() != 1) return false;
        return true;
    }

    public static boolean testOr(){
        if (new Bit(0).or(new Bit(0)).getValue() != 0) return false;
        if (new Bit(0).or(new Bit(1)).getValue() != 1) return false;
        if (new Bit(1).or(new Bit(0)).getValue() != 1) return false;
        if (new Bit(1).or(new Bit(1)).getValue() != 1) return false;
        return true;
    }

    public static boolean testXor(){
        if (new Bit(0).xor(new Bit(0)).getValue() != 0) return false;
        if (new Bit(0).xor(new Bit(1)).getValue() != 1) return false;
        if (new Bit(1).xor(new Bit(0)).getValue() != 1) return false;
        if (new Bit(1).xor(new Bit(1)).getValue() != 0) return false;

        return true;
    }
}