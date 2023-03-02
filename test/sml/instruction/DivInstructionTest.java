package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivInstructionTest {
    private static final int REGISTER_VALUE_1 = 4;
    private static final int REGISTER_VALUE_2 = 2;
    private static final int EXPECTED_RESULT = 2;

    private Machine machine;
    private Registers registers;

    @Before
    public void setUp() {
        registers = new Registers();
        registers.setRegister(1, REGISTER_VALUE_1);
        registers.setRegister(2, REGISTER_VALUE_2);
        machine = new Machine(registers);
    }

    @Test
    public void testDivInstruction() {
        Instruction instruction = new DivInstruction("L1", 3, 1, 2);
        instruction.execute(machine);
        int actualResult = registers.getRegister(3);
        assertEquals(EXPECTED_RESULT, actualResult);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivInstructionDivideByZero() {
        Instruction instruction = new DivInstruction("L1", 3, 1, 0);
        instruction.execute(machine);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivInstructionInvalidRegister() {
        Instruction instruction = new DivInstruction("L1", 3, 0, 2);
    }
}
