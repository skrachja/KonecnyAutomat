import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> rules = Arrays.asList(
                "S [a-zA-Z] E",
                "E [a-zA-Z0-9] E",
                "E [<>] G",
                "E [=] H",
                "H [=] F",
                "G [=] F",
                "F [+-] A",
                "F [.] B",
                "F [0-9] C",
                "G [+-] A",
                "G [.] B",
                "G [0-9] C",
                "A [0-9] C",
                "A [.] B",
                "B [0-9] D",
                "C [.] D",
                "C [0-9] C",
                "D [0-9] D"
        );

        Set<String> finalStates = new HashSet<>(Arrays.asList("C", "D"));

        Automat automat = new Automat(rules, finalStates);
        System.out.println(automat.processInput("a12342=1"));
    }
}