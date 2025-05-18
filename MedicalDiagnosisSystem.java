import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a rule
class Rule {
    List<String> condition;
    String action;

    Rule(List<String> condition, String action) {
        this.condition = condition;
        this.action = action;
    }
}

// Inference Engine class
class InferenceEngine {
    private List<Rule> rules;
    private List<String> facts;

    public InferenceEngine() {
        rules = new ArrayList<>();
        facts = new ArrayList<>();
    }

    // Add a rule to the knowledge base
    public void addRule(List<String> condition, String action) {
        rules.add(new Rule(condition, action));
    }

    // Add a fact to the knowledge base
    public void addFact(String fact) {
        facts.add(fact);
    }

    // Apply forward chaining to infer new facts
    public void applyRules() {
        boolean newFactAdded;
        do {
            newFactAdded = false;
            for (Rule rule : rules) {
                boolean allConditionsMet = true;
                for (String cond : rule.condition) {
                    if (!facts.contains(cond)) {
                        allConditionsMet = false;
                        break;
                    }
                }
                if (allConditionsMet && !facts.contains(rule.action)) {
                    facts.add(rule.action);
                    System.out.println("Inferred: " + rule.action);
                    newFactAdded = true;
                }
            }
        } while (newFactAdded);
    }

    // Display all known facts
    public void displayFacts() {
        System.out.println("\nKnown Facts:");
        for (String fact : facts) {
            System.out.println("- " + fact);
        }
    }
}

// Main class
public class MedicalDiagnosisSystem {
    public static void simulateDiagnosis(InferenceEngine engine) {
        Scanner scanner = new Scanner(System.in);
        String response;

        System.out.println("Welcome to the Medical Diagnosis Expert System");

        System.out.print("Do you have a cough? (yes/no): ");
        response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            engine.addFact("cough");
        }

        System.out.print("Do you have a fever? (yes/no): ");
        response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            engine.addFact("fever");
        }

        System.out.print("Do you have a headache? (yes/no): ");
        response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            engine.addFact("headache");
        }

        System.out.print("Do you have body aches? (yes/no): ");
        response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            engine.addFact("body aches");
        }

        // Apply rules
        engine.applyRules();

        // Display all known facts
        engine.displayFacts();
    }

    public static void main(String[] args) {
        InferenceEngine engine = new InferenceEngine();

        // Define rules
        engine.addRule(List.of("cough", "fever"), "flu");
        engine.addRule(List.of("fever", "headache"), "migraine");
        engine.addRule(List.of("body aches", "fever"), "common cold");

        // Simulate the user interaction
        simulateDiagnosis(engine);
    }
}
