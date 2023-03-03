package sml.instruction;

import sml.Registers;

/**
 * This class represents the SML "mov" instruction
 * of the SML language
 */
public final class MovInstruction extends Instruction {

    private final int register;
    private final int value;

    public MovInstruction(String label, int register, int value) {
        super(label, "mov");
        this.register = register;
        this.value = value;
    }

    /**
     * Executes the instruction in the context of the given machine
     *
     * @param m the machine that the instruction is running on
     */
    @Override
    public void execute(Registers m) {
        m.setRegister(register, value);
    }

    /**
     * Returns a string representation of the instruction
     *
     * @return a string representation of the instruction
     */
    @Override
    public String toString() {
        return super.toString() + " register " + register + " value is " + value;
    }
}
