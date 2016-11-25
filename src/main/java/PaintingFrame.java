import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by UseR7 on 18.11.2016.
 */
public class PaintingFrame extends JFrame {

    static JButton colorPicker;
    static JButton paintAll;
    static JButton paintAllWithImage;
    static JButton clearAll;
    static JColorChooser chooser;
    static JSlider slider;
    static FormPanel formPanel;
//    static JPaintingPanel paintingPanel;
    static JTable table;
    static JScrollPane paintingPanel;
    static Color color;
    static JButton enableAnimation;


    public PaintingFrame(String title) throws IOException {
        super(title);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chooser = new JColorChooser(Color.decode("#000000"));

        colorPicker = new JButton("Choose color");
        colorPicker.setBounds(0, 600, 130, 20);
        colorPicker.setFocusPainted(false);
        colorPicker.setVisible(true);

        paintAll = new JButton("Paint all");
        paintAll.setBounds(0, 660, 140, 20);
        paintAll.setFocusPainted(false);
        paintAll.setVisible(true);

        paintAllWithImage = new JButton("Paint all with image");
        paintAllWithImage.setBounds(0, 690, 150, 20);
        paintAllWithImage.setFocusPainted(false);
        paintAllWithImage.setVisible(true);

        clearAll = new JButton("clearAll");
        clearAll.setBounds(0, 720, 160, 20);
        clearAll.setFocusPainted(false);
        clearAll.setVisible(true);

        enableAnimation = new JButton("Animate");
        enableAnimation.setBounds(0, 750, 170, 20);
        enableAnimation.setFocusPainted(false);
        enableAnimation.setVisible(true);
//
//        paintingPanel = new JPaintingPanel();
//        paintingPanel.setBounds(0, 0, 700, 600);
//        paintingPanel.setVisible(true);

//        pane = new PaintPane();
//        pane.setBounds(0, 0, 600, 500);
//        pane.setVisible(true);



        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][] { {"name"}, {"name2"}, {"name3"}},
                new Object[] {  "name" ,
                 "surname" , "number", "choice" , "gender", "Button" });


        table = new JTable(dm);
        table.getColumn("Button").setCellRenderer(new ButtonRenderer());
        table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));

        paintingPanel = new JScrollPane(table);
        paintingPanel.setBounds(0, 0 , 600, 600);


        slider = new JSlider(0, 100);
        slider.setValue(0);
    }

    public static void main(String[] args) throws IOException {
        final PaintingFrame frame = new PaintingFrame("MyDrawable");


        frame.setResizable(true);
        frame.setLayout(null);
        frame.pack();
        frame.setSize(900, 850);
        frame.setVisible(true);

        final Container c = frame.getContentPane();

        c.add(colorPicker);

        //c.add(erase);

        c.add(paintAll);

        c.add(paintAllWithImage);

        c.add(paintingPanel);

        c.add(clearAll);

        c.add(enableAnimation);

        slider.setBounds(150, 600, 300, 20);
        c.add(slider);


//        colorPicker.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                color = chooser.showDialog(chooser, "Text", Color.black);
//                if(color!=null) {
//                    paintingPanel.setColor(color);
//                    System.out.println("Color is "+color);
//                }
//
//            }
//        });
//
//        paintAll.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (color!=null) {
//                    paintingPanel.setBackground(color);
//                    System.out.println("Setting up background to " + color);
//                }
//            }
//        });
//
//
//        clearAll.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                paintingPanel.clear();
//            }
//        });
//
////        enableAnimation.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                paintingPanel.actionPerformed(e);
////            }
////        });
//
//        slider.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                paintingPanel.setRadius(slider.getValue());
//                paintingPanel.repaint();
//            }
//        });
//
//        paintAllWithImage.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    BufferedImage image = ImageIO.read(new File("C:\\Users\\UseR7\\IdeaProjects\\Swing\\src\\main\\java\\Maroon-5-Logo.jpg"));
//                    paintingPanel.setBackgroundImage(image);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
    }
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    /**
     * @version 1.0 11/09/98
     */

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                formPanel = new FormPanel();
                formPanel.setBounds(650, 0, 200, 200);

            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
