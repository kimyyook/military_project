package end_date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JColorChooser;
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

import end_date.file_saveTEXT;

@SuppressWarnings("serial")
public class ContentPaneEx extends JFrame{
	String PATTERN = "yyyy.MM.dd";
	int PLUS_NUM=26;
	
	int click_pattern=0;
	static boolean STATUS_TOP=true;
	static String STATUS_toptext="YES";
	String STATUS_MSG="YES";
	
	static Color Frame_color = new Color(255,239,171);
	static Color Title_color = Color.BLACK;
	static Color Textbox_color = Color.BLACK;
	static Color Progressbar_color = Color.BLACK;
	static Color Buttonbox_color = Color.gray;
	static Color Buttontext_color = Color.yellow;
	
	static String title_fonttype="나눔손글씨 펜";
	static String text_fonttype="휴먼매직체";
	
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	JTextField jtf4;
	JTextField jtf5;
	JTextField jtf6;
	int i=0;
//	static String starttime_to="2015.04.21";
	static String starttime_to;
	private static Point point = new Point();
	private static int Fontsize = 13;
	private static int title_Fontsize = 13;
	private static int text_Fontsize = 13;
	
	
	ContentPaneEx(){
		setSize(180,290);
//		setSize(180,310);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		this.setUndecorated(true);
		
		file_saveTEXT.setFilename("default");
		if(file_saveTEXT.is_File()==1){
			String load_string[] = new String[10];
			file_saveTEXT.lineRead();
			load_string=file_saveTEXT.getLoadData();
			starttime_to=load_string[0];
		}
		else{
			starttime_to=start_work_date();
			String[] save={"시작일 : "+starttime_to,
					"제목 폰트 : "+title_fonttype,
					"텟트스 폰트 : "+text_fonttype,
					"배경색 : "+Frame_color.toString(),
					"제목글자색 : "+Title_color.toString(),
					"텍스트색 : "+Textbox_color.toString(),
					"복무율 색 : "+Progressbar_color.toString(),
					"버튼 배경색 : "+Buttonbox_color.toString(),
					"버튼 글자색 : "+Buttontext_color.toString(),
					"항상보이기 : "+STATUS_toptext
			};
			file_saveTEXT a = new file_saveTEXT(save,"default");
			a.fileWrite();
		}
		
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
		ContentPaneEx_sour(starttime_to);
	}
	public void JMenubar(final String date){
		JMenuItem frame_color = new JMenuItem("배경색");
		JMenuItem title_color = new JMenuItem("제목 글자색");
		JMenuItem textbox_color  = new JMenuItem("텍스트색");
		JMenuItem progressbar_color = new JMenuItem("복무율 색");
		JMenuItem buttonbox_color  = new JMenuItem("버튼 배경색");
		JMenuItem buttontext_color  = new JMenuItem("버튼 글자색");
		JMenuItem default_color  = new JMenuItem("기본설정 색");
		
		frame_color.setBackground(Frame_color);
		title_color.setBackground(Title_color);
		textbox_color.setBackground(Textbox_color);
		progressbar_color.setBackground(Progressbar_color);
		buttonbox_color.setBackground(Buttonbox_color);
		buttontext_color.setBackground(Buttontext_color);
		
		
		JMenuItem al_ontop = new JMenuItem(" "+STATUS_MSG);
		JMenuItem reload = new JMenuItem("새로고침");
		JMenuItem restart = new JMenuItem("재시작");
		JMenuItem EXIT = new JMenuItem("종료");
		//메뉴바-메뉴
		JMenu setting= new JMenu("설정");
		JMenu colors = new JMenu("색 변경");
		JMenu Top_Set = new JMenu("항상 보이기");
		JMenu set_Save = new JMenu("설정 저장");
		JMenu set_Load = new JMenu("설정 불러오기");
		
		colors.add(frame_color);
		colors.add(title_color);
		colors.add(textbox_color);
		colors.add(progressbar_color);
		colors.add(buttonbox_color);
		colors.add(buttontext_color);
		colors.addSeparator();
		colors.add(default_color);
		
		JMenu title_font = new JMenu("제목 폰트");
		JMenu textbox_font = new JMenu("텍스트 폰트");
		
		JMenuItem titlefont_nowtype = new JMenuItem(title_fonttype);
		titlefont_nowtype.setFont(new Font(title_fonttype, Font.PLAIN,Fontsize));
		JMenuItem titlefont_type1 = new JMenuItem("serif");
		JMenuItem titlefont_type2 = new JMenuItem("궁서");
		JMenuItem titlefont_type3 = new JMenuItem("나눔손글씨 펜");
		JMenuItem titlefont_type4 = new JMenuItem("나눔 고딕");
		JMenuItem titlefont_type5 = new JMenuItem("휴먼매직체");
		
		JMenuItem textfont_nowtype = new JMenuItem(text_fonttype);
		textfont_nowtype.setFont(new Font(text_fonttype, Font.PLAIN,Fontsize));
		JMenuItem textfont_type1 = new JMenuItem("serif");
		JMenuItem textfont_type2 = new JMenuItem("궁서");
		JMenuItem textfont_type3 = new JMenuItem("나눔손글씨 펜");
		JMenuItem textfont_type4 = new JMenuItem("나눔 고딕");
		JMenuItem textfont_type5 = new JMenuItem("휴먼매직체");
		
		titlefont_type1.setFont(new Font("serif", Font.PLAIN,Fontsize));
		titlefont_type2.setFont(new Font("궁서", Font.PLAIN,Fontsize));
		titlefont_type3.setFont(new Font("나눔손글씨 펜", Font.PLAIN,Fontsize));
		titlefont_type4.setFont(new Font("나눔고딕", Font.PLAIN,Fontsize));
		titlefont_type5.setFont(new Font("휴먼매직체", Font.PLAIN,Fontsize));
		
		textfont_type1.setFont(new Font("serif", Font.PLAIN,Fontsize));
		textfont_type2.setFont(new Font("궁서", Font.PLAIN,Fontsize));
		textfont_type3.setFont(new Font("나눔손글씨 펜", Font.PLAIN,Fontsize));
		textfont_type4.setFont(new Font("나눔고딕", Font.PLAIN,Fontsize));
		textfont_type5.setFont(new Font("휴먼매직체", Font.PLAIN,Fontsize));
		
		title_font.add(titlefont_nowtype);
		title_font.addSeparator();
		title_font.add(titlefont_type1);
		title_font.add(titlefont_type2);
		title_font.add(titlefont_type3);
		title_font.add(titlefont_type4);
		title_font.add(titlefont_type5);
		
		textbox_font.add(textfont_nowtype);
		textbox_font.addSeparator();
		textbox_font.add(textfont_type1);
		textbox_font.add(textfont_type2);
		textbox_font.add(textfont_type3);
		textbox_font.add(textfont_type4);
		textbox_font.add(textfont_type5);
		
		JMenuItem default_font = new JMenuItem("기본설정 폰트");
		JMenu fonts_set = new JMenu("폰트 변경");
		fonts_set.add(title_font);
		fonts_set.add(textbox_font);
		fonts_set.addSeparator();
		fonts_set.add(default_font);
		
		titlefont_type1.addActionListener(new fonthandler(date, 1));
		titlefont_type2.addActionListener(new fonthandler(date, 1));
		titlefont_type3.addActionListener(new fonthandler(date, 1));
		titlefont_type4.addActionListener(new fonthandler(date, 1));
		titlefont_type5.addActionListener(new fonthandler(date, 1));
		
		textfont_type1.addActionListener(new fonthandler(date, 2));
		textfont_type2.addActionListener(new fonthandler(date, 2));
		textfont_type3.addActionListener(new fonthandler(date, 2));
		textfont_type4.addActionListener(new fonthandler(date, 2));
		textfont_type4.addActionListener(new fonthandler(date, 2));
		
		default_font.addActionListener(new fonthandler(date, 0));
		
		JMenuItem save1 = new JMenuItem("저장_1");
		JMenuItem save2 = new JMenuItem("저장_2");
		JMenuItem save3 = new JMenuItem("저장_3");
		
		JMenuItem load1 = new JMenuItem("저장_1");
		JMenuItem load2 = new JMenuItem("저장_2");
		JMenuItem load3 = new JMenuItem("저장_3");
		JMenuItem load_default = new JMenuItem("초기화");
		
		set_Save.add(save1);
		set_Save.add(save2);
		set_Save.add(save3);
		
		set_Load.add(load1);
		set_Load.add(load2);
		set_Load.add(load3);
		set_Load.addSeparator();
		set_Load.add(load_default);
		
		//메뉴바-메뉴
		JMenu windows = new JMenu("창");
		windows.add(reload);
		windows.add(restart);
		windows.addSeparator();
		windows.add(EXIT);
		
		frame_color.addActionListener(new colorhandler(date));
		title_color.addActionListener(new colorhandler(date));
		textbox_color.addActionListener(new colorhandler(date));
		progressbar_color.addActionListener(new colorhandler(date));
		buttonbox_color.addActionListener(new colorhandler(date));
		buttontext_color.addActionListener(new colorhandler(date));
		default_color.addActionListener(new colorhandler(date));
		
		al_ontop.addActionListener(new TOPhandler(date));
		EXIT.addActionListener(new exithandler());
		reload.addActionListener(new reloadhandler(date));
		restart.addActionListener(new restartnhandler());
		
		save1.addActionListener(new savehandler(date,"저장 ver_1"));
		save2.addActionListener(new savehandler(date,"저장 ver_2"));
		save3.addActionListener(new savehandler(date,"저장 ver_3"));
		
		load1.addActionListener(new loadhandler(date,"저장 ver_1"));
		load2.addActionListener(new loadhandler(date,"저장 ver_2"));
		load3.addActionListener(new loadhandler(date,"저장 ver_3"));
		load_default.addActionListener(new loaddefaulthandler(date,"default"));
		
		Top_Set.add(al_ontop);
		
		setting.add(fonts_set);
		setting.add(colors);
		setting.addSeparator();
		setting.add(Top_Set);
		setting.addSeparator();
		setting.add(set_Save);
		setting.add(set_Load);
		
		//메뉴바
		JMenuBar JMenuBar = new JMenuBar();
		JMenuBar.add(setting);
		JMenuBar.add(windows);
		/*
		JMenuItem mnuFileSub_Sub1 = new JMenuItem("sub1");
		JMenu mnuFileSub = new JMenu("sub");
		JMenu mnuFile = new JMenu("file");
		mnuFileSub.add(mnuFileSub_Sub1);
		mnuFile.add(mnuFileSub);
		JMenuBar.add(mnuFile);
		*/
		this.setJMenuBar(JMenuBar);
	}
	
	public void ContentPaneEx_sour(String text_msg){
		setTitle("전역일 계산기!");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop( STATUS_TOP );
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Frame_color);
		//contentPane.setForeground(Color.gray);
		//contentPane.setBackground(new Color(240,240,240));
		//setOpacity(0.1f); //투명하게 하기
		contentPane.removeAll();
		
		jtf1 = new JTextField(50);
		jtf2 = new JTextField(50);
		jtf3 = new JTextField(50);
		jtf4 = new JTextField(50);
		jtf5 = new JTextField(50);
		jtf6 = new JTextField(50);
		
		jtf1.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		jtf2.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		jtf3.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		jtf4.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		jtf5.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		jtf6.setFont(new Font(text_fonttype, Font.PLAIN, text_Fontsize));
		
		jtf1.setForeground(Textbox_color);
		jtf2.setForeground(Textbox_color);
		jtf3.setForeground(Textbox_color);
		jtf4.setForeground(Textbox_color);
		jtf5.setForeground(Textbox_color);
		jtf6.setForeground(Textbox_color);
		
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
		TitledBorder titleborder = new TitledBorder("전역 "+Enddate);
		progress1.setBorder(titleborder);
        progress1.setValue(temp);
        progress1.setStringPainted(true);
        titleborder.setTitleFont(new Font("나눔고딕", Font.CENTER_BASELINE,
        		Fontsize-2));
        titleborder.setTitleColor(Progressbar_color);
        
        
        JMenubar(startTime);
        
        JLabel title = new JLabel("    < 병특 전역일 계산기 >    ");
        title.setFont(new Font(title_fonttype, Font.BOLD,  title_Fontsize+5));
        title.setForeground(Title_color);
        
        contentPane.add(title);
        
        
        JButton long_t = new JButton("현역근무");
        long_t.setFont(new Font("나눔고딕", Font.CENTER_BASELINE,Fontsize-2));
        long_t.setForeground(Buttontext_color);
        long_t.setBackground(Buttonbox_color);
        long_t.setSize(2,2);
        long_t.setLocation(20,20); 
        long_t.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(long_t);
        long_t.addActionListener(new long_thandler(startTime));
        
        JButton short_t = new JButton("보충역근무");
        short_t.setFont(new Font("나눔고딕", Font.CENTER_BASELINE,Fontsize-2));
        short_t.setForeground(Buttontext_color);
        short_t.setBackground(Buttonbox_color);
        short_t.setSize(2,2);
        short_t.setLocation(20,20); 
        short_t.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(short_t);
        short_t.addActionListener(new short_thandler(startTime));
        
        contentPane.setLayout(new GridLayout(6,1,0,0));
        contentPane.setForeground(Color.GRAY);
        
        contentPane.add(BorderLayout.CENTER,jtf1);
        jtf1.setHorizontalAlignment(JTextField.CENTER);
        jtf1.setText("산업체 시작일 : " + startTime);
        //jtf1.setBounds(x, y, width, height);
        jtf1.setEditable(false);
        
        contentPane.add(BorderLayout.CENTER,jtf2);
        jtf2.setHorizontalAlignment(JTextField.CENTER);
        jtf2.setText("산업체 전역일 : " + Enddate);
        jtf2.setEditable(false);
        
        contentPane.add(BorderLayout.CENTER,jtf3);
        jtf3.setHorizontalAlignment(JTextField.CENTER);
        jtf3.setText("현재 : " + currTime);
        jtf3.setEditable(false);
        
        contentPane.add(BorderLayout.CENTER,jtf4);
        jtf4.setHorizontalAlignment(JTextField.CENTER);
        jtf4.setText("총 복무일 : " + Integer.toString(total)+" 일");
        jtf4.setEditable(false);
        
        contentPane.add(BorderLayout.CENTER,jtf5);
        jtf5.setHorizontalAlignment(JTextField.CENTER);
        jtf5.setText("현재복무일 : " + pwd +" 일");
        jtf5.setEditable(false);
        
        contentPane.add(BorderLayout.CENTER,jtf6);
        jtf6.setHorizontalAlignment(JTextField.CENTER);
        jtf6.setText("남은복무일 : " + rem+" 일");
        jtf6.setEditable(false);
        
        contentPane.setLayout(new FlowLayout());
        contentPane.add(progress1);
       
//        JButton exit = new JButton("Exit");
//        exit.setSize(2,2);
//        exit.setLocation(20,20); 
//        exit.setMargin(new Insets(0, 0, 0, 0));
//        contentPane.add(exit);
//        exit.addActionListener(new exithandler());
        
		setResizable(false);
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
				STATUS_toptext="NO";
				STATUS_MSG="NO";
			}
			else{
				STATUS_TOP=true;
				STATUS_toptext="YES";
				STATUS_MSG="YES";
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
			 System.out.println("exithandler");
			 System.exit(0);
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
	
	public class restartnhandler implements ActionListener {
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 ContentPaneEx_sour(start_work_date());
			 System.out.println("restartnhandler");
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
	
	public class colorhandler implements ActionListener {
		String Date=null;
		public colorhandler(String Date){
			this.Date = Date;
		}  
		@Override
		   public void actionPerformed(ActionEvent e) {
			 String cmd = e.getActionCommand(); //메뉴 아이템의 이름 리턴
	            System.out.println(cmd);
	            if(cmd.equals("배경색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Frame_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("제목 글자색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Title_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("텍스트색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Textbox_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("복무율 색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Progressbar_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("버튼 배경색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Buttonbox_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("버튼 글자색")) { // Color 메뉴 아이템의 경우
	                // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아온다.                
	                Color selectedColor = JColorChooser.showDialog(null,"Color",Color.YELLOW);
	                
	                // 사용자가 취소 버튼을 누르거나 그냥 다이얼로그를 닫는 경우에 selectedColor는 null이다.
	                if(selectedColor != null) 
	                	Buttontext_color=selectedColor; // 사용자가 선택한 색을 문자열 색으로 변경한다.
	            }
	            else if(cmd.equals("기본설정 색")){
	            	Frame_color = new Color(255,239,171);
	            	Title_color = Color.BLACK;
	            	Textbox_color = Color.BLACK;
	            	Progressbar_color = Color.BLACK;
	            	Buttonbox_color = Color.gray;
	            	Buttontext_color = Color.yellow;	       
	            }
	            ContentPaneEx_sour(Date);
			 System.out.println("colorhandler");
		}
		 public String getString(){
			 return Date;			 
		}
	}
	
	public class fonthandler implements ActionListener {
		String Date=null;
		int type =0;
		public fonthandler(String Date, int type){
			this.Date = Date;
			this.type = type;
		}  
		@Override
		   public void actionPerformed(ActionEvent e) {
			 String cmd = e.getActionCommand(); 
	         System.out.println(cmd);
	         if(cmd.equals("기본설정 폰트")){
	          	title_fonttype = "나눔손글씨 펜";
	           	text_fonttype = "휴먼매직체";
	           	System.out.println("title_fonttype :"+title_fonttype+
	           			"\ntext_fonttype :"+ text_fonttype);
	           	title_Fontsize=13;
	        
	         }
	         else{ 
	          	type_divide(cmd,type);
	         }
	         ContentPaneEx_sour(Date);
			 System.out.println("fonthandler");
		}
		 public String getString(){
			 return Date;			 
		}
		 public int getint(){
			 return type;			 
		}
		public void type_divide(String font_name,int type){
			if(type==1){//title
				title_fonttype=font_name;
				if(font_name.equals("serif")){
					title_Fontsize=9;
					//default=13+5
				}
				else if(font_name.equals("궁서")){
					title_Fontsize=10;
					//default=13+5
				}
				else if(font_name.equals("나눔 고딕")){
					title_Fontsize=10;
					//default=13+5
				}
        	}
        	else if(type==2){//text
        		text_fonttype=font_name;
        		if(font_name.equals("serif")){
        			text_Fontsize=12;
					//default=13
				}
				else if(font_name.equals("궁서")){
					text_Fontsize=12;
					//default=13
				}
				else if(font_name.equals("나눔손글씨 펜")){
					text_Fontsize=14;
					//default=13
				}
				else if(font_name.equals("나눔 고딕")){
					text_Fontsize=12;
					//default=13
				}
        	}
		}
	}
	public class savehandler implements ActionListener {
		String Date=null;
		String filename=null;
		String[] save={"시작일 : "+starttime_to,
				"제목 폰트 : "+title_fonttype,
				"텟트스 폰트 : "+text_fonttype,
				"배경색 : "+Frame_color.toString(),
				"제목글자색 : "+Title_color.toString(),
				"텍스트색 : "+Textbox_color.toString(),
				"복무율 색 : "+Progressbar_color.toString(),
				"버튼 배경색 : "+Buttonbox_color.toString(),
				"버튼 글자색 : "+Buttontext_color.toString(),
				"항상보이기 : "+STATUS_toptext
		};
		public savehandler(String Date, String filename){
			this.Date = Date;	
			this.filename = filename;
		}
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 file_saveTEXT a = new file_saveTEXT(save,filename);
			 a.fileWrite();
			 ContentPaneEx_sour(Date);
			 System.out.println("savehandler");
		}
		 public String getString(){
			 return Date;			 
		}
	}
	
	public class loadhandler implements ActionListener {
		String Date=null;
		String filename=null;
		String set_String[] = new String[10];
		public loadhandler(String Date, String filename){
			this.Date = Date;	
			this.filename = filename;
		}
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 file_saveTEXT a = new file_saveTEXT(filename);
			 a.lineRead();
//			 a.getLoadData();
			 set_String=a.getLoadData();
			 load_setting();
			 ContentPaneEx_sour(Date);
			 System.out.println("loadhandler");
		}
		 public String getString(){
			 return Date;			 
		}
		 public void load_setting(){
			starttime_to=set_String[0];
			title_fonttype=set_String[1];
			text_fonttype=set_String[2];
			Frame_color = new Color(split_colorset(set_String[3])[0],
					split_colorset(set_String[3])[1],
					split_colorset(set_String[3])[2]);
			Title_color = new Color(split_colorset(set_String[4])[0],
					split_colorset(set_String[4])[1],
					split_colorset(set_String[4])[2]);
			Textbox_color = new Color(split_colorset(set_String[5])[0],
					split_colorset(set_String[5])[1],
					split_colorset(set_String[5])[2]);
			Progressbar_color = new Color(split_colorset(set_String[6])[0],
					split_colorset(set_String[6])[1],
					split_colorset(set_String[6])[2]);
			Buttonbox_color = new Color(split_colorset(set_String[7])[0],
					split_colorset(set_String[7])[1],
					split_colorset(set_String[7])[2]);
			Buttontext_color = new Color(split_colorset(set_String[8])[0],
					split_colorset(set_String[8])[1],
					split_colorset(set_String[8])[2]);
			 if(set_String[9].equals("YES")){
				 STATUS_TOP=true;
			 }
			 else if(set_String[9].equals("NO")){
				 STATUS_TOP=false;
			 }
		 }
		 public int[] split_colorset(String input_colorset){
			 String temp_colorString;
			 int red;
			 int green;
			 int blue;
			 int color[]=new int[3];
			 temp_colorString=input_colorset;
			 System.out.println(input_colorset);
			 red=Integer.parseInt((temp_colorString.split("=")[1]).split(",")[0]);
			 System.out.println(red);
			 green=Integer.parseInt((temp_colorString.split("=")[2]).split(",")[0]);
			 System.out.println(green);
			 blue=Integer.parseInt((temp_colorString.split("=")[3]).split("]")[0]);
			 System.out.println(blue);
			 
			 color[0]=red;
			 color[1]=green;
			 color[2]=blue;
			 
			 return color;
		 }
	}
	
	public class loaddefaulthandler implements ActionListener {
		String Date=null;
		String filename=null;
		public loaddefaulthandler(String Date, String filename){
			this.Date = Date;
			this.filename=filename;
		}
		 @Override
		   public void actionPerformed(ActionEvent e) {
			 defaultsetting();
			 ContentPaneEx_sour(Date);
			 System.out.println("loaddefaulthandler");
		}
		 public void defaultsetting(){
			 starttime_to=start_work_date();
			 Date=starttime_to;
			 title_fonttype="나눔손글씨 펜";
			 text_fonttype="휴먼매직체";
			 STATUS_toptext="YES";
			 Frame_color = new Color(255,239,171);
         	 Title_color = Color.BLACK;
         	 Textbox_color = Color.BLACK;
         	 Progressbar_color = Color.BLACK;
         	 Buttonbox_color = Color.gray;
         	 Buttontext_color = Color.yellow;	
		 }
		 public String getString(){
			 return Date;			 
		}
	}
	public static void main(String[] args){
		new ContentPaneEx();
	}

}
