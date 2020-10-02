package Utility;
import java.io.BufferedReader;import java.io.BufferedWriter;import java.io.File;import java.io.FileInputStream;
import java.io.FileNotFoundException;import java.io.FileOutputStream;import java.io.FileReader;import java.io.IOException;import java.io.InputStreamReader;
import java.io.OutputStreamWriter;import java.nio.ByteBuffer;import java.nio.charset.StandardCharsets;import javax.swing.JOptionPane;
/**
 *
 * @author Atahan Ekici
 */
public class Utilities 
{
    public static String encodeToUTF_8(String input) // Encodes a string to UTF-8 //
    {
      ByteBuffer buffer = StandardCharsets.UTF_8.encode(input); 
      String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
      return utf8EncodedString;
    }
/*
    public static void changeEncoding(File file)
    {
        String content = FileUtils.readFileToString(file, "ISO8859_1");
        FileUtils.write(file, content, "UTF-8");
    }
*/ 
   public static void transform(File source, String srcEncoding, String tgtEncoding) throws IOException 
   {
    try
    (
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(source), tgtEncoding)); 
    ) 
    {
        char[] buffer = new char[16384];
        int read;
        while((read = br.read(buffer)) != -1)
        bw.write(buffer, 0, read);
    } 
}
    
    public static void fileAppender(File file , String line)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            
            try (BufferedWriter writer = new BufferedWriter(osw)) 
            {
                writer.append(line);
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage()+"File-Writer failure","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String removeTurkishCharacters(String content)
    {
        content = content.replaceAll("ğ","g");
        content = content.replaceAll("Ğ","G");
        
        content = content.replaceAll("ç","c");
        content = content.replaceAll("Ç","C");
        
        content = content.replaceAll("ı","i");
        content = content.replaceAll("İ","I");
        
        content = content.replaceAll("ö","o");
        content = content.replaceAll("Ö","O");
        
        content = content.replaceAll("ş","s");
        content = content.replaceAll("Ş","S");
        
        content = content.replaceAll("ü","u");
        content = content.replaceAll("Ü","U");
        
        return content;
    }
    
    public static String readFile(File file) throws FileNotFoundException, IOException
    {
        StringBuilder stringBuilder;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
                {
                    stringBuilder = new StringBuilder();
                    String line;
                    String ls = System.getProperty("line.separator");
                    while ((line = reader.readLine()) != null)
                    {
                        stringBuilder.append(line);
                        stringBuilder.append(ls);
                    }
                    reader.close();
                }               
String content = stringBuilder.toString();
return content;
    }
    
    public static void Operate(File file) throws IOException
    {
        String contents = readFile(file);
        contents = removeTurkishCharacters(contents);
        contents = encodeToUTF_8(contents);
        fileAppender(file,contents);
    }
    
    public static void close()
    {
        System.gc();
    }
}