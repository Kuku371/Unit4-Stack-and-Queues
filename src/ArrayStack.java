public class ArrayStack<T> implements Stack<T> {
    private T[] items;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        items = (T[]) new Object[1];
        n = 0;
    }

    @Override
    public void push(T item) {
        items[n] = item;
        n++;
    }

    @Override
    public T pop() {
        if (n==0){
            throw new IllegalArgumentException();
        }
        T outity = items[n-1];
        items[n-1]=null;
        return outity;
    }

    @Override
    public boolean isEmpty() {
        return (n==0);
    }

    @Override
    public int size() {
        return n;
    }

    /**
     * Challenge: Given a string containing '(', ')', '{', '}', '[', and ']'
     * return true if every opening bracket is closed by the same type in the correct order.
     * The empty string is valid.
     */
    public static boolean isValid(String s) {
        // TODO: Use an ArrayStack<Character> to check matching brackets. Push opening brackets;
        // for each closer, check that the most recent opener has the matching type.
        ArrayStack<String> characters = new ArrayStack<String>();
        while (s.length() != 0){
            String newity = String.valueOf(s.charAt(0));
            if (newity.equals("(") || newity.equals("{") || newity.equals("[")){
                characters.push(newity);
            }
            else {
                if (characters.isEmpty()){
                    return false;
                }
                String comparity = characters.pop();
                if (comparity.equals(newity)){
                    return false;
                }
            }

            s=s.substring(1,s.length());
        }
        if (characters.isEmpty()){
            return true;
        }
        return false;
    }

    static void check(String label, Object actual, Object expected) {
        if (actual.equals(expected))
            System.out.println("PASS | " + label);
        else
            System.out.println("FAIL | " + label + " | expected: " + expected + " | got: " + actual);
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        check("size after 3 pushes", stack.size(), 3);
        check("isEmpty after pushes", stack.isEmpty(), false);
        check("pop 1 (LIFO)", stack.pop(), "third");
        check("pop 2 (LIFO)", stack.pop(), "second");
        check("pop 3 (LIFO)", stack.pop(), "first");
        check("isEmpty after all pops", stack.isEmpty(), true);

        check("isValid: \"[]\"", isValid("[]"), true);
        check("isValid: \"([{}])\"", isValid("([{}])"), true);
        check("isValid: \"[(])\"", isValid("[(])"), false);
        check("isValid: empty string", isValid(""), true);
        check("isValid: \"({[]})\"", isValid("({[]})"), true);
        check("isValid: \"(((\"", isValid("((("), false);
    }
}
