public class longword_test {
    public static void main(String[] args) {
        Longword l = new Longword();
        System.out.println(l.not().rightShift(2).leftShift(1).not());
        System.out.println(l.not().rightShift(2).leftShift(1).not().getSigned());
    }
}
