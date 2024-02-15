package me.c0pperx.roleplaybasics.utilities;

public class StringUtils {
    public static String getRolePlayName(String name) {
        return name.replace("_", " ");
    }

    public static String textFormat(String string, String punctuation) {
        char lastChar = string.charAt(string.length() - 1);
        char firstChar = string.charAt(0);
        if(lastChar != '.' && lastChar != ',' && lastChar != '!' && lastChar != '?') string += punctuation;
        string = Character.toUpperCase(firstChar) + string.substring(1);
        return string;
    }
}
