public class cpu_test2 {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
           // testCPU2();
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
    }


    public static void testCPU2() throws Exception{
        System.out.println("TESTING HALT");
        System.out.println();
        Computer mac = new Computer();
        mac.preload(new String[]{"0000","0000","0000","0000"});
        mac.run();
        if(mac.turnedOn()){throw new Exception("CPU Failed to HALT");}else{System.out.println("HALT SUCCESS");}
        System.out.println();

        //MOVE R2 10
        System.out.println("TESTING MOVE R2 10");
        System.out.println();
        mac = new Computer();
        mac.preload(new String[]{"0001","0010","0000","1001"});
        mac.fetch();
        mac.execute(mac.decode());
        mac.preload(new String[]{"0010","0000","0000","0000"},2);
        mac.fetch();
        mac.execute(mac.decode());
        System.out.println("MOVE SUCCESS ?");
        System.out.println();

        //MOVE R1 15, MOVE R2 45, ADD R1 R2 R3
        System.out.println("TESTING MOVE R1 15, MOVE R2 45, ADD R1 R2 R3");
        System.out.println();
        mac = new Computer();
        mac.preload(new String[]{"0001","0001","0000","1111",
                                "0001","0010","0101","1101",
                                "1110","0001","0010","0011",
                                "0010","0000","0000","0000"});
        mac.run();
        System.out.println("MOVE MOVE ADD SUCCESS");
        System.out.println();


        //MOVE R1 30, MOVE R2 -17, ADD R1 R2 R8
        System.out.println("TESTING MOVE R1 30, MOVE R2 -17, ADD R1 R2 R8");
        mac = new Computer();
        mac.preload(new String[]{"0001","0001","0001","1110",
                                "0001","0010","1110","1111",
                                "1110","0001","0010","1000",
                                "0010","0000","0000","0000",
                                "0010","0000","0000","0001"});
        mac.run();
    } 
}
