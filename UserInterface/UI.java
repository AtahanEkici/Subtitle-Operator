package UserInterface;
import static Utility.Utilities.Operate;import java.awt.Desktop;
import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.io.File;import java.io.IOException;import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;import java.util.logging.Logger;import javax.swing.JButton;import javax.swing.JFileChooser;import javax.swing.JFrame;import javax.swing.JMenuBar;
import javax.swing.JOptionPane;import javax.swing.JTextPane;import javax.swing.UIManager;import javax.swing.UnsupportedLookAndFeelException;import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Atahan Ekici
 */
public final class UI extends JFrame implements ActionListener
{
    private static UI single_instance = null;
    
    public static UI getInstance()
    {
        if(single_instance == null)
        {
            single_instance = new UI();
        }
            return single_instance;    
    }
    
    private UI()
    {
        try
        {
            constructMainFrame();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,""+e+"","ERROR!",JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    // -------------------------- Swing Components ----------------------- //
    private static JFrame main;
    private static JButton Select_File,menu_button;
    private static JFileChooser jfc;
    private static JTextPane jtp;
    private static JMenuBar jmb;
     // -------------------------- Swing Components ----------------------- //
    
    private void constructMainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
       
       main = new JFrame("Subtitle Operator"); 
       main.setSize(300, 250);
       main.setLayout(null);
       main.setResizable(false);
       main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       jmb = new JMenuBar();
       main.setJMenuBar(jmb);
       
       menu_button = new JButton("GitHub");
       menu_button.setOpaque(true);
       menu_button.setFocusable(false);
       menu_button.addActionListener(this);
       
       jmb.add(menu_button);
       
       Select_File = new JButton("Select");
       Select_File.setSize(100,30);
       Select_File.setLocation(100,15);
       Select_File.addActionListener(this);
       
       jtp = new JTextPane();
       jtp.setText("Değiştirilecek Harfler:"
               + "\n ç->c      Ç->C"
               + "\n ğ->g      Ğ->G"
               + "\n ı->i      İ->I"
               + "\n ö->o      Ö->O"
               + "\n ş->s      Ş->S"
               + "\n ü->u      Ü->U");
       jtp.setSize(100,120);
       jtp.setLocation(100,50);
       
       main.setLocationRelativeTo(null);
       main.add(Select_File);
       main.add(jtp);
       main.setVisible(true);
    }
    
    private static void openFileChooser() throws IOException
    {
        File[] files;
        jfc = new JFileChooser(System.getProperty("user.home") +"/Desktop");
        jfc.setFileFilter(new FileNameExtensionFilter("Subtitles","srt"));
        jfc.setMultiSelectionEnabled(true);
        int result = jfc.showSaveDialog(null);
        files = jfc.getSelectedFiles();
         
        if(result == JFileChooser.APPROVE_OPTION)
        {
            for (File file : files)
            {
                Operate(file);
                JOptionPane.showMessageDialog(null,"Transaction Successfull","Success",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"File Selection error","ERROR!",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if(e.getSource() == Select_File)
      {
          try 
          {
            openFileChooser();
          } 
          catch (IOException ex) 
          {
            JOptionPane.showMessageDialog(null,""+ex+"",e.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE);
          }
      }
      else if(e.getSource() == menu_button)
      {
          if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
            {
                {
                    try 
                    {
                        Desktop.getDesktop().browse(new URI("https://github.com/AtahanEkici/Subtitle-Operator"));
                    } 
                    catch (URISyntaxException | IOException ex)
                    {
                       JOptionPane.showMessageDialog(null,""+ex+"",e.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE);
                    }
                }
}
      }
    }
}
