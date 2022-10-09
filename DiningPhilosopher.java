import java.util.Random;
import java.util.concurrent.Semaphore;
 
public class DiningPhilosopher {
	// The number of philosophers
	private static final int NUM_PHILOSOPHERS = 5;
 
	/**
 	* Test the dining philosophers solution
 	*
 	* @param args Not used
 	*/
	public static void main(String[] args) {
    	// Model eat fork with a lock
    	Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];
 
    	for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
        	forks[i] = new Semaphore(1);
    	}
 
    	// Create the philosophers and start each running in its own thread.
    	Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
 
    	for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
        	philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
        	new Thread(philosophers[i]).start();
    	}
 
	}
}
class Philosopher implements Runnable{
	// Used to vary how long a philosopher thinks before eating and how long the
	// philosopher eats
 
	final private Random numGenerator = new Random();
 
	// The philosopher's unique id
	final private int id;
 
	// Tht forks this philosopher may use
	final private Semaphore leftFork;
	final private Semaphore rightFork;
 
	/**
 	* Constructs a new philosopher
 	*
 	* @param id the unique id
 	* @param leftFork fork to the left
 	* @param rightFork fork to the right
 	*/
	public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
    	this.id = id;
    	this.leftFork = leftFork;
    	this.rightFork = rightFork;
	}
 
	/**
 	* Repeatedly think, pick up fork, eat and put down fork
 	*/
	public void run() {
    	try {
        	while (true) {
            	think();
            	pickUpLeftFork();
            	pickUpRightFork();
            	eat();
            	putDownForks();
 
        	}
    	} catch (InterruptedException e) {
        	System.out.println("Philosopher " + (id + 1) + " was interrupted.\n");
    	}
	}
 
/**
 * Lets a random amount of time pass to model thinking.
 *
 * @throws InterruptedException
 */
private void think() throws InterruptedException {
	System.out.println("Philosopher " + (id + 1) + " puts down forks and is thinking.\n");
	System.out.flush();
	Thread.sleep(numGenerator.nextInt(1000));
}
 
/**
 * Locks the left fork to signify that this philosopher is holding it
 */
private void pickUpLeftFork() throws InterruptedException{
	if(leftFork.availablePermits() ==0){
    	System.out.println("Philosopher " + (id + 1) +" is WAITING for left fork");
	}
 
 
	leftFork.acquire();
	System.out.println("Philosopher " + (id + 1) + " is HOLDING left fork.\n");
	System.out.flush();
	// }
 
}
 
/**
 * Locks the right fork to signify that this philosopher is holding it
 */
private void pickUpRightFork()  throws InterruptedException{
	if(rightFork.availablePermits() ==0){
    	System.out.println("Philosopher " + (id + 1) +" is WAITING for right fork");
	}
 
	rightFork.acquire();
	System.out.println("Philosopher " + (id + 1) + " is HOLDING right fork.\n");
	System.out.flush();
 
 
}
/**
 	* Lets a random amount of time pass to model eating.
 	*
 	* @throws InterruptedException
 	*/
	private void eat() throws InterruptedException {
    	System.out.println("Philosopher " + (id + 1) + " is eating.\n");
    	System.out.flush();
    	Thread.sleep(numGenerator.nextInt(1000));
	}
 
	/**
 	* Releases the locks on both forks to model putting them down so the
 	* other philosophers can use them.
 	*/
	private void putDownForks() {
    	leftFork.release();
    	rightFork.release();
	}
 
}
