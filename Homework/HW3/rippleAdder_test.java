public class rippleAdder_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testAdd();
            System.out.println("Add Passed!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        try {
            testSub();
            System.out.println("Subtract Passed!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testAdd() throws Exception{
        if (RippleAdder.add(new Longword(1), new Longword(1)).getSigned() != 2) throw new Exception("Add failed @ 1 + 1 = 2");
        if (RippleAdder.add(new Longword(23), new Longword(1)).getSigned() != 24) throw new Exception("Add failed @ 23 + 1 = 24");
        if (RippleAdder.add(new Longword(50), new Longword(50)).getSigned() != 100) {
            //System.out.println(RippleAdder.add(new Longword(50), new Longword(50)).getSigned());
            throw new Exception("Add failed @ 50 + 50 = 100");}
        if (RippleAdder.add(new Longword(-50), new Longword(80)).getSigned() != 30) throw new Exception("Add failed @ -50 + 80 = 30");
        if (RippleAdder.add(new Longword(162), new Longword(-54)).getSigned() != 108) throw new Exception("Add failed @ 162 + -54 = 108");
        if (RippleAdder.add(new Longword(-653), new Longword(-47)).getSigned() != -700) throw new Exception("Add failed @ -653 + -44 = -700");
    }

    public static void testSub() throws Exception{
        if (RippleAdder.subtract(new Longword(1), new Longword(1)).getSigned() != 0) throw new Exception("Sub failed @ 1 - 1 = 0");
        if (RippleAdder.subtract(new Longword(14), new Longword(5)).getSigned() != 9) {
           // System.out.println(new Longword(14));
            //System.out.println(new Longword(5));
            //System.out.println(RippleAdder.subtract(new Longword(14), new Longword(5)));
            throw new Exception("Sub failed @ 14 - 5 = 9");}
        if (RippleAdder.subtract(new Longword(1), new Longword(-1)).getSigned() != 2) throw new Exception("Sub failed @ 1 - -1 = 2");
        if (RippleAdder.subtract(new Longword(-26), new Longword(-24)).getSigned() != -2) throw new Exception("Sub failed @ -26 - -24 = -2");
        if (RippleAdder.subtract(new Longword(-50), new Longword(5)).getSigned() != -55) throw new Exception("Sub failed @ -50 - 5 = -55");
    }
}
