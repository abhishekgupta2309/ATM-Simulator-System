package bank.management.system;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
      
    JPasswordField  pin ,repin ;
    JButton change , back;
    String pinnumber ;
    
    PinChange(String pinnumber){  
        this.pinnumber = pinnumber ;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900,Image.SCALE_DEFAULT) ;
        ImageIcon i3 = new ImageIcon(i2) ;
        JLabel image = new JLabel(i3) ;
        image.setBounds(0, 0,900, 900) ; 
        add(image) ;
        
        JLabel text = new JLabel ("CHANGE YOUR PIN  ") ;
        text.setFont(new Font("System", Font.BOLD , 16));
        text.setBounds(250,290, 500,35);
        text.setForeground(Color.WHITE);
        image.add(text);
        
        JLabel pintext = new JLabel ("New PIN : ") ;
        pintext.setFont(new Font("System", Font.BOLD , 16));
        pintext.setBounds(165,340, 180,25);
        pintext.setForeground(Color.WHITE);
        image.add(pintext);
        
         pin = new JPasswordField();
        pin.setFont(new Font("Ralway" , Font.BOLD,25)) ;
        pin.setBounds(330,340,180,25) ; 
        add(pin) ;
        
        JLabel repintext = new JLabel ("Re-Enter PIN : ") ;
        repintext.setFont(new Font("System", Font.BOLD , 16));
        repintext.setBounds(165,370, 180,25);
        repintext.setForeground(Color.WHITE);
        image.add(repintext);
        
         repin = new JPasswordField();
        repin.setFont(new Font("Ralway" , Font.BOLD,25)) ;
        repin.setBounds(330,370,180,25) ; 
        add(repin) ;
        
        
        change = new JButton("CHANGE");
        change.setBounds(355,485,150,30) ;
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(355,520,150,30) ;
        back.addActionListener(this);   
        image.add(back);
        
        setSize(900,900);
        setLocation(300,0);
//        setUndecorated(true) ;
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== change) {
        try{
            String npin = pin.getText();
            String rpin = repin.getText();
            
            if(!npin.equals(rpin)){
             JOptionPane.showMessageDialog(null, " Entered Pin does not Match ") ;
             return ;
           }
            
            if(npin.equals("")){
             JOptionPane.showMessageDialog(null, " Please Enter new Pin") ;
             return ;    
            }
            
            if(rpin.equals("")){
             JOptionPane.showMessageDialog(null, " Please Re-enter new Pin") ;
             return ;    
            }
            
            Conn conn = new Conn();
            String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"' " ;
            String query2 = "update login set pinnumber = '"+rpin+"' where pinnumber = '"+pinnumber+"' " ;
            String query3 = "update signupthree set pinnumber = '"+rpin+"' where pinnumber = '"+pinnumber+"' " ;

            conn.s.executeUpdate(query1) ;
            conn.s.executeUpdate(query2) ;
            conn.s.executeUpdate(query3) ;
            
            JOptionPane.showMessageDialog(null, "Pin Change Succesfully !!") ;

            setVisible(false);
            new Transactions(rpin).setVisible(true);
            
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
        else {
            setVisible(false) ;
            new Transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
