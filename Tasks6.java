import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Tasks6 {
    public static void main(String[] args) {
        System.out.println(bell(1));
        System.out.println(translateWord("flag"));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(validColor("rgb(0,0,0)"));
        String[] arr = new String[]{"b"};
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", arr));
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(ulam(206));
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(convertToRoman(2));
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(palindromeDescendant(11211230));
    }

    public static int bell(int n) {
        int[] arr0 = new int[n];
        int[] arr1 = new int[n];
        arr0[0] = 1;
        arr1[0] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < i; ++j)
                arr1[j] = arr0[j - 1] + arr1[j - 1];
            for (int k = 0; k < i; ++k)
                arr0[k] = arr1[k];
            arr1[0] = arr1[i - 1];
        }
        return arr1[n - 1];
    }

    public static String translateWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(word.charAt(0)) > -1) {
            word = word.concat("yay");
        }else {
            boolean isUpperCase = Character.isUpperCase(word.charAt(0));
            for (int i = 0; i < word.length(); i++) {
                if (vowels.indexOf(word.charAt(i)) > -1) {
                    word = word.substring(i).concat(word.substring(0, i)).concat("ay");
                    break;
                }
            }
            if (isUpperCase) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
        }
        return word;
    }

    public static String translateSentence(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("\\p{Punct}", "");
            word = translateWord(word);
            word = words[i].replaceAll("[\\w]+", word);
            words[i] = word;
        }
        return String.join(" ", words);
    }

    public static boolean validColor(String color) {
        String s = color.substring(color.indexOf("(")+1,
                color.indexOf(")"));
        String[] parts = s.split(",");

        // ensure no whitespace before paren
        if (color.contains(" ("))
            return false;

        // ensure we have correct number of parts
        if (color.contains("rgba") && parts.length != 4)
            return false;
        else if (color.contains("rgb(") && parts.length != 3)
            return false;

        boolean percentage = false;
        int num = 0;
        double dec = 0;
        // ensure values are correct
        for (String value : parts) {

            if (value.contains("%"))
                percentage = true;

            if (value.equals(""))
                return false;

            value = value.trim().replaceAll("%","");

            if (!value.contains("."))
                num = Integer.parseInt(value);
            else
                dec = Double.parseDouble(value);

            // ensure rgb part doesn't contain decimal
            if (!color.contains("rgba") && value.contains("."))
                return false;

            if (!percentage) {

                // ensure number is between 0 and 255
                if (num < 0 || num > 255)
                    return false;

                // ensure rgba decimal is between 0 and 1
                if (color.contains("rgba") &&
                        (value.contains(".") && dec > 1.0 ||
                                (value.contains(".") && dec < 0)))
                    return false;

            } else {

                // ensure percentage is between 0 and 100
                if (num < 0 || num > 100)
                    return false;

            }

        }

        return true;
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        if (!url.contains("?")) return url;

        String[] surl = url.split("\\?");
        String[] params = surl[1].split("\\&");
        HashMap<String, String> dict = new HashMap<>();

        for (String v : params) {
            dict.put(v.substring(0, 1), v);
        }

        if (paramsToStrip != null) for (String k : paramsToStrip) {
            dict.remove(k);
        }

        return surl[0] + "?" + String.join("&", dict.values());
    }

    public static String getHashTags(String phrase) {
        String[] words = phrase.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        ArrayList<String> orderedWords = new ArrayList<>(Arrays.asList(words));
        orderedWords.sort((s, t1) -> Integer.compare(t1.length(), s.length()));
        String[] result = new String[Math.min(orderedWords.size(), 3)];
        for (int i = 0; i < orderedWords.size() && i < 3; i++) {
            result[i] = String.join("", "#", orderedWords.get(i));
        }
        String intArrayString = Arrays.toString(result);
        return intArrayString;
    }

    public static int ulam(int n) {
        TreeSet<Integer> ulamNums = new TreeSet<Integer>();
        ulamNums.add(1);
        ulamNums.add(2);
        ulamNums.add(3);
        Integer curNum = 3;

        for(int i=3; i < n;)
        {
            curNum++;
            int numAdds = 0;
            for(Integer j : ulamNums) {
                Integer chkNum = curNum - j;
                if (chkNum <= j) {
                    break;
                }
                if (ulamNums.contains(chkNum)) {
                    numAdds++;
                    if (numAdds > 1) {
                        break;
                    }
                }
            }
            if  (numAdds == 1) {
                ulamNums.add(curNum);
                i++;
            }
        }
        return curNum;
    }

    public static String longestNonrepeatingSubstring(String str) {
        String longestSubstring = str.charAt(0) + "";
        String currentSubstring = longestSubstring;

        for (int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);
            int index = currentSubstring.indexOf(current);

            if (index < 0) {
                currentSubstring += current;
            } else {
                if (currentSubstring.length() > longestSubstring.length()) {
                    longestSubstring = currentSubstring;
                }

                currentSubstring = currentSubstring.substring(index + 1) + current;
            }
        }

        if (currentSubstring.length() > longestSubstring.length()) {
            return currentSubstring;
        }

        return longestSubstring;
    }

    public static String convertToRoman(int num) {
        String fs = "";
        while(num != 0){
            if(num >= 1000){
                num -= 1000;
                fs += "M";
            }
            else if(num >= 900){
                num -= 900;
                fs += "CM";
            }
            else if(num >= 500){
                num -= 500;
                fs += "D";
            }
            else if(num >= 400){
                num -= 400;
                fs += "CD";
            }
            else if(num >= 100){
                num -= 100;
                fs += "C";
            }
            else if(num >= 90){
                num -= 90;
                fs += "XC";
            }
            else if(num >= 50){
                num -= 50;
                fs += "L";
            }
            else if(num >= 40){
                num -= 40;
                fs += "XL";
            }
            else if(num >= 10){
                num -= 10;
                fs += "X";
            }
            else if(num >= 9){
                num -= 9;
                fs += "IX";
            }
            else if(num >= 5){
                num -= 5;
                fs += "V";
            }
            else if(num >= 4){
                num -= 4;
                fs += "IV";
            }
            else if(num > 0){
                num -= 1;
                fs += "I";
            }
        }
        return fs;
    }

    public static boolean formula(String str) {
        String s = str.replaceAll(" ","");
        s = s.replaceAll("a", "4");
        if (str.contains("(")) {
            int d1 = str.indexOf("(");
            int d2 = str.indexOf(")");
            int v = getValue(str.substring(d1+1,d2));
            s = s.substring(0,d1) + v + s.substring(d2+1);
        }
        String[] s1 = s.split("[=]",0);
        for (int i = 0; i < s1.length-1; i++) {
            int v1 = getValue(s1[i]);
            int v2 = getValue(s1[i+1]);
            if (v1 != v2) return false;
        }
        return true;
    }
    public static int getValue(String s) {
        int n = 0;
        if (s.contains("*")) {
            String[] s1 = s.split("[*]",0);
            n = 1;
            for (int i = 0; i < s1.length; i++) n *= Integer.parseInt(s1[i]);
        }
        else if (s.contains("+")) {
            String[] s1 = s.split("[+]",0);
            n = 0;
            for (int i = 0; i < s1.length; i++) n += Integer.parseInt(s1[i]);
        }
        else if (s.contains("-")) {
            String[] s1 = s.split("[-]",0);
            n = Integer.parseInt(s1[0]);
            for (int i = 1; i < s1.length; i++) n -= Integer.parseInt(s1[i]);
        }
        else if (s.contains("/")) {
            String[] s1 = s.split("[/]",0);
            n = Integer.parseInt(s1[0]);
            for (int i = 1; i < s1.length; i++) n /= Integer.parseInt(s1[i]);
        }
        else n = Integer.parseInt(s);
        return n;
    }

    public static boolean palindromeDescendant(int num) {
        String ns = Integer.toString(num);
        String rs = "";
        for(int i = 0; i < ns.length(); i++) rs = ns.charAt(i) + rs;

        for(int i = 0; ns.length() >= 2; i++){
            if(ns.equals(rs)) return true;
            ns = "";
            for(int y = rs.length() - 1; y > 0; y = y - 2) ns += Character.getNumericValue(rs.charAt(y)) + Character.getNumericValue(rs.charAt(y - 1));
            rs = "";
            for(int x = 0; x < ns.length(); x++) rs = ns.charAt(x) + rs;
        }
        return false;
    }
}
