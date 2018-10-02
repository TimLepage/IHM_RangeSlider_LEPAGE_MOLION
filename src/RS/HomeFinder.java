package RS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HomeFinder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map map;
	private CityCreator city;
	private ArrayList<Home> homeList = new ArrayList<Home>();
	private JLabel bedroomSliderLowValueLabel = new JLabel();
	private JLabel bedroomSliderLowValue = new JLabel();
	private JLabel bedroomSliderHighValueLabel = new JLabel();
	private JLabel bedroomSliderHighValue = new JLabel();

	private JLabel priceSliderLowValueLabel = new JLabel();
	private JLabel priceSliderLowValue = new JLabel();
	private JLabel priceSliderHighValueLabel = new JLabel();
	private JLabel priceSliderHighValue = new JLabel();

	private int priceLowThumb;
	private int priceHighThumb;
	private int bedroomLowThumb;
	private int bedroomHighThumb;
	
	public void updateHomeList(int priceLowValue, int priceHighValue, int bedroomLowValue, int bedroomHighValue) {
		homeList.clear();
		Iterator<Home> ite = city.getHomeList().iterator();
		int v = ite.next().getValue();
		System.out.println("\n\n");
		while (ite.hasNext()) {
			Home currentHome = ite.next();
			if (currentHome.getValue() >= priceLowValue && currentHome.getValue() <= priceHighValue
					&& currentHome.getNbBedrooms() >= bedroomLowValue
					&& currentHome.getNbBedrooms() <= bedroomHighValue) {
				System.out.println(currentHome.toString());
				homeList.add(currentHome);
			}
		}
	}

	private ChangeListener bedroomListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			//System.out.println(bedroomLowThumb + " " + bedroomHighThumb);
			RangeSlider slider = (RangeSlider) e.getSource();
			bedroomLowThumb = slider.getValue();
			bedroomHighThumb = slider.getSliderRight();
			bedroomSliderLowValue.setText(String.valueOf(bedroomLowThumb));
			bedroomSliderHighValue.setText(String.valueOf(bedroomHighThumb));
			updateHomeList(priceLowThumb, priceHighThumb, bedroomLowThumb, bedroomHighThumb);// Stocke
			// dans
			// une
			// liste
			// les
			// maisons
			// correspondant
			// aux sliders
			map.repaint();
		}
	};
	private ChangeListener priceListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			//System.out.println(priceLowThumb + " " + priceHighThumb);
			RangeSlider slider = (RangeSlider) e.getSource();
			priceLowThumb = slider.getValue();
			priceHighThumb = slider.getSliderRight();
			priceSliderLowValue.setText(String.valueOf(priceLowThumb));
			priceSliderHighValue.setText(String.valueOf(priceHighThumb));
			updateHomeList(priceLowThumb, priceHighThumb, bedroomLowThumb, bedroomHighThumb);// Stocke
			// dans
			// une
			// liste
			// les
			// maisons
			// correspondant
			// aux sliders
			map.repaint();
		}
	};

	public HomeFinder() {
		// Create a city
		city = new CityCreator(10);// generates a size sized city with
									// random values, lon, lat and
									// bedroom

		// create a new panel with GridBagLayout manager
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		rightPanel.setLayout(new BorderLayout());

		JPanel rightTop = new JPanel();
		rightTop.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		rightTop.setLayout(new GridBagLayout());

		JPanel rightBot = new JPanel();
		rightBot.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		rightBot.setLayout(new GridBagLayout());

		// Create the map
		map = new Map();
		map.setBackground(Color.WHITE);
		map.setPreferredSize(new Dimension(240, 240));

		// create the slider bedroom
		RangeSlider rangeSliderTop = new RangeSlider(0, 10, 3, 7);
		// rangeSliderTop.init(3, 7, rangeSliderLabel1)
		bedroomSliderLowValueLabel.setText("Bedroom Lower value:");
		bedroomSliderHighValueLabel.setText("Bedroom Upper value:");
		bedroomSliderLowValue.setHorizontalAlignment(JLabel.LEFT);
		bedroomSliderHighValue.setHorizontalAlignment(JLabel.LEFT);

		rightTop.add(bedroomSliderLowValueLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightTop.add(bedroomSliderLowValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 3, 0), 0, 0));
		rightTop.add(bedroomSliderHighValueLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightTop.add(bedroomSliderHighValue, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 6, 0), 0, 0));
		rightTop.add(rangeSliderTop, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		// Initialize values of slider bedroom
		rangeSliderTop.setValue(3);
		rangeSliderTop.setSliderRight(7);
		bedroomLowThumb = 3;
		bedroomHighThumb = 7;

		// Add listener to update display of slider bedroom
		rangeSliderTop.addChangeListener(bedroomListener);

		// Initialize value display of slider bedroom
		bedroomSliderLowValue.setText(String.valueOf(rangeSliderTop.getValue()));
		bedroomSliderHighValue.setText(String.valueOf(rangeSliderTop.getSliderRight()));

		// create the slider price
		RangeSlider rangeSliderBot = new RangeSlider(0, 100000, 0, 5000);
		priceSliderLowValueLabel.setText("Price Lower value:");
		priceSliderHighValueLabel.setText("Price Upper value:");
		priceSliderLowValue.setHorizontalAlignment(JLabel.LEFT);
		priceSliderHighValue.setHorizontalAlignment(JLabel.LEFT);

		rightBot.add(priceSliderLowValueLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightBot.add(priceSliderLowValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 3, 0), 0, 0));
		rightBot.add(priceSliderHighValueLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightBot.add(priceSliderHighValue, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 6, 0), 0, 0));
		rightBot.add(rangeSliderBot, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		// Initialize values slider price.
		rangeSliderBot.setValue(0);
		rangeSliderBot.setSliderRight(50000);
		priceLowThumb = 0;
		priceHighThumb = 50000;

		// Add listener to update display.
		rangeSliderBot.addChangeListener(priceListener);

		// First call of updateHomeList to show the initial results
		updateHomeList(priceLowThumb, priceHighThumb, bedroomLowThumb, bedroomHighThumb);

		// Initialize value display.
		priceSliderLowValue.setText(String.valueOf(rangeSliderBot.getValue()));
		priceSliderHighValue.setText(String.valueOf(rangeSliderBot.getSliderRight()));

		// add components to the panel
		rightPanel.add(rightTop, BorderLayout.PAGE_START);
		rightPanel.add(rightBot, BorderLayout.PAGE_END);
		add(map, BorderLayout.LINE_START); // map
		add(rightPanel, BorderLayout.LINE_END); // sliders

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// set look and feel to the system look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new HomeFinder().setVisible(true);
			}
		});

	}

	class Map extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			Iterator<Home> ite = homeList.iterator();
			super.paint(g);
			while (ite.hasNext()) {
				Home currentHome = ite.next();
				System.out.println(currentHome.getLon() + " " + currentHome.getLat());
				g.fillOval(currentHome.getLon(),currentHome.getLat(), 5, 5);
			}
		}

	}

}
