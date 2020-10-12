public class Module1 {
    public static void main(String[] args)
    {
        System.out.println(task1(1, 3));
        System.out.println(task2(3,8));
        System.out.println(task3(3,8,2));
        System.out.println(task4(3,8,9 ));
        System.out.println(task5(3,8, 11));
        System.out.println(task6('A'));
        System.out.println(task7(3));
        System.out.println(task8(3,8));
        int [] array = {1,3,5};
        System.out.println(task9(array));
        System.out.println(task10(3,8, 4));
    }

    public static int task1(int a, int b)
    {
        return a % b;
    }

    public static int task2(int a, int b)
    {
        return (a * b) / 2;
    }
    public static int task3(int chickens, int cows, int pigs)
    {
        int chickenLegs = 2;
        int cowLegs = 4;
        int pigLegs = 4;
        return chickens * chickenLegs + cows * cowLegs + pigs * pigLegs;
    }
    public static boolean task4(int prob, int prize, int pay)
    {
        return (prob * prize > pay);
    }
    public static String task5(int a, int b, int N)
    {
        if (a + b == N)
            return "added";
        if (a / b == N)
            return "divide";
        if (a - b == N)
            return "subtracted";
        if (a * b == N)
            return "multiply";
        else return "none";
    }

    public static int task6(char s)
    {
        return (int)s;
    }
    public static int task7(int n)
    {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
    public static int task8(int a, int b)
    {
        return a + b - 1;
    }
    public static int task9(int[] array)
    {
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum += Math.pow(array[i], 3);
        }
        return sum;
    }
    public static boolean task10(int a, int b, int c)
    {
        return (( a * b) % c == 0);
    }
}
