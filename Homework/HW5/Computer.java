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
            execute();
            store();
        }
    }

    public void fetch(){
        currentInstruction = mem.read(PC);
        PC = PC.plus(new Longword(2)); //increments PC by 2
    }

    public void decode(){
        //TODO: examine currentInstruction to determine 2 registers by shifting it around
    }

    public void execute(){
        
    }

    public void store(){
        
    }
}
