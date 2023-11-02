package edu.odu.cs;

public class WordFilter {

    public static String filter(String string) {
        // Trim hyphens (carefully). Discard hyphens between two alphabetic
        // characters. Retain all others as ordinary punctuation.
        StringBuffer result = new StringBuffer();
        String unhyphenated = trimHyphens(string, result);

        // Extract the first block of consecutive alphabetic chars.
        String trimmed = getAlphabeticBlock(unhyphenated);
        return removeCapitalization(trimmed);
    }

    private static String removeCapitalization(String trimmed) {
        if (isAllUpperCase(trimmed)) {
            return trimmed;
        } else {
            return trimmed.toLowerCase();
        }
    }

    private static String getAlphabeticBlock(String unhyphenated) {
        int startOfBlock = getStartOfAlphabeticBlock(unhyphenated);
        String trimmed = unhyphenated.substring(startOfBlock);
        int endOfBlock = getEndOfAlphabeticBlock(trimmed);

        if (endOfBlock < 0) {
            trimmed = "";
        } else if (endOfBlock < trimmed.length() - 1) {
            trimmed = trimmed.substring(0, endOfBlock + 1);
        }
        return trimmed;
    }

    private static int getEndOfAlphabeticBlock(String trimmed) {
        int endOfBlock = -1;
        for (int pos = 0; pos < trimmed.length(); ++pos) {
            char c = trimmed.charAt(pos);
            if (!(Character.isAlphabetic(c) || c == '-')) {
                endOfBlock = pos - 1;
                break;
            }
        }
        if (endOfBlock < 0)
            endOfBlock = trimmed.length() - 1;
        return endOfBlock;
    }

    private static int getStartOfAlphabeticBlock(String unhyphenated) {
        int startOfBlock = -1;
        for (int pos = 0; pos < unhyphenated.length(); ++pos) {
            char c = unhyphenated.charAt(pos);
            if (Character.isAlphabetic(c)) {
                startOfBlock = pos;
                break;
            }
        }
        if (startOfBlock < 0) {
            startOfBlock = unhyphenated.length();
        }
        return startOfBlock;
    }

    private static String trimHyphens(String string, StringBuffer result) {
        int i;
        for (i = 0; i < string.length() - 2; ++i) {
            char c1 = string.charAt(i + 1);
            if (c1 == '-') {
                char c0 = string.charAt(i);
                char c2 = string.charAt(i + 2);
                if (Character.isAlphabetic(c0) && Character.isAlphabetic(c2)) {
                    result.append(c0);
                    result.append(c1);
                    ++i; // to skip over the coming hyphen
                } else {
                    result.append(c0);
                    // replace the bad hyphen by something that will be clipped later
                    result.append('%');
                    ++i;
                }
            } else {
                result.append(string.charAt(i));
            }
        }
        for (int j = i; j < string.length(); ++j) {
            char c = string.charAt(j);
            if (Character.isAlphabetic(c)) {
                result.append(c);
            }
        }
        String unhyphenated = result.toString();
        return unhyphenated;
    }

    private static boolean isAllUpperCase(String string) {
        for (int pos = 0; pos < string.length(); ++pos) {
            char c = string.charAt(pos);
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

}
