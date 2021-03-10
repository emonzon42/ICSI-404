

public class Memory {

    public final int MEM_SIZE = 1024;
    private Bit[] mem;

    public Memory(){ //constructor, sets all bits to 0
        mem = new Bit[MEM_SIZE];
        for (int i = 0; i < MEM_SIZE; i++)
            mem[i] = new Bit(0);
    }

    public Longword read(Longword address){
        Longword data = new Longword();
        for (int i = 0, j = address.getSigned(); i < data.LONGWORD_SIZE && j < MEM_SIZE; i++, j++) {
            data.setBit(i, mem[j]);
        }
        return data;
    }

    public void write(Longword address, Longword value){
        for (int i = address.getSigned(), j = 0; i < MEM_SIZE && j< value.LONGWORD_SIZE; i++, j++) {
            mem[i] = value.getBit(j);
        }
    }
}
