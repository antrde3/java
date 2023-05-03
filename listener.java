package actionview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import backend.newgame;
import view.Collection;



public class listener implements ActionListener{
	private Collection ctv;
	private String previousButton="";
	private newgame NewGame;
	public listener(Collection ctv) {
		this.ctv=ctv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String src = e.getActionCommand();
		
		if(src.equals("Exit")) {
			System.out.println("đã nghe");
			System.exit(0);
		}
		else if(src.equals("Delete")) {
			this.ctv.delete1();
		}
		else if(src.equals("Dễ")) {
		    System.out.println("dễ");
		    try {
		        newgame NewGame = new newgame(41);
		        Collection newCollection = new Collection();
		        // lấy frame của cửa sổ hiện tại để hiển thị cửa sổ mới
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this.ctv.getContentPane());
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                newCollection.setVisible(true);
		                currentFrame.setVisible(false); // ẩn cửa sổ hiện tại
		            }
		        });
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}
		else if(src.equals("Trung Bình")) {
			try {
		        newgame NewGame = new newgame(46);
		        Collection newCollection = new Collection();
		        // lấy frame của cửa sổ hiện tại để hiển thị cửa sổ mới
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this.ctv.getContentPane());
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                newCollection.setVisible(true);
		                currentFrame.setVisible(false); // ẩn cửa sổ hiện tại
		            }
		        });
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}	
		else if(src.equals("Khó")) {
			try {
		        newgame NewGame = new newgame(51);
		        Collection newCollection = new Collection();
		        // lấy frame của cửa sổ hiện tại để hiển thị cửa sổ mới
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this.ctv.getContentPane());
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                newCollection.setVisible(true);
		                currentFrame.setVisible(false); // ẩn cửa sổ hiện tại
		            }
		        });
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}
		else if(src.equals("Chuyên Gia")) {
			try {
		        newgame NewGame = new newgame(56);
		        Collection newCollection = new Collection();
		        // lấy frame của cửa sổ hiện tại để hiển thị cửa sổ mới
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this.ctv.getContentPane());
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                newCollection.setVisible(true);
		                currentFrame.setVisible(false); // ẩn cửa sổ hiện tại
		            }
		        });
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}
		else if(src.equals("Gợi ý")) {
			this.ctv.play_back();
		}
		else if(isNumeric(src)==true) {
			//System.out.println(src);
			try {
				this.ctv.name(src,0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		previousButton=src;
		
	}
	public boolean isNumeric(String str){
	    try{
	        int d = Integer.parseInt(str);
	    } catch(NumberFormatException nfe){
	        return false;
	    }
	    return true;
	}
	
}
