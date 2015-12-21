import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Skvorcov on 17.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String row = bufferedReader.readLine();
            bufferedReader.close();
            //входная строка должна быть не длиннее 10^6
            if (row.length() <= 1000000) {
                List<Character> list = new ArrayList<>();

                //string to list
                for (char c : row.toCharArray()) {
                    list.add(c);
                }
                //результирующий дек
                Deque<Character> deque = new ArrayDeque<>();
                //стэк открывающих скобок
                Stack<Character> stack = new Stack<>();

                boolean impossible = false;

                for (char c : list) {
                    if (!isOpen(c)) {
                        if (!stack.isEmpty()) {
                            char currentChar = stack.pop();
                            if (currentChar == getReverse(c)) {
                                deque.addLast(c);
                            } else {
                                impossible = true;
                                break;
                            }
                        } else {
                            deque.addFirst(getReverse(c));
                            deque.addLast(c);
                        }
                    } else {
                        stack.push(c);
                        deque.addLast(c);
                    }
                }

                while (!stack.isEmpty()) {
                    deque.addLast(getReverse(stack.pop()));
                }

                if (impossible) {
                    System.out.println("IMPOSSIBLE");
                } else {
                    for (char c : deque) {
                        System.out.print(c);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param c
     * @return true - открывающая скобка
     */
    private static boolean isOpen(Character c) {
        return (c == '(' || c == '{' || c == '[');
    }

    /**
     *
     * @param c
     * @return возвращает пару скобке такого же типа
     */
    private static char getReverse(Character c) {
        char cReverse = ' ';
        switch (c) {
            case '{':
                cReverse = '}';
                break;
            case '}':
                cReverse = '{';
                break;
            case '[':
                cReverse = ']';
                break;
            case  ']':
                cReverse = '[';
                break;
            case  '(':
                cReverse = ')';
                break;
            case ')':
                cReverse = '(';
                break;
        }
        return cReverse;
    }

}
