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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HomeFinder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyPanel map;
	ArrayList<Home> homeList = new ArrayList<Home>();
	private JLabel bedroomSliderLowValueLabel = new JLabel();
	private JLabel bedroomSliderLowValue = new JLabel();
	private JLabel bedroomSliderHighValueLabel = new JLabel();
	private JLabel bedroomSliderHighValue = new JLabel();

	private JLabel distanceSliderLowValueLabel = new JLabel();
	private JLabel distanceSliderLowValue = new JLabel();
	private JLabel distanceSliderHighValueLabel = new JLabel();
	private JLabel distanceSliderHighValue = new JLabel();
	private ChangeListener bedroomListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			RangeSlider slider = (RangeSlider) e.getSource();
			bedroomSliderLowValue.setText(String.valueOf(slider.getValue()));
			bedroomSliderHighValue.setText(String.valueOf(slider.getSliderRight()));
			map.repaint();
		}
	};
	private ChangeListener distanceListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			RangeSlider slider = (RangeSlider) e.getSource();
			distanceSliderLowValue.setText(String.valueOf(slider.getValue()));
			distanceSliderHighValue.setText(String.valueOf(slider.getSliderRight()));
			map.repaint();
		}
	};

	public HomeFinder() {
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
		map = new MyPanel();
		map.setBackground(Color.WHITE);
		map.setPreferredSize(new Dimension(240, 240));

		// create the slider1
		RangeSlider rangeSliderTop = new RangeSlider(0, 10);
		// rangeSliderTop.init(3, 7, rangeSliderLabel1)
		bedroomSliderLowValueLabel.setText("Bedroom Lower value:");
		bedroomSliderHighValueLabel.setText("Bedroom Upper value:");
		bedroomSliderLowValue.setHorizontalAlignment(JLabel.LEFT);
		bedroomSliderHighValue.setHorizontalAlignment(JLabel.LEFT);
		// Add listener to update display of slider1
		rangeSliderTop.addChangeListener(bedroomListener);

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

		// Initialize values of slider 1
		rangeSliderTop.setValue(3);
		rangeSliderTop.setSliderRight(7);

		// Initialize value display of slider 1
		bedroomSliderLowValue.setText(String.valueOf(rangeSliderTop.getValue()));
		bedroomSliderHighValue.setText(String.valueOf(rangeSliderTop.getSliderRight()));

		// create the slider2
		RangeSlider rangeSliderBot = new RangeSlider(0, 100);
		distanceSliderLowValueLabel.setText("Distance Lower value:");
		distanceSliderHighValueLabel.setText("Distance Upper value:");
		distanceSliderLowValue.setHorizontalAlignment(JLabel.LEFT);
		distanceSliderHighValue.setHorizontalAlignment(JLabel.LEFT);
		// Add listener to update display.
		rangeSliderBot.addChangeListener(distanceListener);

		rightBot.add(distanceSliderLowValueLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightBot.add(distanceSliderLowValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 3, 0), 0, 0));
		rightBot.add(distanceSliderHighValueLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
		rightBot.add(distanceSliderHighValue, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 6, 0), 0, 0));
		rightBot.add(rangeSliderBot, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		// Initialize values.
		rangeSliderBot.setValue(3);
		rangeSliderBot.setSliderRight(7);

		// Initialize value display.
		distanceSliderLowValue.setText(String.valueOf(rangeSliderBot.getValue()));
		distanceSliderHighValue.setText(String.valueOf(rangeSliderBot.getSliderRight()));

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

	class MyPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Iterator<Home> ite = homeList.iterator();

		public void paint(Graphics g) {
			super.paint(g);
			updateHomeList();// Stocke dans une liste les maisons correspondant
								// aux sliders
			while (ite.hasNext()) {
				g.fillOval(ite.next().x, ite.next().y, 5, 5);
			}
		}

	}

}
