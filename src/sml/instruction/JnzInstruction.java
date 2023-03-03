package sml.instruction;

import sml.Registers;

/**
 * This class represents the SML "jnz" instruction
 * of the SML language
 */
public final class JnzInstruction extends Instruction {

    private final int register;
    private final String jumpLabel;

    public JnzInstruction(String label, int register, String jumpLabel) {
        super(label, "jnz");
        this.register = register;
        this.jumpLabel = jumpLabel;
    }

    /**
     * Executes the instruction in the context of the given machine
     *
     * @param m the machine that the instruction is running on
     */
    @Override
    public void execute(Registers m) {
        if (m.getRegister(register) != 0) {
            m.setPc(m.getLabels().indexOf(jumpLabel));
        }
    }

    /**
     * Returns a string representation of the instruction
     *
     * @return a string representation of the instruction
     */
    @Override
    public String toString() {
        return super.toString() + " register " + register + " jump to " + jumpLabel;
    }
}
