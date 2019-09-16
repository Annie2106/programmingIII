package server.model;

import java.util.ArrayList;

/**
 * Models a the dinner for the philosophers
 */
public class Dinner {
	// Actual status of the dinner
	private static boolean mDinningStatus = false;
	// The group of philosophers
	private Philosopher[] mPhilosophers;

	/**
	 * Main constructor for the class, instantiates the group of philosophers.
	 *
	 * @param philosophers The group of philosophers that will stay on the dinner.
	 */
	public Dinner(Philosopher[] philosophers) {
		this.mPhilosophers = philosophers;

	}

	/**
	 * Returns the dinner status.
	 *
	 * @return Dinner status.
	 */
	static boolean getDinnerStatus() {
		return Dinner.mDinningStatus;
	}

	/**
	 * Starts the dinner and every philosopher thread.
	 */
	private void initializeDinner() {
		this.forkAssign();
		Dinner.mDinningStatus = true;
		//		for (Philosopher sPhilosopher : this.mPhilosophers) {
		////			sPhilosopher.start();
		//		}
	}

	/**
	 * Assign the forks, left and right, to every philosopher on the dinner.
	 */
	private void forkAssign() {
		Fork[] forks = new Fork[this.mPhilosophers.length];
		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Fork();
		}
		for (int i = 0; i < this.mPhilosophers.length - 1; i++) {
			int nextPosition = (i + 1) % this.mPhilosophers.length;
			this.mPhilosophers[i].setForks(forks[i], forks[nextPosition]);
		}
	}

	public void increasingVect(UserServer user) {
		if (mPhilosophers!=null) {
			Philosopher newVect[]= new Philosopher[mPhilosophers.length+1];
			for (int i = 0; i < mPhilosophers.length; i++) {
				newVect[i]=mPhilosophers[i];
			}
			Philosopher np=new Philosopher(user);
			newVect[newVect.length-1]=np;
			mPhilosophers = newVect;
		}else {
			Philosopher phi = new Philosopher(user);
			mPhilosophers = new Philosopher[1];
			mPhilosophers[0] =phi;
		}
		
	}

	public void addPhilosopher(UserServer user) {
		this.increasingVect(user);
	}

	public Philosopher[] getPhilosopher() {
		return mPhilosophers;
	}
}
