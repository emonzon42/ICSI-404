public class cpu_test3 {
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
        Computer m1xmacbookpro = new Computer();
       
        try {
            m1xmacbookpro.assemble(new String[]{
                "move R0 5 ", //0
                "MOVE R1 7", //2
                "COMPARE R0 R1",// 4
                "BNE 8", //6
                "HALT", //8
                "MULTIPLY R0 R1 R7", //10
                "RETURN",
                "PUSH R1", //14
                "CALL 10",  //16
                "INTERRUPT 1", //18
                "MOVE R1 65",
                "POP R1",
                "INTERRUPT 0",
                "HALT",
            });
    
            m1xmacbookpro.run();
            
        } catch (Exception e) {
            throw e;
        }

        m1xmacbookpro = new Computer();
        try {
            m1xmacbookpro.assemble(new String[]{
                "MOVE R0 99 ", //0
                "MOVE R7 5 ", //2
                "MULTIPLY R0 R7 R2 ",
                "MOVE R1 1",
                "MULTIPLY R1 R2 R3 ",
                "COMPARE R2 R3", //10
                "BEQ 26", //12
                "INTERRUPT 0", 
                "HALT",
                "CALL 12", //18
                "RETURN",
                "PUSH R2", //22
                "MOVE R2 86",
                "INTERRUPT 0",
                "MULTIPLY R2 R0 R15",
                "POP R9",
                "RETURN",
                "HALT",
                "HALT", 
                "PUSH R0", //38
                "MOVE R0 5",
                "POP R6",
                "CALL 22",
                "ADD R6 R9 R11",
                "COMPARE R6 R11",
                "CALL 18",
            });
            m1xmacbookpro.run();
        }catch (Exception e) {
                throw e;
        }

        m1xmacbookpro = new Computer();
        try {
            m1xmacbookpro.assemble(new String[]{
                "MOVE R0 23 ", //0
                "MOVE R1 45 ", //2
                "MULTIPLY R0 r1 R2 ",
                "MOVE r3 69",
                "SUBTRACT R0 R2 R7 ",
                "COMPARE R0 R7", //10
                "BGT 6", //12
                "JUMP 12", //infinite loop if above cond is false
                "HALT", 
                "INTERRUPT 0", //18
                "JUMP 24",
                "HALT", //22
                "MOVE R7 1035",
                "COMPARE R2 R7",
                "BGE -6",
                "JUMP 0",
            });
            m1xmacbookpro.run();
        }catch (Exception e) {
                throw e;
        }
        
    }
}
