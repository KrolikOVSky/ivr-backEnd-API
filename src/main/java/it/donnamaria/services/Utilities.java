package it.donnamaria.services;

public class Utilities {
    public static String convert(String word) {
        StringBuilder result = new StringBuilder();
        word = word.toLowerCase();
        for (var letter : word.toCharArray()) {
            result.append(cyrillicToLatin(String.valueOf(letter)));
        }
        return result.toString();
    }

    private static String cyrillicToLatin(String letter) {
        return switch (letter) {
            case "а" -> "a";
            case "б" -> "b";
            case "в" -> "v";
            case "г" -> "g";
            case "д" -> "d";
            case "е", "э" -> "e";
            case "ё" -> "yo";
            case "ж" -> "zh";
            case "з" -> "z";
            case "и" -> "i";
            case "й" -> "y";
            case "к" -> "k";
            case "л" -> "l";
            case "м" -> "m";
            case "н" -> "n";
            case "о" -> "o";
            case "п" -> "p";
            case "р" -> "r";
            case "с" -> "s";
            case "т" -> "t";
            case "у" -> "u";
            case "ф" -> "f";
            case "х" -> "h";
            case "ц" -> "c";
            case "ч" -> "ch";
            case "ш" -> "sh";
            case "щ" -> "sch";
            case "ъ" -> "";
            case "ы" -> "y";
            case "ь" -> "";
            case "ю" -> "yu";
            case "я" -> "ya";
            case " " -> "-";
            default -> letter;
        };
    }

    private static String latinToCyrillic(String letter) {
        return switch (letter) {
            case "a" -> "а";
            case "b" -> "б";
            case "c" -> "ц";
            case "d" -> "д";
            case "e" -> "е";
            case "f" -> "ф";
            case "g" -> "г";
            case "h" -> "х";
            case "i" -> "и";
            case "j" -> "дж";
            case "k" -> "к";
            case "l" -> "л";
            case "m" -> "м";
            case "n" -> "н";
            case "o" -> "о";
            case "p" -> "п";
            case "q" -> "к";
            case "r" -> "р";
            case "s" -> "с";
            case "t" -> "т";
            case "u" -> "ю";
            case "v" -> "в";
            case "w" -> "в";
            case "x" -> "кс";
            case "y" -> "ch";
            case "z" -> "з";
            case " " -> "-";
            default -> letter;
        };
    }

}
