package end_date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


@SuppressWarnings("serial")
public class ContentPaneEx extends JFrame{
	
	String PATTERN = "yyyy.MM.dd";
	int PLUS_NUM=26;
	
	int click_pattern=0;
	boolean STATUS_TOP=true;
	String STATUS_MSG="Y/TOP";
	
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;
	private JTextField jtf4;
	private JTextField jtf5;
	private JTextField jtf6;
	int i=0;
	private static Point point = new Point();

	ContentPaneEx(){
		//String a = "2015.04.21";
		this.setUndecorated(true);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
				}
			});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			   @Override
			   public void mouseDragged(MouseEvent e) {
			    Point p = getLocation();
			    setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			    
			   }
			 
		});
		String a = start_work_date();
		ContentPaneEx_sour(a);
	}
	
	public void ContentPaneEx_sour(String text_msg){
		setTitle("전역일 계산기!");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop( STATUS_TOP );
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(255,239,171));
		//contentPane.setBackground(new Color(240,240,240));
		//setOpacity(0.1f); //투명하게 하기
		contentPane.removeAll();
		
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();
		
		String currTime = getFormatDateString(PATTERN);
		
		String startTime = text_msg;
		//String startTime = start_work_date();
		String Enddate = end_Date(startTime,PATTERN);
		String pwd = Between_Date(startTime,currTime,PATTERN);
		String rem = Between_Date(currTime,Enddate,PATTERN);
		
		int total = Integer.parseInt(pwd)+Integer.parseInt(rem);
		int temp=Integer.parseInt(pwd);
		double per_date = (((double)temp / (double)total)*10000);
		temp = (int)per_date;		
		
		JProgressBar progress1;
		progress1 = new JProgressBar(0,10000);
		progress1.setBorder(new TitledBorder("전역 "+Enddate));
        progress1.setValue(temp);
        progress1.setStringPainted(true);
		
        contentPane.add(new JLabel("    < 전역일 계산기 >    "));
        
        JButton long_t = new JButton("현역근무");
        long_t.setSize(2,2);
        long_t.setLocation(20,20); 
        long_t.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(long_t);
        long_t.addActionListener(new long_thandler(startTime));
        
        JButton short_t = new JButton("보충역근무");
        short_t.setSize(2,2);
        short_t.setLocation(20,20); 
        short_t.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(short_t);
        short_t.addActionListener(new short_thandler(startTime));
        
        
        
        contentPane.setLayout(new GridLayout(6,1,0,0));
        contentPane.setForeground(Color.GRAY);
        
        contentPane.add(jtf1);
        jtf1.setHorizontalAlignment(JTextField.CENTER);
        jtf1.setText("산업체 시작일 : " + startTime);
        jtf1.setEditable(false);
        
        contentPane.add(jtf2);
        jtf2.setHorizontalAlignment(JTextField.CENTER);
        jtf2.setText("산업체 전역일 : " + Enddate);
        jtf2.setEditable(false);
        
        contentPane.add(jtf3);
        jtf3.setHorizontalAlignment(JTextField.CENTER);
        jtf3.setText("      현   재         : " + currTime);
        jtf3.setEditable(false);
        
        contentPane.add(jtf4);
        jtf4.setHorizontalAlignment(JTextField.CENTER);
        jtf4.setText("     총 복무일     :       " + Integer.toString(total)+"       ");
        jtf4.setEditable(false);
        
        contentPane.add(jtf5);
        jtf5.setHorizontalAlignment(JTextField.CENTER);
        //i++;
        //jtf5.setText("    현재복무일   :       " + (Integer.parseInt(pwd)+i)+"       ");
        jtf5.setText("    현재복무일   :       " + pwd+"       ");
        jtf5.setEditable(false);
        
        contentPane.add(jtf6);
        jtf6.setHorizontalAlignment(JTextField.CENTER);
        jtf6.setText("    남은복무일   :       " + rem+"       ");
        jtf6.setEditable(false);
        
        contentPane.setLayout(new FlowLayout());
        contentPane.add(progress1);
        
        
        
        JButton reload = new JButton("Reload");
        reload.setSize(2,2);
        reload.setLocation(20,20); 
        reload.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(reload);
        reload.addActionListener(new reloadhandler(startTime));
        
        //i++;
        //contentPane.add( new JLabel(Integer.toString(i)));
        
        
        JButton reset = new JButton("Reset");
        reset.setSize(2,2);
        reset.setLocation(20,20); 
        reset.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(reset);
        reset.addActionListener(new resetnhandler());
        
        JButton exit = new JButton("Exit");
        exit.setSize(2,2);
        exit.setLocation(20,20); 
        exit.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(exit);
        exit.addActionListener(new exithandler());
        
        JButton TOP = new JButton(STATUS_MSG);
        TOP.setSize(2,2);
        TOP.setLocation(20,20); 
        TOP.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(TOP);
        TOP.addActionListener(new TOPhandler(startTime));
		
		setResizable(false);
		setSize(180,300);
		setVisible(true);
		
	}
	
	public class TOPhandler implements ActionListener {
		String Date=null;
		public TOPhandler(String Date){
			this.Date = Date;		
		}
		@Override
		   public void actionPerformed(ActionEvent e) {
			click_pattern++;
			if(click_pattern%2==1){
				STATUS_TOP=false;
				STATUS_MSG="N/TOP";
			}
			else{
				STATUS_TOP=true;
				STATUS_MSG="Y/TOP";
			}
			ContentPaneEx_sour(Date);
			System.out.println("TOPhandler");
		}
		public String getString(){
			 return Date;			 
		}
	}
	
	
	public class long_thandler implements ActionListener {
		String Date=null;
		public long_thandler(String Date){
			this.Date = Date;		
		}
		@Override
		   public void actionPerformed(ActionEvent e) {
			 PLUS_NUM=34;
			 ContentPaneEx_sour(Date);
			 System.out.println("long_thandler");
		}
		public String getString(){
			 return Date;			 
		}
	}
	
	public class short_thandler implements ActionListener {
		String Date=null;
		public short_thandler(String Date){
			this.Date = Date;		
		} 
		@Override
		   public void actionPerformed(ActionEvent e) {
			 PLUS_NUM=26;
			 ContentPaneEx_sour(Date);
			 System.out.println("short_thandler");
		}
		 public String getString(){
			 return Date;			 
		}
	}
	
	public class exithandler implements ActionListener {
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 System.exit(0);
			 System.out.println("exithandler");
		}	
	}
	
	
	
	public class reloadhandler implements ActionListener {
		String Date=null;
		public reloadhandler(String Date){
			this.Date = Date;		
		}
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 ContentPaneEx_sour(Date);
			 System.out.println("reloadhandler");
		}
		 public String getString(){
			 return Date;			 
		}
	}
	
	public class resetnhandler implements ActionListener {
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 ContentPaneEx_sour(start_work_date());
			 System.out.println("resetnhandler");
		}	
	}
		
	
	public String Between_Date(String FromDate, String ToDate, String pattern){
		 
		String bet_date=null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date beginDate = null;
		try {
			beginDate = formatter.parse(FromDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date endDate = null;
		try {
			endDate = formatter.parse(ToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		 
		bet_date = Long.toString(diffDays+1);
		return bet_date;
	
	}
	public String end_Date(String startDate, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date beginDates = null;
		try {
			beginDates = format.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDates);
        
        calendar.add(Calendar.MONTH, PLUS_NUM);
        calendar.add(Calendar.DATE, -1);
        
        String output=format.format(calendar.getTime());
		return output;
	}
	public String getFormatDateString(String pattern ) {
		 SimpleDateFormat formatter = new SimpleDateFormat( pattern, Locale.KOREA );
	     String dateString = formatter.format(new Date());
	                    
	     return dateString;
	}
	public String start_work_date(){
		String msg =null;
		String temping=null;
		msg = JOptionPane.showInputDialog("산업체 시작일 : (yyyy.mm.dd)");
		temping=msg;
		if (Isright(temping)==0){
			JOptionPane.showMessageDialog(null, "       형식에 맞지 않습니다.\n          다시 입력해 주세요",
					"Message", JOptionPane.ERROR_MESSAGE);
			temping=start_work_date();
		};
		
		
		return convtostring(convtodate(temping));
	}
	
	public int Isright(String msg_re){
		int state=1;
		/*
		if(fst<1900 || fst>2100
				|| snd<1 || snd>12
				|| trd<1 || trd>31){
			state=0;
		}
		*/
		SimpleDateFormat fconv = new SimpleDateFormat(PATTERN);
		Date temp1 = null;
		try {
			temp1 = fconv.parse(msg_re);
		} catch (ParseException e1) {
			e1.printStackTrace();
			state=0;
			
		}
		System.out.println(temp1);
		return state;
	}
	/*String -> date*/
	public Date convtodate(String a){
		SimpleDateFormat d = new SimpleDateFormat(PATTERN);
		Date s = null;
		try {
			s = d.parse(a);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return s;
	}
	/*date -> string*/
	public String convtostring(Date a){
		SimpleDateFormat f = new SimpleDateFormat(PATTERN);
	    String dateString = f.format(a);
	                    
	    return dateString;
	}
	
	public static void main(String[] args){
		new ContentPaneEx();
	}

}
