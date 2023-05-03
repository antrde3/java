package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actionview.listener;
import backend.back211;
import backend.newgame;


public class Collection extends JFrame{
	private JPanel panel;
	private int error;
	private JMenuItem loisai;
	private back211 bk;
	private  JButton [] jButtons;
	private ActionListener ac;
	private int[][] matrix; 
	private int[][] matrix1; 
	private newgame NewGame;
	private Collection currentWindow;
	private Timer timer;
	private int dem;
	private int goiy;
	private int dem1;
		public Collection() throws IOException {
	        // tạo đối tượng JFrame với tên "Nine By Nine Board"
	        super("Nine By Nine Board");
	        this.matrix1 = new int[9][9];
	        this.matrix = new int[9][9];
	        this.bk =new back211();
	        ActionListener ac = new listener(this);
	        this.view();
	        currentWindow = this;
	        this.error=0;
	        this.dem=0;
	        this.goiy=1;
		}
	
        public void view() {
    		
    		
 		   setSize(600, 400);
 		   setLayout(new BorderLayout());
 		   this.jButtons = new JButton[100];
 		   this.ac = new listener(this);
 		   
        // tạo một đối tượng JPanel để chứa các ô vuông
 		   ////
 		  JMenuBar bar=new JMenuBar();
 		  JMenuItem exit= new JMenuItem("Exit");
 		  exit.addActionListener(ac);
 		  JMenuItem delete= new JMenuItem("Delete");
		  delete.addActionListener(ac);
 		  loisai= new JMenuItem("Lỗi sai"+" "+this.error+""+"/3");
		  loisai.addActionListener(ac);
 		  JMenu game = new JMenu("New Game");
 		  JMenuItem de = new JMenuItem("Dễ");
 		  JMenuItem trungbinh = new JMenuItem("Trung Bình");
 		  JMenuItem kho = new JMenuItem("Khó");
 		  JMenuItem chuyengia = new JMenuItem("Chuyên Gia");
 		  game.add(de);
 		  de.addActionListener(ac);
 		  game.add(trungbinh);
 		  trungbinh.addActionListener(ac);
 		  game.add(kho);
 		  kho.addActionListener(ac);
 		  game.add(chuyengia);
 		  chuyengia.addActionListener(ac);
 		 JMenuItem goiy = new JMenuItem("Gợi ý");
 		 goiy.addActionListener(ac);
 		 JMenuItem timePanel = new JMenuItem("Time: 00:00"); 
         bar.add(timePanel);
         add(timePanel, BorderLayout.WEST);
         // Tạo Timer để cập nhật thời gian giải Sudoku
         timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
             private int second = 0;
             private int minute = 0;
             @Override
             public void run() {
                 second++;
                 if (second >= 60) {
                     second = 0;
                     minute++;
                 }
                 String time = String.format("Time: %02d:%02d", minute, second);
                 timePanel.setText(time);
             }
         }, 0, 1000);
   
 		  bar.add(exit);
 		  bar.add(delete);
 		  bar.add(loisai);
 		  bar.add(goiy);
 		  bar.add(timePanel);
 		  bar.add(game); 
 		 
        this.panel = new JPanel(new GridLayout(3, 3));
        // đọc ma trận để xuất ra ván chơi
        File file = new File("G:\\test_outfile\\test_file1.txt");
        File file1 = new File("G:\\test_outfile\\test_file.txt");
    	Scanner scanner = null;
    	try {
    	    scanner = new Scanner(file);
    	    int i = 0;
    	    while (scanner.hasNextLine() && i < 9) {
    	        String line = scanner.nextLine();
    	        String[] values = line.split("\\s+");
    	        for (int j = 0; j < 9; j++) {
    	            if (values.length > j) {
    	                matrix[i][j] = Integer.parseInt(values[j]);
    	            }
    	        }
    	        i++;
    	    }
    	} catch (FileNotFoundException e) {
    	    System.out.println("File not found!");
    	} catch (NumberFormatException e) {
    	    System.out.println("Invalid format!");
    	} finally {
    	    if (scanner != null) {
    	        scanner.close();
    	    }
    	}
        
    	// đọc ma trận đã được giải
    	try {
    	    scanner = new Scanner(file1);
    	    int i = 0;
    	    while (scanner.hasNextLine() && i < 9) {
    	        String line = scanner.nextLine();
    	        String[] values = line.split("\\s+");
    	        for (int j = 0; j < 9; j++) {
    	            if (values.length > j) {
    	                matrix1[i][j] = Integer.parseInt(values[j]);
    	            }
    	        }
    	        i++;
    	    }
    	} catch (FileNotFoundException e) {
    	    System.out.println("File not found!");
    	} catch (NumberFormatException e) {
    	    System.out.println("Invalid format!");
    	} finally {
    	    if (scanner != null) {
    	        scanner.close();
    	    }
    	}
        // tạo 81 ô vuông và thêm chúng vào panel
    	for(int a=1; a<=3; a++) {
			for(int b=1; b<=3; b++) {
				JPanel jPanel_3 =new JPanel();
				jPanel_3.setLayout(new GridLayout(3,3));
				for(int i=a*3-2; i<=a*3; i++) {
					for(int j=b*3-2; j<=b*3; j++) {
						jButtons[i*10+j] = new JButton();
						jButtons[i*10+j].setBackground(Color.white);
						jButtons[i*10+j].setActionCommand(i+""+j);
						jPanel_3.add(jButtons[i*10+j]);
						if(matrix[i-1][j-1]!=0) {
							jButtons[i*10+j].setText(matrix[i-1][j-1]+"");	
							this.dem++;
							
						}
						else {
							jButtons[i*10+j].addActionListener(ac);
							JButton button = jButtons[i*10+j];
							
							button.addMouseListener((MouseListener) new MouseAdapter() {
							    @Override
							    public void mouseEntered(MouseEvent e) {
							        button.setBackground(Color.LIGHT_GRAY);
							        
							    }

							    @Override
							    public void mouseExited(MouseEvent e) {
							        button.setBackground(Color.white);
							    }
							});
						}
					}
						
				}
				this.dem1=this.dem;
				jPanel_3.setBorder(BorderFactory.createLineBorder(new Color(175, 238, 238),2));
				panel.add(jPanel_3);
				
			}	
			
        }	
		
		
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        JPanel jPanel=new JPanel();
      //  jPanel.setLayout(new BorderLayout());
        for(int i=1;i<=9;i++) {
        	JButton jButton =new JButton(i+"");
        	jButton.setActionCommand(i+"");
        	jButton.addActionListener(ac);
        	jPanel.add(jButton,BorderLayout.CENTER);
        }
        
        
        // đặt panel vào giữa của JFrame
        add(panel, BorderLayout.CENTER);
        add(bar, BorderLayout.NORTH);
        add(jPanel,BorderLayout.SOUTH);
        // thiết lập kích thước của JFrame và tuỳ chọn khác
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
	public void name(String a, int b) throws IOException {
		int ha= Integer.parseInt(a);
		if(b==0) {		
			this.bk.exit(a);
			if(this.bk.getC() != 0 && this.bk.getB()!=0  && this.bk.getB()!=100) {
				this.jButtons[this.bk.getC()].setText(this.bk.getB()+"");
				this.jButtons[this.bk.getC()].setBackground(Color.gray);
				int m=this.bk.getC()/10-1;
				int n=this.bk.getC()%10-1;
				if(this.matrix1[m][n]== this.bk.getB()) {
					this.jButtons[this.bk.getC()].removeActionListener(ac);
					this.jButtons[this.bk.getC()].setBackground(Color.white);
					
					if(ha<10) {
						this.dem++;
						
					}
					if(this.dem+this.dem1==81) {
						int option = JOptionPane.showOptionDialog(null, "Bạn đã thắng!","Bấm newgame để chơi ván khác", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if(option == JOptionPane.OK_OPTION) {
						//	this.play_back();
							Collection newGameWindow = new Collection(); 
							newGameWindow.view();
							this.NewGame = new newgame(41); 
						    currentWindow.dispose(); // tắt cửa sổ hiện tại
						    currentWindow = newGameWindow;  
							
							
						}
					}
				}
				if(this.matrix1[m][n]!= this.bk.getB() && Integer.parseInt(a)<10) {
					this.error++;
					this.loisai.setText("Lỗi sai"+" "+this.error+""+"/3");
					System.out.println("lỗi");
					// còn nữa này
					if(this.error==3) {
						this.timer.cancel();
					
					//	JOptionPane.showMessageDialog(null, "Bạn đã thua!");
						int option = JOptionPane.showOptionDialog(null, "Bạn đã thua!", "Chơi lại màn thua", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if(option == JOptionPane.OK_OPTION) {
							
							Collection play_back= new Collection();
							currentWindow.dispose();
						}
					}
				}
			}
			if(this.bk.getC() != 0 &&  this.bk.getB()==100) {
				this.jButtons[this.bk.getC()].setText("");
			}
		}
		
		    
	}  
	
	public void new_game(int b) throws IOException, InterruptedException {
		Collection newGameWindow = new Collection(); 
		newGameWindow.view();
		this.NewGame = new newgame(b); 
	    currentWindow.dispose(); // tắt cửa sổ hiện tại
	    currentWindow = newGameWindow;  // hiển thị ván mới trên cửa sổ mới
	} 
	
	public void delete1() {
		this.bk.rasdd();
		this.jButtons[this.bk.getD()].setText("");
		this.jButtons[this.bk.getD()].setBackground(Color.white);
	}
	
	public void play_back() {
		if(this.goiy !=0) {
			int m=this.bk.getD()/10-1;
			int n=this.bk.getD()%10-1;
			this.jButtons[this.bk.getD()].setText(this.matrix1[m][n]+"");
			this.jButtons[this.bk.getD()].setBackground(Color.white);
			this.goiy--;
		}
		else if(this.goiy == 0) {
			int option = JOptionPane.showOptionDialog(null, "Đã hết gợi ý!", "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}
		
	}
    public static void main(String[] args) throws IOException{
        // tạo đối tượng NineByNineBoard
   
        Collection a= new Collection();
      //  System.out.println(a.matrix[2][4]);
    }
}
