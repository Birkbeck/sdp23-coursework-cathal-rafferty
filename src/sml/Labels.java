package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A singleton class that stores labels and their corresponding addresses.
 * Uses dependency injection, the singleton design pattern, and a factory class.
 */
public final class Labels {
    private static Labels instance;
    private final Map<String, Integer> labels;

    /**
     * Private constructor to prevent external instantiation.
     */
    private Labels() {
        labels = new HashMap<>();
    }

    /**
     * Factory method to get the singleton instance of Labels.
     *
     * @return the singleton instance of Labels
     */
    public static Labels getInstance() {
        if (instance == null) {
            instance = new Labels();
        }
        return instance;
    }

    /**
     * Adds a label with the associated address to the map.
     *
     * @param label the label
     * @param address the address the label refers to
     * @throws IllegalArgumentException if the label already exists in the map
     */
    public void addLabel(String label, int address) {
        Objects.requireNonNull(label);
        if (labels.containsKey(label)) {
            throw new IllegalArgumentException("Label already exists: " + label);
        }
        labels.put(label, address);
    }

    /**
     * Returns the address associated with the label.
     *
     * @param label the label
     * @return the address the label refers to
     * @throws IllegalArgumentException if the label does not exist in the map
     */
    public int getAddress(String label) {
        Objects.requireNonNull(label);
        Integer address = labels.get(label);
        if (address == null) {
            throw new IllegalArgumentException("Label does not exist: " + label);
        }
        return address;
    }

    /**
     * Returns a string representation of the labels map.
     *
     * @return the string representation of the labels map
     */
    @Override
    public String toString() {
        return labels.entrySet().stream()
                .map(e -> e.getKey() + " -> " + e.getValue())
                .reduce("[", (a, b) -> a + b + ", ") + "]";
    }

    /**
     * Resets the labels map to its initial state (empty).
     */
    public void reset() {
        labels.clear();
    }
}
