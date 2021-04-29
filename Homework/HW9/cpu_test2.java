public class cpu_test2 {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testCPU();
            System.out.println("CPU Passed All Tests!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCPU() throws Exception{
        Computer m1macbookpro = new Computer();
        
        try {
            m1macbookpro.assemble(new String[]{
                "MOVE R0 5 ",
                "MOVE R1 7",
                "COMPARE R0 R1",
                "JUMP 12",
                "MOVE R15 4",
                "JUMP 24",
                "BNE 6",
                "BGT -6",
                "BEQ 6",
                "MOVE R15 2",
                "JUMP 24",
                "MOVE R15 8",
                "INTERRUPT 0",
                "HALT",
            });
    
            m1macbookpro.run();
            
        } catch (Exception e) {
            throw e;
        }

        m1macbookpro = new Computer();
        try {
            m1macbookpro.assemble(new String[]{
                "MOVE R0 5 ",
                "MOVE R9 34",
                "ADD R0 R9 R1",
                "MULTIPLY R9 R1 R5",
                "MOVE R15 4",
                "COMPARE R9 R0",
                "INTERRUPT 0",
                "BGT 10",
                "HALT",
                "HALT",
                "HALT",
                "HALT",
                "INTERRUPT 1",
                "HALT",
            });
            m1macbookpro.run();
        }catch (Exception e) {
                throw e;
        }
        
    }

}
