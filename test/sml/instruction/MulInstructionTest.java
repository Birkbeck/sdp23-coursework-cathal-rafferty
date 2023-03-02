package sml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MulInstructionTest {

    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    public void setup() {
        registers = new Registers();
        machine = new Machine(registers);
        labels = new Labels();
    }

    @Test
    public void testExecute() {
        int registerIndex1 = 1;
        int registerIndex2 = 2;
        int registerIndex3 = 3;
        int value1 = 5;
        int value2 = 10;
        int expectedValue = value1 * value2;

        // add some values to the registers
        registers.setRegister(registerIndex1, value1);
        registers.setRegister(registerIndex2, value2);

        // create and execute the instruction
        Instruction instruction = new MulInstruction("L1", registerIndex3, registerIndex1, registerIndex2);
        instruction.execute(machine);

        // check that the result is correct
        assertEquals(expectedValue, registers.getRegister(registerIndex3));
    }
}
