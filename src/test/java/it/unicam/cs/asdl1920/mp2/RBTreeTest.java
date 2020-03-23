/**
 * 
 */
package it.unicam.cs.asdl1920.mp2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Classe di test JUnit 5 per i metodi di RBTree<E>
 * 
 * @author Luca Tesei, Dante Domizi, Nicola Del Giudice
 *
 */
class RBTreeTest {

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#BRTree()}.
     */
    @Test
    final void testBRTree() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.isEmpty());
        assertTrue(t.getSize() == 0);
        assertTrue(t.getNumberOfNodes() == 0);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#BRTree(java.lang.Comparable)}.
     */
    @Test
    final void testBRTreeE() {
        assertThrows(NullPointerException.class,
                () -> new RBTree<Integer>(null));
        RBTree<Integer> t = new RBTree<Integer>(41);
        assertFalse(t.isEmpty());
        assertTrue(t.getSize() == 1);
        assertTrue(t.getNumberOfNodes() == 1);
        RBTree<Integer>.RBTreeNode n = t.getRoot();
        assertFalse(RBTree.isNil(n));
        assertTrue(n.getEl().intValue() == 41);
        assertTrue(n.getColor() == RBTree.BLACK);
        assertTrue(RBTree.isNil(n.getLeft()));
        assertTrue(RBTree.isNil(n.getRight()));
        assertTrue(n.getCount() == 1);
        assertTrue(RBTree.isNil(n.getParent()));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#contains(java.lang.Comparable)}.
     */
    @Test
    final void testContains() {
        RBTree<Integer> t = new RBTree<Integer>(4);
        assertThrows(NullPointerException.class, () -> t.contains(null));
        t.insert(6);
        t.insert(3);
        t.insert(9);
        t.insert(1);
        assertFalse(t.isEmpty());
        assertTrue(t.contains(9));
        assertTrue(t.contains(3));
        assertTrue(t.contains(6));
        assertTrue(t.contains(1));
        assertFalse(t.contains(2));
        t.remove(9);
        assertFalse(t.contains(9));
        t.remove(3);
        t.remove(1);
        t.remove(6);
        t.remove(4);
        assertTrue(t.isEmpty());
        assertFalse(t.contains(9));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getCount(java.lang.Comparable)}.
     */
    @Test
    final void testGetCount() {
        RBTree<Integer> t = new RBTree<Integer>(4);
        assertThrows(NullPointerException.class, () -> t.getCount(null));
        t.insert(5);
        t.insert(5);
        t.insert(6);
        assertFalse(t.isEmpty());
        assertTrue(t.getCount(5) == 2);
        assertTrue(t.getCount(9) == 0);
        assertTrue(t.getCount(4) == 1);
        assertTrue(t.getCount(6) == 1);
        t.remove(5);
        assertTrue(t.getCount(5) == 1);
        assertFalse(t.getCount(5) == 2);
        t.insert(5);
        t.insert(5);
        assertTrue(t.getCount(5) == 3);
        t.remove(5);
        t.remove(5);
        t.remove(5);
        assertTrue(t.getCount(5) == 0);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getBlackHeight()}.
     */
    @Test
    final void testGetBlackHeight() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.getBlackHeight() == -1);
        t.insert(41);
        assertTrue(t.getBlackHeight() == 1);
        t.insert(38);
        assertTrue(t.getBlackHeight() == 1);
        t.insert(31);
        assertTrue(t.getBlackHeight() == 1);
        t.insert(12);
        assertTrue(t.getBlackHeight() == 2);
        t.insert(9);
        assertTrue(t.getBlackHeight() == 2);
        t.insert(8);
        assertTrue(t.getBlackHeight() == 2);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getMinimum()}.
     */
    @Test
    final void testGetMinimum() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.getMinimum() == null);
        t.insert(4);
        assertTrue(t.getMinimum().intValue() == 4);
        t.insert(10);
        t.insert(20);
        t.insert(50);
        t.insert(5);
        t.insert(3);
        assertFalse(t.getMinimum().intValue() == 4);
        assertTrue(t.getMinimum().intValue() == 3);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getMaximum()}.
     */
    @Test
    final void testGetMaximum() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.getMaximum() == null);
        t.insert(4);
        RBTree<Integer>.RBTreeNode n4 = t.getRoot();
        assertTrue(t.getMaximum().equals(n4.el));
        t.insert(5);
        assertFalse(t.getMaximum().equals(n4.el));
        assertTrue(t.getMaximum().intValue() == 5);
        t.insert(3);
        t.insert(-40);
        t.insert(-100);
        assertFalse(t.getMaximum().intValue() == 4);
        assertTrue(t.getMaximum().intValue() == 5);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getNodeOf(java.lang.Comparable)}.
     */
    @Test
    final void testGetNodeOf() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertThrows(NullPointerException.class, () -> t.getNodeOf(null));
        assertTrue(t.getNodeOf(10) == null);
        t.insert(10);
        RBTree<Integer>.RBTreeNode root = t.getRoot();
        assertTrue(t.getNodeOf(10) == root);
        t.insert(20);
        t.insert(5);
        RBTree<Integer>.RBTreeNode n20 = root.getRight();
        RBTree<Integer>.RBTreeNode n5 = root.getLeft();
        assertTrue(t.getNodeOf(20) == n20);
        assertTrue(t.getNodeOf(5) == n5);
        assertTrue(t.getNodeOf(15) == null);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getNumberOfNodes()}.
     */
    @Test
    final void testGetNumberOfNodes() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.getNumberOfNodes() == 0);
        t.insert(4);
        assertTrue(t.getNumberOfNodes() == 1);
        t.insert(4);
        t.insert(4);
        t.insert(5);
        assertFalse(t.getNumberOfNodes() == 4);
        assertTrue(t.getNumberOfNodes() == 2);
        t.remove(4);
        assertFalse(t.getNumberOfNodes() == 3);
        assertTrue(t.getNumberOfNodes() == 2);
        t.remove(5);
        assertFalse(t.getNumberOfNodes() == 2);
        assertTrue(t.getNumberOfNodes() == 1);
        t.remove(4);
        assertFalse(t.getNumberOfNodes() == 0);
        assertTrue(t.getNumberOfNodes() == 1);
        t.remove(4);
        assertTrue(t.getNumberOfNodes() == 0);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getPredecessor(java.lang.Comparable)}.
     */
    @Test
    final void testGetPredecessor() {
        RBTree<Integer> t = new RBTree<Integer>(4);
        t.insert(3);
        t.insert(8);
        t.insert(1);
        t.insert(9);
        t.insert(2);
        assertThrows(NullPointerException.class, () -> t.getPredecessor(null));
        assertThrows(IllegalArgumentException.class, () -> t.getPredecessor(6));
        assertTrue(t.getPredecessor(3).intValue() == 2);
        assertTrue(t.getPredecessor(8).intValue() == 4);
        assertTrue(t.getPredecessor(4).intValue() == 3);
        assertTrue(t.getPredecessor(9).intValue() == 8);
        assertTrue(t.getPredecessor(2).intValue() == 1);
        assertTrue(t.getPredecessor(1) == null);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getRoot()}.
     */
    @Test
    final void testGetRoot() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(RBTree.isNil(t.getRoot()));
        t.insert(10);
        RBTree<Integer>.RBTreeNode root = t.getRoot();
        RBTree<Integer>.RBTreeNode n10 = t.getNodeOf(10);
        assertTrue(root == n10);
        t.insert(20);
        assertTrue(t.getRoot() == n10);
        t.insert(5);
        assertTrue(t.getRoot() == n10);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getSize()}.
     */
    @Test
    final void testGetSize() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.getSize() == 0);
        t.insert(4);
        assertTrue(t.getSize() == 1);
        t.insert(4);
        t.insert(4);
        t.insert(5);
        assertFalse(t.getSize() == 2);
        assertTrue(t.getSize() == 4);
        t.remove(4);
        assertFalse(t.getSize() == 2);
        assertTrue(t.getSize() == 3);
        t.remove(5);
        assertFalse(t.getSize() == 1);
        assertTrue(t.getSize() == 2);
        t.remove(4);
        assertFalse(t.getSize() == 0);
        assertTrue(t.getSize() == 1);
        t.remove(4);
        assertTrue(t.getSize() == 0);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#getSuccessor(java.lang.Comparable)}.
     */
    @Test
    final void testGetSuccessor() {
        RBTree<Integer> t = new RBTree<Integer>(10);
        t.insert(13);
        t.insert(81);
        t.insert(1);
        t.insert(99);
        t.insert(21);
        t.insert(38);
        assertThrows(NullPointerException.class, () -> t.getSuccessor(null));
        assertThrows(IllegalArgumentException.class, () -> t.getSuccessor(6));
        assertTrue(t.getSuccessor(1).intValue() == 10);
        assertTrue(t.getSuccessor(10).intValue() == 13);
        assertTrue(t.getSuccessor(13).intValue() == 21);
        assertTrue(t.getSuccessor(21).intValue() == 38);
        assertTrue(t.getSuccessor(38).intValue() == 81);
        assertTrue(t.getSuccessor(81).intValue() == 99);
        assertTrue(t.getSuccessor(99) == null);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#inOrderVisit()}.
     */
    @Test
    final void testInOrderVisit() {
        RBTree<Integer> t = new RBTree<Integer>();
        List<Integer> l = new ArrayList<Integer>();
        assertEquals(l, t.inOrderVisit());
        t.insert(34);
        t.insert(44);
        t.insert(0);
        t.insert(-50);
        t.insert(1000);
        t.insert(13);
        t.insert(22);
        t.insert(34);
        t.insert(1);
        t.insert(22);
        t.insert(22);
        t.insert(30);
        l.add(-50);
        l.add(0);
        l.add(1);
        l.add(13);
        l.add(22);
        l.add(22);
        l.add(22);
        l.add(30);
        l.add(34);
        l.add(34);
        l.add(44);
        l.add(1000);
        assertEquals(l, t.inOrderVisit());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#isEmpty()}.
     */
    @Test
    final void testIsEmpty() {
        RBTree<Integer> t = new RBTree<Integer>();
        assertTrue(t.isEmpty());
        t.insert(3);
        t.insert(0);
        assertFalse(t.isEmpty());
        t.remove(3);
        assertFalse(t.isEmpty());
        t.remove(0);
        assertTrue(t.isEmpty());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#insert(java.lang.Comparable)}.
     */
    @Test
    final void testInsert1() {
        // Sequenza 41, 38, 31, 12, 9, 8
        RBTree<Integer> t = new RBTree<Integer>(41);

        // Test insert foglia rossa
        t.insert(38);
        RBTree<Integer>.RBTreeNode root = t.getRoot();
        assertTrue(RBTree.isBlack(root));
        assertTrue(root.getEl().intValue() == 41);
        RBTree<Integer>.RBTreeNode n41 = root;
        assertTrue(RBTree.isNil(root.getRight()));
        RBTree<Integer>.RBTreeNode n38 = root.getLeft();
        assertFalse(RBTree.isNil(n38));
        assertTrue(n38.getEl().intValue() == 38);
        assertTrue(n38.getCount() == 1);
        assertTrue(RBTree.isRed(n38));
        assertTrue(n38.getParent() == root);
        assertTrue(RBTree.isNil(n38.getLeft()));
        assertTrue(RBTree.isNil(n38.getRight()));

        // Test multiplicity
        t.insert(38);
        assertTrue(n38.getCount() == 2);
        t.insert(41);
        assertTrue(root.getCount() == 2);

        // Test insert caso left/3 right-rotate, cambio root
        t.insert(31);
        root = t.getRoot();
        assertTrue(root == n38);
        assertTrue(RBTree.isBlack(root));
        assertTrue(root.getEl().intValue() == 38);
        assertTrue(root.getRight() == n41);
        RBTree<Integer>.RBTreeNode n31 = root.getLeft();
        assertFalse(RBTree.isNil(n31));
        assertTrue(n31.getParent() == n38);
        assertTrue(n41.getParent() == n38);
        assertTrue(n31.getEl().intValue() == 31);
        assertTrue(n31.getCount() == 1);
        assertTrue(RBTree.isRed(n31));
        assertTrue(RBTree.isNil(n31.getLeft()));
        assertTrue(RBTree.isNil(n31.getRight()));
        assertTrue(n41.getEl().intValue() == 41);
        assertTrue(n41.getCount() == 2);
        assertTrue(RBTree.isRed(n41));
        assertTrue(RBTree.isNil(n41.getLeft()));
        assertTrue(RBTree.isNil(n41.getRight()));
        assertTrue(t.getBlackHeight() == 1);

        // Caso left/1 no rotazioni
        t.insert(12);
        assertTrue(t.getRoot() == n38);
        assertTrue(n38.getLeft() == n31);
        assertTrue(n38.getRight() == n41);
        assertTrue(RBTree.isBlack(n31));
        assertTrue(RBTree.isBlack(n41));
        RBTree<Integer>.RBTreeNode n12 = n31.getLeft();
        assertFalse(RBTree.isNil(n12));
        assertTrue(n12.getEl().intValue() == 12);
        assertTrue(RBTree.isRed(n12));
        assertTrue(RBTree.isNil(n12.getLeft()));
        assertTrue(RBTree.isNil(n12.getRight()));
        assertTrue(n12.getParent() == n31);
        assertTrue(t.getBlackHeight() == 2);

        // Caso left/3 right-rotate
        t.insert(9);
        assertTrue(t.getRoot() == n38);
        assertTrue(n38.getLeft() == n12);
        assertTrue(RBTree.isBlack(n12));
        assertTrue(t.getBlackHeight() == 2);
        RBTree<Integer>.RBTreeNode n9 = n12.getLeft();
        assertFalse(RBTree.isNil(n9));
        assertTrue(n9.getEl().intValue() == 9);
        assertTrue(RBTree.isRed(n9));
        assertTrue(RBTree.isNil(n9.getLeft()));
        assertTrue(RBTree.isNil(n9.getRight()));
        assertTrue(n9.getParent() == n12);
        assertTrue(n12.getRight() == n31);
        assertTrue(RBTree.isRed(n31));
        assertTrue(RBTree.isNil(n31.getLeft()));
        assertTrue(RBTree.isNil(n31.getRight()));
        assertTrue(n31.getParent() == n12);

        // Caso left/1, no rotazioni
        t.insert(8);
        RBTree<Integer>.RBTreeNode n8 = t.getNodeOf(8);
        assertTrue(n8 != null);
        assertTrue(RBTree.isRed(n8));
        assertTrue(RBTree.isNil(n8.getLeft()));
        assertTrue(RBTree.isNil(n8.getRight()));
        assertTrue(n8.getParent() == n9);

        assertTrue(RBTree.isBlack(n9));
        assertTrue(RBTree.isNil(n9.getRight()));
        assertTrue(n9.getLeft() == n8);
        assertTrue(n9.getParent() == n12);

        assertTrue(RBTree.isBlack(n31));
        assertTrue(RBTree.isNil(n31.getLeft()));
        assertTrue(RBTree.isNil(n31.getRight()));
        assertTrue(n31.getParent() == n12);

        assertTrue(RBTree.isRed(n12));
        assertTrue(n12.getLeft() == n9);
        assertTrue(n12.getRight() == n31);
        assertTrue(n12.getParent() == n38);

    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.BRTree#insert(java.lang.Comparable)}.
     */
    @Test
    final void testInsert2() {
        // Sequenza 50, 75, 25, 65, 70, 72, 80, 85, 77
        RBTree<Integer> t = new RBTree<Integer>(50);
        t.insert(75);
        t.insert(25);

        RBTree<Integer>.RBTreeNode root = t.getRoot();
        assertTrue(RBTree.isBlack(root));
        assertFalse(RBTree.isNil(root));
        assertTrue(RBTree.isNil(root.parent));
        assertTrue(root.getEl().intValue() == 50);
        RBTree<Integer>.RBTreeNode n50 = root;

        RBTree<Integer>.RBTreeNode n75 = root.getRight();
        assertFalse(RBTree.isNil(n75));
        assertTrue(RBTree.isNil(n75.getLeft()));
        assertTrue(RBTree.isNil(n75.getRight()));
        assertTrue(n75.getEl().intValue() == 75);
        assertTrue(n75.parent == n50);
        assertTrue(RBTree.isRed(n75));

        RBTree<Integer>.RBTreeNode n25 = root.getLeft();
        assertFalse(RBTree.isNil(n25));
        assertTrue(RBTree.isNil(n25.getLeft()));
        assertTrue(RBTree.isNil(n25.getRight()));
        assertTrue(n25.getEl().intValue() == 25);
        assertTrue(n25.parent == n50);
        assertTrue(RBTree.isRed(n25));

        // caso right/1
        t.insert(65);
        RBTree<Integer>.RBTreeNode n65 = t.getNodeOf(65);
        assertTrue(n65 != null);
        assertTrue(n65.getEl().intValue() == 65);

        assertTrue(RBTree.isNil(n65.getLeft()));
        assertTrue(RBTree.isNil(n65.getRight()));
        assertTrue(n65.parent == n75);
        assertTrue(RBTree.isRed(n65));

        assertTrue(n75.getLeft() == n65);
        assertTrue(RBTree.isNil(n75.getRight()));
        assertTrue(n75.parent == n50);
        assertTrue(RBTree.isBlack(n75));

        // caso left/2, leftRotate + rightRotate
        t.insert(70);
        RBTree<Integer>.RBTreeNode n70 = t.getNodeOf(70);
        assertTrue(n70 != null);
        assertTrue(n70.getEl().intValue() == 70);

        assertTrue(RBTree.isNil(n75.getLeft()));
        assertTrue(RBTree.isNil(n75.getRight()));
        assertTrue(n75.parent == n70);
        assertTrue(RBTree.isRed(n75));

        assertTrue(RBTree.isNil(n65.getLeft()));
        assertTrue(RBTree.isNil(n65.getRight()));
        assertTrue(n65.parent == n70);
        assertTrue(RBTree.isRed(n65));

        assertTrue(n70.getLeft() == n65);
        assertTrue(n70.getRight() == n75);
        assertTrue(n70.parent == n50);
        assertTrue(RBTree.isBlack(n70));

        // caso right/1, no rotazioni
        t.insert(72);
        RBTree<Integer>.RBTreeNode n72 = t.getNodeOf(72);
        assertTrue(n72 != null);
        assertTrue(n72.getEl().intValue() == 72);

        assertTrue(n75.getLeft() == n72);
        assertTrue(RBTree.isNil(n75.getRight()));
        assertTrue(n75.parent == n70);
        assertTrue(RBTree.isBlack(n75));

        assertTrue(RBTree.isNil(n65.getLeft()));
        assertTrue(RBTree.isNil(n65.getRight()));
        assertTrue(n65.parent == n70);
        assertTrue(RBTree.isBlack(n65));

        assertTrue(n70.getLeft() == n65);
        assertTrue(n70.getRight() == n75);
        assertTrue(n70.parent == n50);
        assertTrue(RBTree.isRed(n70));

        assertTrue(RBTree.isNil(n72.getLeft()));
        assertTrue(RBTree.isNil(n72.getRight()));
        assertTrue(n72.parent == n75);
        assertTrue(RBTree.isRed(n72));

        // caso inserimento foglia con padre nero
        t.insert(80);
        RBTree<Integer>.RBTreeNode n80 = t.getNodeOf(80);
        assertTrue(n80 != null);
        assertTrue(n80.getEl().intValue() == 80);

        assertTrue(n75.getLeft() == n72);
        assertTrue(n75.getRight() == n80);
        assertTrue(n75.parent == n70);
        assertTrue(RBTree.isBlack(n75));

        assertTrue(RBTree.isNil(n72.getLeft()));
        assertTrue(RBTree.isNil(n72.getRight()));
        assertTrue(n72.parent == n75);
        assertTrue(RBTree.isRed(n72));

        assertTrue(RBTree.isNil(n80.getLeft()));
        assertTrue(RBTree.isNil(n80.getRight()));
        assertTrue(n80.parent == n75);
        assertTrue(RBTree.isRed(n80));

        // caso right/1 + rightRotate + right/3
        t.insert(85);
        RBTree<Integer>.RBTreeNode n85 = t.getNodeOf(85);
        assertTrue(n85 != null);
        assertTrue(n85.getEl().intValue() == 85);

        assertTrue(t.getRoot() == n70);
        assertTrue(n70.getLeft() == n50);
        assertTrue(n70.getRight() == n75);
        assertTrue(RBTree.isNil(n70.parent));
        assertTrue(RBTree.isBlack(n70));

        assertTrue(n50.getLeft() == n25);
        assertTrue(n50.getRight() == n65);
        assertTrue(n50.parent == n70);
        assertTrue(RBTree.isRed(n50));

        assertTrue(RBTree.isNil(n25.getLeft()));
        assertTrue(RBTree.isNil(n25.getRight()));
        assertTrue(n25.parent == n50);
        assertTrue(RBTree.isBlack(n25));

        assertTrue(RBTree.isNil(n65.getLeft()));
        assertTrue(RBTree.isNil(n65.getRight()));
        assertTrue(n65.parent == n50);
        assertTrue(RBTree.isBlack(n65));

        assertTrue(n75.getLeft() == n72);
        assertTrue(n75.getRight() == n80);
        assertTrue(n75.parent == n70);
        assertTrue(RBTree.isRed(n75));

        assertTrue(RBTree.isNil(n72.getLeft()));
        assertTrue(RBTree.isNil(n72.getRight()));
        assertTrue(n72.parent == n75);
        assertTrue(RBTree.isBlack(n72));

        assertTrue(RBTree.isNil(n80.getLeft()));
        assertTrue(n80.getRight() == n85);
        assertTrue(n80.parent == n75);
        assertTrue(RBTree.isBlack(n80));

        assertTrue(RBTree.isNil(n85.getLeft()));
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n80);
        assertTrue(RBTree.isRed(n85));

        // caso foglia rossa di nodo nero
        t.insert(77);
        RBTree<Integer>.RBTreeNode n77 = t.getNodeOf(77);
        assertTrue(n77 != null);
        assertTrue(n77.getEl().intValue() == 77);

        assertTrue(RBTree.isNil(n77.getLeft()));
        assertTrue(RBTree.isNil(n77.getRight()));
        assertTrue(n77.parent == n80);
        assertTrue(RBTree.isRed(n77));

        assertTrue(RBTree.isNil(n85.getLeft()));
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n80);
        assertTrue(RBTree.isRed(n85));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.BRTree#insert(java.lang.Comparable)}.
     */
    @Test
    final void testInsert3() {
        // Sequenza 100, 50, 150, 200, 175
        RBTree<Integer> t = new RBTree<Integer>(100);
        t.insert(50);
        t.insert(150);

        RBTree<Integer>.RBTreeNode root = t.getRoot();
        assertTrue(RBTree.isBlack(root));
        assertFalse(RBTree.isNil(root));
        assertTrue(RBTree.isNil(root.parent));
        assertTrue(root.getEl().intValue() == 100);
        RBTree<Integer>.RBTreeNode n100 = root;

        RBTree<Integer>.RBTreeNode n150 = root.getRight();
        assertFalse(RBTree.isNil(n150));
        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(RBTree.isNil(n150.getRight()));
        assertTrue(n150.getEl().intValue() == 150);
        assertTrue(n150.parent == n100);
        assertTrue(RBTree.isRed(n150));

        RBTree<Integer>.RBTreeNode n50 = root.getLeft();
        assertFalse(RBTree.isNil(n50));
        assertTrue(RBTree.isNil(n50.getLeft()));
        assertTrue(RBTree.isNil(n50.getRight()));
        assertTrue(n50.getEl().intValue() == 50);
        assertTrue(n50.parent == n100);
        assertTrue(RBTree.isRed(n50));

        // caso right/1
        t.insert(200);
        RBTree<Integer>.RBTreeNode n200 = t.getNodeOf(200);
        assertTrue(n200 != null);
        assertTrue(n200.getEl().intValue() == 200);

        assertTrue(RBTree.isNil(n200.getLeft()));
        assertTrue(RBTree.isNil(n200.getRight()));
        assertTrue(n200.parent == n150);
        assertTrue(RBTree.isRed(n200));

        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(n150.getRight() == n200);
        assertTrue(n150.parent == n100);
        assertTrue(RBTree.isBlack(n150));

        // caso right/2 + rightRotate + leftRotate
        t.insert(175);
        RBTree<Integer>.RBTreeNode n175 = t.getNodeOf(175);
        assertTrue(n175 != null);
        assertTrue(n175.getEl().intValue() == 175);

        assertTrue(RBTree.isNil(n200.getLeft()));
        assertTrue(RBTree.isNil(n200.getRight()));
        assertTrue(n200.parent == n175);
        assertTrue(RBTree.isRed(n200));

        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(RBTree.isNil(n150.getRight()));
        assertTrue(n150.parent == n175);
        assertTrue(RBTree.isRed(n150));

        assertTrue(n175.getLeft() == n150);
        assertTrue(n175.getRight() == n200);
        assertTrue(n175.parent == n100);
        assertTrue(RBTree.isBlack(n175));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#remove(java.lang.Comparable)}.
     */
    @Test
    final void testRemove1() {
        // Ricreo l'albero utilizzato per il test di insert con la sequenza 100,
        // 50, 150, 200, 175
        RBTree<Integer> t = new RBTree<Integer>(100);
        t.insert(50);
        t.insert(150);
        t.insert(200);
        t.insert(175);

        // Test del remove di elementi con molteplicità
        t.insert(200);
        t.insert(200);
        assertEquals(3, t.getCount(200));
        t.remove(200);
        assertEquals(2, t.getCount(200));
        t.remove(200);
        assertEquals(1, t.getCount(200));

        // Test di rimozione di una foglia rossa (200)
        t.remove(200);
        assertFalse(t.contains(200));

        RBTree<Integer>.RBTreeNode n100 = t.getNodeOf(100);
        RBTree<Integer>.RBTreeNode n150 = t.getNodeOf(150);
        RBTree<Integer>.RBTreeNode n175 = t.getNodeOf(175);
        RBTree<Integer>.RBTreeNode n50 = t.getNodeOf(50);
        assertFalse(RBTree.isNil(n100));
        assertFalse(RBTree.isNil(n150));
        assertFalse(RBTree.isNil(n175));
        assertFalse(RBTree.isNil(n50));

        assertTrue(t.getRoot() == n100);
        assertTrue(RBTree.isNil(n100.getParent()));
        assertTrue(RBTree.isBlack(n100));
        assertTrue(n100.getLeft() == n50);
        assertTrue(n100.getRight() == n175);

        assertTrue(n100 == n50.getParent());
        assertTrue(RBTree.isBlack(n50));
        assertTrue(RBTree.isNil(n50.getLeft()));
        assertTrue(RBTree.isNil(n50.getRight()));

        assertTrue(n100 == n175.getParent());
        assertTrue(RBTree.isBlack(n175));
        assertTrue(n150 == n175.getLeft());
        assertTrue(RBTree.isNil(n175.getRight()));

        assertTrue(n175 == n150.getParent());
        assertTrue(RBTree.isRed(n150));
        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(RBTree.isNil(n150.getRight()));

        // Test Remove, esce dal fix subito e ricolora
        t.remove(175);
        assertFalse(t.contains(175));

        assertTrue(t.getRoot() == n100);
        assertTrue(RBTree.isNil(n100.getParent()));
        assertTrue(RBTree.isBlack(n100));
        assertTrue(n100.getLeft() == n50);
        assertTrue(n100.getRight() == n150);

        assertTrue(n100 == n50.getParent());
        assertTrue(RBTree.isBlack(n50));
        assertTrue(RBTree.isNil(n50.getLeft()));
        assertTrue(RBTree.isNil(n50.getRight()));

        assertTrue(n100 == n150.getParent());
        assertTrue(RBTree.isBlack(n150));
        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(RBTree.isNil(n150.getRight()));

        // Test remove caso right/2, sostituzione radice
        t.remove(100);
        assertFalse(t.contains(100));

        assertTrue(t.getRoot() == n150);
        assertTrue(RBTree.isNil(n150.getParent()));
        assertTrue(RBTree.isBlack(n150));
        assertTrue(n150.getLeft() == n50);
        assertTrue(RBTree.isNil(n150.getRight()));

        assertTrue(n150 == n50.getParent());
        assertTrue(RBTree.isRed(n50));
        assertTrue(RBTree.isNil(n50.getLeft()));
        assertTrue(RBTree.isNil(n50.getRight()));

        // Test remove no-fix
        t.remove(50);
        assertFalse(t.contains(50));

        assertTrue(t.getRoot() == n150);
        assertTrue(RBTree.isNil(n150.getParent()));
        assertTrue(RBTree.isBlack(n150));
        assertTrue(RBTree.isNil(n150.getLeft()));
        assertTrue(RBTree.isNil(n150.getRight()));

        // Test remove radice, l'albero diventa vuoto
        t.remove(150);
        assertTrue(t.isEmpty());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#remove(java.lang.Comparable)}.
     */
    @Test
    final void testRemove2() {
        // Ricreo l'albero usato per il test di insert con la sequenza 50, 75,
        // 25, 65, 70, 72, 80, 85, 77
        RBTree<Integer> t = new RBTree<Integer>(50);
        t.insert(75);
        RBTree<Integer>.RBTreeNode n75 = t.getNodeOf(75);
        t.insert(25);
        RBTree<Integer>.RBTreeNode n25 = t.getNodeOf(25);
        t.insert(65);
        RBTree<Integer>.RBTreeNode n65 = t.getNodeOf(65);
        t.insert(70);
        RBTree<Integer>.RBTreeNode n70 = t.getNodeOf(70);
        t.insert(72);
        RBTree<Integer>.RBTreeNode n72 = t.getNodeOf(72);
        t.insert(80);
        t.insert(85);
        RBTree<Integer>.RBTreeNode n85 = t.getNodeOf(85);
        t.insert(77);
        RBTree<Integer>.RBTreeNode n77 = t.getNodeOf(77);

        assertTrue(n70 == t.getRoot());
        assertTrue(RBTree.isNil(n70.parent));
        assertTrue(RBTree.isBlack(n70));

        // caso no-fix
        t.remove(80);

        assertTrue(RBTree.isNil(n77.getLeft()));
        assertTrue(RBTree.isNil(n77.getRight()));
        assertTrue(n77.parent == n85);
        assertTrue(RBTree.isRed(n77));

        assertTrue(n85.getLeft() == n77);
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n75);
        assertTrue(RBTree.isBlack(n85));

        assertTrue(n75.getLeft() == n72);
        assertTrue(n75.getRight() == n85);
        assertTrue(n75.parent == n70);
        assertTrue(RBTree.isRed(n75));

        // caso right/2 ricolorazioni
        t.remove(50);

        assertTrue(RBTree.isNil(n25.getLeft()));
        assertTrue(RBTree.isNil(n25.getRight()));
        assertTrue(n25.parent == n65);
        assertTrue(RBTree.isRed(n25));

        assertTrue(n65.getLeft() == n25);
        assertTrue(RBTree.isNil(n65.getRight()));
        assertTrue(n65.parent == n70);
        assertTrue(RBTree.isBlack(n65));

        assertTrue(n70.getLeft() == n65);
        assertTrue(n70.getRight() == n75);
        assertTrue(RBTree.isNil(n70.parent));
        assertTrue(RBTree.isBlack(n70));

        // caso ricolorazione
        t.remove(65);

        assertTrue(RBTree.isNil(n25.getLeft()));
        assertTrue(RBTree.isNil(n25.getRight()));
        assertTrue(n25.parent == n70);
        assertTrue(RBTree.isBlack(n25));

        assertTrue(n70.getLeft() == n25);
        assertTrue(n70.getRight() == n75);
        assertTrue(RBTree.isNil(n70.parent));
        assertTrue(RBTree.isBlack(n70));

        // caso left/1 + leftRotate + left/2 + cambio radice
        t.remove(25);

        assertTrue(n75 == t.getRoot());
        assertTrue(n75.getLeft() == n70);
        assertTrue(n75.getRight() == n85);
        assertTrue(RBTree.isNil(n75.parent));
        assertTrue(RBTree.isBlack(n75));

        assertTrue(RBTree.isNil(n70.getLeft()));
        assertTrue(n70.getRight() == n72);
        assertTrue(n70.parent == n75);
        assertTrue(RBTree.isBlack(n70));

        assertTrue(n85.getLeft() == n77);
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n75);
        assertTrue(RBTree.isBlack(n85));

        assertTrue(RBTree.isNil(n72.getLeft()));
        assertTrue(RBTree.isNil(n72.getRight()));
        assertTrue(n72.parent == n70);
        assertTrue(RBTree.isRed(n72));

        assertTrue(RBTree.isNil(n77.getLeft()));
        assertTrue(RBTree.isNil(n77.getRight()));
        assertTrue(n77.parent == n85);
        assertTrue(RBTree.isRed(n77));

        // caso no-fix, trapianto di radice
        t.remove(75);

        assertTrue(RBTree.isNil(n72.getLeft()));
        assertTrue(RBTree.isNil(n72.getRight()));
        assertTrue(n72.parent == n70);
        assertTrue(RBTree.isRed(n72));

        assertTrue(RBTree.isNil(n85.getLeft()));
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n77);
        assertTrue(RBTree.isBlack(n85));

        assertTrue(RBTree.isNil(n70.getLeft()));
        assertTrue(n70.getRight() == n72);
        assertTrue(n70.parent == n77);
        assertTrue(RBTree.isBlack(n70));

        assertTrue(n77 == t.getRoot());
        assertTrue(n77.getLeft() == n70);
        assertTrue(n77.getRight() == n85);
        assertTrue(RBTree.isNil(n77.parent));
        assertTrue(RBTree.isBlack(n77));

        // caso right/3 + rotateLeft + caso right/4 + rotateRight + trapianto di
        // radice
        t.remove(77);

        assertTrue(RBTree.isNil(n70.getLeft()));
        assertTrue(RBTree.isNil(n70.getRight()));
        assertTrue(n70.parent == n72);
        assertTrue(RBTree.isBlack(n70));

        assertTrue(RBTree.isNil(n85.getLeft()));
        assertTrue(RBTree.isNil(n85.getRight()));
        assertTrue(n85.parent == n72);
        assertTrue(RBTree.isBlack(n85));

        assertTrue(n72 == t.getRoot());
        assertTrue(n72.getLeft() == n70);
        assertTrue(n72.getRight() == n85);
        assertTrue(RBTree.isNil(n72.parent));
        assertTrue(RBTree.isBlack(n72));
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl1920.miniproject2.RBTree#remove(java.lang.Comparable)}.
     */
    @Test
    final void testRemove3() {
        // Ricreo l'albero usato per il test di insert con la sequenza 41, 38,
        // 31, 12, 9, 8
        RBTree<Integer> t = new RBTree<Integer>(41);
        t.insert(38);
        RBTree<Integer>.RBTreeNode n38 = t.getNodeOf(38);
        t.insert(31);
        RBTree<Integer>.RBTreeNode n31 = t.getNodeOf(31);
        t.insert(12);
        RBTree<Integer>.RBTreeNode n12 = t.getNodeOf(12);
        t.insert(9);
        RBTree<Integer>.RBTreeNode n9 = t.getNodeOf(9);
        t.insert(8);
        RBTree<Integer>.RBTreeNode n8 = t.getNodeOf(8);

        assertTrue(n38 == t.getRoot());
        assertTrue(RBTree.isNil(n38.parent));
        assertTrue(RBTree.isBlack(n38));

        // caso right/1 + rightRotate + caso right/2 + cambio radice
        t.remove(41);

        assertTrue(RBTree.isNil(n8.getLeft()));
        assertTrue(RBTree.isNil(n8.getRight()));
        assertTrue(n8.parent == n9);
        assertTrue(RBTree.isRed(n8));

        assertTrue(RBTree.isNil(n31.getLeft()));
        assertTrue(RBTree.isNil(n31.getRight()));
        assertTrue(n31.parent == n38);
        assertTrue(RBTree.isRed(n31));

        assertTrue(n9.getLeft() == n8);
        assertTrue(RBTree.isNil(n9.getRight()));
        assertTrue(n9.parent == n12);
        assertTrue(RBTree.isBlack(n9));

        assertTrue(n38.getLeft() == n31);
        assertTrue(RBTree.isNil(n38.getRight()));
        assertTrue(n38.parent == n12);
        assertTrue(RBTree.isBlack(n38));

        assertTrue(n12 == t.getRoot());
        assertTrue(n12.getLeft() == n9);
        assertTrue(n12.getRight() == n38);
        assertTrue(RBTree.isNil(n12.parent));
        assertTrue(RBTree.isBlack(n12));

        // trapianto radice, no-fix
        t.remove(12);

        assertTrue(RBTree.isNil(n8.getLeft()));
        assertTrue(RBTree.isNil(n8.getRight()));
        assertTrue(n8.parent == n9);
        assertTrue(RBTree.isRed(n8));

        assertTrue(n9.getLeft() == n8);
        assertTrue(RBTree.isNil(n9.getRight()));
        assertTrue(n9.parent == n31);
        assertTrue(RBTree.isBlack(n9));

        assertTrue(RBTree.isNil(n38.getLeft()));
        assertTrue(RBTree.isNil(n38.getRight()));
        assertTrue(n38.parent == n31);
        assertTrue(RBTree.isBlack(n38));

        assertTrue(n31 == t.getRoot());
        assertTrue(n31.getLeft() == n9);
        assertTrue(n31.getRight() == n38);
        assertTrue(RBTree.isNil(n31.parent));
        assertTrue(RBTree.isBlack(n31));

        // caso no-ciclo while
        t.remove(9);

        assertTrue(n31 == t.getRoot());
        assertTrue(n31.getLeft() == n8);
        assertTrue(n31.getRight() == n38);
        assertTrue(RBTree.isNil(n31.parent));
        assertTrue(RBTree.isBlack(n31));

        assertTrue(RBTree.isNil(n8.getLeft()));
        assertTrue(RBTree.isNil(n8.getRight()));
        assertTrue(n8.parent == n31);
        assertTrue(RBTree.isBlack(n8));

        assertTrue(RBTree.isNil(n38.getLeft()));
        assertTrue(RBTree.isNil(n38.getRight()));
        assertTrue(n38.parent == n31);
        assertTrue(RBTree.isBlack(n38));
    }

}
