public class Computer {
    
    public Bit onoff; // bit to represent whether computer is on or off
    private Memory mem; //computer memory

    public Computer(){ //constructor
        mem = new Memory();
        onoff = new Bit(0);
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

    }

    public void decode(){
        
    }

    public void execute(){
        
    }

    public void store(){
        
    }
}
