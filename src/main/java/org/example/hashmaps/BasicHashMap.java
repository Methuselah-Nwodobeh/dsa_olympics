package org.example.hashmaps;

import java.util.Arrays;

public class BasicHashMap {

    private final String[] hashMap;

    public BasicHashMap(int size) {
        this.hashMap = new String[size];
    }

    public int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++){
            int code = Character.codePointAt(key, i);
            System.out.println("this is code for character: " + code);
            hashCode += code;

        }
        System.out.println("this is modulos: " + hashCode % hashMap.length);
        return hashCode % hashMap.length;
    }

    public void put(String key, String value){
        int arrayIndex = hash(key);
        System.out.println("this is array index in put: " + arrayIndex);
        hashMap[arrayIndex] = value;
        System.out.println("this is updated array after put: " + Arrays.toString(hashMap));
    }

    public String get(String key){
        int arrayIndex = hash(key);
        System.out.println("this is array index in get: " + arrayIndex);
        return hashMap[arrayIndex];
    }

    public static void main(String[] args) {
        BasicHashMap songs = new BasicHashMap(5);
        songs.put("Bob Marley", "Three little birds");
        songs.put("Westlife", "Queen of my heart");
        songs.put("HillSong", "Good Grace");
        songs.put("qewcc", "Good Grace2");

        System.out.println("this is Hillsong: " +songs.get("HillSong"));
        System.out.println("this is westlife: " +songs.get("Westlife"));
        System.out.println("this is qewcc: " +songs.get("qewcc"));
    }
}