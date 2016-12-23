/**
 * 
 */
package org.liang.lab.concurrent;

/**
 * @author miaol
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().execute();
	}

	private void execute(){
		try{
			for(int i = 0; i < 10; i++){
				Thread t = new Thread(new ThreadLocalTest(), "T" + String.valueOf(i + 1));
				t.join();
				t.start();
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
}
