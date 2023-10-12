package org.example.linkedlist;

public class LinkedList {
    private Node head;

    public Node getHead () {
        return head;
    }

    public LinkedList() {
        this.head = null;
    }

    public void addToHead(String key, String value) {
        Node newHead = new Node(key, value);
        Node currentHead = this.head;
        this.head = newHead;
        if (currentHead != null) {
            this.head.setNextNode(currentHead);
        }
    }

    public void addToTail(String key, String value) {
        Node tail = this.head;
        if (tail == null) {
            this.head = new Node(key, value);
        } else {
            while (tail.getNextNode() != null) {
                tail = tail.getNextNode();
            }
            tail.setNextNode(new Node(key, value));
        }
    }

    public void removeHead() {
        Node removedHead = this.head;
        if (removedHead == null) {
            return;
        }
        this.head = removedHead.getNextNode();
    }

    public String toString() {
		Node currentNode = this.head;
		StringBuilder output = new StringBuilder("[ ");
		while (currentNode != null) {
			output.append(currentNode.value).append(" ");
			currentNode = currentNode.getNextNode();
		}
		output.append("]");
		return output.toString();
	}

    public static void main(String[] args) {
      LinkedList list = new LinkedList();
      list.addToHead("2", "4");
      System.out.println(list);
    }
}