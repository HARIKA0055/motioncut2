import java.util.Scanner;

public class week5 {
    
    // Method to calculate grade
    public static String calculateGrade(double average) {
        if (average >= 90) return "A (Excellent)";
        else if (average >= 75) return "B (Good)";
        else if (average >= 60) return "C (Average)";
        else if (average >= 40) return "D (Needs Improvement)";
        else return "F (Fail)";
    }

    // Method to give feedback
    public static String giveFeedback(String grade) {
        switch (grade.charAt(0)) {
            case 'A': return "Outstanding performance!";
            case 'B': return "Good job! Keep pushing towards excellence.";
            case 'C': return "Satisfactory. Focus on improvement.";
            case 'D': return "Needs improvement. Put in more effort.";
            default: return "Unfortunately, you failed. Work hard next time.";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Student info
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        // Skill areas
        String[] skills = {"Programming", "Communication", "Problem-Solving", "Teamwork", "Creativity"};
        int[] marks = new int[skills.length];
        int total = 0;

        // Input marks
        for (int i = 0; i < skills.length; i++) {
            System.out.print("Enter marks for " + skills[i] + ": ");
            marks[i] = sc.nextInt();
            total += marks[i];
        }

        // Calculations
        double average = (double) total / skills.length;
        String grade = calculateGrade(average);
        String feedback = giveFeedback(grade);

        // Output
        System.out.println("\n--- Student Report ---");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        for (int i = 0; i < skills.length; i++) {
            System.out.println(skills[i] + ": " + marks[i]);
        }
        System.out.printf("Total Marks: %d%n", total);
        System.out.printf("Average Score: %.2f%n", average);
        System.out.println("Grade: " + grade);
        System.out.println("Feedback: " + feedback);

        sc.close();
    }
}

