import java.util.Scanner;
public class Lab1 {
    static public void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите координаты 1ой точки: ");
        double xCoord = in.nextDouble();
        double yCoord = in.nextDouble();
        double zCoord = in.nextDouble();
        Point3d point1 = new Point3d(xCoord, yCoord, zCoord);

        System.out.print("Введите координаты 2ой точки: ");
        xCoord = in.nextDouble();
        yCoord = in.nextDouble();
        zCoord = in.nextDouble();
        Point3d point2 = new Point3d(xCoord, yCoord, zCoord);

        System.out.print("Введите координаты 3ой точки: ");
        xCoord = in.nextDouble();
        yCoord = in.nextDouble();
        zCoord = in.nextDouble();
        Point3d point3 = new Point3d(xCoord, yCoord, zCoord);

        if (Point3d.areEqual(point1, point2) || Point3d.areEqual(point2, point3) || Point3d.areEqual(point3, point1))
        {
            System.out.print("Ошибка! Точки совпадают!");
            return;
        }
        System.out.print("Площадь треугольника = " + Lab1.computeArea(point1, point2, point3));
    }

    public static double computeArea(Point3d point1, Point3d point2, Point3d point3)
    {

        double a = point1.distance(point2);
        double b = point2.distance(point3);
        double c = point3.distance(point1);

        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
