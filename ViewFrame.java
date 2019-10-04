import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame
{
Container c;
JButton btnback;
TextArea tadata;
ViewFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());
tadata = new TextArea(10,50);
String info="ROLL-N0         Name\t\t\t  Marks\t\tPercentage\tGrade\n";
tadata.setText(info);

JLabel background = new JLabel(new ImageIcon("background1.jpg"));
add(background);
background.setLayout(new FlowLayout());


background.add(tadata);
btnback = new JButton("Back");
background.add(btnback);


String data = new DbHandler().viewStudent();
tadata.append(data);

tadata.setEditable(false);
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
/*public static void main(String args[])
{
ViewFrame vf = new ViewFrame();
}*/
}
