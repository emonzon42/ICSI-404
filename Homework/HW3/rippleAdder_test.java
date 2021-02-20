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
        if (RippleAdder.add(new Longword(1), new Longword(2)).getSigned() != 3) throw new Exception("Add failed @ 1 + 2 = 3");
        if (RippleAdder.add(new Longword(50), new Longword(50)).getSigned() != 100) {
            System.out.println(RippleAdder.add(new Longword(50), new Longword(50)).getSigned());
            throw new Exception("Add failed @ 50 + 50 = 100");}
        //if (RippleAdder.add(new Longword(-50), new Longword(80)).getSigned() != 30) throw new Exception("Add failed @ -50 + 80 = 70");
    }

    public static void testSub() throws Exception{
        
        
    }
}
