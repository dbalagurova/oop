public class lab1pal {
    public static void main(String[] args)
    {
        for (int i = 0; i< args.length; i++)
        {
            String s = args[i];
            System.out.println(s + ": "+  isPalindrome(s));
        }

    }

    //возвращает строку

    public static String reverseString(String s)
    {
        String str = new String();
        for (int i=s.length() - 1; i >= 0; i--)
        {
            str += s.charAt(i);
        }
        return str;
    }

    //возвращает результат проверки на палиндром

    public static boolean isPalindrome(String s)
    {
        String reversed = reverseString(s);
        return s.equals(reversed);
    }
}
