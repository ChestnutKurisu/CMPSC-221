package substring_problem;

public class StringContains {
    public static void main(String[] args) {
        System.out.println(contains("Java programming", "ogr")); //true
        System.out.println(contains("Java programming", "grammy")); //false
        System.out.println(contains("Quintessence", "essence")); //true
        System.out.println(contains("Coding", "Coding")); //true
        System.out.println(contains("Car", "Carpool")); //false
        System.out.println(contains("abcabdaabbcdabcba", "abcd")); //false
        System.out.println(contains("abcabdaabbcdabcd", "abcd")); //true
        System.out.println(contains("pokemon@nintendo.com", "@")); //true
    }

    public static boolean contains(String haystack, String needle) {
        return containsHelper(haystack, needle, 0, 0);
    }

    private static boolean containsHelper(String haystack, String needle, int i, int j) {
        if (haystack.length() - i < needle.length())
            return false;
        if (haystack.charAt(i + j) != needle.charAt(j))
            return containsHelper(haystack, needle, i + 1, 0);
        else {
            if (j == needle.length() - 1)
                return true;
            return containsHelper(haystack, needle, i, j + 1);
        }
    }
}
