import java.io.*;
import java.util.*;

class Habit {
    String name;
    String description;
    String frequency;
    int daysTracked = 0;
    int completions = 0;

    Habit(String name, String description, String frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
    }

    double getStrength() {
        if (daysTracked == 0) return 0;
        return ((double) completions / daysTracked) * 100;
    }

    String getFeedback() {
        double strength = getStrength();
        if (strength >= 80) return "Great job! You're consistently keeping up with your habit.";
        else if (strength >= 50) return "Good job! You're making progress, but there's room for improvement.";
        else return "Keep going! Try to be more consistent in completing your habit.";
    }

    @Override
    public String toString() {
        return name + "," + description + "," + frequency + "," + daysTracked + "," + completions;
    }

    static Habit fromString(String line) {
        String[] parts = line.split(",");
        Habit h = new Habit(parts[0], parts[1], parts[2]);
        h.daysTracked = Integer.parseInt(parts[3]);
        h.completions = Integer.parseInt(parts[4]);
        return h;
    }
}

public class week3 {
    static final String FILE_NAME = "habits.txt";
    static ArrayList<Habit> habits = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        loadHabits();
        while (true) {
            System.out.println("\n--- Habit Strength Calculator ---");
            System.out.println("1. Create Habit");
            System.out.println("2. Mark Habit Completion");
            System.out.println("3. View Habits & Strength");
            System.out.println("4. Save & Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: createHabit(); break;
                case 2: markCompletion(); break;
                case 3: viewHabits(); break;
                case 4: saveHabits(); System.out.println("Data saved. Goodbye!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void createHabit() {
        System.out.print("Enter habit name: ");
        String name = sc.nextLine();
        System.out.print("Enter habit description: ");
        String desc = sc.nextLine();
        System.out.print("Enter frequency (Daily/Weekly): ");
        String freq = sc.nextLine();
        habits.add(new Habit(name, desc, freq));
        System.out.println("Habit created successfully!");
    }

    static void markCompletion() {
        if (habits.isEmpty()) { System.out.println("No habits found."); return; }
        for (int i = 0; i < habits.size(); i++)
            System.out.println((i+1) + ". " + habits.get(i).name);
        System.out.print("Select habit: ");
        int idx = sc.nextInt() - 1;
        if (idx < 0 || idx >= habits.size()) { System.out.println("Invalid habit."); return; }
        Habit h = habits.get(idx);
        h.daysTracked++;
        System.out.print("Did you complete this habit today? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) h.completions++;
        System.out.println("Progress updated!");
    }

    static void viewHabits() {
        if (habits.isEmpty()) { System.out.println("No habits found."); return; }
        for (Habit h : habits) {
            System.out.printf("Habit: %s | Strength: %.2f%% | %s%n", h.name, h.getStrength(), h.getFeedback());
        }
    }

    static void saveHabits() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Habit h : habits) pw.println(h);
        }
    }

    static void loadHabits() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) habits.add(Habit.fromString(line));
        }
    }
}
