import java.util.*;

class TreePathSum {

  static public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  };

  public static boolean hasPath(TreeNode root, int sum) {
    if (root == null)
      return false;

    // if the current node is a leaf and its value is equal to the sum, we've found
    // a path
    if (root.val == sum && root.left == null && root.right == null)
      return true;

    // recursively call to traverse the left and right sub-tree
    // return true if any of the two recursive call return true
    return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
  }

  // write an iterative solution to find the path in a tree with the given sum
  public static List<List<Integer>> findPath(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null)
      return result;

    // use a stack to store the path
    Stack<TreeNode> stack = new Stack<>();
    // use a stack to store the sum of the path
    Stack<Integer> sumStack = new Stack<>();

    // push the root node to the stack
    stack.push(root);
    // push the sum of the root node to the sum stack
    sumStack.push(root.val);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      int currSum = sumStack.pop();

      // if the current node is a leaf and its value is equal to the sum, we've found
      // a path
      if (node.left == null && node.right == null && currSum == sum) {
        List<Integer> path = new ArrayList<>();
        while (!stack.isEmpty()) {
          path.add(stack.pop().val);
        }
        Collections.reverse(path);
        result.add(path);
      }

      // if the current node has a left child, push it to the stack
      if (node.left != null) {
        stack.push(node.left);
        sumStack.push(currSum + node.left.val);
      }

      // if the current node has a right child, push it to the stack
      if (node.right != null) {
        stack.push(node.right);
        sumStack.push(currSum + node.right.val);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println("Tree has path: " + TreePathSum.findPath(root, 23));
    System.out.println("Tree has path: " + TreePathSum.findPath(root, 16));
  }
}


