public class Bit implements IBit {

    private int bit = 0;

    public Bit(int value) {
        set(value);
    }

    public Bit() {
        this(0);
    }

    public void set(int value) {
        if (value < 0 || value > 1)
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
            set(0);
        else
            set(1);
    }

}