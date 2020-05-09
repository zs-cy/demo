package data.tree;

/**
 * 二叉查找树
 */
public class BinarySearchTree {
    private Node tree;
    private int count;

    public int getCount() {
        return count;
    }

    public Node find(int data) {
        // 查找次数
        this.count = 1;
        Node p = tree;
        while (p != null) {
            if (data < p.getValue()) {
                p = p.getLeft();
            } else if (data > p.getValue()) {
                p = p.getRight();
            } else {
                return p;
            }
            count++;
        }
        return null;
    }

    public void inOrder(Node root){
        if(root==null){
            return ;
        }
        if(root.left!=null){
            inOrder(root.left);
        }
        System.out.println(root.value);
        if(root.right!=null){
            inOrder(root.right);
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Node minValueNode = new Node(1);
        Node secondMinValueNode = new Node(2);
        Node thirdMinValueNode = new Node(3);
        Node rootNode = new Node(4);
        Node fifthNode = new Node(5);

        secondMinValueNode.setLeft(minValueNode);
        secondMinValueNode.setRight(thirdMinValueNode);
        rootNode.setLeft(secondMinValueNode);
        rootNode.setRight(fifthNode);

        binarySearchTree.tree = rootNode;
        Node node = binarySearchTree.find(6);
        System.out.println("查找次数："+"\t"+binarySearchTree.getCount());
        System.out.println(node!=null?node.toString():"未找到");

        binarySearchTree.inOrder(binarySearchTree.tree);
    }
}


