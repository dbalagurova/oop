import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.awt.image.*;

public class FractalExplorer
{
    private int displaySize;
    
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    private JComboBox myComboBox;
    private JButton saveButton;
    private JButton resetButton;

    private int rowsRemaining;

    private void enableUI(boolean val)
    {
        myComboBox.setEnabled(val);
        saveButton.setEnabled(val);
        resetButton.setEnabled(val);
    }

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
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ButtonHandler());
        
        display.addMouseListener(new MouseHandler());
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myComboBox = new JComboBox();
        myComboBox.addItem(new Mandelbrot());
        myComboBox.addItem(new Tricorn());
        myComboBox.addItem(new BurningShip());
        myComboBox.addActionListener(new ButtonHandler());
        
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Fractal:"));
        myPanel.add(myComboBox);
        myFrame.add(myPanel, BorderLayout.NORTH);
        
        saveButton = new JButton("Save");
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
        enableUI(false);

        for (int y = 0; y < displaySize; y++) {
            FractalWorker worker = new FractalWorker(y);
            worker.execute();
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
            if (rowsRemaining > 0)
                return;

            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
            
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
            
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            drawFractal();
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object> {

        private int yStroka;

        private int[] StrokaRGB;

        public FractalWorker(int y) {
            yStroka = y;
        }

        public Object doInBackground()
        {
            StrokaRGB = new int[displaySize];

            double xCoord;
            double yCoord;

            yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, yStroka);

            for (int x = 0; x < displaySize; x++)
            {
                xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration >= 0) {
                    float hue = 0.7f + (float) iteration / 200f;
                    StrokaRGB[x] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }
            return null;
        }

        public void done()
        {
            for (int x = 0; x < displaySize; x++)
                display.drawPixel(x, yStroka, StrokaRGB[x]);

            display.repaint(0, 0, yStroka, displaySize, 1);

            if (rowsRemaining-- < 1)
                enableUI(true);

        }
    }
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}