package sml.instruction;

import sml.Registers;

public class LinInstruction extends Instruction {
    private final int register;
    private final int value;

    public LinInstruction(String label, int register, int value) {
        super(label, "lin");
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute(Registers registers) {
        registers.setRegister(register, value);
    }

    @Override
    public String toString() {
        return super.toString() + " register " + register + " value is " + value;
    }
}
