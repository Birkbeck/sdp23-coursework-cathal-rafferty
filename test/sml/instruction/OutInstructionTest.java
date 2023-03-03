package sml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutInstructionTest {

    private Machine machine;
    private Registers registers;
    private Instruction outInstruction;

    @BeforeEach
    public void setup() {
        registers = new Registers();
        machine = new Machine(registers);
        outInstruction = new OutInstruction("out", 1);
    }

    @Test
    public void testExecute() {
        int registerIndex = 1;
        int registerValue = 5;
        registers.setRegister(registerIndex, registerValue);

        outInstruction.execute(machine);

        assertEquals(registerValue, machine.getRegisters().getRegister(31));
    }

    @Test
    public void testToString() {
        String expected = "out: out register 1";
        assertEquals(expected, outInstruction.toString());
    }
}
