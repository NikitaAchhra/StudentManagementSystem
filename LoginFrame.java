import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
class LoginFrame extends JFrame
{
Container c;
JLabel lbusername,lbpassword;
JButton btnsubmit;
JTextField txtusername;
JPasswordField txtpassword;
LoginFrame()
{
c=getContentPane();
c.setLayout(new BorderLayout());

JLabel background = new JLabel(new ImageIcon("book.jpg"));
add(background);
background.setLayout(null);

btnsubmit = new JButton("SUBMIT");
lbusername = new JLabel("Username: ");
lbpassword = new JLabel("Password: ");
txtusername = new JTextField(15);
txtpassword = new JPasswordField(20);
txtpassword.setEchoChar('*');
background.add(lbusername);
background.add(txtusername);
background.add(lbpassword);
background.add(txtpassword);
background.add(btnsubmit);



ActionListener a1 =(ae) ->
{
String s1,s2;
s1=txtusername.getText();
s2=txtpassword.getText();
if(s1.equals("system") && s2.equals("abc123"))
{

try 
   {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SOUND EFFECT TADA.wav").getAbsoluteFile( ));
    Clip clip = AudioSystem.getClip( );
    clip.open(audioInputStream);
    clip.start( );
   }
   catch(Exception ex)
   {
     System.out.println("Error with playing sound.");
     ex.printStackTrace( );
   }
JOptionPane.showMessageDialog(new JDialog(),"Successful Login");
Frame f = new Frame();
dispose();

}
else
{
   try 
   {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("accessdenied.wav").getAbsoluteFile( ));
    Clip clip = AudioSystem.getClip( );
    clip.open(audioInputStream);
    clip.start( );
   }
   catch(Exception ex)
   {
     System.out.println("Error with playing sound.");
     ex.printStackTrace( );
   }
JOptionPane.showMessageDialog(new JDialog(),"Unsuccessful Login");
txtusername.setText("");
txtusername.requestFocus();
txtpassword.setText("");
txtpassword.requestFocus();
}
};
btnsubmit.addActionListener(a1);

lbusername.setBounds(30,40,70,25);
txtusername.setBounds(150,40,120,25);
lbpassword.setBounds(30,90,65,25);
txtpassword.setBounds(150,90,120,25);
btnsubmit.setBounds(165,150,80,30);


setTitle("Student Management Sytsem");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


public static void main(String args[])
{
LoginFrame f = new LoginFrame();
}
}



class DbHandler
{
ResultSet rs;
public String viewStudent()
{
Connection con = null;
StringBuffer sb = new StringBuffer();
try
{
System.out.println("loading the driver");
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
System.out.println("driver loaded");

System.out.println("trying to connect");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
System.out.println("connected");

String sql = "select * from student order by rno";
Statement stmt = con.createStatement();
rs = stmt.executeQuery(sql);
while(rs.next())
{
int rno = rs.getInt(1);
String name = rs.getString(2);
int marks = rs.getInt(3);
float percentage = rs.getFloat(4);
String grade = rs.getString(5);
System.out.println("rno: "+rno+"name: "+name+"marks: "+marks+"percentage: "+percentage+"grade: "+grade);
sb.append(rno+"\t           "+name+"\t\t\t   "+marks+"\t\t "+percentage+"%"+"\t\t "+grade+"\n");
}
rs.close();
stmt.close();
}//end of try
catch(SQLException e)
{
System.out.println("issues "+ e);
}//end of catch
finally
{
try{
System.out.println("trying to close the connection ");
if(con!=null)
con.close();
System.out.println("connection closed");
}
catch(SQLException e)
{
System.out.println("closing issues "+e);
}
}//end of finally
return sb.toString();
}//end of veiwStudent()


public void addStudent(int rno,String name,int marks,float percentage,String grade)
{
Connection con = null;
int result=0;
try
{
System.out.println("loading the driver");
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
System.out.println("driver loaded");

System.out.println("trying to connect");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
System.out.println("connected");

String sql = "insert into student values(?, ?, ?, ?, ?)";
PreparedStatement pst = con.prepareStatement(sql);
pst.setInt(1,rno);
pst.setString(2,name);
pst.setInt(3,marks);
pst.setFloat(4,percentage);
pst.setString(5,grade);
try
{
result = pst.executeUpdate();
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"UNIQUE CONSTRAINT VIOLATION.");
}
System.out.println(result +" record inserted ");
JOptionPane.showMessageDialog(new JDialog(),result + " records inserted");
pst.close();
}//end of try
catch(SQLException e)
{
System.out.println("issues "+ e);
}//end of catch
finally
{
try{
System.out.println("trying to close the connection ");
if(con!=null)
con.close();
System.out.println("connection closed");
}
catch(SQLException e)
{
System.out.println("closing issues "+e);
}
}//end of finally
}//end of addStudent()

public void updateStudent(int srno,String sname,int smarks,float spercentage,String sgrade)
{
Connection con = null;
try
{
System.out.println("loading the driver");
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
System.out.println("driver loaded");

System.out.println("trying to connect");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
System.out.println("connected");


String sql = "Update student set name=?,marks=?,percentage=?,grade=? where rno=?";
PreparedStatement pst = con.prepareStatement(sql);
pst.setString(1,sname);
pst.setInt(2,smarks);
pst.setFloat(3,spercentage);
pst.setString(4,sgrade);
pst.setInt(5,srno);
int result = pst.executeUpdate();
if(result==0)
{
System.out.println(result +" record updated ");
JOptionPane.showMessageDialog(new JDialog(),"Sorry,Can't Update Record.");
}
else
{
JOptionPane.showMessageDialog(new JDialog(),result + " record updated");
}
pst.close();
}//end of try
catch(SQLException e)
{
System.out.println("issues "+ e);
JOptionPane.showMessageDialog(new JDialog(),"issues "+e);
}//end of catch
finally
{
try{
System.out.println("trying to close the connection ");
if(con!=null)
con.close();
System.out.println("connection closed");
}
catch(SQLException e)
{
System.out.println("closing issues "+e);
}
}//end of finally
}//end of updateStudent()


public void deleteStudent(int srno)
{
Connection con = null;
try
{
System.out.println("loading the driver");
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
System.out.println("driver loaded");

System.out.println("trying to connect");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
System.out.println("connected");

String sql = "delete from student where rno="+srno;
Statement stmt = con.createStatement();
int result = stmt.executeUpdate(sql);

if(result!=0)
{
 System.out.println(result +" record deleted ");
JOptionPane.showMessageDialog(new JDialog(),result + " records deleted");
}
else
{
JOptionPane.showMessageDialog(new JDialog(),"NO RECORD FOUND");
}
stmt.close();
}//end of try
catch(SQLException e)
{
System.out.println("issues "+ e);
}//end of catch
finally
{
try{
System.out.println("trying to close the connection ");
if(con!=null)
con.close();
System.out.println("connection closed");
}
catch(SQLException e)
{
System.out.println("closing issues "+e);
}
}//end of finally
}//end of deleteStudent()
}