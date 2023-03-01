package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.Registers.Register;

public class AddInstruction extends Instruction {
    private final Register register1;
    private final Register register2;

    public AddInstruction(String label, Register register1, Register register2) {
        super(label, "add");
        this.register1 = register1;
        this.register2 = register2;
    }

    @Override
    public int execute(Machine machine) {
        int value1 = machine.getRegisters().getRegister(register1.getNumber());
        int value2 = machine.getRegisters().getRegister(register2.getNumber());
        int result = value1 + value2;
        machine.getRegisters().setRegister(register1.getNumber(), result);
        return Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return super.getLabelString() + getOpcode() + " " + register1 + " " + register2;
    }
}
