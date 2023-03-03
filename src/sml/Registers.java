package sml;

import java.util.HashMap;
import java.util.Map;

public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();
    private static Registers instance = null;

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
    }

    private Registers() {
        clear();
    }

    public static Registers getInstance() {
        if (instance == null) {
            synchronized (Registers.class) {
                if (instance == null) {
                    instance = new Registers();
                }
            }
        }
        return instance;
    }

    public void clear() {
        for (Register register : Register.values()) {
            registers.put(register, 0);
        }
    }

    public void set(RegisterName register, int value) {
        registers.put((Register) register, value);
    }

    public int get(RegisterName register) {
        return registers.get((Register) register);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers) {
            Registers other = (Registers) o;
            return registers.equals(other.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.toString();
    }
}
