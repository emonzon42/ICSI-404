public class ALU_test {

    //for better readability (and since this is a test class idc about the space used)
    private static Bit[] and = new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(0)};
    private static Bit[] or = new Bit[]{new Bit(1),new Bit(0),new Bit(0),new Bit(1)};
    private static Bit[] xor = new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(0)};
    private static Bit[] not = new Bit[]{new Bit(1),new Bit(0),new Bit(1),new Bit(1)};
    private static Bit[] leftshift = new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(0)};
    private static Bit[] rightshift = new Bit[]{new Bit(1),new Bit(1),new Bit(0),new Bit(1)};
    private static Bit[] add = new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(0)};
    private static Bit[] subtract = new Bit[]{new Bit(1),new Bit(1),new Bit(1),new Bit(1)};
    private static Bit[] multiply = new Bit[]{new Bit(0),new Bit(1),new Bit(1),new Bit(1)};

    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testALU();
            System.out.println("ALU Passed All Tests!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testALU() throws Exception{
        if (ALU.doOp(and, new Longword(-2), new Longword(99)).getSigned() != 98){throw new Exception("ALU failed @ -2 AND 99 = 98");}
        if (ALU.doOp(or, new Longword(56), new Longword(604)).getSigned() != 636){throw new Exception("ALU failed @ 56 OR 604 = 636");}
        if (ALU.doOp(xor, new Longword(56), new Longword(604)).getSigned() != 612){throw new Exception("ALU failed @ 56 XOR 604 = 612");}
        if (ALU.doOp(not, new Longword(1), null).getSigned() != -2){throw new Exception("ALU failed @ 1 NOT = -2");}
        if (ALU.doOp(leftshift, new Longword(-1), null).getSigned() != -2){throw new Exception("ALU failed @ -1 LEFTSHIFT (1) = -2");}
        if (ALU.doOp(rightshift, new Longword(-1), null).getSigned() != 2147483647){throw new Exception("ALU failed @ -1 RIGHTSHIFT (1) = 2147483647");}
        if (ALU.doOp(add, new Longword(512), new Longword(98739)).getSigned() != 99251){throw new Exception("ALU failed @ 512 + 98739 = 99251");}
        if (ALU.doOp(subtract, new Longword(-50), new Longword(29)).getSigned() != -79){throw new Exception("ALU failed @ -50 - 29 = -79");}
        if (ALU.doOp(multiply, new Longword(7654), new Longword(99126)).getSigned() != 758710404){throw new Exception("ALU failed @ 7654 * 99126 = 758710404");}
    } 
}
