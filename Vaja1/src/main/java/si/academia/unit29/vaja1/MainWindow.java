package si.academia.unit29.vaja1;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
// import java.nio.file.FileSystem;
// import java.util.Timer;
// import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Scanner;

public class MainWindow extends JFrame {

    File file;
    String name;
    graphPanel gPanel;
    bottomPanel bPanel;
    MenuBar menuBar;

    MainWindow(String name) {
        this.name = name;
        this.setTitle(name);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        // setLocationRelativeTo(null);
        // this.getContentPane().add(BorderLayout.NORTH, menuBar);

        run();
    }

    private void run() {
        gPanel = new graphPanel();
        bPanel = new bottomPanel();
        this.getContentPane().add(BorderLayout.CENTER, gPanel);
        this.getContentPane().add(BorderLayout.SOUTH, bPanel);
        this.setVisible(true);


        // LAMBDA EXPRESSION -> pom.xml -> change properties to 1.8
        // https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html
        bPanel.openBtn.addActionListener(listener -> {
            File path = new File("C:\\Users\\goraz\\IdeaProjects\\Unit29_Vaje\\Vaja1");
            JFileChooser chooser = new JFileChooser(path, FileSystemView.getFileSystemView()); // definiramo svojo mapo, rabimo 'path' !!
            // JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // definiramo chooser za HomeDirectory
            int opened = chooser.showOpenDialog(null);  // odpre Open dialog window (druga opcija -> Save dialog window)

            if (opened == JFileChooser.APPROVE_OPTION) {    // ce je izbira potrjena !!
                file = chooser.getSelectedFile();
                bPanel.txtField.setText(file.getName());
                bPanel.drawBtn.setEnabled(true);
                bPanel.resetBtn.setEnabled(true);
            }

        });

        bPanel.drawBtn.addActionListener(listener -> {
            ArrayList<Float> temps = new ArrayList<Float>();
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    temps.add(scanner.nextFloat());
                }
                scanner.close();
                // System.out.println("Temps -> " + temps);
                gPanel.setTemperatures(temps);   // prenesem v graphPanel array 'temperaturesArray' in poklicem 'repaint()'  !!
            } catch (FileNotFoundException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
            // bPanel.drawBtn.setEnabled(false);
            bPanel.drawBtn.setVisible(false);
            bPanel.toggleBtn.setVisible(true);
        });

        bPanel.toggleBtn.addActionListener(listener -> {
            System.out.println("toggle button");
            gPanel.toggleBoolean();
        });

        bPanel.resetBtn.addActionListener(listener -> {
            System.out.println("Reset");
            bPanel.txtField.setText("click on the Open-File button");
            bPanel.drawBtn.setVisible(true);
            bPanel.drawBtn.setEnabled(false);
            bPanel.toggleBtn.setVisible(false);
            bPanel.resetBtn.setEnabled(false);  // can't use this.setEnabled(false); !!

            ArrayList<Float> empty = new ArrayList<Float>();
            gPanel.setTemperatures(empty);
            gPanel.setBoolean();
        });

        bPanel.closeBtn.addActionListener(listener -> {
            System.exit(0);
        });

        // https://www.geeksforgeeks.org/java-swing-jfilechooser/
        menuBar.itemOpen.addActionListener(listener -> {
            System.out.println("MenuBar - Open");
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\goraz\\IdeaProjects\\Unit29_Vaje\\Vaja1");  // type in the path
            int dialog = fileChooser.showOpenDialog(null);
            if (dialog == JFileChooser.APPROVE_OPTION) {
                bPanel.txtField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                file = fileChooser.getSelectedFile();   // shranim v 'file', da lahko narisem graf
                bPanel.drawBtn.setEnabled(true);
                bPanel.resetBtn.setEnabled(true);
            } else {
                bPanel.txtField.setText("");
            }
        });
        menuBar.itemClose.addActionListener(listener -> {
            System.out.println("MenuBar - Close");
            System.exit(0);
        });

    }
}
