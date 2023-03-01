package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public final class Translator {

    private final String fileName; // source file of SML code
    private String line = ""; // line contains the characters in the current line that's not been processed yet

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads and translates the small program in the file into {@code labels} (the labels) and {@code program} (the program)
     * @param labels The labels of the SML program
     * @param program The list of instructions in the SML program
     * @throws IOException If there is an error reading the file
     */
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Returns the next instruction from the current line
     * @param label The label of the instruction
     * @return The next instruction in the program
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                String t = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s), Register.valueOf(t));
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                String t = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s), Register.valueOf(t));
            }
            case MulInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                String t = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s), Register.valueOf(t));
            }
            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                String t = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s), Register.valueOf(t));
            }
            case OutInstruction.OP_CODE -> {
                String r = scan();
                return new OutInstruction(label, Register.valueOf(r));
            }
            case LinInstruction.OP_CODE -> {
                String r = scan();
                int value = scanInt();
                return new LinInstruction(label, Register.valueOf(r), value);
            }
            case BnzInstruction.OP_CODE -> {
                String r = scan();
                String l = scan();
                return new BnzInstruction(label, Register.valueOf(r), l);
            }
            case MovInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new MovInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case JnzInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new JnzInstruction(label, Register.valueOf(r), label2);

            }

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;
    }


    private String getLabel() {
        String word = scan
