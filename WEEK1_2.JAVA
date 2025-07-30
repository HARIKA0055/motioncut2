import java.util.Random;
import java.util.Scanner;

public class FakeJobTitleGenerator {

    public static void main(String[] args) {

        // Step 1: Define adjectives and job titles
        String[] adjectives = {
            "Quantum", "Dynamic", "Digital", "Strategic", "Creative", 
            "Virtual", "Chief", "Global", "Synergistic", "Futuristic"
        };

        String[] jobTitles = {
            "Marketing Ninja", "Synergy Officer", "Innovation Engineer", 
            "Brand Evangelist", "Operations Guru", "Solutions Architect", 
            "Data Whisperer", "Experience Designer", "AI Prophet", "Happiness Manager"
        };

        // Random and Scanner initialization
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String choice;

        do {
            // Step 2 & 3: Generate random title
            String adjective = adjectives[random.nextInt(adjectives.length)];
            String job = jobTitles[random.nextInt(jobTitles.length)];
            String fakeTitle = adjective + " " + job;

            // Step 4: Show the title
            System.out.println("\n🚀 Your Fake Job Title: " + fakeTitle);

            // Step 5: Ask to generate another
            System.out.print("Want another one? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("Thanks for using the Fake Job Title Generator, Baig saab!");
        scanner.close();
    }
}
