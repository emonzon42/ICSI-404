public class cpu_test1 {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testCPU();
            System.out.println("CPU Passed All Tests!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testCPU() throws Exception{
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
