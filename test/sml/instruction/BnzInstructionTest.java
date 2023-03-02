package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @Before
    public void setUp() {
        registers = new Registers();
        machine = new Machine(registers);
    }

    @Test
    public void testExecuteWhenNotBranching() {
        registers.setRegister(1, 0);
        BnzInstruction instruction = new BnzInstruction("L1", 1, 2);
        machine.getLabels().addLabel("L1");
        int programCounter = instruction.execute(machine);
        assertEquals(Machine.NORMAL_PROGRAM_COUNTER_UPDATE, programCounter);
    }

    @Test
    public void testExecuteWhenBranching() {
        registers.setRegister(1, 5);
        BnzInstruction instruction = new BnzInstruction("L1", 1, 2);
        machine.getLabels().addLabel("L1");
        int programCounter = instruction.execute(machine);
        assertEquals(2, programCounter);
    }

    @Test
    public void testToString() {
        BnzInstruction instruction = new BnzInstruction("L1", 1, 2);
        assertEquals("bnz L1 1 2", instruction.toString());
    }
}
