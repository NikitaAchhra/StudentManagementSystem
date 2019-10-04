import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class  Frame extends JFrame
{
JButton btnview,btnadd,btnupdate,btndelete;
Container c;
Frame()
{
c=getContentPane();
c.setLayout(new GridLayout(2,2));
btnview=new JButton("View");
btnadd=new JButton("Add");
btnupdate=new JButton("Update");
btndelete=new JButton("Delete");
btnview.setBackground(new Color(135,206,250));
btnview.setForeground(Color.blue);
btnview.setFont(new Font("Arial",Font.ITALIC,25));
btnupdate.setBackground(new Color(186,85,211));
//btnupdate.setForeground();
btnupdate.setFont(new Font("Arial",Font.ITALIC,25));
btnadd.setBackground(new Color(0,238,0));
btnadd.setForeground(new Color(34,139,34));
btnadd.setFont(new Font("Arial",Font.ITALIC,25));
btndelete.setBackground(new Color(154,205,50));
btndelete.setForeground(Color.yellow);
btndelete.setFont(new Font("Arial",Font.ITALIC,25));


btnview.setHorizontalAlignment(SwingConstants.LEFT);
btnview.setVerticalAlignment(SwingConstants.BOTTOM);
btnadd.setHorizontalAlignment(SwingConstants.RIGHT);
btnadd.setVerticalAlignment(SwingConstants.BOTTOM);
btnupdate.setHorizontalAlignment(SwingConstants.RIGHT);
btnupdate.setVerticalAlignment(SwingConstants.BOTTOM);
btndelete.setHorizontalAlignment(SwingConstants.LEFT);
btndelete.setVerticalAlignment(SwingConstants.BOTTOM);


c.add(btnadd);
c.add(btnview);
c.add(btnupdate);
c.add(btndelete);

ActionListener a1 =(ae) ->
{
AddFrame af = new AddFrame();
dispose();
};
ActionListener a2 =(ae) ->
{
ViewFrame vf = new ViewFrame();
dispose();
};
ActionListener a3 =(ae) ->
{
UpdateFrame uf = new UpdateFrame();
dispose();
};
ActionListener a4 =(ae) ->
{
DeleteFrame df = new DeleteFrame();
dispose();
};
btnadd.addActionListener(a1);
btnview.addActionListener(a2);
btnupdate.addActionListener(a3);
btndelete.addActionListener(a4);
setSize(400,400);
setTitle("Student Management System");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[])
{
Frame f = new Frame();
}
}