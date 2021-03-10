public class memory_test {
    public static void main(String[] args) {
        runtests();
    }

    public static void runtests(){
        try {
            testReadWrite();
            System.out.println("Read/Write Passed!");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void testReadWrite() throws Exception{
        Memory test = new Memory();
        test.write(new Longword(0), new Longword(64));
        if(test.read(new Longword(0)).getSigned() != 64){throw new Exception("Failed to read 64 starting @ memory index 0");}

        test = new Memory();
        test.write(new Longword(992), new Longword(-1));
        if(test.read(new Longword(992)).getSigned() != -1){throw new Exception("Failed to read -1 starting @ memory index 992");}
        if(test.read(new Longword(993)).getSigned() != -2){throw new Exception("Failed to read -2 starting @ memory index 993");}

        test = new Memory();
        test.write(new Longword(523), new Longword(9876543));
        if(test.read(new Longword(523)).getSigned() != 9876543){throw new Exception("Failed to read -1 starting @ memory index 992");}

        test = new Memory();
        test.write(new Longword(32), new Longword(-824));
        if(test.read(new Longword(32)).getSigned() != -824){throw new Exception("Failed to read -1 starting @ memory index 992");}
        if(test.read(new Longword(0)).getSigned() != 0){throw new Exception("Failed to read 0 starting @ memory index 0");}
        test.write(new Longword(0), new Longword(7253));
        if(test.read(new Longword(0)).getSigned() != 7253){throw new Exception("Failed to read 7253 starting @ memory index 0");}
        if(test.read(new Longword(32)).getSigned() != -824){throw new Exception("Failed to read -1 starting @ memory index 992");}

        test = new Memory();
        test.write(new Longword(21), new Longword(-1));
        if(test.read(new Longword(21)).getSigned() != -1){throw new Exception("Failed to read -1 starting @ memory index 21");}
        test.write(new Longword(0), new Longword(3));
        if(test.read(new Longword(0)).getSigned() != 3){throw new Exception("Failed to read 3 starting @ memory index 0");}
    }
}
