package at.htlsaalfelden;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(11);
        tree.add(21);

        tree.inOrder(System.out::print);
        System.out.println();
        tree.postOrder(System.out::print);
        System.out.println();
        tree.preOrder(System.out::print);
        System.out.println();
        tree.levelOrder(System.out::print);

        System.out.println(tree.getDepth());
    }
}