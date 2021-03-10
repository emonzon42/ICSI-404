public class Memory {

    public final int MEM_SIZE = 1024; //memory size
    private Bit[] mem; //memory

    public Memory(){ //constructor, sets all bits to 0
        mem = new Bit[MEM_SIZE];
        for (int i = 0; i < MEM_SIZE; i++)
            mem[i] = new Bit(0);
    }

    public Longword read(Longword address){ //reads the bits of the memory starting at mem[address] into a new longword
        Longword data = new Longword();
        for (int i = 0, j = address.getSigned(); i < data.LONGWORD_SIZE && j < MEM_SIZE; i++, j++) {
            data.setBit(i, mem[j]);
        }
        return data;
    }

    public void write(Longword address, Longword value){ //writes value into memory starting at mem[address]
        for (int i = address.getSigned(), j = 0; i < MEM_SIZE && j< value.LONGWORD_SIZE; i++, j++) {
            mem[i] = value.getBit(j);
        }
    }
}
