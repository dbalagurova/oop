import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tasks3 {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, 1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(28));
        System.out.println(flipEndChars("Cat, dog and mouse."));
        System.out.println(isValidHexCode("#CD5C57"));
        int[] arr1 = {1, 2, 3, 4, 4, 4};
        int[] arr2 = {2, 5, 7};
        System.out.println(same(arr1, arr2));
        System.out.println(isKaprekar(297));
        System.out.println(longestZero("01100001011000"));
        System.out.println(nextPrime(12));
        System.out.println(rightTriangle(3, 8, 4));
    }

    public static int solutions(int a, int b, int c)
    {
        int n = 0, discr;
        discr = b*b - 4*a*c;
        if (discr > 0)
            n = 2;
        if (discr == 0)
            n = 1;
        return n;
    }

    public static int findZip(String str)
    {
        String zip = "zip";
        int pos1 = str.indexOf(zip);
        if (pos1 == -1)
            return -1;
        return str.indexOf(zip, pos1 + 1);
    }

    public static boolean checkPerfect(int n)
    {
        int s = 0;
        for (int i = 1; i < n; i++)
        {
            if (n % i == 0)
                s += i;
        }
        return s == n;
    }

    public static String flipEndChars(String str)
    {
        if (str.length() < 2)
            return "Incompatible";

        char StartChar = str.charAt(0);
        char EndChar = str.charAt(str.length() - 1);

        if (StartChar == EndChar)
            return "Two's a pair";

        String s = EndChar + str.substring(2, str.length() - 2) + StartChar;
        return s;
    }

    public static boolean isValidHexCode(String str)
    {
        if (str.length() != 7 || str.charAt(0) != '#')
            return false;

        String Symbols = "abcdefABCDEF0123456789";
        for (int i = 1; i < 7; i++)
        {
            if (Symbols.indexOf(str.charAt(i)) == -1)
                return false;
        }
        return true;
    }

    public static boolean same(int[] arr1, int[] arr2)
    {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();

        for (int i = 0; i < arr1.length; i++)
        {
            if (!list1.contains(arr1[i]))
                list1.add(arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++)
        {
            if (!list2.contains(arr2[i]))
                list2.add(arr2[i]);
        }
        return list1.size() == list2.size();
    }

    public static boolean isKaprekar(int n)
    {
        int n2 = n * n;

        String sn = String.valueOf(n);
        String sn2 = String.valueOf(n2);

        while (sn.length() != sn2.length())
        {
            int l = sn2.length();

            String s1 = sn2.substring(0, l / 2);
            String s2 = sn2.substring(l / 2, sn2.length());

            n2 = Integer.parseInt(s1) + Integer.parseInt(s2);
            sn2 = String.valueOf(n2);
        }
        return n == n2;
    }

    public static String longestZero(String str)
    {
        String s0 = "";

        for (int i = 0; i < str.length(); i++)
        {
            if (!str.contains(s0 + "0"))
                return s0;
            s0 += "0";
        }
        return s0;
    }

    public static int nextPrime(int n)
    {
        int nnext = n;
        boolean isprime;

        do {
            nnext += 1;
            isprime = true;
            for (int j = 2; j < nnext; j++)
                if (nnext % j == 0) {
                    isprime = false;
                    break;
                }
        } while (!isprime);
        return nnext;
    }

    public static boolean rightTriangle(int a, int b, int c)
    {
        return ((a * a + b * b) == c * c) || ((a * a + c * c) == b * b) || ((b * b + c * c) == a * a);
    }
}
