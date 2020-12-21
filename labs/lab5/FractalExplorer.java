import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;

public class FractalExplorer
{
    private int displaySize;
    
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    
    public FractalExplorer(int size) {

        displaySize = size;
        
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }
    
    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame myFrame = new JFrame("Fractal Explorer");
        
        myFrame.add(display, BorderLayout.CENTER);
        
        JButton resetButton = new JButton("Reset");
        
        resetButton.addActionListener(new ButtonHandler());
        
        display.addMouseListener(new MouseHandler());
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JComboBox myComboBox = new JComboBox();
        myComboBox.addItem(new Mandelbrot());
        myComboBox.addItem(new Tricorn());
        myComboBox.addItem(new BurningShip());
        myComboBox.addActionListener(new ButtonHandler());
        
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Fractal:"));
        myPanel.add(myComboBox);
        myFrame.add(myPanel, BorderLayout.NORTH);
        
        JButton saveButton = new JButton("Save");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);
        
        saveButton.addActionListener(new ButtonHandler());

        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }
    
    private void drawFractal()
    {
        double xCoord;
        double yCoord;

        for (int x = 0; x < displaySize; x++)
            for (int y = 0; y < displaySize; y++){
                
                xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                
                int iteration = fractal.numIterations(xCoord, yCoord);
                
                if (iteration == -1)
                    display.drawPixel(x, y, 0);
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        display.repaint();
    }

    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();
            
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
                
            }
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (command.equals("Save")) {
                
                JFileChooser myFileChooser = new JFileChooser();
                myFileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
                myFileChooser.setAcceptAllFileFilterUsed(false);
                
                if (myFileChooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
                    
                    java.io.File file = myFileChooser.getSelectedFile();

                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                    return;
            }
        }
    }
    
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
            
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
            
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            drawFractal();
        }
    }
    
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}