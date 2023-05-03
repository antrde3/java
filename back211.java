package backend;

import java.util.LinkedList;
import java.util.Queue;


import view.Collection;

public class back211 {
	private Collection tst;
	private int b;
	private int c;
	private int d;
	private Queue<String> comand;
	public back211() {
		super();
		
		this.comand = new LinkedList<>();
	
		
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public void exit(String a) {
			
			comand.add(a);
			int mu = Integer.parseInt(a);
			if(mu>9) {
				this.d=mu;
			}
		//	}
			if(comand.size()==2) {
			//	comand.add(a);
				String x=comand.poll();
				String y=comand.poll();
			
					int h= Integer.parseInt(y.trim());
					int k= Integer.parseInt(x.trim());
					if(0<h && h<10 && 10<k && k<100) {
						comand.add(x);
						this.b=h;
						this.c=k;
					}
					else if(0<h && h<10 && 10>k ) {
						comand.add(y);
					}
					else if(h>=10 && 10<k && k<100) {
						comand.add(y);
					}
					else if(k<9 &&  h>10) {
						comand.add(y);
					}
					
				}
				
				
			//}
			
	}
	
	public void rasdd() {
		this.b=100;
	}	
	
}
