import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by UseR7 on 23.11.2016.
 */
public class FormPanel extends JPanel{

    // Parameters
    public final static String TITLE = "Swing Form Example";

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JPanel productFormPanel;
    private JLabel categoryLabel;
    private JTextField categoryField;
    private JButton saveButton;

    public FormPanel() {
        createGui();
    }

    private void createGui() {
        // Create main frame and set it up
        mainFrame = new JFrame(FormPanel.TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Let Frame to be closed on ESC:
        // for global Key listening use KeyboardFocusManager
        // It is needed due to focus-listen behavior
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                            System.exit(0);
                        }
                        return false;
                    }
                });

        // Add Components
        mainPanel = new JPanel(null);

        productFormPanel = new JPanel(new GridBagLayout());

        nameLabel = new JLabel("Name", JLabel.LEFT);
        productFormPanel.add(nameLabel, this.newLabelConstraints());

        nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        productFormPanel.add(nameField, this.newTextFieldConstraints());

        categoryLabel = new JLabel("Category", JLabel.LEFT);
        productFormPanel.add(categoryLabel, this.newLabelConstraints());

        categoryField = new JTextField();
        categoryLabel.setLabelFor(categoryField);
        productFormPanel.add(categoryField, this.newTextFieldConstraints());

        // Add a spacer to push all the form rows to the top of the window.
        GridBagConstraints spacer = new GridBagConstraints();
        spacer.fill = GridBagConstraints.BOTH;
        spacer.gridwidth = GridBagConstraints.REMAINDER;
        spacer.weighty = 1.0;
        productFormPanel.add(new JPanel(), spacer);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                final JDialog dialog = new JDialog(mainFrame, "Saving");
                dialog.setLayout(new FlowLayout());
                JLabel dialogLabel = new JLabel("Element has been saved", JLabel.CENTER);
                dialog.add(dialogLabel);
                JButton dialogButton = new JButton("OK");
                dialogButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                    }
                });
                dialog.add(dialogButton);
                dialog.setBounds(100, 100, 200, 80);
                dialog.setResizable(false);
                dialog.setVisible(true);
            }
        });
        productFormPanel.add(saveButton, this.newTextFieldConstraints());

        productFormPanel.setBorder(new TitledBorder("Form"));
        productFormPanel.setBounds(50, 50, 300, 300);
        mainPanel.add(productFormPanel);

        mainFrame.add(mainPanel);

        // Set up main frame's settings
        mainFrame.setBounds(100, 50, 400, 600);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    private GridBagConstraints newConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        // a little breathing room
        c.insets = new Insets(2, 2, 2, 2);
        return c;
    }


    private GridBagConstraints newLabelConstraints() {
        GridBagConstraints c = this.newConstraints();
        // right-align labels
        c.anchor = GridBagConstraints.LAST_LINE_END;
        // do not grow labels
        c.weightx = 0.0;
        return c;
    }

    private GridBagConstraints newTextFieldConstraints() {
        GridBagConstraints c = this.newConstraints();
        c.anchor = GridBagConstraints.BASELINE;
        // grow text fields horizontally
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        // text fields end a row
        c.gridwidth = GridBagConstraints.REMAINDER;
        return c;
    }
}