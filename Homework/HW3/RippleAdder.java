public class RippleAdder {
    public static Longword add(Longword a, Longword b){
        Longword c = new Longword();
        int carry = 0;
        for (int i = c.LONGWORD_SIZE-1  ; i > 0; i--) {
            int add =  a.getBit(i).getValue() + b.getBit(i).getValue() + carry;
            
            if (carry == 0 && add > 1){ // 1 + 1 + 0(carry)
                c.setBit(i, 0);
                carry++;
            } else if (carry > 0 && add > 2){ // 1 + 1 + 1(carry)
                c.setBit(i, 1);
            }else if (carry > 1)  { // 0 + 0 + (carry > 1)  or 1 + 0 + (carry > 1)
                c.setBit(i, add - carry + 1);
                carry--;
            } else { // 0 + 0 + 1(carry) or 1 + 0 + 1(carry)
                c.setBit(i, add);
                if (carry != 0)
                    carry--;
            }

           //// System.out.println();
          ////  System.out.println(a + "\n+\n" + b + " = " + c + " - carry: " + carry);
        }

        return c;
    }
}
