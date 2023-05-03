package backend;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;


public class newgame implements Runnable{
	private int a;
	private int[][] matrix;
	private static void generateRandomMatrix(int[][] matrix) {
	    Vector<Integer> b = new Vector<>();
	    Random random = new Random();
	    int x;
	    while (b.size() < 9) {
	        x = random.nextInt(9) + 1;
	        if (!b.contains(x)) {
	            b.add(x);
	        }
	    }
	    for (int i = 0; i < 9; i++) {
	        matrix[0][i] = b.get(i);
	    }
	}
	
	public newgame(int a) throws IOException {
		this.a=a;
		 this.matrix = new int[9][9];
		 generateRandomMatrix(this.matrix);
		 Thread t = new Thread(this);
		 t.start();
	}
	
	public void run() {
		try {
			sodoku(this.matrix, 0, 0);
		} catch (IOException e) {
			System.err.println("");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sodoku(int[][] c, int x,int y) throws IOException, InterruptedException {
		if (Thread.interrupted()) {
			throw new InterruptedException();  // kích hoạt trường hợp ngoại lệ tắt các tác vụ đang chạy 1 cách an toàn
		}
		
		if(y==9){
			if(x==8){
				printSolution(c,"G:\\test_outfile\\test_file.txt");
				generateSudoku(c,"G:\\test_outfile\\test_file1.txt");
				Thread.currentThread().interrupt();  //set trạng thái interrupt của một thread sẽ chạy trong tương lai.
				
				return;
			}
			else{
				sodoku(c,x+1,0);
			}
		}
		else if(c[x][y]==0){
			for(int k=1;k<=9;k++){
				if(check(c,x,y,k)==true){
					c[x][y]=k;
					sodoku(c,x,y+1);  
					c[x][y]=0;
				}
			}
		}
		else{
			sodoku(c,x,y+1);
		}
	} 
	//
	//
	public void  printSolution(int[][] c,String fileName) throws IOException{
		FileWriter writer = new FileWriter(fileName);
		for(int i=0;i<9;i++){
		    for(int j=0;j<9;j++){

				writer.write(c[i][j] + " ");
		    }
		    writer.write("\n"); 
		}
		writer.close();
	}
	
	//
	//
	 public void generateSudoku(int[][] c, String filename) throws IOException {
	        Random random = new Random();
	        
	        int holes = 0;
	        while (holes < this.a) {
	            int i = random.nextInt(9);
	            int j = random.nextInt(9);
	            if (c[i][j] != 0) {
	                holes++;
	                c[i][j] = 0;
	            }
	        }

	        // in ra file
	        printSolution(c, filename);
			
	}
	    
	public boolean check(int[][] c,int x,int y, int k) {
		for(int i=0;i<9;i++){
			if(c[i][y]==k || c[x][i]==k){
				return false;
			}
		}
		int a=x/3; int b=y/3;
		for(int i=a*3;i<a*3+3;i++){
			for(int j=b*3;j<b*3+3;j++){
				if(c[i][j]==k){
					return false;
				}
			}
		}
		
		return true;
	}	//
		//
		
		
/*	public static void main(String[] args) throws IOException, InterruptedException {
		 System.out.println("nhớndsad1");
	    newgame a1 = new newgame();
		// System.out.println("da chay xong");
	}  */
}


