package RS;

import java.util.ArrayList;

public class CityCreator {

	private static int minLon = 0 ;
	private static int maxLon = 240 ;
	private static int minLat = 0 ;
	private static int maxLat = 240 ;
	
	private static int minNbBedrooms = 1 ;
	private static int maxNbBedrooms = 10 ;
	
	private static int minValue = 20000;
	private static int maxValue = 100000;
	
	private static int nbHomes = 2;
	
	private static ArrayList<Home> homeList = new ArrayList<Home>();
	
	public ArrayList<Home> getHomeList(){
		return homeList;
	}
	
	public CityCreator(int size){
		for (int i = 0; i < size; i++) {
			Home h = new Home(randomIntInRange(minLon, maxLon), randomIntInRange(minLat, maxLat), randomIntInRange(minNbBedrooms, maxNbBedrooms), randomIntInRange(minValue, maxValue));
			System.out.println(h.toString());
			homeList.add(h);
		}
	}
	
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
