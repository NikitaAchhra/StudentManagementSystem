import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lbrno;
JTextField txtrno;
JButton btndelete,btnback;
int rno=0;
DeleteFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

JLabel background = new JLabel(new ImageIcon("backgroundsame.jpg"));
add(background);
background.setLayout(null);

lbrno= new JLabel("ENTER RNO: ");
txtrno = new JTextField(5);
btndelete = new JButton ("Delete");
btnback =new JButton("Back");

lbrno.setBounds(50,30,80,30);
txtrno.setBounds(170,30,80,30);
btndelete.setBounds(110,100,80,30);
btnback.setBounds(210,100,80,30);

background.add(lbrno);
background.add(txtrno);
background.add(btndelete);
background.add(btnback);

ActionListener a1 =(ae) ->
{
Frame f = new Frame();
dispose();
};
btnback.addActionListener(a1);

ActionListener a2 = (ae) ->
{
try
{
rno = Integer.parseInt(txtrno.getText());
int output = JOptionPane.showConfirmDialog(new JDialog(),"ARE YOU SURE YOU WANT TO DELETE.");
if(output == JOptionPane.YES_OPTION)
{
new DbHandler().deleteStudent(rno);
txtrno.setText("");
txtrno.requestFocus();
}
else if(output == JOptionPane.NO_OPTION)
{
txtrno.setText("");
txtrno.requestFocus();
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"PLEASE! ENTER VALID ROLL-NO.");
txtrno.setText("");
txtrno.requestFocus();
}


};
btndelete.addActionListener(a2);

setSize(400,200);
setTitle("Student Management System");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[])
{
DeleteFrame df= new DeleteFrame();
}
}