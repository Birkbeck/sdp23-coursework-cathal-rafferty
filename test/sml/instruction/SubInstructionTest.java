package sml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubInstructionTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    public void setup() {
        registers = new Registers();
        labels = new Labels();
        machine = new Machine(registers);
    }

    @Test
    public void testSubInstruction() {
        // Subtract two values and store result in a register
        registers.setRegister(1, 10);
        registers.setRegister(2, 3);

        SubInstruction sub = new SubInstruction("L1", 3, 1, 2);
        sub.execute(machine);

        assertEquals(7, registers.getRegister(3));
    }

    @Test
    public void testSubInstructionWithZero() {
        // Subtract zero from a value and store result in a register
        registers.setRegister(1, 10);

        SubInstruction sub = new SubInstruction("L1", 3, 1, 0);
        sub.execute(machine);

        assertEquals(10, registers.getRegister(3));
    }

    @Test
    public void testSubInstructionWithNegativeValue() {
        // Subtract a negative value from another value and store result in a register
        registers.setRegister(1, 10);
        registers.setRegister(2, -3);

        SubInstruction sub = new SubInstruction("L1", 3, 1, 2);
        sub.execute(machine);

        assertEquals(13, registers.getRegister(3));
    }

    @Test
    public void testSubInstructionWithNegativeResult() {
        // Subtract a larger value from a smaller value and store result in a register
        registers.setRegister(1, 3);
        registers.setRegister(2, 10);

        SubInstruction sub = new SubInstruction("L1", 3, 1, 2);
        sub.execute(machine);

        assertEquals(-7, registers.getRegister(3));
    }
}
