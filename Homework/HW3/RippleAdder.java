public class RippleAdder {
    public static Longword add(Longword a, Longword b){ //takes two longwords and adds them together
        if(a.getSigned() < 0) //if one of the longwords is negative
            return subtract(b, twosComplement(a));
        else if(b.getSigned() < 0)
            return subtract(a, twosComplement(b));
        
        Longword c = new Longword();
        int carry = 0;
        for (int i = c.LONGWORD_SIZE-1  ; i >= 0; i--) {
            int add =  a.getBit(i).getValue() + b.getBit(i).getValue() + carry;

            if (carry == 0 && add > 1){ // 1 + 1 + 0(carry)
                c.setBit(i, 0);
                carry++;
            }else if (carry > 0 && add > 2){ // 1 + 0 + 1(carry)
                c.setBit(i, 1);
            }else if (carry > 0 && add > 1){ // 1 + 1 + 1(carry)
                c.setBit(i, 0);
            }else { // 0 + 0 + 1(carry) or 1 + 0 + 1(carry) or 1 + 0 + 0(carry)
                c.setBit(i, add);
                if (carry != 0)
                    carry--;
            }
        }
        return c;
    }

    public static Longword subtract(Longword a, Longword b){ //takes two longword and subtracts one from the other
        if(a.getSigned() < 0 && b.getSigned() < 0) //if both longwords are negative
            return subtract(twosComplement(b), twosComplement(a));

        Longword aa = new Longword(a); //backups a because a can get modified during subtraction
        Longword c = new Longword();
        for (int i = c.LONGWORD_SIZE-1  ; i >= 0; i--) {
            int sub =  a.getBit(i).getValue() - b.getBit(i).getValue();
            if (sub < 0){ //0 - 1
                int borrow = borrow(i, a); //i tried skipping this step to save mem but it forces me to save the value 
                sub = a.getBit(i).getValue() - b.getBit(i).getValue() + borrow; //should now be 1 - 1 + 1
            }
            c.setBit(i, sub);
        }
        a.copy(aa); //sets a back to what it started as
        return c;
    }

    public static Longword sub(Longword a, Longword b){ //subtract with a shorter method name
        return subtract(a, b);
    }

    public static Longword twosComplement(Longword a){ // returns the two's complement of a
        return add(a.not(),new Longword(1));
    }

    private static int borrow(int index, Longword a){ //recursively borrows a 1 from the nearest bit = 1 
        if(index == 0){
            a.setBit(index, 1);
            return 1;
        }else if (a.getBit(index - 1).getValue() == 1){
            a.setBit(index - 1, 0);
            a.setBit(index, 1);
            return 1;
        }else{
            a.setBit(index, borrow(index - 1, a));
            return 1;
        }
    }
    
}
