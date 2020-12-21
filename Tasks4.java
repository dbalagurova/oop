import java.util.*;
import java.util.Iterator;

public class Tasks4 {
    public static void main(String[] args) {
        System.out.println(bessieessay(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println(String.join(", ", split("()()()")));
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        double[] arr1 = {9, 17, 30, 1.5};
        System.out.println(overTime(arr1));
        System.out.println(BMI(205,73));
        System.out.println(bugger(39));
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(trouble(1222345, 12345));
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
    }

    public static String bessieessay(int n, int k, String str)
    {
        String words[] = str.split(" ");
        String text = "";
        String stroka = words[0];

        for (int i = 1; i < n; i++) {
            String word = words[i];
            if (stroka.length() + word.length() > k) {
                text = text + stroka + "\n";
                stroka = word;
            }
            else
                stroka = stroka + " " + word;
        }
        text += stroka;
        return text;
    }

    public static String[] split(String str) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> clusters = new ArrayList<>();

        int open=0;
        for (int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            sb.append(c);
            if (c == '(')
                open++;
            else
                open--;
            if (open == 0) {
                clusters.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return clusters.toArray(new String[clusters.size()]);
    }

    public static String toCamelCase(String str) {
        String []arr=str.split("_");
        String newstr=arr[0];
        for(int i=1;i<arr.length;i++) {
            newstr+=(arr[i].substring(0,1).toUpperCase()+arr[i].substring(1));
        }
        return newstr;
    }

    public static String toSnakeCase(String str) {
        String newstr="";
        for(int i=0;i<str.length()-1;i++) {
            if(str.substring(i,i+1).matches("[A-Z]")) {
                newstr=newstr + "_" + str.substring(i,i+1).toLowerCase();
            }else {
                newstr+=str.substring(i,i+1);
            }
        }
        newstr+=str.substring(str.length()-1);
        return newstr;
    }

    public static String overTime(double[] arr) {
        double sum = 0.0;
        for(double i=arr[0]; i<arr[1]; i+=0.25){
            if(i<17){
                sum += 0.25 * arr[2];
            }else if(i>=17){
                sum += 0.25 * arr[2] * arr[3];
            }
        }
        return String.format("$%.2f", sum);
    }

    public static String BMI(double weight, double height)
    {
        double BMI = weight / (height*height);
        String res = "";
        if (BMI < 18.5)
        {
            res = "Underweight";
        }
        if (BMI >= 18.5 && BMI < 25)
        {
            res = "Underweight";
        }
        if (BMI >= 25)
        {
            res = "Underweight";
        }
        String bmi = String.format("%.3f", BMI);
        return (res + bmi);
    }

    public static int bugger(int num)
    {
        if (num < 10) {
            return 0;
        }

        int acc = 1;
        while (num >  0) {
            acc *= num % 10;
            num = num / 10;
        }
        return 1 + bugger(acc);
    }

    public static String toStarShorthand(String str) {
        String m="";
        for(int i=0;i<str.length();i++){
            int c=1;
            for(int j=i+1;j<str.length();j++){
                if(str.charAt(i)==str.charAt(j)) {
                    i++;
                    c++;}

            }
            if(c>1) m+=str.charAt(i)+"*"+Integer.toString(c);
            else m+=str.charAt(i);
        }
        return m;
    }

    public static boolean doesRhyme(String str1, String str2)
    {
        String[] str1Arr = str1.toLowerCase().split(" ");
        String[] str2Arr = str2.toLowerCase().split(" ");
        return str1Arr[str1Arr.length - 1].replaceAll("[^aeiou]", "")
                .equals(str2Arr[str2Arr.length - 1].replaceAll("[^aeiou]", ""));
    }

    public static boolean trouble(long num1, long num2)
    {
        String numOne = Long.toString(num1);
        String numTwo = Long.toString(num2);
        for (int i = 2; i < numOne.length(); i++)
        {
            if (numOne.charAt(i) == numOne.charAt(i - 1) && numOne.charAt(i - 1) == numOne.charAt(i - 2))
            {
                for (int j = 1; j < numTwo.length(); j++)
                {
                    if (numTwo.charAt(j) == numTwo.charAt(j - 1) && numTwo.charAt(j) == numOne.charAt(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int countUniqueBooks(String str, char bookend) {
        int returnedNum = 0;
        boolean isOpened = false;
        HashSet<Character> set = new HashSet<Character>();
        for(int i = 0;i<str.length();i++){
            if(isOpened && str.charAt(i) != bookend){
                if(set.add(str.charAt(i))){
                    returnedNum++;
                }
            }
            if(str.charAt(i) == bookend){
                isOpened = !isOpened;
            }
        }
        return returnedNum;
    }
    
}
