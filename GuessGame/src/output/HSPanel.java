package output;

import entry.GameFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HSPanel extends JPanel {
    DefaultTableModel tableModel ;
    JPanel inPanel;
    String[] columns;
    Object[][] data; 
    JTable table = null;
    public JPanel getHSPanel() {
        return inPanel;
    }

    public HSPanel(GameFrame mainFrame) {
        inPanel = new JPanel(new FlowLayout());
        data = new Object[0][0];

       // String imgPath = System.getProperty("user.dir") + "/img/Hearth.png";
       // inPanel.add(new JLabel(new ImageIcon(imgPath)));


           columns = new String[] {
            "name","level", "score", "time"
        };
        
         tableModel = new DefaultTableModel(data,columns);
        //create table with data
        table = new JTable(tableModel);
        

        //button1 = new JButton("new");
        JButton b  = new JButton("back");
        //button1.addActionListener(mainFrame);
        b.addActionListener(mainFrame);
        inPanel.add(table);
        
        inPanel.add(b);
    }

    public void setData(Object[][] data) {
        this.data = data;
        tableModel.setDataVector(data, columns);
        tableModel.fireTableDataChanged();
        table.updateUI();
        table.revalidate();
        table.repaint();
      
    }

}
