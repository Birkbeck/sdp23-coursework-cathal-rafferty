package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegInstructionTest {

    private Machine machine;
    private Registers registers;

    @Before
    public void setUp() {
        registers = new Registers();
        machine = new Machine(registers);
    }

    @Test
    public void testExecute() {
        RegInstruction instruction1 = new RegInstruction("r0", 10);
        RegInstruction instruction2 = new RegInstruction("r1", 20);

        // Execute the instructions
        instruction1.execute(machine);
        instruction2.execute(machine);

        // Check that the registers were correctly set
        assertEquals(10, registers.getRegister(0));
        assertEquals(20, registers.getRegister(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionWhenRegisterIsInvalid() {
        new RegInstruction("r32", 10);
    }

}
