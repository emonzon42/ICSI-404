import java.util.ArrayList;

public class Assembler {

    public static String[] assemble(String[] commands) throws Exception{ //returns a string array of instructions in the format "0000 0000 0000 0000"
        ArrayList<String> bitCommands = new ArrayList<String>();
        for (String command : commands) {
            String[] keys = command.split(" ");
            if (keys[0].equalsIgnoreCase("HALT")) {
                addToList(halt(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("MOVE")) {
                addToList(move(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("INTERRUPT")) {
                addToList(interrupt(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("JUMP")) {
                addToList(jump(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("COMPARE")) {
                addToList(compare(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("PUSH")) {
                addToList(stack(keys,1),bitCommands);
            }else if (keys[0].equalsIgnoreCase("POP")) {
                addToList(stack(keys,2),bitCommands);
            }else if (keys[0].equalsIgnoreCase("CALL")) {
                addToList(stack(keys,3),bitCommands);
            }else if (keys[0].equalsIgnoreCase("RETURN")) {
                addToList(stack(keys,4),bitCommands);
            }else if (keys[0].equalsIgnoreCase("MULTIPLY")) {
                addToList(multiply(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("AND")) {
                addToList(and(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("OR")) {
                addToList(or(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("XOR")) {
                addToList(xor(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("NOT")) {
                addToList(not(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("LEFTSHIFT")) {
                addToList(leftshift(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("RIGHTSHIFT")) {
                addToList(rightshift(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("ADD")) {
                addToList(add(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("SUBTRACT")) {
                addToList(subtract(keys),bitCommands);
            }else if (keys[0].equalsIgnoreCase("BEQ") || keys[0].equalsIgnoreCase("BRANCHIFEQUAL")) {
                addToList(branch(keys,1),bitCommands);
            }else if (keys[0].equalsIgnoreCase("BGT") || keys[0].equalsIgnoreCase("BRANCHIFGREATERTHAN")) {
                addToList(branch(keys,2),bitCommands);
            }else if (keys[0].equalsIgnoreCase("BGE") || keys[0].equalsIgnoreCase("BRANCHIFGREATERTHANOREQUAL")) {
                addToList(branch(keys,3),bitCommands);
            }else if (keys[0].equalsIgnoreCase("BNE") || keys[0].equalsIgnoreCase("BRANCHIFNOTEQUAL")) {
                addToList(branch(keys,4),bitCommands);
            }else {
                throw new Exception("INVALID COMMAND @" + keys[0]);
            }
        }

        return bitCommands.toArray(new String[bitCommands.size()]);
    }



    private static String halt(String[] keys) throws Exception{ //halt instruction
        if (keys.length != 1)
            throw new Exception("INVALID NO. OF KEYS");

        StringBuilder sb = new StringBuilder();
        sb.append("0000 0000 0000 0000"); //halt
        return sb.toString();
    }

    private static String move(String[] keys) throws Exception{ //move instruction
        if (keys.length != 3)
            throw new Exception("INVALID NO. OF KEYS");
        
        StringBuilder sb = new StringBuilder();
        sb.append("0001"); //move
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //register
        sb.append(" ");
        sb.append(numToBinary(Integer.parseInt(keys[2])).substring(5)); //value to move

        return sb.toString();
    }

    private static String interrupt(String[] keys) throws Exception{ //interrupt instruction
        if (keys.length != 2)
            throw new Exception("INVALID NO. OF KEYS");
        

        StringBuilder sb = new StringBuilder();
        sb.append("0010"); //interrupt
        sb.append(" ");
        sb.append(numToBinary(Integer.parseInt(keys[1]))); //0 or 1

        return sb.toString();
    }

    private static String jump(String[] keys) throws Exception{ //jump instruction
        if (keys.length != 2)
            throw new Exception("INVALID NO. OF KEYS");

        StringBuilder sb = new StringBuilder();
        sb.append("0011"); //jump
        sb.append(" ");
        sb.append(numToBinary(Integer.parseInt(keys[1]))); //address

        return sb.toString();
    }

    private static String compare(String[] keys) throws Exception{ //compare instruction
        if (keys.length != 3)
            throw new Exception("INVALID NO. OF KEYS");
        StringBuilder sb = new StringBuilder();
        sb.append("0100"); //branch
        sb.append(" ");
        sb.append("0000"); // padding 0s
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //register x
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //register y
        

        return sb.toString();
    }

    private static String branch(String[] keys, int type) throws Exception{ //branch instructions
        if (keys.length != 2)
            throw new Exception("INVALID NO. OF KEYS");

        StringBuilder sb = new StringBuilder();

        sb.append("0101"); //branch
        sb.append(" ");
        switch (type) {
            case 1: //BRANCH IF EQUAL
                sb.append("11");
                break;
            case 2: //BRANCH IF GREATER THAN
                sb.append("10");
                break;
            case 3: //BRANCH IF GREATER THAN OR EQUAL
                sb.append("01");
                break;
            default: //BRANCH IF NOT EQUAL
                sb.append("00");
                break;
        }
        sb.append(numToBinary(Integer.parseInt(keys[1])).substring(2)); //value to jump

        return sb.toString();
    }

    private static String stack(String[] keys, int type) throws Exception{ //stack instructions
        if (type != 4 && keys.length != 2 || type == 4 && keys.length != 1)
            throw new Exception("INVALID NO. OF KEYS");

        StringBuilder sb = new StringBuilder();
        sb.append("0110"); //stack
        sb.append(" ");
        switch (type) {
            case 1: //PUSH
                sb.append("0000");
                sb.append(" ");
                sb.append("0000");
                sb.append(" ");
                sb.append(registerNumber(keys[1]));
                break;
            case 2: //POP
                sb.append("0100");
                sb.append(" ");
                sb.append("0000");
                sb.append(" ");
                sb.append(registerNumber(keys[1]));
                break;
            case 3: //CALL
                sb.append("10");
                sb.append(numToBinary(Integer.parseInt(keys[1])).substring(2));
                break;
            default: //RETURN
                sb.append("1100 0000 0000");
            break;
        }
        return sb.toString();
    }

    private static String multiply(String[] keys) throws Exception{ //multiply instruction
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

    private static String and(String[] keys) throws Exception{ //and instruction
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

    private static String or(String[] keys) throws Exception{ //or instruction
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

    private static String xor(String[] keys) throws Exception{ //xor instruction
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

    private static String not(String[] keys)throws Exception{ //not instruction
        if (keys.length != 3)
            throw new Exception("INVALID NO. OF REGISTERS");

        StringBuilder sb = new StringBuilder();
        sb.append("1011"); //not
        sb.append(" ");
        sb.append(registerNumber(keys[1])); //source register
        sb.append(" ");
        sb.append("0000"); //padding 0s
        sb.append(" ");
        sb.append(registerNumber(keys[2])); //destination register
        
        

        return sb.toString();
    }

    private static String leftshift(String[] keys) throws Exception{ //leftshift instruction
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

    private static String rightshift(String[] keys) throws Exception{ //rightshift instruction
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

    private static String add(String[] keys) throws Exception{ //add instruction
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

    private static String subtract(String[] keys) throws Exception{ //subtract instruction
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

    private static String registerNumber(String reg) throws Exception{ //returns register number indicated by reg in binary
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

    private static String numToBinary(int num) throws Exception{ //returns a string number formatted 0000 0000 0000
        StringBuilder sb;
        if(num < -2048 || num > 2047){
            throw new Exception("INVALID NUMBER @ "+ num);
        } else if (num < 0) {
            sb = new StringBuilder(Long.toBinaryString(num));
            sb.delete(0, sb.length()-12);
        } else {
            sb = new StringBuilder(Integer.toBinaryString(num));
        }

        if (sb.length() > 12){
            throw new Exception("INVALID NUMBER @ "+ num);
        }else if(sb.length() < 12){
            int initLength = sb.length();
            for (int i = 0; i < 12-initLength; i++) {
                sb.insert(i, "0");
            }
        }

        //the following should always evaluate to true
        if(sb.length() == 12){ //adds spaces
            sb.insert(4," ");
            sb.insert(9," ");
        }else{
            throw new Exception("ASSEMBLY ERROR");
        }

        return sb.toString();
    }

    private static void addToList(String strbCommands, ArrayList<String> bCommands){ //adds bitcommand string to arraylist command by command
        for (String string : strbCommands.split(" ")) {
            bCommands.add(string);
        }
    }
}
