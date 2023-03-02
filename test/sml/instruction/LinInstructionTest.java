package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinInstructionTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;

    @Before
    public void setUp() {
        labels = new Labels();
        registers = new Registers();
        machine = new Machine(registers);
    }

    @Test
    public void testExecute() {
        // create an instance of LinInstruction with label L1, register 1 and value 42
        Instruction lin = new LinInstruction("L1", 1, 42);
        labels.addLabel("L1");

        // execute the instruction on the machine
        lin.execute(machine);

        // assert that the value in register 1 is now 42
        assertEquals(42, registers.getRegister(1));
    }

    @Test
    public void testToString() {
        // create an instance of LinInstruction with label L1, register 1 and value 42
        Instruction lin = new LinInstruction("L1", 1, 42);

        // assert that the string representation of the instruction is as expected
        assertEquals("L1: lin register 1 value is 42", lin.toString());
    }
}
