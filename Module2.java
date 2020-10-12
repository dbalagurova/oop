public class Module2 {

        public static void main(String[] args) {
            System.out.println(task1("cat", 3));
            int [] array = {1,3,5};
            System.out.println(task2(array));
            System.out.println(task3(array));
            System.out.println(task4(array));
            System.out.println(task5("505.600000"));
            System.out.println(task6(7));
            System.out.println(task7("8987a"));
            System.out.println(task8("cat", "tic"));
            System.out.println(task91("automation", "auto-"));
            System.out.println(task91("phobia", "-bia"));
            System.out.println(task10(2));
        }
        public static String task1(String a, int b) {

            String outS = "";
            for (int i = 0; i < a.length(); i++) {
                for (int j = 0; j < b; j++){
                    outS += a.charAt(i);
                }
            }
            return outS;
        }

        public static int task2(int[] array)
        {
            if (array.length == 0)
                return 0;

            int max = array[0];
            int min = array[0];

            for (int i = 1; i < array.length; i++){
                if (array[i] > max){
                    max = array[i];
                }
                if (array[i] < min){
                    min = array[i];
                }
            }
            return max - min;
        }
        public static boolean task3(int[] array)
        {
            int sum = 0;
            for (int i = 0; i < array.length; i++){
                sum += array[i];
            }
            return (sum % array.length == 0);
        }
        public static int[] task4(int[] array)
        {
            int [] arraySum = new int[array.length];
            int sum = 0;
            for (int i = 0; i < array.length; i++){
                arraySum[i] = array[i] + sum;
                sum += array[i];
            }
            return arraySum;
        }
        public static int task5(String s)
        {
            int dotpos = s.lastIndexOf(".");
            if (dotpos == -1){
                return 0;
            } else {
                return s.length() - dotpos - 1;
            }
        }
        public static int task6(int n)
        {
            //1 1 2 3 5 8 13 21
            int fib = 1;
            int pred = 1;
            for (int i = 0; i <= n; i++){
                int a = fib;
                fib += pred;
                pred = a;
            }
            return fib;
        }
        public static boolean task7(String s)
        {
            if (s.length() < 5)
                return false;

            for (int i = 0; i < s.length(); i++){
                if (!Character.isDigit(s.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        public static boolean task8(String s1, String s2)
        {
            return (s1.charAt(0) == s2.charAt(s2.length() - 1)) & (s2.charAt(0) == s1.charAt(s1.length() - 1));
        }
        public static boolean task91(String s, String s1)
        {
            return s.substring(0, s1.length() - 2).equals(s1.substring(0,s1.length() - 2));
        }
        public static boolean task92(String s, String s1)
        {
            return s.substring(s.length() - s1.length() + 1).equals(s1.substring(1));
        }

        public static int task10(int n)
        {
            int rez = 0;
            for (int i = 1; i <= n; i++){

                if (i % 2 == 1)
                    rez += 3;
                else
                    rez -= 1;
            }
            return rez;
        }

    }

