package si.academia.unit29.vaja1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

public class bottomPanel extends JPanel {
    JLabel label = new JLabel("Select File");
    JTextField txtField = new JTextField(20);

    JButton openBtn = new JButton("Open File");
    JButton drawBtn = new JButton("Draw graph");
    JButton resetBtn = new JButton("Reset graph");
    JButton closeBtn = new JButton("Close Window");

    JButton toggleBtn = new JButton("Toggle");

    bottomPanel() {
        this.add(label);
        this.add(txtField);

        this.add(openBtn);
        this.add(drawBtn);
        this.add(toggleBtn); toggleBtn.setVisible(false);   // gumb za spreminjanje grafa
        this.add(resetBtn);
        this.add(closeBtn);

        this.setBackground(new ColorUIResource(177, 158, 158));

        drawBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        txtField.setEditable(false);
        txtField.setText("click on the Open-File button");

    }
}
