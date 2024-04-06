package me.waterarchery;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import me.waterarchery.models.Person;

import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();
        Map<String, Person> map = hazelcast.getMap("map");

        for (int i = 0; i < 10000; i++) {
            String randomName = generateRandomName();
            map.put(randomName, new Person(randomName));

            Person person = map.get(randomName);
            System.out.println(person);
        }

        Hazelcast.shutdownAll();
    }

    public static String generateRandomName() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i ++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return sb.toString();
    }

}