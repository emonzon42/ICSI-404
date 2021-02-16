public class Longword {
    
    public final int LONGWORD_SIZE = 32;
    private Bit[] bits;

    public Longword(){
        bits = new Bit[LONGWORD_SIZE];
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            bits[i] = new Bit();
              //  System.out.println(bit.getValue());
        }
    }

    public Bit getBit(int i){  // Get bit i
        return bits[i];
    }

    public void setBit(int i, Bit value){ // set bit i's value
        bits[i].set(value.getValue());
    }

    public Longword and(Longword b){
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].and(b.getBit(i)));
        }
        return a;
    }
    
    public Longword or(Longword b){
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].or(b.getBit(i)));
        }
        return a;
    }

    public Longword xor(Longword b){
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].xor(b.getBit(i)));
        }
        return a;
    }
    
    public Longword not(){
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].not());
        }
        return a;
    }

    Longword rightShift(int amount){ // rightshift this longword by amount bits, creating a new longword    
        Longword a = new Longword();
        for (int i = amount, j =0; i < LONGWORD_SIZE; i++, j++) {
            a.setBit(i, bits[j]);
        }
        return a;
    }

    Longword leftShift(int amount){ // leftshift this longword by amount bits, creating a new longword
        Longword a = new Longword();
        for (int i = 0, j = amount; j < LONGWORD_SIZE; i++, j++) {
            a.setBit(i, bits[j]);
        }
        return a;
    }

    @Override 
    public String toString(){ // returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
        StringBuilder str = new StringBuilder();
        for (Bit bit : bits) {
            str.append(bit.getValue());
            
            if (str.length() != 63) //63 = 32 bits + 31 commas
                str.append(",");
        }
        return str.toString();
    }

    public long getUnsigned() {// returns the value of this longword as a long
        long num = 0;
        long power = 1;

        for (int i = LONGWORD_SIZE - 1; i >= 0; i--) {
            Bit b = bits[i];
            num = num + (Long.parseLong(b + "") * power);
            power = power * 2;
        }

        return num;
    }

    /**
     * twos complement: flip all the bits then add ...0001 (1) to it to negate the number
     * @return
     */
    public int getSigned(){
        int num = 0;
        int power = 1;
        if(bits[0].getValue() == 1){
            for (int i = LONGWORD_SIZE - 1; i >= 1; i--) {
                Bit b = bits[i].not();
                num = num + (Integer.parseInt(b + "") * power);
                power = power * 2;
            }
            num +=1;
            num *= -1;
        } else {
            for (int i = LONGWORD_SIZE - 1; i >= 0; i--) {
                Bit b = bits[i];
                num = num + (Integer.parseInt(b + "") * power);
                power = power * 2;
            }
        }

        return num;
    }

}