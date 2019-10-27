/**
 * @(#)VoiceChat.java
 *
 *
 * @author 
 * @version 1.00 2019/5/30
 */

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
public class TextChat extends JFrame {

    public TextChat() {
    	super("TextChat");
    	
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    ControlPanel cPanel = new ControlPanel();
    panel.add(cPanel, BorderLayout.NORTH);   
    

    Container c = getContentPane();
    c.add(panel, BorderLayout.CENTER);

    }
    
    public static void main(String[] args) {

    TextChat window = new TextChat();
    window.setBounds(200, 200, 700, 500);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setContentPane(new ControlPanel());
    window.setVisible(true);
  }

    
    
    
}
