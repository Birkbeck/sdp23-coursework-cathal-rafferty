package sml.instruction;

import sml.Registers;

public class SubInstruction extends Instruction {
    private final int resultReg;
    private final int op1Reg;
    private final int op2Reg;

    public SubInstruction(String label, int resultReg, int op1Reg, int op2Reg) {
        super(label, "sub");
        this.resultReg = resultReg;
        this.op1Reg = op1Reg;
        this.op2Reg = op2Reg;
    }

    @Override
    public void execute(Registers registers) {
        int value1 = registers.getRegister(op1Reg);
        int value2 = registers.getRegister(op2Reg);
        registers.setRegister(resultReg, value1 - value2);
    }

    @Override
    public String toString() {
        return super.toString() + " " + op1Reg + " - " + op2Reg + " to " + resultReg;
    }
}
