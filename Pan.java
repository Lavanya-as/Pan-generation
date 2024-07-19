

import java.util.Formatter;
import java.util.Scanner;

public class Pan {

    private static int currentNumber = 1; // Starting number

    // Method to generate a random string of length 'n' from the given characters
    private static String randomString(int n, String characters) {
        StringBuilder randString = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            randString.append(randomChar);
        }
        return randString.toString();
    }

    // Method to generate a PAN number using type, name
    private static String generatePan(String type, String name) {
        StringBuilder pan = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Generate a random string of 3 characters
        String randomStr = randomString(3, characters);
        pan.append(randomStr);

        // Append type (fourth character)
        pan.append(type.toUpperCase());

        // Append the first letter of the surname (fifth character)
        pan.append(name.toUpperCase().charAt(0));

        // Append the formatted number sequence (next four digits)
        pan.append(numSeq(currentNumber));

        // Calculate the ASCII sum of the first 9 characters
        int asciiSum = calculateAsciiSum(pan.toString());
        char checkDigit = (char) ('A' + (asciiSum % 26)); // Map ASCII sum to a letter A-Z

        // Append the check digit (last character)
        pan.append(checkDigit);

        // Increment the number for the next PAN
        currentNumber++;

        return pan.toString();
    }

    // Method to format the number sequence to 4 digits with leading zeros
    private static String numSeq(int n) {
        Formatter formatter = new Formatter();
        formatter.format("%04d", n);
        String formattedNum = formatter.toString();
        formatter.close();
        return formattedNum;
    }

    // Method to calculate the ASCII sum of the given string
    private static int calculateAsciiSum(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            sum += (int) c;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String type;

            // Prompt the user for type and validate input
            while (true) {
                System.out.println("Enter type (single character, e.g., P for Individual, C for Company, etc.):");
                type = scan.next();
                
                // Validate type input
                if (type.length() == 1 && Character.isLetter(type.charAt(0))) {
                    break; // Exit loop if input is valid
                } else {
                    System.out.println("Invalid type. Type must be a single character.");
                }
            }

            // Prompt the user for surname and validate input
            String surname;
            while (true) {
                System.out.println("Enter surname:");
                scan.nextLine(); // Consume the remaining newline
                surname = scan.nextLine();
                
                // Validate surname input
                if (!surname.isEmpty() && Character.isLetter(surname.charAt(0))) {
                    break; // Exit loop if input is valid
                } else {
                    System.out.println("Invalid surname. Surname cannot be empty and must start with a letter.");
                }
            }

            // Generate PAN number
            String pan = generatePan(type, surname);
            System.out.println("Generated PAN: " + pan);

            // Ask the user if they want to generate another PAN or exit
            System.out.println("Do you want to generate another PAN? (y/n)");
            String response = scan.next().trim().toLowerCase();

            if (!response.equals("y")) {
                break; // Exit the loop if the user does not want to generate another PAN
            }
        }

        scan.close();
    }
}



