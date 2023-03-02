package sml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    public void setup() {
        registers = new Registers();
        machine = new Machine(registers);
    }

    @Test
    public void testExecute() {
        int registerIndex1 = 1;
        int registerIndex2 = 2;
        int value = 5;
        registers.setRegister(registerIndex1, value);
        MovInstruction movInstruction = new MovInstruction("L1", registerIndex1, registerIndex2);
        movInstruction.execute(machine);
        assertEquals(value, registers.getRegister(registerIndex2));
    }

    @Test
    public void testToString() {
        int registerIndex1 = 1;
        int registerIndex2 = 2;
        MovInstruction movInstruction = new MovInstruction("L1", registerIndex1, registerIndex2);
        String expected = "L1: mov register 1 value to register 2";
        assertEquals(expected, movInstruction.toString());
    }
}
