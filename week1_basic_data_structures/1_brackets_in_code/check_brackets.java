import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

/**
 * @author aurobindamondal
 * To solve this problem, you can slightly modify the IsBalanced algorithm from the lectures 
 * to account not only for the brackets, but also for other characters in the code,
 *  and return not just whether the code uses brackets correctly, but also what is the 
 *  first position where the code becomes broken.
 */
class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    /**
     * @param c
     * @return
     */
    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

/**
 * @author aurobindamondal
 *
 */
class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            // Processing opening bracket, pushing to stack
            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }
            // Processing closing bracket
            if (next == ')' || next == ']' || next == '}') {
                Bracket prevElement = opening_brackets_stack.pop(); //get previous element
                char prev = prevElement.type;
                if((next == ')' && prev != '(') || (next == ']' && prev != '[') || (next == '}' && prev != '{')){ //if valid
                    System.out.println(position+1); //print position
                    break;
                }   
            }
        }

        // Printing answer
        if(opening_brackets_stack.isEmpty())
            System.out.println("Success"); //if stack is empty
        else //if the stack is not empty
            System.out.println(opening_brackets_stack.pop().position+1);
    }
}
