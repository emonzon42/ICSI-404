public class Computer {
    
    public Bit onoff; // bit to represent whether computer is on or off
    private Memory mem; //computer memory
    private Longword PC; //program counter
    private Longword[] R; //the registers

    private Longword currentInstruction;
    private Longword op1, op2;

    public Computer(){ //constructor
        mem = new Memory();
        onoff = new Bit(0);
        PC = new Longword();
        R = new Longword[16];
        for (int i = 0; i < R.length; i++)
            R[i] = new Longword();
    }

    public void run(){
        while (onoff.getValue() == 1) {
            fetch();
            decode();
            Longword result = execute();
            store(result);
        }
    }

    public void fetch(){
        currentInstruction = mem.read(PC);
        PC = PC.plus(new Longword(2)); //increments PC by 2
    }

    public void decode(){
        op1 = R[currentInstruction.leftShift(20).rightShift(8).rightShift(20).getSigned()];
        op2 = R[currentInstruction.leftShift(24).rightShift(4).rightShift(24).getSigned()];
    }

    public Longword execute(){
        Longword result = ALU.doOp(
            new Bit[]{currentInstruction.getBit(0 + 16), // i + 16 skips the first 16 bits as the values would be 0s
                currentInstruction.getBit(1 + 16),
                currentInstruction.getBit(2 + 16),
                currentInstruction.getBit(3 + 16)},
                op1, op2);

        return result;
    }

    public void store(Longword result){
        R[currentInstruction.leftShift(28).rightShift(28).getSigned()] = result;
    }
/*
    private void buildInstruction(){
        currentInstruction = new Longword();
        currentInstruction.setBit(16, 1);
        currentInstruction.setBit(17, 1);
        currentInstruction.setBit(18, 1);
        currentInstruction.setBit(19, 0);

        currentInstruction.setBit(20, 0);
        currentInstruction.setBit(21, 0);
        currentInstruction.setBit(22, 0);
        currentInstruction.setBit(23, 1);

        currentInstruction.setBit(24, 0);
        currentInstruction.setBit(25, 0);
        currentInstruction.setBit(26, 1);
        currentInstruction.setBit(27, 0);

        currentInstruction.setBit(28, 0);
        currentInstruction.setBit(29, 0);
        currentInstruction.setBit(30, 1);
        currentInstruction.setBit(31, 1);
    }
*/
}
