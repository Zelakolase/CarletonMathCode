import java.util.ArrayList;
import java.util.HashMap;
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
    // a --> b,c and b --> c for a,b a,c b,c (example)
    public static HashMap<Long, ArrayList<Long>> uniqueElements = new HashMap<>(); // Hashmap of unique elements
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
            
            // Is the set reflexive?
            boolean isReflexive = isReflexive();
            System.out.println("Is the set reflexive? " + (isReflexive? "Yes!" : "No:("));

            // Is the set symmetric?
            boolean isSymmetric = isSymmetric();
            System.out.println("Is the set symmetric? " + (isSymmetric? "Yes!" : "No:("));
            
            // Is the set transitive?
            boolean isTransitive = isTransitive();
            System.out.println("Is the set transitive? " + (isTransitive? "Yes!" : "No:("));

            // Does this set resemble an equivalence relation?
            System.out.println("Does this set resemble an equivalence relation? " + (isReflexive && isSymmetric && isTransitive ? "Yuppy!" : "Nah"));
        }catch(Exception e) {
            e.printStackTrace(); // Just throw the exception
        }finally {
            S.close(); // Close the scanner no matter what
        }
    }

    /**
     * Uses generalized regex format to convert user input to actual data structure
     * @param in The user input from the terminal
     */
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
            if(! uniqueElements.keySet().contains(first)) uniqueElements.put(first, new ArrayList<>());
            if(! uniqueElements.keySet().contains(second)) uniqueElements.put(second, new ArrayList<>());
            uniqueElements.get(first).add(second);
        }
    }

    /**
     * Check if each x belongs to an arbitrary pair in Set has the pair (x,x) in Set
     * @return Whether the set is reflexive or not
     */
    public static boolean isReflexive() {
        boolean isReflexive = false;

        // Create a copy of uniqueElements. That is a trade-off in favor of CP since Memory is abundant
        ArrayList<Long> tempUniqueElements = new ArrayList<>(uniqueElements.keySet());

        // This way, we can pass on the Set of ordered pairs only once.
        for(Pair<Long> P : Set) {
            if(P.first.equals(P.second)) tempUniqueElements.remove(P.first);
        }

        // If all unique elements were identified and removed, the set is reflexive.
        if(tempUniqueElements.isEmpty()) isReflexive = true;

        return isReflexive;
    }

    /**
     * For each (a,b) in Set, check if there exists (b,a) in Set
     * @return Whether the set is symmetric or not
     */
    public static boolean isSymmetric() {
        boolean isSymmetric = true;

        // Create a copy of Set. That is a trade-off in favor of CP since Memory is abundant
        ArrayList<Pair<Long>> tempSet = new ArrayList<>(Set);

        // Get the first pair, find the inverse, check if it exists, then delete both.
        // Stop when you exhaust the entire set (Time to run < O(size(Set)))
        while (!tempSet.isEmpty()) {
            Pair<Long> P = tempSet.getFirst();
            Pair<Long> inverse = new Pair<Long>(P.second, P.first);
            // We AND the variable. If it's false once, will remain false
            isSymmetric &= tempSet.contains(inverse);
            tempSet.remove(inverse);
            tempSet.remove(P);
        }

        return isSymmetric;
    }
    
    /**
     * Checks if this implication holds: (a,b) in Set, and (b,c) in Set, implies (a,c) are in Set
     * @return Whether the set is transitive or not
     */
    public static boolean isTransitive() {
        boolean isTransitive = true;

        // A,B ^ B,C --> A,C
        for(Long A : uniqueElements.keySet()) {
            for(long B : uniqueElements.get(A)) {
                if(uniqueElements.containsKey(B)) isTransitive &= (new ArrayList<>(uniqueElements.get(A))).retainAll(uniqueElements.get(B)) || uniqueElements.get(A).equals(uniqueElements.get(B));
            }
        }

        return isTransitive;
    }
}
