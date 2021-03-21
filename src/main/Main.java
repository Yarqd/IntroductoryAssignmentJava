package main;

public final class Main {
    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("arguments are empty");

        for (String input : args) {
            System.out.println("input: " + input);
            System.out.println("output: " + convert(input));
        }
    }

    public static int findClosingParen(String text, int openPos) {
        int closePos = openPos;
        int counter = 1;

        while(counter > 0) {
            ++closePos;
            char c = text.charAt(closePos);
            if (c == '[') {
                ++counter;
            } else if (c == ']') {
                --counter;
            }
        }

        return closePos;
    }

    public static String convert(String input) {
        StringBuilder result = new StringBuilder();
        StringBuilder num = new StringBuilder();
        int pos = 0;
        while(pos < input.length()) {
            char c = input.charAt(pos++);
            if (Character.isDigit(c)) {
                num.append(c);
            } else if (Character.isLetter(c)) {
                result.append(c);
            } else if (c == '[') {
                int end = findClosingParen(input, pos - 1);
                for (int n = 0; n < Integer.parseInt(num.toString()); n++) {
                    result.append(convert(input.substring(pos, end)));
                }

                num = new StringBuilder();
                pos = end + 1;
            }
        }

        return result.toString();
    }
}