import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        int max=0; //to keep track of max in O(1)
        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                if(value >= max) { //if the value is more than max 
                	stack.push(max); //push current max
                	max=value; //replace max with value
                }
                stack.push(value); //in any case push the new value
            } else if ("pop".equals(operation)) {
                int popped = stack.pop(); //store the poppoed number
                if(popped==max) //check if it is equals to max
                	max = stack.pop(); //pop the stack to get the previous max number
            } else if ("max".equals(operation)) {
                //System.out.println(Collections.max(stack)); //original code not in O(1)
            	System.out.println(max);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
