import  java.security.MessageDigest;
import java.util.*;

public class Tasks5 {
    public static void main(String[] args) {
        System.out.println(encrypt("Hello"));
        int[] arr1 = { 72, 33, -73, 84, -12, -3, 13, -13, -68 };
        System.out.println(decrypt(arr1));
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(sumDigProd(16, 28));
        String[] arr = new String[]{"toe", "ocelot", "maniac"};
        System.out.println(String.join(", ", sameVowelGroup(arr)));
        System.out.println(validateCard(1234567890123456L));
        System.out.println(numToEng(0));
        System.out.println(getSha256Hash("password123"));
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(hexLattice(7));
    }

    public static String encrypt(String str) {
        int len = str.length();
        int charCode=0;
        int[] arrEncrypt = new int[len];
        for(int j=0;j<len;j++) {
            arrEncrypt[j]=str.charAt(j)-charCode;
            charCode=str.charAt(j);
        }
        String intArrayString = Arrays.toString(arrEncrypt);
        return intArrayString;
    }

    public static String decrypt(int[] arr) {
        String decoded = (char) arr[0] + "";

        for (int i = 1; i < arr.length; i++) {
            decoded += (char) (arr[i] + decoded.charAt(i - 1));
        }

        return decoded;
    }

    public static boolean canMove(String piece, String current, String target)
    {
        boolean flag=false;
        String a=current.substring(0,1);
        int b=Integer.parseInt(current.substring(1));
        String c=target.substring(0,1);
        int d=Integer.parseInt(target.substring(1));
        if(piece.equals("Pawn"))
        {
            if(a.equals(c) && (b+1==d || b+2==d))
                flag=true;
        }
        else if(piece.equals("Knight"))
        {
            if((int)Math.abs((int)a.charAt(0)-(int)c.charAt(0))==1 && (int)Math.abs(b-d)==2)
                flag=true;
        }
        else if(piece.equals("Bishop"))
        {
            if((int)Math.abs((int)a.charAt(0)-(int)c.charAt(0))==(int)Math.abs(b-d))
                flag=true;
        }
        else if(piece.equals("Rook"))
        {
            if(a.equals(c) || b==d)
                flag=true;
        }
        else if(piece.equals("Queen"))
        {
            if(((int)Math.abs((int)a.charAt(0)-(int)c.charAt(0))==(int)Math.abs(b-d)) || (a.equals(c) || b==d))
                flag=true;
        }
        else
        {
            if((int)Math.abs((int)a.charAt(0)-(int)c.charAt(0))==1 || (int)Math.abs(b-d)==1 || (((int)a.charAt(0)-(int)c.charAt(0))==1 && ((int)Math.abs(b-d)==1)))
                flag=true;
        }
        return flag;
    }

    public static boolean canComplete(String initial, String word) {
        int count = 0;
        for(int i = 0, j = 0; i < word.length(); i++){

            if(initial.toLowerCase().charAt(j) == word.toLowerCase().charAt(i)){
                count++;
                j++;
            }
        }

        if(count == initial.length())
            return true;
        else
            return false;
    }

    public static int sumDigProd(int...i) {
        int s=0;
        for (int n:i) s+=n;
        if (s<10) return s;
        int p=1;
        while (s>0) {
            p*=s%10;
            s/=10;
        }
        return sumDigProd(p);
    }

    public static String[] sameVowelGroup(String[] words)
    {
        if (words.length == 0)
            return new String[0];

        ArrayList<String> matchWords = new ArrayList<>();

        matchWords.add(words[0]);

        String glasnye = words[0].replaceAll("[^aeiou]","");

        for (int i = 1; i < words.length; i++)
            if (words[i].replaceAll("[^aeiou]|[" + glasnye + "]", "").isEmpty())
                matchWords.add(words[i]);
        return matchWords.toArray(new String[0]);
    }

    public static boolean validateCard(long num)
    {
        final String N = Long.toString(num);
        // Check length
        num = N.length();
        if(num < 14 || num > 19) return false;
        // Luhn test
        short sum = 0;
        boolean odd = true;
        for(int i = (int)num - 2; i >= 0; --i) {
            num = N.charAt(i) - '0';
            if(odd) {
                num *= 2;
                if(num >= 10) num -= 9;
            }
            sum += num;
            odd = !odd;
        }
        return 10 - sum % 10 == N.charAt(N.length() - 1) - '0';
    }

    public static String numToEng(int n) {
        final String[] NUMS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        final String[] TENS2 = {"", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        final String[] TENS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String ret = "";
        if (n == 0) {
            return NUMS[0];
        }
        if (n >= 100) {
            ret += NUMS[n/100] + " hundred";
            n = n%100;
            if (n != 0) {
                ret += " ";
            }
        }
        if (n >= 20) {
            ret += TENS2[n/10];
            n = n%10;
            if (n != 0) {
                ret += " ";
            }
        } else if (n >= 10) {
            ret += TENS[n%10];
            n = 0;
        }
        if (n > 0) {
            ret += NUMS[n];
        }
        return ret;
    }

    public static String getSha256Hash(String str) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String correctTitle(String str) {
        String[] words = str.split(" ");
        String output = "";
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                output += " ";
            }
            String[] wordsNoHyphens = words[i].split("-");
            for (int j = 0; j < wordsNoHyphens.length; j++) {
                if (j > 0) {
                    output += "-";
                }
                if (wordsNoHyphens[j].equalsIgnoreCase("and")
                        || wordsNoHyphens[j].equalsIgnoreCase("the")
                        || wordsNoHyphens[j].equalsIgnoreCase("of")
                        || wordsNoHyphens[j].equalsIgnoreCase("in")) {
                    output += wordsNoHyphens[j].toLowerCase();
                } else {
                    output += wordsNoHyphens[j].substring(0, 1).toUpperCase();
                    output += wordsNoHyphens[j].substring(1).toLowerCase();
                }
            }
        }
        return output;
    }

    public static String hexLattice(int n) {
        int i = 0;
        boolean isHexLattice = false;
        while (3*i*(i+1)+1 <= n){
            if (3*i*(i+1)+1 == n) isHexLattice = true;
            i++;
        }
        String str = "";
        if (isHexLattice){
            int l = i;
            int m = i;
            String str2;
            for (int j = 0; j < 2*i-1; j++){
                str += "\n";
                str2 = "";
                for (int k = 1; k < m; k++){
                    str2 +=  " ";
                }
                str += str2;
                for (int k = 0; k < l; k++){
                    str +=  " o";
                }
                str += str2 + " ";
                l += (j < i-1) ? 1 : -1;
                m += (j < i-1) ? -1 : 1;
            }
            str = str.replaceFirst("\n", "");
            return str;
        } else return "Invalid";
    }

}
