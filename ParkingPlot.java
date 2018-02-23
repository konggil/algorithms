public class ParkingPlot {
	public static void main(String[] args) {
		Plot plot = new Plot(2, 0, 2, 0);
		Car car = new Car("123");
		Spot emptySpot = plot.searchEmpty(car);
		if (emptySpot != null) {
			car.enter(emptySpot);
		}
		car.exit();
	}
}

enum Size {
	BIG, MEDIUM, SMALL

};

interface Vehicle {
	public void enter(Spot spot);

	public void exit();

	public Size getSize();

	public String getId();
}

class Car implements Vehicle {
	final String id;
	final Size size;
	Spot spot;

	public Car(String id) {
		this.id = id;
		this.size = Size.MEDIUM;
	}

	public void enter(Spot spot) {
		System.out.println("car " + this.id + " enter spot");
		this.spot = spot;
		this.spot.setVehicle(this);
	}

	public void exit() {
		System.out.println("car " + this.id + " exit spot");
		spot.setVehicle(null);
		spot = null;
	}

	public Size getSize() {
		return size;
	}

	public String getId() {
		return id;
	}
}

interface Spot {

	boolean isEmpty();

	boolean canFit(Vehicle v);

	public void setVehicle(Vehicle v);
}

class MediumSpot implements Spot {
	final Size size = Size.MEDIUM;
	Vehicle vehicle;

	@Override
	public void setVehicle(Vehicle v) {
		this.vehicle = v;

	}

	@Override
	public boolean isEmpty() {
		return vehicle == null;
	}

	@Override
	public boolean canFit(Vehicle v) {
		return this.size.compareTo(v.getSize()) >= 0;
	}

}

class Level {
	int level;
	Map<Size, List<Spot>> spots;

	public Level(Map<Size, Integer> limits, int level) {
		this.level = level;
		spots = new HashMap<Size, List<Spot>>();
		for (Size size : Size.values()) {
			Integer limit = limits.get(size);
			List<Spot> spotList = new ArrayList<Spot>();
			spots.put(size, spotList);
			for (int i = 0; i < limit; i++) {
				Spot spot;
				switch (size) {
				case BIG:
				case MEDIUM:
					spot = new MediumSpot();
					spotList.add(spot);
					break;
				case SMALL:
				}
			}
		}
	}

	public Spot searchEmpty(Vehicle v) {
		for (Size size : Size.values()) {
			if (size.compareTo(v.getSize()) >= 0) {
				List<Spot> currSpots = spots.get(size);
				for (Spot spot : currSpots) {
					if (spot.isEmpty() && spot.canFit(v)) {
						return spot;
					}
				}
			}
		}
		return null;
	}
}

class Plot {
	int level;
	Map<Integer, Level> levels;

	public Plot(int level, int maxBigNum, int maxMediumNum, int maxSmallNum) {
		this.level = level;
		levels = new HashMap<Integer, Level>();
		Map<Size, Integer> maxNumMap = new HashMap<Size, Integer>();
		maxNumMap.put(Size.BIG, maxBigNum);
		maxNumMap.put(Size.MEDIUM, maxMediumNum);
		maxNumMap.put(Size.SMALL, maxSmallNum);
		for (int i = 0; i < level; i++) {
			Level currLevel = new Level(maxNumMap, i);
			levels.put(i, currLevel);
		}
	}

	public Spot searchEmpty(Vehicle v) {
		for (int i = 0; i < level; i++) {
			Level currLevel = levels.get(i);
			if (currLevel != null) {
				Spot spot = currLevel.searchEmpty(v);
				if (spot != null) {
					return spot;
				}
			}
		}
		return null;
	}
}
