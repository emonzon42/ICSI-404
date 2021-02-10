public class Bit implements IBit {

    private int bit = 0;

    public Bit(int value) {
        set(value);
    }

    public Bit() {
        this(0);
    }

    public void set(int value) {
        if (value != 0)
            bit = 1;
        else
            bit = value;
    }

    public void set() {
        set(1);
    }

    public void clear() {
        set(0);
    }

    public void toggle() {
        if (bit == 1)
            clear();
        else
            set();
    }

    public int getValue(){
        return bit;
    }

    public Bit and(Bit b){
        if (bit == 1 && b.getValue() == 1)
            return new Bit(1);
        else
            return new Bit(0);
    }

    public Bit or(Bit b){
        if (bit == 0 && b.getValue() == 0)
            return new Bit(0);
        else
            return new Bit(1);
    }

    public Bit xor(Bit b){
        if ((bit == 1 && b.getValue() == 0 )|| (bit == 0 && b.getValue() == 1))
            return new Bit(1);
        else
            return new Bit(0);
    }

    public Bit not(){
        if (bit == 0)
            return new Bit(1);
        else
            return new Bit(0);
    }

    @Override 
    public String toString(){
        return Integer.toString(bit);
    }

}