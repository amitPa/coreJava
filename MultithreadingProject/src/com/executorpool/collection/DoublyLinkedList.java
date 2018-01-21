package com.executorpool.collection;

public class DoublyLinkedList<T> {

	Node<T> first;
	Node<T> head;
	int position;

	public void insert(T value) {

		Node<T> nj = new Node<T>();
		nj.setValue(value);
		nj.setPrevious(first);
		nj.setNext(null);
		nj.setIndex(position);
		position++;

		if (first != null) {
			first.setNext(nj);
		}

		first = nj;

		if (first.getPrevious() == null && first.getNext() == null) {
			head = first;
		}
	}

	public void remove(T value) {

		deleteElement(head, value);

	}

	public void print() {
		printFrontTraversal(head);
	}

	private void printFrontTraversal(Node<T> node) {
		System.out.println(node.getValue());
		if (node.getNext() != null) {
			printFrontTraversal(node.getNext());
		}
	}

	private void deleteElement(Node<T> first, T value) {

		if (first != null) {
			if (first.getValue() == value) {
				Node<T> next = first.getNext();
				Node<T> prev = first.getPrevious();
				next.setPrevious(prev);
				prev.setNext(next);
			} else
				deleteElement(first.getNext(), value);
		}

	}

	public static void main(String args[]) {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.insert("Amit");
		list.insert("Ajit");
		list.insert("Deepak");
		list.print();
		list.remove("Ajit");
		list.print();
	}

}
