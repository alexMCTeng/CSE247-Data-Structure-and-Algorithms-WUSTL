package AVLTree.util;

import AVLTree.trees.BST;
import AVLTree.nodes.TreeNode;
import AVLTree.nodes.UnbalancedTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeToStrings {

    final private BST<?> tree;

    public static String toTree(BST<?> tree) {
        return new TreeToStrings(tree).formatVertically(tree.Root(),"",false);
    }

    public static String toOutline(BST<?> tree) {
        return new TreeToStrings(tree).formatVertically();
    }

    private TreeToStrings(BST<?> tree) {
        this.tree = tree;
    }

    private String spacing(int level) {
        String ans = "";
        for (int i = 0; i < level; ++i) {
            ans = ans + "  ";
        }
        return ans;
    }

    /**
     * Formats the tree with indentation to show level.
     * This is NOT the way you see trees in a text, but it is
     * common to show nested and recursive structures this way.
     *
     * @return a String representing this node and below
     */
    private String formatVertically() {
        if (tree.Root() == null)
            return "";
        else {
            Queue<TreeNode<?>> q = new LinkedList<>();
            q.add(tree.Root());
            q.add(null);
            int level = 0;
            String ans = "";
            while (!q.isEmpty()) {
                TreeNode<?> element = q.poll();
                if (element == null) {
                    q.add(null);
                    if (q.peek()==null){
                        return ans;
                    }
                    level++;
                    continue;
                }
                ans = ans + spacing(level);
                ans = ans + element.getValue().toString() + "\n";
            }

            return ans;
        }
    }

    private int treeHeight()
    {
        if (tree.Root()==null) {
            return 0;
        }
        return treeHeight(tree.Root());
    }

    private int treeHeight(TreeNode<?> root){
        if (root==null)
            return 0;
        return Math.max(treeHeight(root.Left())+1,treeHeight(root.Right())+1);
    }

    private static String center(String s, int width) {
        if (s.length() > width) {
            throw new Error("String  " + s + " already longer than " + width);
        }
        String ans = s;
        boolean left = true;
        while (ans.length() < width) {
            if (left)
                ans = " " + ans;
            else
                ans = ans + " ";
            left = !left;
        }
        return ans;
    }

    private String formatEntry(String s, int maxWidth, int centeredOver) {
        String entry = center(s, maxWidth);
        int answidth = maxWidth * centeredOver + (centeredOver - 1);
        return center(entry, answidth);
    }

    /**
     * Returns a representation of the tree in the form one usually sees.
     * It looks like a tree with the root centered on the first line.
     *
     * @return the tree as a tree
     */
    public String formatVertically(TreeNode<?> root, String prefix, boolean left) {
        String pre = "";
        if (root == tree.Root()) {
            pre = "Root-";
        } else {
            pre = (left ? "L-" : "R-");
        }
        String ans = "";
        if (root == null) {
            //System.out.println(prefix + pre + " <null>");
            return ans;
        }
        ans+=prefix + pre+root.toString()+"\n";
        ans+=formatVertically(root.Left(), prefix + "|  ", true);
        ans+=formatVertically(root.Right(), prefix + "|  ", false);
        return ans;
    }

    //prints it out as a tree by doing a breadth first search
    public String formatAsTree() {
        Queue<TreeNode<?>> q = new LinkedList<>();
        TreeNode special = new UnbalancedTreeNode<>(1);
        String ans = "";
        q.add(tree.Root());
        q.add(special);
        String line = "";
        int level = treeHeight();
        String space = space(level);
        int count = 0;
        while(!q.isEmpty()){
            TreeNode<?> t = q.poll();
            if(t==special){
                ans+=line+"\n";
                line="";
                level-=1;
                space = space(level);
                q.add(special);
                if (q.peek()==special)
                    break;
                continue;
            }
            if (t!=null){
                line += space + t.getValue().toString() + space;
                q.add(t.Left());
                q.add(t.Right());
            }
            else {
                line += space + "NIL" + space;
            }
        }
        return ans;
    }

    public String space(int level){
        String ans = "";
        for (int i = 0; i < 2<<level; i++){
            ans+=" ";
        }
        return ans;
    }

}
