import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;
    
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.setRect(-2,-1.5,3,3);
    }
    
    public int numIterations(double x, double y)
    {
        int iter = 0;
        double zr = 0;
        double zm = 0;
        double zrNew = 0;
        double zmNew = 0;

        while (iter < MAX_ITERATIONS && zr * zr + zm * zm < 4)
        {
            zrNew = zr * zr - zm * zm + x;
            zmNew = 2 * zr * zm + y;
            zr = zrNew;
            zm = zmNew;
            iter += 1;
        }
        
        if (iter == MAX_ITERATIONS)
            return -1;

        return iter;
    }
}