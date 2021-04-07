public class Computer {
    
    private Bit onoff; // bit to represent whether computer is on or off
    private Memory mem; //computer memory
    private Longword PC; //program counter
    private Longword[] R; //the registers

    private Longword currentInstruction; //current instruction
    private Longword op1, op2; //holds operations retrieved from registers

    public Computer(){ //constructor
        mem = new Memory();
        onoff = new Bit(1);
        PC = new Longword();
        R = new Longword[16];
        for (int i = 0; i < R.length; i++)
            R[i] = new Longword();
        op1 = new Longword();
        op2 = new Longword();
    }

    public Bit status(){ //returns a bit representing whether the computer is on or off
        return onoff;
    }

    public boolean turnedOn(){
        return onoff.getValue() == 1 ? true : false;
    }

    public void run(){ //runs the computer until signalled off
        while (turnedOn()) {
            fetch();
            Bit[] op = decode();
            Longword result = execute(op);
            if (result == null)
                continue;
            store(result);
        }
    }

    public void preload(String[] bits){ //preloads bits into memory starting at memory index 0
        preload(bits,0);
    }
    
    public void preload(String[] bits, int bitIndex){ //preloads bits into memory starting at bitIndex
        Longword temp = new Longword();
        byte index = 0;

        for (int i = 0; i < bits.length; i++) {
            for (char bit : bits[i].toCharArray()) {
                temp.setBit(index + 16, bit-48); // bit - 48 bc 0 in ascii is 48
                index++;
            }
            if(bitIndex == 64)
                break;
            if(index == 16 && i != bits.length-1){ // an instruction has been added but theres more bits to add
                mem.write(new Longword(bitIndex), temp);
                temp = new Longword();
                index = 0;
                bitIndex+=2;
            }
        }
        mem.write(new Longword(bitIndex), temp);
    }

    public void fetch(){ //fetches instruction from memory
        System.out.print("Fetching: ");
        currentInstruction = mem.read(PC);
        PC = PC.plus(new Longword(2)); //increments PC by 2
    }

    public Bit[] decode(){ //decodes the instruction for the register numbers 
                        //and stores whats in R[source1] and R[source2] to op1 and op2
        System.out.println(currentInstruction);

        Bit[] opcode = new Bit[]{currentInstruction.getBit(0 + 16), // i + 16 skips the first 16 bits as the values would be 0s
            currentInstruction.getBit(1 + 16),
            currentInstruction.getBit(2 + 16),
            currentInstruction.getBit(3 + 16)};

        op1.copy(R[currentInstruction.leftShift(20).rightShift(28).getSigned()]);
        op2.copy(R[currentInstruction.leftShift(24).rightShift(28).getSigned()]);

        return opcode;
    }

    public Longword execute(Bit[] operation){ //executes operation
        if (ALU.areEqual(operation,new Bit[]{new Bit(0),new Bit(0),new Bit(0),new Bit(0)})){ //HALT
            halt();
            return null;
        }else if (ALU.areEqual(operation,new Bit[]{new Bit(0),new Bit(0),new Bit(0),new Bit(1)})){ //MOVE
            move();
            return null;
        }else if (ALU.areEqual(operation,new Bit[]{new Bit(0),new Bit(0),new Bit(1),new Bit(0)})){ //INTERRUPT
            interrupt(currentInstruction.getBit(currentInstruction.LONGWORD_SIZE-1));
            return null;
        }else{
            return ALU.doOp(operation,op1, op2);
        }
    }

    public void store(Longword result){ // stores result in R[target]    
        R[currentInstruction.leftShift(28).rightShift(28).getSigned()] = result;
    }

    private void halt(){ //turns off the computer
        System.out.println("HALTED");
        onoff.set(0);
    }
    
    private void move(){ //moves value in the instruction into the register identified in the instruction
        Bit[] value = new Bit[]{
            currentInstruction.getBit(8 + 16), currentInstruction.getBit(9 + 16), currentInstruction.getBit(10 + 16), currentInstruction.getBit(11 + 16),
            currentInstruction.getBit(12 + 16), currentInstruction.getBit(13 + 16), currentInstruction.getBit(14 + 16), currentInstruction.getBit(15 + 16),
        };

        Longword temp = new Longword();

        for (int i = 24; i < temp.LONGWORD_SIZE; i++) {
            temp.setBit(i, value[i - 24]);
        }

        if (value[0].getValue() == 1){ //if value is negative extend the 1
            for (int i = 0; i < 24; i++) {
                temp.setBit(i, 1);
            }
        }
        R[currentInstruction.leftShift(20).rightShift(28).getSigned()] = temp;
    }

    private void interrupt(Bit type){ //interrupts displaying either the registers or all 1024 bits of memory
        System.out.println("INTERRUPTED:");
        System.out.println();
        if (type.getValue() == 0){ //print all the registers
            int regnum = 0;
            for (Longword register : R) {
                System.out.println("Register "+regnum+": "+register);
                regnum++;
            }
        }else if (type.getValue() == 1){ //print all 1024 bytes from memory
            for (int i = 0; i < 64; i+=2) {
                System.out.println("Block "+((i/2)+1)+": "+ mem.read(new Longword(i)));
            }
        }
        System.out.println();
    }
    

}
