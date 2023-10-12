package org.example.hashmaps.collision_handling;

import org.example.linkedlist.LinkedList;
import org.example.linkedlist.Node;

import java.util.Objects;

public class SeparateChainingHashMap {
    private final LinkedList[] hashmap;

    public SeparateChainingHashMap(int size) {
        this.hashmap = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.hashmap[i] = new LinkedList();
        }
    }

     public int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = hashCode + Character.codePointAt(key, i);
        }
        hashCode = hashCode % this.hashmap.length;
        return hashCode;
    }

    public void assign(String key, String value) {
        int arrayIndex = this.hash(key);
        LinkedList list = this.hashmap[arrayIndex];
        if (list.getHead() == null) {
            list.addToHead(key, value);
            return;
        }
        Node current = list.getHead();
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.setKeyValue(key, value);
            }
            if (current.getNextNode() == null) {
                current.setNextNode(new Node(key, value));
                break;
            }
            current = current.getNextNode();
        }
    }

    public String retrieve(String key) {
        int arrayIndex = this.hash(key);
        Node current = this.hashmap[arrayIndex].getHead();
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.getNextNode();
        }
        return null;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("{ ");
        for (LinkedList list :hashmap) {
            builder.append(list).append(" , ");
        }
        builder.append(" }");
        return builder.toString();
    }

    public static void main(String[] args) {
      SeparateChainingHashMap birdCensus = new SeparateChainingHashMap(15);
      birdCensus.assign("mandarin duck", "Central Park Pond");
      birdCensus.assign("monk parakeet", "Brooklyn College");
      birdCensus.assign("horned owl", "Pelham Bay Park");

      System.out.println(birdCensus.retrieve("mandarin duck"));
      System.out.println(birdCensus.retrieve("monk parakeet"));
      System.out.println(birdCensus.retrieve("horned owl"));
      System.out.println(birdCensus);
    }
}
