package RS;

import java.util.LinkedList;
import java.util.List;

public class CityCreator {

	private static int minLon = 0 ;
	private static int maxLon = 100 ;
	private static int minLat = 0 ;
	private static int maxLat = 100 ;
	
	private static int minNbBedrooms = 1 ;
	private static int maxNbBedrooms = 7 ;
	
	private static int minValue = 20000;
	private static int maxValue = 1000000;
	
	private static int nbHomes = 2;
	
	private	static List<Home> homeList = new LinkedList<Home>();
	
	private static int randomIntInRange(int minValue, int maxValue){
		return (int)(Math.random() * ((maxValue - minValue) + 1)) + minValue;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < nbHomes; i++) {
			homeList.add(new Home(randomIntInRange(minLon, maxLon), randomIntInRange(minLat, maxLat), randomIntInRange(minNbBedrooms, maxNbBedrooms), randomIntInRange(minValue, maxValue)));
		}

		for(Home currentHome : homeList){
			System.out.println(currentHome);
		}
		
	}

}
