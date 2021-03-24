public class Computer {
    
    public Bit onoff; // bit to represent whether computer is on or off
    private Memory mem; //computer memory
    private Longword PC; //program counter
    private Longword[] R; //the registers

    private Longword currentInstruction; //current instruction
    private Longword op1, op2; //

    public Computer(){ //constructor
        mem = new Memory();
        onoff = new Bit(0);
        PC = new Longword();
        R = new Longword[16];
        for (int i = 0; i < R.length; i++)
            R[i] = new Longword();
    }

    public void run(){ //runs the computer until signalled off
        while (onoff.getValue() == 1) {
            fetch();
            decode();
            Longword result = execute();
            store(result);
        }
    }

    public void fetch(){ //fetches instruction from memory
        currentInstruction = mem.read(PC);
        PC = PC.plus(new Longword(2)); //increments PC by 2
    }

    public void decode(){ //decodes the instruction for the register numbers 
                        //and stores whats in R[source1] and R[source2] to op1 and op2
        op1 = R[currentInstruction.leftShift(20).rightShift(28).getSigned()];
        op2 = R[currentInstruction.leftShift(24).rightShift(28).getSigned()];
    }

    public Longword execute(){ //executes operation
        Longword result = ALU.doOp(
            new Bit[]{currentInstruction.getBit(0 + 16), // i + 16 skips the first 16 bits as the values would be 0s
                currentInstruction.getBit(1 + 16),
                currentInstruction.getBit(2 + 16),
                currentInstruction.getBit(3 + 16)},
                op1, op2);

        return result;
    }

    public void store(Longword result){ // stores result in R[target]
        R[currentInstruction.leftShift(28).rightShift(28).getSigned()] = result;
    }

}
