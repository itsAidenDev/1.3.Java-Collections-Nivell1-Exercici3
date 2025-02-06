package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> countriesMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/countries.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    countriesMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        List<String> countries = new ArrayList<>(countriesMap.keySet());
        Collections.shuffle(countries);

        int score = 0;
        for (int i = 0; i < 10 && i < countries.size(); i++) {
            String country = countries.get(i);
            System.out.print("What is the capital of " + country + "? ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(countriesMap.get(country))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The capital is " + countriesMap.get(country));
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("classification.txt", true))) {
            bw.write(userName + ": " + score + " points");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }

        System.out.println("Game over. Your score is: " + score + " points.");

        scanner.close();
    }
}