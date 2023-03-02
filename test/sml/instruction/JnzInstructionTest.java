package sml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JnzInstructionTest {

    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    public void setUp() {
        registers = new Registers();
        labels = new Labels();
        machine = new Machine(registers);
    }

    @Test
    public void testExecuteWithZeroValue() {
        Instruction jnz = new JnzInstruction("f1", 0, "f3");
        labels.addLabel("f3");
        machine.getProgram().add(jnz);
        machine.execute();
        assertEquals(1, machine.getProgramCounter());
    }

    @Test
    public void testExecuteWithPositiveValue() {
        registers.setRegister(1, 5);
        Instruction jnz = new JnzInstruction("f1", 1, "f3");
        labels.addLabel("f3");
        machine.getProgram().add(jnz);
        machine.execute();
        assertEquals(0, machine.getProgramCounter());
    }

    @Test
    public void testExecuteWithNegativeValue() {
        registers.setRegister(2, -5);
        Instruction jnz = new JnzInstruction("f1", 2, "f3");
        labels.addLabel("f3");
        machine.getProgram().add(jnz);
        machine.execute();
        assertEquals(1, machine.getProgramCounter());
    }

    @Test
    public void testToString() {
        Instruction jnz = new JnzInstruction("f1", 0, "f3");
        assertEquals("f1: jnz if 0 not zero go to f3", jnz.toString());
    }

    @Test
    public void testConstructorWithInvalidRegister() {
        assertThrows(IllegalArgumentException.class, () -> new JnzInstruction("f1", 32, "f3"));
    }

    @Test
    public void testConstructorWithNullLabel() {
        assertThrows(NullPointerException.class, () -> new JnzInstruction("f1", 1, null));
    }

}
