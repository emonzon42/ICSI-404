public class Longword {
    
    Bit[] bits;

    public Longword(){
        bits = new Bit[32];
    }

    public Bit getBit(int i){  // Get bit i
        return bits[i];
    }

    public void setBit(int i, Bit value){ // set bit i's value
        bits[i].set(value.getValue());
    }


}