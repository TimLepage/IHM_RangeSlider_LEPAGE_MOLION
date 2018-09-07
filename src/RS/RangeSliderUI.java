package RS;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RangeSliderUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RangeSliderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JSlider slider = new JSlider();
		BoundedRangeModel model = slider.getModel();
		model.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				BoundedRangeModel m = (BoundedRangeModel) e.getSource();
				System.out.println("Slider position changed to " + m.getValue());
			}
		});
		getContentPane().add(slider);
		pack();
	}

	public static void main(String[] args) {
		RangeSliderUI m = new RangeSliderUI();
		m.setVisible(true);
	}
}