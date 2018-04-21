public class LiftSimulator {

	public static void main(String[] args) {
		Lift lift = new Lift(10);
		lift.open();
		lift.pressButton(5);
		lift.pressButton(6);
		lift.pressButton(2);
		lift.pressButton(1);
	}
}

interface State {
	public void pressButton(int floor);

	public void open();

	public void close();

	public void run();

	public void stop();
}

class Lift {
	State stopState;
	State upState;
	State downState;
	State state;

	int floorCount;
	boolean[] floorButton;	
	int floor;

	public Lift(int floorCount) {
		if (floorCount <= 0) {
			throw new IllegalArgumentException();
		}
		this.floorButton = new boolean[floorCount + 1];
		this.floorCount = floorCount;
		this.floor = 1;

		stopState = new StopState(this);
		upState = new UpState(this);
		downState = new DownState(this);
		this.state = stopState;
	}

	public void pressButton(int floor) {
		state.pressButton(floor);
	}

	public void open() {
		state.open();
	}

	public void close() {
		state.close();
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		if (floor > 0 && floor <= floorCount) {
			this.floor = floor;
		}
	}
	
	public boolean checkCanUp() {
		return floor < floorCount;
	}

	public boolean checkCanDown() {
		return floor > 1;
	}

	public void setButton(int floor, boolean openClose) {
		if (floor > 0 && floor <= floorCount) {
			floorButton[floor] = openClose;
		}
	}

	public boolean checkButton() {
		return floorButton[floor];
	}

	public boolean checkUpButton() {
		for (int i = floor + 1; i <= floorCount; i++) {
			if (floorButton[i] == true) {
				return true;
			}
		}
		return false;

	}

	public boolean checkDownButton() {
		for (int i = floor - 1; i > 0; i--) {
			if (floorButton[i] == true) {
				return true;
			}
		}
		return false;
	}

	public void setState(State newState) {
		state = newState;
	}

	public State getState() {
		return state;
	}

	public State getStopState() {
		return stopState;
	}

	public State getUpState() {
		return upState;
	}

	public State getDownState() {
		return downState;
	}
}

class StopState implements State {
	Lift lift;

	public StopState(Lift lift) {
		this.lift = lift;
	}

	public void pressButton(int floor) {
		System.out.println("client press button " + floor);
		if (lift.getFloor() == floor) {
			System.out.println("this is floor " + floor);
		} else {
			lift.setButton(floor, true);
			close();
		}
	}

	public void open() {
		System.out.println("open door " + lift.getFloor());
	}

	public void close() {
		System.out.println("close door " + lift.getFloor());
		checkUpOrDown();
		lift.state.run();
	}

	@Override
	public void run() {
		System.out.println("currently lift stops");

	}

	@Override
	public void stop() {
		System.out.println("you have stoped");
	}

	private void checkUpOrDown() {
		if (lift.checkDownButton()) {
			lift.setState(lift.getDownState());
		} else if (lift.checkUpButton()) {
			lift.setState(lift.getUpState());
		}
	}
}

class UpState implements State {
	Lift lift;

	public UpState(Lift lift) {
		this.lift = lift;
	}

	@Override
	public void pressButton(int floor) {
		lift.setButton(floor, true);
	}

	@Override
	public void open() {
		System.out.println("you cannot open door");
	}

	@Override
	public void close() {
		System.out.println("you cannot close door");
	}

	@Override
	public void run() {
		while (lift.checkCanUp()) {
			lift.setFloor(lift.getFloor() + 1);
			System.out.println("go to floor" + lift.getFloor());
			if (lift.checkButton()) {
				lift.setButton(lift.getFloor(), false);
				break;
			}
		}
		stop();
		lift.setState(lift.getStopState());
		lift.getState().open();
	}

	@Override
	public void stop() {
		System.out.println("stop at floor at" + lift.getFloor());
	}

}

class DownState implements State {
	Lift lift;

	public DownState(Lift lift) {
		this.lift = lift;
	}

	@Override
	public void pressButton(int floor) {
		lift.setButton(floor, true);
	}

	@Override
	public void open() {
		System.out.println("you cannot open door");
	}

	@Override
	public void close() {
		System.out.println("you cannot close door");
	}

	@Override
	public void run() {
		while (lift.checkCanDown()) {
			lift.setFloor(lift.getFloor() - 1);
			System.out.println("go to floor" + lift.getFloor());
			if (lift.checkButton()) {
				lift.setButton(lift.getFloor(), false);
				break;
			}
		}
		stop();
		lift.setState(lift.getStopState());
		lift.getState().open();
	}

	@Override
	public void stop() {
		System.out.println("stop at floor at" + lift.getFloor());
	}

}