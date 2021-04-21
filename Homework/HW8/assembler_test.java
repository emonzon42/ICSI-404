import java.util.Arrays;

public class assembler_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testAssembler();
            System.out.println("CPU Passed All Tests!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testAssembler() throws Exception{
        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"HALT"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"MOVE R1 -1"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"LEFTSHIFT R1 R0 R15"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"NOT R7 R3"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"INTERRUPT 1"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"SUBTRACT R0 R3 R3","MULTIPLY R3 R6 R3","RIGHTSHIFT R13 R3 R3"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"XOR R1 R5 R2"})));

        System.out.println(Arrays.toString(Assembler.assemble(new String[]{"AND R7 R5 R1","MOVE R7 67"})));

    }
    
}
