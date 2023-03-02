package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 */
public final class Machine {

	private final Labels labels;
	private final List<Instruction> program;
	private final Registers registers;
	private final static Machine instance = new Machine(Registers.getInstance());

	private Machine(Registers registers) {
		this.labels = new Labels();
		this.program = new ArrayList<>();
		this.registers = registers;
	}

	public static Machine getInstance() {
		return instance;
	}

	/**
	 * Execute the program in program, beginning at instruction 0.
	 * Precondition: the program and its labels have been stored properly.
	 */
	public void execute() {
		int programCounter = 0;
		registers.clear();
		while (programCounter < program.size()) {
			Instruction ins = program.get(programCounter);
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == Instruction.NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}

	public Labels getLabels() {
		return this.labels;
	}

	public List<Instruction> getProgram() {
		return this.program;
	}

	public Registers getRegisters() {
		return this.registers;
	}

	/**
	 * Adds an instruction to the program.
	 *
	 * @param instruction the instruction to add
	 */
	public void addInstruction(Instruction instruction) {
		program.add(instruction);
	}

	/**
	 * String representation of the program under execution.
	 *
	 * @return pretty formatted version of the code.
	 */
	@Override
	public String toString() {
		return program.stream()
				.map(Instruction::toString)
				.collect(Collectors.joining("\n"));
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine) {
			Machine other = (Machine) o;
			return Objects.equals(this.labels, other.labels)
					&& Objects.equals(this.program, other.program)
					&& Objects.equals(this.registers, other.registers);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels, program, registers);
	}
}
