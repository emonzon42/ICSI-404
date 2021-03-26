public class Bit implements IBit, Comparable<Bit> {

    private byte bit = 0;

    public Bit(int value) { //constructor, sets bit to 1 unless value is 0
        set(value);
    }

    public Bit() { //default constructor, sets bit to 0
        this(0);
    }

    public void set(int value) { //sets value of bit
        if (value != 0)
            bit = 1;
        else
            bit = 0;
    }

    public void set() { //sets value of bit to 1
        set(1);
    }

    public void clear() { //clears value of bit
        set(0);
    }

    public void toggle() { //toggles value of bit
        if (bit == 1)
            clear();
        else
            set();
    }

    public int getValue(){ //returns value of bit
        return bit;
    }

    public Bit and(Bit b){ //returns the result of this bit AND b
        if (bit == 1 && b.getValue() == 1)
            return new Bit(1);
        else
            return new Bit(0);
    }

    public Bit or(Bit b){ //returns the result of this bit OR b
        if (bit == 0 && b.getValue() == 0)
            return new Bit(0);
        else
            return new Bit(1);
    }

    public Bit xor(Bit b){ //returns the result of this bit XOR b
        if ((bit == 1 && b.getValue() == 0 )|| (bit == 0 && b.getValue() == 1))
            return new Bit(1);
        else
            return new Bit(0);
    }

    public Bit not(){ //returns the value of this value NOT
        if (bit == 0)
            return new Bit(1);
        else
            return new Bit(0);
    }

    @Override 
    public String toString(){
        return Integer.toString(bit);
    }

    @Override
    public int compareTo(Bit b){
        if (bit == b.getValue())
            return 0;
        else if (bit < b.getValue())
            return -1;
        else
            return 1;
    }

    public boolean equals(Bit b){ //checks whether this and b are equal
        return bit == b.getValue() ? true : false;
    }

}