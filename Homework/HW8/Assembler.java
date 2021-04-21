import java.util.ArrayList;

public class Assembler {
    
    public static String[] assemble(String[] commands) throws Exception{ //returns a string array of instructions in the format "0000 0000 0000 0000"
        ArrayList<String> bitCommands = new ArrayList<>();
        for (String command : commands) {
            String[] keys = command.split(" ");
            if (keys[0].equalsIgnoreCase("HALT")) {
                bitCommands.add(halt(keys));
            }else if (keys[0].equalsIgnoreCase("MOVE")) {
                bitCommands.add(move(keys));
            }else if (keys[0].equalsIgnoreCase("INTERRUPT")) {
                bitCommands.add(interrupt(keys));
            }else if (keys[0].equalsIgnoreCase("JUMP")) {
                bitCommands.add(jump(keys));
            }else if (keys[0].equalsIgnoreCase("COMPARE")) {
                bitCommands.add(compare(keys));
            }else if (keys[0].equalsIgnoreCase("STACK")) {
                bitCommands.add(stack(keys));
            }else if (keys[0].equalsIgnoreCase("MULTIPLY")) {
                bitCommands.add(multiply(keys));
            }else if (keys[0].equalsIgnoreCase("AND")) {
                bitCommands.add(and(keys));
            }else if (keys[0].equalsIgnoreCase("OR")) {
                bitCommands.add(or(keys));
            }else if (keys[0].equalsIgnoreCase("XOR")) {
                bitCommands.add(xor(keys));
            }else if (keys[0].equalsIgnoreCase("NOT")) {
                bitCommands.add(not(keys));
            }else if (keys[0].equalsIgnoreCase("LEFTSHIFT")) {
                bitCommands.add(leftshift(keys));
            }else if (keys[0].equalsIgnoreCase("RIGHTSHIFT")) {
                bitCommands.add(rightshift(keys));
            }else if (keys[0].equalsIgnoreCase("ADD")) {
                bitCommands.add(add(keys));
            }else if (keys[0].equalsIgnoreCase("SUBTRACT")) {
                bitCommands.add(subtract(keys));
            }else {
                throw new Exception("INVALID COMMAND @" + keys[0]);
            }
        }
        return (String []) bitCommands.toArray();
    }

    private static String halt(String[] keys){
        StringBuilder sb = new StringBuilder();
        sb.append("0000 0000 0000 0000"); //halt
        return sb.toString();
    }

    private static String move(String[] keys) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("0001"); //move
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //register
        sb.append(" ");
        sb.append(numToBinary(Integer.parseInt(keys[2]))); //number

        return sb.toString();
    }

    private static String interrupt(String[] keys) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("0010"); //interrupt
        sb.append(" ");
        sb.append("0000"); //padding 0s
        sb.append(" ");
        sb.append(numToBinary(Integer.parseInt(keys[1]))); //0 or 1

        return sb.toString();
    }

    private static String jump(String[] keys) throws Exception{
        StringBuilder sb = new StringBuilder();
        //TODO: Assignment 9

        return sb.toString();
    }

    private static String compare(String[] keys) throws Exception{
        StringBuilder sb = new StringBuilder();
        //TODO: Assignment 9

        return sb.toString();
    }

    private static String stack(String[] keys) throws Exception{
        StringBuilder sb = new StringBuilder();
        //TODO: Assignment 10

        return sb.toString();
    }

    private static String multiply(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("0111"); //multiply
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register

        return sb.toString();
    }

    private static String and(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1000"); //and
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register

        return sb.toString();
    }

    private static String or(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1001"); //or
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register

        return sb.toString();
    }

    private static String xor(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1010"); //xor
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register
        
        return sb.toString();
    }

    private static String not(String[] keys)throws Exception{
        if (keys.length != 3)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1011"); //not
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //destination register
        

        return sb.toString();
    }

    private static String leftshift(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1100"); //leftshift
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register
        
        return sb.toString();
    }

    private static String rightshift(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1101"); //rightshift
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register
        
        return sb.toString();
    }

    private static String add(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1110"); //add
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register
        
        return sb.toString();
    }

    private static String subtract(String[] keys) throws Exception{
        if (keys.length != 4)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1111"); //subtract
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source 1 register
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //source 2 register
        sb.append(" ");
        sb.append(registerNumber(keys[3])); //destination register
        
        return sb.toString();
    }

    private static String registerNumber(String reg) throws Exception{
        switch (reg.toUpperCase()) {
            case "R0":
                return "0000";
            case "R1":
                return "0001";
            case "R2":
                return "0010";
            case "R3":
                return "0011";
            case "R4":
                return "0100";
            case "R5":
                return "0101";
            case "R6":
                return "0110";
            case "R7":
                return "0111";
            case "R8":
                return "1000";
            case "R9":
                return "1001";
            case "R10":
                return "1010";
            case "R11":
                return "1011";
            case "R12":
                return "1100";
            case "R13":
                return "1101";
            case "R14":
                return "1110";
            case "R15":
                return "1111";
            default:
                throw new Exception("INVALID REGISTER @" + reg);
        }
    }

    private static String numToBinary(int num){ //returns a string number formatted 0000 0000
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
        sb.delete(0, sb.length()-9);
        sb.insert(4, " ");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
