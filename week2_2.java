import java.util.ArrayList; // Added for sorting display
import java.util.Collections; // Added for sorting display
import java.util.HashMap;
import java.util.List; // Added for sorting display
import java.util.Map;
import java.util.Scanner;

public class week2_2 {

    // HashMap to store color names and their meanings
    private static Map<String, String> colorMeanings = new HashMap<>();

    // Static block to initialize the map with predefined colors
    static {
        colorMeanings.put("RED", "Symbolizes passion, energy, love, anger, and danger.");
        colorMeanings.put("BLUE", "Represents tranquility, stability, peace, intelligence, and sadness.");
        colorMeanings.put("GREEN", "Associated with nature, growth, harmony, freshness, and fertility. Also envy.");
        colorMeanings.put("YELLOW", "Signifies joy, happiness, intellect, and energy. Can also indicate caution.");
        colorMeanings.put("ORANGE", "Combines the energy of red and the happiness of yellow. Associated with enthusiasm, creativity, and success.");
        colorMeanings.put("PURPLE", "Often linked with royalty, power, nobility, luxury, ambition, and magic.");
        colorMeanings.put("BLACK", "Symbolizes power, elegance, formality, death, evil, and mystery.");
        colorMeanings.put("WHITE", "Represents purity, innocence, light, goodness, safety, and cleanliness.");
        colorMeanings.put("PINK", "Symbolizes compassion, nurturing, love, romance, and playfulness.");
        colorMeanings.put("BROWN", "Associated with stability, reliability, warmth, honesty, and nature.");
        colorMeanings.put("GREY", "Represents neutrality, balance, formality, and sophistication. Can also be associated with sadness.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Color Meaning Checker ---");
        System.out.println("Enter a color name to find its meaning, or type 'list' to see available colors.");
        System.out.println("Type 'add' to add a new color, or 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter color name or command: ");
            String userInput = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces

            if (userInput.equalsIgnoreCase("exit")) {
                break; // Exit the loop if user types 'exit'
            }

            if (userInput.isEmpty()) {
                System.out.println("Input cannot be blank. Please enter a color or command.");
                continue; // Skip to the next iteration
            }

            String colorKey = userInput.toUpperCase(); // Convert to uppercase for case-insensitive comparison

            if (colorMeanings.containsKey(colorKey)) {
                System.out.println("Meaning of " + capitalizeFirstLetter(userInput) + ": " + colorMeanings.get(colorKey));
            } else {
                // If it's not a recognized color, check for other commands
                if (colorKey.equalsIgnoreCase("LIST")) {
                    displayAvailableColors();
                } else if (colorKey.equalsIgnoreCase("ADD")) {
                    addNewColorMeaning(scanner);
                } else {
                    System.out.println("Color '" + capitalizeFirstLetter(userInput) + "' not found. Try again or type 'list' to see available colors, 'add' to add a new color.");
                }
            }
        }

        scanner.close(); // Close the scanner when done
        System.out.println("Thank you for using the Color Meaning Checker!");
    }

    /**
     * Helper method to add a new color meaning to the map.
     * @param scanner The Scanner object for user input.
     */
    private static void addNewColorMeaning(Scanner scanner) {
        System.out.print("Enter the new color name: ");
        String newColorName = scanner.nextLine().trim();

        if (newColorName.isEmpty()) {
            System.out.println("Color name cannot be blank. Aborting add operation.");
            return;
        }

        String newColorKey = newColorName.toUpperCase();
        if (colorMeanings.containsKey(newColorKey)) {
            System.out.println("Color '" + capitalizeFirstLetter(newColorName) + "' already exists. Its current meaning is: " + colorMeanings.get(newColorKey));
            System.out.print("Do you want to update its meaning? (yes/no): ");
            String confirmUpdate = scanner.nextLine().trim();
            if (!confirmUpdate.equalsIgnoreCase("yes")) {
                System.out.println("Meaning not updated.");
                return;
            }
        }

        System.out.print("Enter the meaning for " + capitalizeFirstLetter(newColorName) + ": ");
        String newMeaning = scanner.nextLine().trim();

        if (newMeaning.isEmpty()) {
            System.out.println("Meaning cannot be blank. Aborting add operation.");
            return;
        }

        colorMeanings.put(newColorKey, newMeaning);
        System.out.println("Color '" + capitalizeFirstLetter(newColorName) + "' and its meaning have been added/updated successfully!");
    }

    /**
     * Helper method to display all available colors in the map.
     */
    private static void displayAvailableColors() {
        if (colorMeanings.isEmpty()) {
            System.out.println("No colors available in the dictionary yet.");
            return;
        }
        System.out.println("\n--- Available Colors ---");
        // Get the keys (color names) from the map and iterate
        List<String> sortedColors = new ArrayList<>(colorMeanings.keySet());
        Collections.sort(sortedColors); // Sort alphabetically for better display

        for (String color : sortedColors) {
            System.out.println("- " + capitalizeFirstLetter(color));
        }
        System.out.println("------------------------");
    }

    /**
     * Helper method to capitalize the first letter of a string and make the rest lowercase.
     * @param str The input string.
     * @return The string with the first letter capitalized.
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}