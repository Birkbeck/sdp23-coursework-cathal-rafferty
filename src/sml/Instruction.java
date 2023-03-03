package sml;

/**
 * Represents an abstract instruction.
 *
 * <p>Each instruction has an optional label and an operation.</p>
 */
public abstract class Instruction {
    protected final String label;
    protected final String opcode;

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param opcode operation name
     */
    public Instruction(String label, String opcode) {
        this.label = label;
        this.opcode = opcode;
    }

    /**
     * Get the label of the instruction.
     *
     * @return the label of the instruction
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the opcode of the instruction.
     *
     * @return the opcode of the instruction
     */
    public String getOpcode() {
        return opcode;
    }

    /**
     * Executes the instruction in the given machine.
     *
     * @param machine the machine the instruction runs on
     * @return the new program counter (for jump instructions)
     *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    public abstract int execute(Machine machine);

    /**
     * Get the label string, including a colon and a space if the label is not null.
     *
     * @return the label string
     */
    protected String getLabelString() {
        return (getLabel() == null) ? "" : getLabel() + ": ";
    }

    /**
     * Returns a string representation of the instruction, in the format:
     *   label opcode ...
     *
     * Subclasses must override this method.
     *
     * @return a string representation of the instruction
     */
    @Override
    public abstract String toString();

    /**
     * Returns true if the instruction is equal to the given object, false otherwise.
     * Instructions are equal if they have the same opcode and label.
     *
     * Subclasses must override this method.
     *
     * @param o the object to compare with
     * @return true if the instruction is equal to the given object, false otherwise
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Returns a hash code value for the instruction.
     *
     * Subclasses must override this method.
     *
     * @return a hash code value for the instruction
     */
    @Override
    public abstract int hashCode();
}
