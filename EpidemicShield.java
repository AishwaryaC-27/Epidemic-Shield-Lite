import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WavePanel extends JPanel {

    Color cream = new Color(250,245,235);
    Color maroonWave = new Color(120,20,30,60);

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(cream);
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.setColor(maroonWave);

        int[] x = {0,getWidth()/2,getWidth(),getWidth()};
        int[] y = {getHeight()/3,getHeight()/2,getHeight(),0};

        g2.fillPolygon(x,y,4);
    }
}

public class EpidemicShield extends JFrame implements ActionListener{

CardLayout card = new CardLayout();
JPanel main = new JPanel(card);

Color red = new Color(170,40,40);

JComboBox<String> area,season;

JTextField pharmacy,clinic;

JTextField school[] = new JTextField[6];
JTextField work[] = new JTextField[6];

JRadioButton satEnter = new JRadioButton("Enter Absentees");
JRadioButton satHoliday = new JRadioButton("Holiday");

JTextArea result;

public EpidemicShield(){

createWelcome();
createLocation();
createHealth();
createAbsentee();
createResult();

add(main);

setTitle("Epidemic Shield Lite");
setSize(700,520);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

void createWelcome(){

JPanel page = new WavePanel();
page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
page.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

page.add(Box.createVerticalGlue());

JLabel title = new JLabel("Epidemic Shield Lite");
title.setFont(new Font("Serif",Font.BOLD,34));
title.setAlignmentX(Component.CENTER_ALIGNMENT);

JLabel tagline = new JLabel("Early Signals. Smarter Surveillance.");
tagline.setFont(new Font("Serif",Font.ITALIC,14));
tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

JButton start = new JButton("Start Monitoring");
start.setBackground(red);
start.setForeground(Color.WHITE);
start.setAlignmentX(Component.CENTER_ALIGNMENT);

start.addActionListener(e -> card.show(main,"loc"));

page.add(title);
page.add(Box.createRigidArea(new Dimension(0,10)));
page.add(tagline);
page.add(Box.createRigidArea(new Dimension(0,25)));
page.add(start);

page.add(Box.createVerticalGlue());

main.add(page,"welcome");
}

void createLocation(){

JPanel page = new WavePanel();
page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
page.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

String areas[]={
"Whitefield","Yelahanka","BTM Layout","Indiranagar",
"Jayanagar","Rajajinagar","Hebbal","Banashankari",
"Hennur","Kalyan Nagar","Sarjapur","KR Puram"
};

String seasons[]={"Winter","Summer","Monsoon","Post-Monsoon"};

area = new JComboBox<>(areas);
season = new JComboBox<>(seasons);

area.setMaximumSize(new Dimension(200,30));
season.setMaximumSize(new Dimension(200,30));

JButton next = new JButton("Next");
next.setBackground(red);
next.setForeground(Color.WHITE);
next.setAlignmentX(Component.CENTER_ALIGNMENT);

next.addActionListener(e -> card.show(main,"health"));

page.add(Box.createVerticalGlue());

JLabel city = new JLabel("City: Bangalore");
city.setAlignmentX(Component.CENTER_ALIGNMENT);

JLabel areaLabel = new JLabel("Area");
areaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

JLabel seasonLabel = new JLabel("Season");
seasonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

page.add(city);
page.add(Box.createRigidArea(new Dimension(0,12)));

page.add(areaLabel);
page.add(area);

page.add(Box.createRigidArea(new Dimension(0,12)));

page.add(seasonLabel);
page.add(season);

page.add(Box.createRigidArea(new Dimension(0,20)));
page.add(next);

page.add(Box.createVerticalGlue());

main.add(page,"loc");
}

void createHealth(){

JPanel page = new WavePanel();
page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
page.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

pharmacy = new JTextField(3);
clinic = new JTextField(3);

JPanel row1 = new JPanel();
row1.setOpaque(false);

row1.add(new JLabel("Pharmacy Medicine Sales Count"));
row1.add(Box.createHorizontalStrut(10));
row1.add(pharmacy);

JPanel row2 = new JPanel();
row2.setOpaque(false);

row2.add(new JLabel("Clinic Visits"));
row2.add(Box.createHorizontalStrut(10));
row2.add(clinic);

JButton next = new JButton("Next");
next.setBackground(red);
next.setForeground(Color.WHITE);
next.setAlignmentX(Component.CENTER_ALIGNMENT);

next.addActionListener(e -> card.show(main,"abs"));

page.add(Box.createVerticalGlue());

page.add(row1);
page.add(Box.createRigidArea(new Dimension(0,12)));
page.add(row2);

page.add(Box.createRigidArea(new Dimension(0,25)));
page.add(next);

page.add(Box.createVerticalGlue());

main.add(page,"health");
}

void createAbsentee(){

JPanel page = new WavePanel();
page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
page.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

JPanel header = new JPanel();
header.setOpaque(false);

header.add(new JLabel("Day"));
header.add(Box.createHorizontalStrut(30));
header.add(new JLabel("School Absentees"));
header.add(Box.createHorizontalStrut(30));
header.add(new JLabel("Workplace Absentees"));

page.add(header);

String days[]={"Mon","Tue","Wed","Thu","Fri"};

for(int i=0;i<5;i++){

JPanel row=new JPanel();
row.setOpaque(false);

school[i]=new JTextField(3);
work[i]=new JTextField(3);

row.add(new JLabel(days[i]));
row.add(Box.createHorizontalStrut(20));
row.add(school[i]);
row.add(Box.createHorizontalStrut(40));
row.add(work[i]);

page.add(row);
}

ButtonGroup satGroup=new ButtonGroup();
satGroup.add(satEnter);
satGroup.add(satHoliday);

satEnter.setSelected(true);

school[5]=new JTextField(3);
work[5]=new JTextField(3);

satHoliday.addActionListener(e->{
school[5].setEnabled(false);
work[5].setEnabled(false);
});

satEnter.addActionListener(e->{
school[5].setEnabled(true);
work[5].setEnabled(true);
});

JPanel satRow=new JPanel();
satRow.setOpaque(false);

satRow.add(new JLabel("Sat"));
satRow.add(satEnter);
satRow.add(school[5]);
satRow.add(work[5]);
satRow.add(satHoliday);

page.add(satRow);

JPanel sunRow=new JPanel();
sunRow.setOpaque(false);

sunRow.add(new JLabel("Sun"));
sunRow.add(Box.createHorizontalStrut(25));
sunRow.add(new JLabel("Holiday"));
sunRow.add(Box.createHorizontalStrut(45));
sunRow.add(new JLabel("Holiday"));

page.add(sunRow);

JButton analyze=new JButton("Analyze");
analyze.setBackground(red);
analyze.setForeground(Color.WHITE);
analyze.setAlignmentX(Component.CENTER_ALIGNMENT);

analyze.addActionListener(this);

page.add(Box.createRigidArea(new Dimension(0,20)));
page.add(analyze);

main.add(page,"abs");
}

void createResult(){

JPanel page = new WavePanel();
page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
page.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

JLabel title = new JLabel("Analysis Result");
title.setFont(new Font("Serif",Font.BOLD,26));
title.setAlignmentX(Component.CENTER_ALIGNMENT);

result = new JTextArea(8,40);
result.setFont(new Font("Serif",Font.PLAIN,16));
result.setOpaque(false);
result.setEditable(false);
result.setLineWrap(true);
result.setWrapStyleWord(true);

JButton reason = new JButton("View Reasoning");
reason.setBackground(red);
reason.setForeground(Color.WHITE);
reason.setAlignmentX(Component.CENTER_ALIGNMENT);

reason.addActionListener(e -> {

JOptionPane.showMessageDialog(this,

"Detection considers multiple community indicators:\n\n"+
"• Pharmacy medicine purchase counts\n"+
"• Number of clinic visits\n"+
"• School absentee trends\n"+
"• Workplace absentee patterns\n"+
"• Seasonal disease risk\n\n"+
"A simultaneous increase across indicators may signal\n"+
"an early community outbreak."

);

});

page.add(title);
page.add(Box.createRigidArea(new Dimension(0,20)));
page.add(result);
page.add(Box.createRigidArea(new Dimension(0,20)));
page.add(reason);

main.add(page,"res");
}

public void actionPerformed(ActionEvent e){

int risk=0;

int pharm=Integer.parseInt(pharmacy.getText());
int clin=Integer.parseInt(clinic.getText());

if(pharm>40) risk+=2;
if(clin>50) risk+=2;

int schoolSum=0,workSum=0;

for(int i=0;i<5;i++){
schoolSum+=Integer.parseInt(school[i].getText());
workSum+=Integer.parseInt(work[i].getText());
}

if(!satHoliday.isSelected()){
schoolSum+=Integer.parseInt(school[5].getText());
workSum+=Integer.parseInt(work[5].getText());
}

if(schoolSum>100) risk+=2;
if(workSum>80) risk+=2;

String s=season.getSelectedItem().toString();

if(s.equals("Monsoon")||s.equals("Winter"))
risk+=1;

String msg;

if(risk<=3){

msg="Status: Normal\n\nCommunity health indicators are within normal limits.\nNothing to worry about at the moment.";

}
else if(risk<=6){

msg="Status: Warning\n\nSlight increase in illness indicators detected.\nMonitoring is recommended.";

}
else{

msg="Status: Possible Outbreak\n\nPrecautions:\n"+
"• Avoid crowded indoor spaces\n"+
"• Maintain good hand hygiene\n"+
"• Wear masks if symptomatic\n"+
"• Seek medical advice if symptoms persist";

}

result.setText(
"City: Bangalore\n"+
"Area: "+area.getSelectedItem()+"\n\n"+
"Risk Score: "+risk+"\n\n"+msg
);

card.show(main,"res");
}

public static void main(String[] args){

new EpidemicShield();

}
}