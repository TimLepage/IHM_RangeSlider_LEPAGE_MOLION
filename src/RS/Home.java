package RS;

public class Home {
	
	// Geolocation
	private int lon ;
	private int lat ; 
	
	// Number of rooms
	private int nbBedrooms ;
	
	// Value 
	private int value ;
	
	public Home(int lon, int lat, int nbBedrooms, int value) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.nbBedrooms = nbBedrooms;
		this.value = value;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getNbBedrooms() {
		return nbBedrooms;
	}

	public void setNbBedrooms(int nbBedrooms) {
		this.nbBedrooms = nbBedrooms;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
