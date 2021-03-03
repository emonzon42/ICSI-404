public class multiplier_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testMultiply();
            System.out.println("Multiply Passed!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testMultiply() throws Exception{
        if (Multiplier.multiply(new Longword(3), new Longword(2)).getSigned() != 6) throw new Exception("Multiply failed @ 3 * 2 = 6");
        if (Multiplier.multiply(new Longword(5), new Longword(9)).getSigned() != 45) throw new Exception("Multiply failed @ 5 * 9 = 45");
        if (Multiplier.multiply(new Longword(5), new Longword(-2)).getSigned() != -10) throw new Exception("Multiply failed @ 5 * -2 = -10");
        if (Multiplier.multiply(new Longword(1), new Longword(-2)).getSigned() != -2) throw new Exception("Multiply failed @ 1 * -2 = -2");
        if (Multiplier.multiply(new Longword(-2), new Longword(1)).getSigned() != -2) throw new Exception("Multiply failed @ -2 * 1 = -2");
        if (Multiplier.multiply(new Longword(7654), new Longword(0)).getSigned() != 0) throw new Exception("Multiply failed @ 7654 * 0 = 0");
        if (Multiplier.multiply(new Longword(231), new Longword(-0)).getSigned() != 0) throw new Exception("Multiply failed @ 231 * -0 = 0");
        if (Multiplier.multiply(new Longword(-42), new Longword(45)).getSigned() != -1890) throw new Exception("Multiply failed @ -42 * 45 = -1890");
    }
}
