import java.util.*;
import java.io.*;

public class tree_height {
	private TreeNode root;
	class TreeNode{
		int val;
		List<TreeNode> children;
		
		TreeNode(int val){
			this.val= val;
		}
		
		List<TreeNode> getChildren(TreeNode node) {
			return node.children;
		}
		
		void setChildren(TreeNode child) {
			if(this.children!=null)
				this.children.add(child);
			else {
				this.children = new ArrayList<TreeNode>();
				this.children.add(child);
			}
				
		}
	}
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

	public class TreeHeight {
		int n;
		int parent[];
		TreeNode[] nodes;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			nodes = new TreeNode[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
				if(nodes[i]==null)
					nodes[i]=new TreeNode(i);
				
				if(parent[i] == -1)
					root = nodes[i];
				else {
					if(nodes[parent[i]] != null)
						nodes[parent[i]].setChildren(nodes[i]);
					else {
						TreeNode parentNode = new TreeNode(parent[i]);
						parentNode.setChildren(nodes[i]);
						nodes[parent[i]] = parentNode;
					}
				}
					
			}
			//printTree(root);
			
		}

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
		int maxHeight = 0;
		
		int computeHeight() {
			int height = 0;
			Queue<TreeNode> q = new LinkedList<>();
			if(root==null) {
				return 0;
			}else {
				q.offer(root);
				while(!q.isEmpty()) {
					++height;
					int size = q.size();
					for(int i=0; i<size; i++) {
						TreeNode node = q.poll();
						if(node.children!=null && !node.children.isEmpty()) {
							for(TreeNode child: node.children) {
								q.offer(child);
							}
						}	
					}
					maxHeight = Math.max(height, maxHeight);
				}
			}
			return maxHeight;
		}

	}

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
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
