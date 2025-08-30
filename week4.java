import java.util.Random;
import java.util.Scanner;

public class week4 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // Rewards list
        String[] rewards = {
                "🎉 Free Coffee",
                "📚 Extra Credit",
                "🎁 Amazon Gift Card",
                "😢 Try Again",
                "🍔 Free Burger",
                "🎟️ Movie Ticket"
        };

        System.out.println("=====================================");
        System.out.println("   🎡 Welcome to Virtual Spin Wheel! ");
        System.out.println("=====================================");
        System.out.println("Available Rewards:");
        for (String reward : rewards) {
            System.out.println(" - " + reward);
        }

        int spins = 0;  // Track total spins
        int maxSpins = 5;  // Limit spins (optional)
        
        String choice;
        do {
            System.out.println("\n👉 Press ENTER to Spin the Wheel...");
            sc.nextLine();

            // Animation effect
            System.out.print("Spinning");
            for (int i = 0; i < 5; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            System.out.println();

            // Pick a random reward
            int index = random.nextInt(rewards.length);
            String selectedReward = rewards[index];
            System.out.println("🎯 Congratulations! You got: " + selectedReward);

            spins++;

            // Limit spins
            if (spins >= maxSpins) {
                System.out.println("\n⚠️ You have reached the maximum spins (" + maxSpins + ").");
                break;
            }

            // Ask user to continue
            System.out.print("Do you want to spin again? (yes/no): ");
            choice = sc.nextLine().trim().toLowerCase();

        } while (choice.equals("yes"));

        System.out.println("\n✨ Thank you for playing!");
        System.out.println("You spun the wheel " + spins + " time(s).");
        System.out.println("=====================================");

        sc.close();
    }
}
