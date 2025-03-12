import java.util.*;
import java.util.regex.Pattern;

class Automat {
    private final Map<String, List<Transition>> transitions = new HashMap<>();
    private final Set<String> finalStates;
    private final String startState = "S";

    public Automat(List<String> rules, Set<String> finalStates) {
        this.finalStates = finalStates;
        parseRules(rules);
    }

    private void parseRules(List<String> rules) {
        for (String rule : rules) {
            String[] parts = rule.split(" ");
            if (parts.length != 3) {
                continue;
            }
            String fromState = parts[0];
            String condition = parts[1].replace("[", "").replace("]", ""); // Odebrání []
            String toState = parts[2];

            transitions.putIfAbsent(fromState, new ArrayList<>());
            transitions.get(fromState).add(new Transition(condition, toState));
        }
    }

    public boolean processInput(String input) {
        String currentState = startState;

        for (char ch : input.toCharArray()) {
            boolean transitioned = false;
            if (transitions.containsKey(currentState)) {
                for (Transition t : transitions.get(currentState)) {
                    if (t.matches(ch)) {
                        currentState = t.toState;
                        transitioned = true;
                        break;
                    }
                }
            }
            if (!transitioned) {
                return false;
            }
        }
        return finalStates.contains(currentState);
    }

    private static class Transition {
        String condition;
        String toState;

        public Transition(String condition, String toState) {
            this.condition = condition;
            this.toState = toState;
        }

        public boolean matches(char ch) {
            return Pattern.matches("[" + condition + "]", String.valueOf(ch));
        }
    }
}
