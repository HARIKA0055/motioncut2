import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration; // Import Duration for calculateDelay

public class week2_1{

    // A simple class to hold goal details
    static class Goal {
        String description;
        LocalTime reminderTime;

        public Goal(String description, LocalTime reminderTime) {
            this.description = description;
            this.reminderTime = reminderTime;
        }

        @Override
        public String toString() {
            if (reminderTime != null) {
                return "\"" + description + "\" at " + reminderTime.toString();
            } else {
                return "\"" + description + "\" (No specific reminder time)";
            }
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        List<Goal> goals = new ArrayList<>();
        Timer timer = new Timer();

        System.out.println("--- Daily Goal Reminder Application ---");
        System.out.println("Enter your daily goals. Type 'done' to finish.");
        System.out.println("You can specify a time like: 'Exercise at 08:00' or 'Read book'.");

        while (true) {
            System.out.print("Enter goal: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            if (input.isEmpty()) {
                System.out.println("Goal cannot be empty. Please try again.");
                continue;
            }

            LocalTime reminderTime = null;
            String goalDescription = input;

            // Simple heuristic to detect time in input (e.g., "at HH:MM")
            int atIndex = input.toLowerCase().indexOf(" at ");
            if (atIndex != -1 && atIndex + 4 < input.length()) {
                String timeString = input.substring(atIndex + 4).trim();
                try {
                    reminderTime = LocalTime.parse(timeString);
                    goalDescription = input.substring(0, atIndex).trim(); // Remove the " at HH:MM" part
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format for '" + timeString + "'. Please use HH:MM. No specific time set for this goal.");
                    // reminderTime remains null
                }
            }

            goals.add(new Goal(goalDescription, reminderTime));
            System.out.println("Added: " + new Goal(goalDescription, reminderTime));
        }

        if (goals.isEmpty()) {
            System.out.println("No goals set for today. Exiting.");
            scanner.close();
            timer.cancel();
            return;
        }

        System.out.println("\n--- Your Goals for Today ---");
        for (int i = 0; i < goals.size(); i++) {
            System.out.println((i + 1) + ". " + goals.get(i));
        }
        System.out.println("----------------------------\n");

        System.out.println("Scheduling reminders...");

        for (Goal goal : goals) {
            if (goal.reminderTime != null) {
                long delay = calculateDelay(goal.reminderTime);

                if (delay > 0) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("\n--- REMINDER ---");
                            System.out.println("Time to: " + goal.description);
                            System.out.println("Scheduled for: " + goal.reminderTime);
                            System.out.println("----------------\n");
                        }
                    }, delay);
                    System.out.println("Reminder scheduled for " + goal.description + " at " + goal.reminderTime);
                } else {
                    System.out.println("Reminder time for \"" + goal.description + "\" (" + goal.reminderTime + ") is in the past or invalid for today. Not scheduling.");
                }
            } else {
                System.out.println("No specific reminder time set for \"" + goal.description + "\". Not scheduling an alert.");
            }
        }

        System.out.println("\nAll reminders configured. The application will notify you at the specified times.");
        System.out.println("Keep this window open. Press Ctrl+C to stop the application.");

        // Keep the main thread alive so Timer can execute tasks
        // A simple way is to make the main thread wait or sleep indefinitely.
        // For a console application, this typically means the program will just run
        // until explicitly terminated by the user (Ctrl+C).
        // If this were a GUI app, the GUI event loop would keep it alive.
    }

    /**
     * Calculates the delay in milliseconds from the current time to the reminder time.
     * If the reminder time is in the past for today, it returns -1.
     */
    private static long calculateDelay(LocalTime reminderTime) {
        LocalTime now = LocalTime.now();

        if (reminderTime.isAfter(now)) {
            // If reminder time is later today
            return Duration.between(now, reminderTime).toMillis();
        } else {
            // Reminder time is in the past for today
            return -1;
        }
    }
}