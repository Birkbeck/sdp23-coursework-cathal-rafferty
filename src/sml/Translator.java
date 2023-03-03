package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
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
     *
     * @param labels  The labels of the SML program
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
     *
     * @param label The label of the instruction
     * @return The next instruction in the program
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();

        // use reflection to create an instance of the instruction class
        try {
            Class<?> instructionClass = Class.forName("sml.instruction." + opcode + "Instruction");
            Constructor<?> constructor = instructionClass.getConstructor(String.class, Register.class, Register.class, Object.class);
            switch (constructor.getParameterCount()) {
                case 2:
                    return (Instruction) constructor.newInstance(label, Register.valueOf(scan()));
                case 3:
                    return (Instruction) constructor.newInstance(label, Register.valueOf(scan()), parseArgument(instructionClass.getConstructor(int.class), scan()));
                case 4:
                    return (Instruction) constructor.newInstance(label, Register.valueOf(scan()), Register.valueOf(scan()), parseArgument(instructionClass.getConstructor(int.class), scan()));
                default:
                    System.out.println("Unknown instruction: " + opcode);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Unknown instruction: " + opcode);
            return null;
        }
    }

    /**
     * Parses the argument for an instruction that takes an int argument
     *
     * @param constructor The constructor for the instruction
     * @param arg         The argument to be parsed
     * @return The parsed argument
     */
    private int parseArgument(Constructor<?> constructor, String arg) {
        try {
            return (int) constructor.newInstance(Integer.parseInt(arg)).newInstance();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the next word in the current line
     *
     * @return The next word in the line
     */
    private String scan() {
        line = line.trim();
        int whiteSpaceIndex = line.indexOf
