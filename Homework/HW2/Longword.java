public class Longword {
    
    public final int LONGWORD_SIZE = 32;
    private Bit[] bits;

    public Longword(){ // default constructor, all bits set to 0
        bits = new Bit[LONGWORD_SIZE];
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            bits[i] = new Bit();
        }
    }

    public Longword(Longword l){ // constructor, all bits copied from other longword
        bits = new Bit[LONGWORD_SIZE];
        copy(l);
    }

    public Longword(int value){ // constructor, with value as signed int
        this();
        set(value);
    }

    public Bit getBit(int i){  // Get bit i
        return bits[i];
    }

    public void setBit(int i, Bit value){ // set bit i's value
        bits[i].set(value.getValue());
    }

    public void setBit(int i, int value){ // set bit i's value
        bits[i].set(value);
    }

    public Longword and(Longword b){ // and two longwords, returning a third
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].and(b.getBit(i)));
        }
        return a;
    }
    
    public Longword or(Longword b){ // or two longwords, returning a third
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].or(b.getBit(i)));
        }
        return a;
    }

    public Longword xor(Longword b){ // xor two longwords, returning a third
        Longword a = new Longword();
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            a.setBit(i, bits[i].xor(b.getBit(i)));
        }
        return a;
    }
    
    public Longword not(){ // negate this longword, creating another
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

    public long getUnsigned() { // returns the value of this longword as a long
        long num = 0;
        long power = 1;

        for (int i = LONGWORD_SIZE - 1; i >= 0; i--) {
            Bit b = bits[i];
            num = num + (Long.parseLong(b + "") * power);
            power = power * 2;
        }

        return num;
    }

    public int getSigned(){ // returns the value of this longword as an int
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

    public Longword twosComplement(){ // returns the two's complement of the longword
        return new Longword(this.not().getSigned() + 1);
    }

    public void copy(Longword l){ // copies the values of the bits from another longword into this one
        for (int i = 0; i < LONGWORD_SIZE; i++) {
            bits[i] = l.getBit(i);
        }
    }

    public void set(int value){  // set the value of the bits of this longword (used for tests)
        String sbits = Long.toBinaryString(Integer.toUnsignedLong(value) | 0x100000000L).substring(1); //converts value into 32bit binary as a string

        for (int i = 0; i < LONGWORD_SIZE; i++) {
            bits[i].set(sbits.charAt(i) - 48); // 48 is dec for char 0, 49 = 1
        }
    }

    public Longword plus(Longword a){ // adds a to this longword, creating a new longword
        return RippleAdder.add(this, a);
    }

    public Longword minus(Longword a){ // subtracts a from this longword, creating a new longword
        return RippleAdder.sub(this, a);
    }
}