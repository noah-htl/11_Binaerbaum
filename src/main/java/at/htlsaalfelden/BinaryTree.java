package at.htlsaalfelden;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode<T> root) {
        this.root = root;
    }

    public BinaryTree(T root) {
        this(new TreeNode<>(root));
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void setRoot(T root) {
        this.root = new TreeNode<>(root);
    }

    public void add(T data) {
        this.add(new TreeNode<>(data));
    }

    public void add(TreeNode<T> node) {
        if(this.getRoot() == null) {
            this.setRoot(node);
        } else {
            add(node, this.getRoot());
        }
    }

    public void add(TreeNode<T> node, TreeNode<T> root) {
        if(node.getData().compareTo(root.getData()) < 0) {
            if(root.getLeft() == null) {
                root.setLeft(node);
            } else {
                add(node, root.getLeft());
            }
        } else {
            if(root.getRight() == null) {
                root.setRight(node);
            } else {
                add(node, root.getRight());
            }
        }
    }

    public void inOrder(Consumer<T> consumer) {
        inOrder(consumer, this.getRoot());
    }
    public void preOrder(Consumer<T> consumer) {
        preOrder(consumer, this.getRoot());
    }
    public void postOrder(Consumer<T> consumer) {
        postOrder(consumer, this.getRoot());
    }
    public void levelOrder(Consumer<LevelData<T>> consumer) {
        levelOrder(consumer, List.of(this.getRoot()));
    }

    private void inOrder(Consumer<T> consumer, TreeNode<T> root) {
        if(root == null) {
            return;
        }
        inOrder(consumer, root.getLeft());
        consumer.accept(root.getData());
        inOrder(consumer, root.getRight());
    }

    private void preOrder(Consumer<T> consumer, TreeNode<T> root) {
        if(root == null) {
            return;
        }
        consumer.accept(root.getData());
        preOrder(consumer, root.getLeft());
        preOrder(consumer, root.getRight());
    }

    private void postOrder(Consumer<T> consumer, TreeNode<T> root) {
        if(root == null) {
            return;
        }
        postOrder(consumer, root.getLeft());
        postOrder(consumer, root.getRight());
        consumer.accept(root.getData());
    }

    private void levelOrder(Consumer<LevelData<T>> consumer, List<TreeNode<T>> roots) {
        List<TreeNode<T>> nodes = new ArrayList<>();

        for (int i = 0; i < roots.size(); i++) {
            TreeNode<T> node = roots.get(i);

            if(i == roots.size() - 1) {
                consumer.accept(new LevelData<>(node.getData(), true));
            } else {
                consumer.accept(new LevelData<>(node.getData(), false));
            }

            if(node.getLeft() != null) {
                nodes.add(node.getLeft());
            }
            if(node.getRight() != null) {
                nodes.add(node.getRight());
            }
        }

        if(!nodes.isEmpty()) {
            levelOrder(consumer, nodes);
        }
    }


    public static class TreeNode<T extends Comparable<T>> {
        private final T data;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
            this.right = null;
            this.left = null;
        }

        public T getData() {
            return this.data;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public void setLeft(T left) {
            this.left = new TreeNode<>(left);
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

        public void setRight(T right) {
            this.right = new TreeNode<>(right);
        }
    }

    public record LevelData<T>(T data, boolean isLastInLevel) {
        @Override
        public String toString() {
            if(isLastInLevel) {
                return data + "\n";
            }
            return data + "";
        }
    }
}
