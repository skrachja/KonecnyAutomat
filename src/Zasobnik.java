import java.util.EmptyStackException;

public class Zasobnik<T> {
    private static class Node<T> {
        T prvek;
        Node<T> dalsi;

        Node(T prvek, Node<T> dalsi) {
            this.prvek = prvek;
            this.dalsi = dalsi;
        }
    }

    private Node<T> vrchol;

    public Zasobnik() {
        vrchol = null;
    }

    public boolean jePrazdny() {
        return vrchol == null;
    }

    public void pridej(T prvek) {
        vrchol = new Node<>(prvek, vrchol);
    }

    public T vyber() {
        if (jePrazdny()) {
            throw new EmptyStackException();
        }
        T prvek = vrchol.prvek;
        vrchol = vrchol.dalsi;
        return prvek;
    }
}
