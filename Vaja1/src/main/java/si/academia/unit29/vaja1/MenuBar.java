package si.academia.unit29.vaja1;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

    // JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem itemOpen = new JMenuItem("Open");
    JMenuItem itemClose = new JMenuItem("Close");

    MenuBar() {
        this.add(menu);
        menu.add(itemOpen);
        menu.add(itemClose);
    }
}
