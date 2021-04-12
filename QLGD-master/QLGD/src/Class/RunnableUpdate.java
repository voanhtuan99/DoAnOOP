/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author NHT
 */
public class RunnableUpdate extends ThucHienGD implements Runnable{
	private Thread t;
	private String threadName;

	public RunnableUpdate(String name) {
		threadName = name;
	}

	@Override
	public void run() {
		try {
			while(true) {
                            super.thucHienGD();
                            super.upDateTienVaCP();
				// Let the thread sleep for a while.    
                            Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
                }
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
        
        public void stopt() {
            t.stop();
	}
}

