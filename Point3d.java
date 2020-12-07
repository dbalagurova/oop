public class Point3d {

    private double xCoord;
    private double yCoord;
    private double zCoord;

    public Point3d (double x, double y, double z)
    {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d () {
        this(0, 0, 0);
    }

    public double getX () {
        return xCoord;
    }
    public double getY () {
        return yCoord;
    }
    public double getZ () {
        return zCoord;
    }

    public void setX ( double val) {
        xCoord = val;
    }
    public void setY ( double val) {
        yCoord = val;
    }
    public void setZ ( double val) {
        zCoord = val;
    }

    static public boolean areEqual(Point3d point1, Point3d point2)
    {
        return (point1.getX() == point2.getX()) && (point1.getY() == point2.getY()) && (point1.getZ() == point2.getZ());
    }

    double distance(Point3d point)
    {
        double dx = this.xCoord - point.getX();
        double dy = this.yCoord - point.getY();
        double dz = this.zCoord - point.getZ();
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
}