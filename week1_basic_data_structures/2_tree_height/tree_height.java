import java.util.*;
import java.io.*;

/**
 * @author 
 * To solve this problem, change the height function described in the lectures 
 * with an implementation which will work for an arbitrary tree. 
 * Note that the tree can be very deep in this problem, 
 * so you should be careful to avoid stack overflow problems if you’re using recursion, 
 * and definitely test your solution on a tree with the maximum possible height.
 */
public class tree_height {
	private TreeNode root; //to keep track of the root
	
	/**
	 * @author aurobindamondal
	 * my own Tree implementation class
	 */
	class TreeNode{
		int val;
		List<TreeNode> children;
		
		/**
		 * @param val
		 * constructor
		 */
		TreeNode(int val){
			this.val= val;
		}
		
		/**
		 * @param node
		 * @returnList<TreeNode>
		 */
		List<TreeNode> getChildren(TreeNode node) {
			return node.children;
		}
		
		/**
		 * @param child
		 */
		void setChildren(TreeNode child) {
			if(this.children!=null)
				this.children.add(child);
			else {
				this.children = new ArrayList<TreeNode>();
				this.children.add(child);
			}
				
		}
	}
    /**
     * 
     *
     */
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		/**
		 * @return
		 * @throws IOException
		 */
		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		/**
		 * @return
		 * @throws IOException
		 */
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	/**
	 * @author 
	 *
	 */
	public class TreeHeight {
		int n;
		int parent[];
		TreeNode[] nodes; //to store all the nodes
		
		/**
		 * @throws IOException
		 */
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			nodes = new TreeNode[n]; //initialize
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
				if(nodes[i]==null) //if doesn't already exist
					nodes[i]=new TreeNode(i);
				
				if(parent[i] == -1) //if parent is -1 that means it is root
					root = nodes[i];
				else {
					if(nodes[parent[i]] != null) //if parent already exist just add child
						nodes[parent[i]].setChildren(nodes[i]);
					else { //if parent doesn't yet exist
						TreeNode parentNode = new TreeNode(parent[i]); //create a new parent node
						parentNode.setChildren(nodes[i]); //set child
						nodes[parent[i]] = parentNode; //add to the node
					}
				}
					
			}
			//printTree(root);//if you want to traverse the tree
			
		}

		/**
		 * @param node
		 * traverse the tree
		 */
		private void printTree(TreeNode node) {
			if(node==null)
				return;
			else {
				System.out.print(node.val+" ,");
				if(node.children!=null && !node.children.isEmpty()) {
					for(TreeNode child: node.children) {
						printTree(child);
					}
				}
				
			}
			
		}
		//to get max height
		int maxHeight = 0;
		
		/**
		 * @return max height
		 */
		int computeHeight() {
			int height = 0;
			//BFS implementation
			Queue<TreeNode> q = new LinkedList<>();
			if(root==null) {
				return 0;
			}else {
				q.offer(root);
				while(!q.isEmpty()) {
					++height; //increase height at each level
					int size = q.size();
					for(int i=0; i<size; i++) {
						TreeNode node = q.poll();
						if(node.children!=null && !node.children.isEmpty()) {
							for(TreeNode child: node.children) {
								q.offer(child);
							}
						}	
					}
					maxHeight = Math.max(height, maxHeight); //calculate max height
				}
			}
			return maxHeight;
		}

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	/**
	 * @throws IOException
	 */
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
