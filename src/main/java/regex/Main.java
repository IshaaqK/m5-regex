package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * The Main method for this assignment.
     * You can optionally run this to interactively try the three methods.
     * 
     * @param args parameters are unused
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    // Method 1 for checking if a string matches a regex: using Pattern.matches
    // least
    // one lower case letter, at least one upper case letter, and at least one
    // digit. If the
    // string has all of these properties, the method should return true. If it is
    // missing one
    // or more properties, it should return false.

    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum
     * length.
     * 
     * @param str       the string to check for the properties in
     * @param minLength the minimum length required for the password
     * @return whether the string satisfies the password requirements
     */
    public static boolean checkForPassword(String str, int minLength) {
        if (str == null) {
            return false;
        }
        // (?=.*[a-z]) ensures at least one lowercase
        // (?=.*[A-Z]) ensures at least one uppercase
        // (?=.*[0-9]) ensures at least one digit
        // .{minLength,} ensures minimum length
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{" + minLength + ",}";
        return str.matches(regex);
    }

    // Method 2 for checking if a string conforms to a regex: using Matcher.find
    // the
    // input string that end with "@mail.utoronto.ca" or "@utoronto.ca" with at
    // least one
    // character before the "@" symbol. The email addresses should be in the order
    // they
    // appear in the string.

    /**
     * Returns a list of email addresses that occur in a given string.
     * 
     * @param str the string to look for email addresses in
     * @return a list containing the email addresses in the string.
     */
    public static List<String> extractEmails(String str) {
        final List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }
        // [^\\s@]+ matches one or more characters that are not whitespace or @
        // @(mail\\.)?utoronto\\.ca matches @utoronto.ca or @mail.utoronto.ca
        // We use \\b at the end to ensure we don't match something like
        // "bob@utoronto.ca.com" if that was a concern,
        // but strictly following "end with..." in the context of extraction usually
        // implies word boundaries or specific delimiters.
        // However, the prompt says "end with ...".
        // Let's use a pattern that captures the email.
        final Pattern pattern = Pattern.compile("[^\\s@]+@(mail\\.)?utoronto\\.ca\\b");
        final Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    // Method 3 for checking if a string conforms to a regex: using String.matches

    // capital letter
    // twice. For example "Amazing Apple" contains "A" twice. If the string does
    // repeat the same
    // capital letter twice, the method should return true. Otherwise it should
    // return false.

    /**
     * Checks whether a given string contains the same capital letter twice.
     * 
     * @param str the string to look for doubles in
     * @return whether str contains the same capital letter twice.
     */
    public static boolean checkForDoubles(String str) {
        if (str == null) {
            return false;
        }
        // .* matches any characters (except newline)
        // ([A-Z]) captures a single capital letter
        // .* matches any characters in between
        // \\1 matches the same capital letter captured in group 1
        // .* matches any remaining characters
        return str.matches(".*([A-Z]).*\\1.*");
    }
}
