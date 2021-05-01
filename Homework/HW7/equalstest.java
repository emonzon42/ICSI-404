import java.util.Arrays;

public class equalstest {
    public static void main(String[] args) {
        Longword s = new Longword();
        System.out.println(s);
        runtests();
    }

    public static void runtests(){
        try {
            testEquals();
            System.out.println("equals Passed All Tests!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testEquals() throws Exception{
        if (! new Bit(0).equals(new Bit(0))){
            System.out.println(new Bit(0).equals(new Bit(0)));
            System.out.println(new Bit(0).compareTo(new Bit(0)));
            throw new Exception("Equals failed @ 0 = 0");}
        if (new Bit(0).equals(new Bit(1))){throw new Exception("Equals failed @ 0 = 1");}
        if (new Bit(1).equals(new Bit(0))){throw new Exception("Equals failed @ 1 = 0");}
        if (! new Bit(1).equals(new Bit(1))){throw new Exception("Equals failed @ 1 = 1");}
        
        Bit[] bits1 = new Bit[]{new Bit(1),new Bit(0)};
        Bit[] bits2 = new Bit[]{new Bit(1),new Bit(0)};

        if(Arrays.equals(bits1,bits2)){throw new Exception("Equals failed @ 10 = 10");}
    } 
}
