import java.util.HashMap;
import java.util.Scanner;

public class EmojiMoodResponder {

    public static void main(String[] args) {

        // Step 1: Create the mood-emoji-message map
        HashMap<String, String> moodMap = new HashMap<>();
        moodMap.put("happy", "😊 Stay positive and spread joy!");
        moodMap.put("sad", "😢 It's okay to feel down. Better days are coming.");
        moodMap.put("tired", "😴 Take a break, you've earned it!");
        moodMap.put("angry", "😠 Breathe in... breathe out. Let it go.");
        moodMap.put("excited", "🤩 Awesome! Keep that energy up!");
        moodMap.put("bored", "😐 Try doing something new or creative.");
        moodMap.put("anxious", "😟 You're doing your best. That's enough.");

        // Step 2: Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("How are you feeling today? (e.g., happy, sad, tired): ");
        String userMood = scanner.nextLine().toLowerCase().trim();

        // Step 3 & 4: Check mood and respond
        if (moodMap.containsKey(userMood)) {
            System.out.println("Your mood: " + userMood);
            System.out.println("Response: " + moodMap.get(userMood));
        } else {
            // Step 5: Mood not found
            System.out.println("🤔 Sorry, I don't recognize that mood.");
            System.out.println("Try moods like: " + moodMap.keySet());
        }

        scanner.close();
    }
}
