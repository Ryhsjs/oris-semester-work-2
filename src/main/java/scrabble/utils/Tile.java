package scrabble.utils;

import java.util.Random;

public class Tile {
        private final String id;
        private final char letter;
        private final int points;

        public Tile(char letter, int points) {
            this.letter = letter;
            this.points = points;
            this.id = generateId();
        }

        private String generateId() {
            return letter + "_" + System.currentTimeMillis() + "_" + (new Random()).nextInt(1000);
        }

        public char getLetter() {
            return letter;
        }

        public int getPoints() {
            return points;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return String.valueOf(letter).toUpperCase() + "(" + points + ")";
        }
    }