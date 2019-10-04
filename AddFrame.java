import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame
{
Container c;
JButton btnsave,btnback;
public JTextField txtname,txtrno,txtmarks,txtgrade,txtpercentage;
JLabel lbname,lbmarks,lbrno,lbgrade,lbpercentage,lb1,lb2,lb3;
AddFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

JLabel background = new JLabel(new ImageIcon("background1.jpg"));
add(background);
background.setLayout(null);

lbrno = new JLabel("ENTER ROLL-NO: ");
lbname = new JLabel("NAME: ");
lbmarks = new JLabel("MARKS OUT OF 500: ");
lbgrade = new JLabel("GRADE: ");
lbpercentage = new JLabel("PERCENTAGE: ");
txtrno = new JTextField(10);
txtname = new JTextField(30);
txtmarks = new JTextField(10);
txtgrade = new JTextField(10);
txtpercentage = new JTextField(20);
btnsave = new JButton ("Save");
btnback = new JButton("Back");
lb1 = new JLabel("*");
lb2 = new JLabel("*");
lb3 = new JLabel("*");
lb1.setForeground(Color.red);
lb2.setForeground(Color.red);
lb3.setForeground(Color.red);

lbrno.setBounds(30,30,110,20);
lb1.setBounds(140,30,10,20);
txtrno.setBounds(175,30,70,20);
lbname.setBounds(30,70,50,20);
lb2.setBounds(80,70,10,20);
txtname.setBounds(175,70,170,20);
lbmarks.setBounds(30,110,125,20);
lb3.setBounds(160,110,10,20);
txtmarks.setBounds(175,110,70,20);
lbpercentage.setBounds(30,150,100,20);
txtpercentage.setBounds(175,150,70,20);
lbgrade.setBounds(30,190,60,20);
txtgrade.setBounds(175,190,100,20);
btnsave.setBounds(110,250,80,30);
btnback.setBounds(210,250,80,30);
background.add(lbrno);
background.add(txtrno);
background.add(lbname);
background.add(txtname);
background.add(lbmarks);
background.add(txtmarks);
background.add(lbpercentage);
background.add(txtpercentage);
background.add(lbgrade);
background.add(txtgrade);
background.add(btnsave);
background.add(btnback);
background.add(lb1);
background.add(lb2);
background.add(lb3);

ActionListener a2 =(ae)->
{

String name="";
String grade="";
int marks=0,rno=0;
int output=JOptionPane.showConfirmDialog(new JDialog(),"ARE YOU REALLY WANT TO Save.");
if(output == JOptionPane.YES_OPTION)
{
try
{
rno=Integer.parseInt(txtrno.getText());
if(rno<0)
{
JOptionPane.showMessageDialog(new JDialog(),"ENTER POSITIVE INTEGER");
txtrno.setText("");
txtrno.requestFocus();
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"please,enter valid roll-no.");
txtrno.setText("");
txtrno.requestFocus();
}

if(!(txtname.getText().isEmpty()))
{
name = txtname.getText();
if(!(isAlpha(name)))
{
JOptionPane.showMessageDialog(new JDialog(),"PLEASE,ENTER VALID NAME");
txtname.setText("");
txtname.requestFocus();
}
else
{}
}
else
{
JOptionPane.showMessageDialog(new JDialog(),"PLEASE,ENTER NAME");
txtname.setText("");
txtname.requestFocus();
}


try
{
if(!(txtmarks.getText().isEmpty()))
{
marks =Integer.parseInt(txtmarks.getText());
if(marks<0 || marks>500)
{
JOptionPane.showMessageDialog(new JDialog(),"ENTER VALID MARKS");
txtmarks.setText("");
txtmarks.requestFocus();
}
}
else{
JOptionPane.showMessageDialog(new JDialog(),"PLEASE! ENTER MARKS");
txtmarks.setText("");
txtmarks.requestFocus();
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"please,enter valid marks");
txtmarks.setText("");
txtmarks.requestFocus();
}

float percentage = (marks/500.0f)*100;
txtpercentage.setText(Float.toString(percentage)+"%");
if(percentage>=75.0f)
	grade="DISTINCTION";
else if (percentage >= 60.0f)
	grade = "FIRST CLASS";
else if(percentage >= 50.0f)
	grade = "SECOND CLASS";
else if(percentage >= 40.0f)
	grade ="PASS";
else
	grade = "FAIL";
txtgrade.setText(grade);
if(rno > 0 && (marks>0 && marks<=500) && isAlpha(name)  && !(txtmarks.getText().isEmpty()) && !(txtrno.getText().isEmpty()) && !(txtname.getText().isEmpty()) )
{
new DbHandler().addStudent(rno,name,marks,percentage,grade);
}
else
{
if(rno < 0)
{
txtrno.setText("");
txtrno.requestFocus();
}
if(marks<0 || marks>500)
{
txtmarks.setText("");
txtmarks.requestFocus();
}
if(!(isAlpha(name)))
{
txtname.setText("");
txtname.requestFocus();
}

}
}

else if(output == JOptionPane.NO_OPTION){
txtrno.setText("");
txtrno.requestFocus();
txtname.setText("");
txtname.requestFocus();
txtmarks.setText("");
txtmarks.requestFocus();
txtpercentage.setText("");
txtpercentage.requestFocus();
txtgrade.setText("");
txtgrade.requestFocus();
}
};
btnsave.addActionListener(a2);

ActionListener a1 =(ae) ->
{
Frame f = new Frame();
dispose();
};

btnback.addActionListener(a1);
setSize(400,350);
setTitle("Student Management System");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static boolean isAlpha(String s)
{
return s!=null && s.matches("^[ a-zA-Z]+$");
}
public static void main(String args[])
{
AddFrame af = new AddFrame();
}
}