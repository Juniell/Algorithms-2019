package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> parent = null;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    public boolean add(List<T> list) {
        for (T el : list) {
            if (!add(el))
                return false;
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else {
            newNode.parent = closest;
            if (comparison < 0) {
                assert closest.left == null;
                closest.left = newNode;
            }
            else {
                assert closest.right == null;
                closest.right = newNode;
            }
        }
        size++;
        return true;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private Node<T> min (Node<T> start) {
        if (start == null)
            return null;
        if (start.left == null)
            return start;
        return min(start.left);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        //TODO
        throw new NotImplementedError();
    }


    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    private Node<T> nextNode(Node<T> start) {
        if (root == null)
            return null;
        if (start == null)
            return min(root);
        if (start.right != null)
            return min(start.right);
        Node<T> node = start.parent;
        while (node != null && start == node.right) {
            start = node;
            node = node.parent;
        }
        return node;
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> current;
        private Node<T> previous = null;

        private BinaryTreeIterator() {
               current = min(root);
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        // Трудоёмкость: O(1);
        // Ресурсоёмкость: O(1).
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        // Трудоёмкость: O(log(N));
        // Ресурсоёмкость: O(1).
        @Override
        public T next() {
            previous = current;
            current = nextNode(current);
            if (previous != null)
                return previous.value;
            throw new NoSuchElementException();
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Найти множество всех элементов в диапазоне [fromElement, toElement)
     * Очень сложная
     */
    @NotNull
    @Override
    // Трудоёмкость: O(1);
    // Ресурсоёмкость: O(1).
    public SortedSet<T> subSet(T fromElement, T toElement) {
        if (toElement == null && fromElement == null)
            throw new IllegalArgumentException();
        assert toElement != null;
        if (toElement.compareTo(fromElement) == 0)
            throw new IllegalArgumentException();
       return new BinaryTreeSubSet(this, fromElement, toElement);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    // Трудоёмкость: O(1);
    // Ресурсоёмкость: O(1).
    public SortedSet<T> headSet(T toElement) {
        if (toElement == null)
            throw new IllegalArgumentException();
        return new BinaryTreeSubSet(this, null, toElement);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    // Трудоёмкость: O(1);
    // Ресурсоёмкость: O(1).
    public SortedSet<T> tailSet(T fromElement) {
        if (fromElement == null)
            throw new IllegalArgumentException();
        return new BinaryTreeSubSet(this, fromElement, null);
    }

    class BinaryTreeSubSet extends BinaryTree<T> {
        final T upLimit;
        final T botLimit;
        BinaryTree<T> tree;

        private BinaryTreeSubSet (BinaryTree<T> tree, T botLimit, T upLimit) {
            if (botLimit != null && upLimit != null && botLimit.compareTo(upLimit) > 0)
                throw new IllegalArgumentException("Borders set incorrectly");
            this.botLimit = botLimit;
            this.upLimit = upLimit;
            this.tree = tree;
        }

        private boolean onInterval (T value) {
            return (botLimit == null && value.compareTo(upLimit) < 0) ||
                    (upLimit == null && value.compareTo(botLimit) >= 0) ||
                    (botLimit != null && upLimit != null && value.compareTo(botLimit) >= 0 && value.compareTo(upLimit) < 0);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean contains (Object o) {
            return onInterval((T) o) && tree.contains(o);
        }

        @Override
        public boolean add (T t) {
            if (!onInterval(t))
                throw new IllegalArgumentException();
            return  tree.add(t);
        }

        @Override
        public int size() {
            return size(tree.root);
        }

        private int size(Node<T> node) {
            int count = 0;
            if (node == null)
                return count;
            count += size(node.left);
            if (onInterval(node.value))
                count++;
            count += size(node.right);
            return count;
        }
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
