public class Memory {

    public final short MEM_SIZE = 8192; //memory size (1024 bytes)
    public final short BLOCK_SIZE = 8; //size of a byte in memory
    private Bit[] mem; //memory

    public Memory(){ //constructor, sets all bits to 0
        mem = new Bit[MEM_SIZE];
        for (int i = 0; i < MEM_SIZE; i++)
            mem[i] = new Bit(0);
    }

    public Longword read(Longword address){ //reads the bits of the memory starting at mem[BLOCK_SIZE*address] into a new longword
        Longword data = new Longword();
        for (int i = 0, j = address.getSigned()*BLOCK_SIZE; i < data.LONGWORD_SIZE && j < MEM_SIZE; i++, j++) {
            data.setBit(i, mem[j]);
        }
        return data;
    }

    public void write(Longword address, Longword value){ //writes value into memory starting at mem[BLOCK_SIZE*address]
        for (int i = address.getSigned()*BLOCK_SIZE, j = 0; i < MEM_SIZE && j< value.LONGWORD_SIZE; i++, j++) {
            mem[i] = value.getBit(j);
        }
    }

    public int capacity(){ //capacity of memory
        return MEM_SIZE/BLOCK_SIZE;
    }
}
