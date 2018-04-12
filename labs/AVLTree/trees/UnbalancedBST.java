package AVLTree.trees;
// An unbalanced Binary Search Tree. This is used in the Pre-Lab write up to get student's comforable with tree code.xs

import AVLTree.nodes.UnbalancedTreeNode;
import timing.Ticker;

public class UnbalancedBST<T extends Comparable<T>> extends BST<T> {
    private UnbalancedTreeNode<T> root;
    public Ticker ticker;

    public UnbalancedBST(Ticker t) {
        super();
        this.root = null;
        this.ticker = t;

    }

    public UnbalancedTreeNode<T> Root() {
        return root;
    }

    /**
     * Attempts to locate a value in the Binary Search Tree.
     * Returns the node the node if it exists, null otherwise
     * Utilizes a helper function that is recursively called.
     *
     * @param value the value to be found
     * @return the node element if it exists/otherwise null
     * <p>
     * Associated WriteUp Question: What is the purpose of the helper function? (hint: look at the method headers and parameters)
     * Associated WriteUp Question: What is the recurrence and the overall time complexity of find? Solve this for both (average case?) best case and worst case
     */
    public UnbalancedTreeNode<T> Find(T value) {
        return findHelper(value, this.root);
    }

    // helper function for find, see above for description
    private UnbalancedTreeNode<T> findHelper(T value, UnbalancedTreeNode<T> curr) {
        if (curr == null) {
            return null;
        }
        int comparison = curr.getValue().compareTo(value);
        if (comparison == 0) {
            return curr;
        } else if (comparison < 0) {
            return findHelper(value, curr.Left());
        } else {
            return findHelper(value, curr.Right());
        }
    }

    /**
     * Attempts to insert a value into the Binary Search Tree.
     * Returns the node that was inserted;;
     *
     * @param value the value to be inserted
     * @return the node element that was inserted
     * <p>
     * Associated WriteUp Question: How does the UBST resolve duplicates? what is another way the duplicates could be dealt with?
     * Associated WriteUp Question: What is the time complexity of this insert?
     * Associated WriteUp Question: What code would replace the indicated portion to make this a working recursive function?
     */
    public UnbalancedTreeNode<T> Insert(T value) {
        UnbalancedTreeNode<T> toInsert = new UnbalancedTreeNode<T>(value);
        if (this.isEmpty()) {
            this.root = toInsert;
            size++;
            return toInsert;
        }

        // indicated portion = everything below this line in the function
        UnbalancedTreeNode<T> curr = this.root;
        while (curr != null) {
            if (toInsert.getValue().compareTo(curr.getValue()) < 0) {
                if (curr.Left() == null) {
                    curr.setLeft(toInsert);
                    toInsert.setParent(curr);
                    size++;
                    return toInsert;
                } else {
                    curr = curr.Left();
                }
            } else {
                if (curr.Right() == null) {
                    curr.setRight(toInsert);
                    toInsert.setParent(curr);
                    size++;
                    return toInsert;
                } else {
                    curr = curr.Right();
                }
            }
        }
        return null; // Will this ever happen? write  sentence that describes when it will or why it never will.
    }

    public boolean Delete(T value) {
        UnbalancedTreeNode<T> toDelete = Find(value);
        if (toDelete == null) {
            return false;
        }
        UnbalancedTreeNode<T> parent = toDelete.Parent();
        UnbalancedTreeNode<T> right = toDelete.Right();
        UnbalancedTreeNode<T> left = toDelete.Left();
        if (toDelete.isLeaf()) {
            if (parent.Left() == toDelete) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (right == null) {
            if (parent.Left() == toDelete) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            return true;
        } else if (left == null) {
            if (parent.Left() == toDelete) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            return true;
        } else {
            UnbalancedTreeNode<T> successor = minimumOfSubtree(right);
            Delete(successor.getValue());
            if (parent.Left() == toDelete) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(left);
            successor.setRight(right);
            return true;
        }
        return false; //Will this ever happen?
    }


    public boolean isEmpty() {
        return this.root == null;
    }

    public UnbalancedTreeNode<T> minimum() {
        return minimumOfSubtree(this.root);
    }

    public UnbalancedTreeNode<T> maximum() {
        return maximumOfSubtree(this.root);
    }

    public UnbalancedTreeNode<T> minimumOfSubtree(UnbalancedTreeNode<T> curr) {
        if (curr == null) {
            return null;
        }
        while (curr.Left() != null) {
            curr = curr.Left();
        }
        return curr;
    }

    public UnbalancedTreeNode<T> maximumOfSubtree(UnbalancedTreeNode<T> curr) {
        if (curr == null) {
            return null;
        }
        while (curr.Right() != null) {
            curr = curr.Right();
        }
        return curr;
    }

    public void InorderTraversal(UnbalancedTreeNode<T> curr) {
        if (curr == null) {
            return;
        }
        InorderTraversal(curr.Left());
        System.out.println(curr.getValue());
        InorderTraversal(curr.Right());
    }

    public void PrintTree() {
        InorderTraversal(this.root);
    }

}