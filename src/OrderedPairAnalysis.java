import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Pair;

/**
 * This class checks if the set of ordered pairs is Transitive, Reflexive, Symmetric.
 * ... Also checks if the set of ordered pairs is an equivalence relation, if so,
 * ... find all equivalence classes.
 */
public class OrderedPairAnalysis {
    public static ArrayList<Pair<Long>> Set = new ArrayList<>(); // Set of ordered pairs
    public static ArrayList<Long> uniqueElements = new ArrayList<>(); // Set of unique elements
    /**
     * Spawn point
     * @param args Useless
     */
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        try {
            // Real stuff happens here
            System.out.print("Enter the ordered pairs (accepted delimiters: comma, dot, space): ");
            String rawUserInput = S.nextLine();
            /* WARNING: THE FOLLOWING LINE DIRECTLY MODIFIES Set AND uniqueElements. THIS IS NOT RECOMMENDED IN GENERAL. */
            rawStringToSet(rawUserInput); // Converts the raw input to the set
        }catch(Exception e) {
            e.printStackTrace(); // Just throw the exception
        }finally {
            S.close(); // Close the scanner no matter what
        }
    }

    public static void rawStringToSet(String in) {
        /* WARNING: THE FOLLOWING FUNCTION DIRECTLY MODIFIES Set AND uniqueElements. THIS IS NOT RECOMMENDED IN GENERAL. */
        // Regex pattern to capture various formats of pairs
        Pattern pattern = Pattern.compile(
            "(\\d+)[, .]?(\\d+)"  // Captures pairs with optional comma, space, or dot between digits
        );

        Matcher matcher = pattern.matcher(in);
        while (matcher.find()) {
            long first = Long.parseLong(matcher.group(1));
            long second = Long.parseLong(matcher.group(2));
            Set.add(new Pair<Long>(first, second));
            if(! uniqueElements.contains(first)) uniqueElements.add(first);
            if(! uniqueElements.contains(second)) uniqueElements.add(second);
        }
    }

    public static boolean isReflexive(ArrayList<Pair<Long>> Set) {
        boolean isReflexive = false;

        return isReflexive;
    }
}
