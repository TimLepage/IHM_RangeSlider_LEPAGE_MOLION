package RS;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class RangeSlider extends JSlider implements _RangeSlider {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int minValue;
	private int maxValue;
	private int leftSlider;
	private int rightSlider;
	
	@Override
	public void addChangeListenerRight(ChangeListener clr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChangeListenerLeft(ChangeListener cll) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeChangeListener(ChangeListener cl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMin(int min) {
		minValue = min;
	}

	@Override
	public void setMax(int max) {
		maxValue = max;
	}

	@Override
	public int getMin() {
		return minValue;
	}

	@Override
	public int getMax() {
		return maxValue;
	}

	@Override
	public void setSliderLeft(int slleft) {
		leftSlider = slleft;		
	}

	@Override
	public void setSliderRight(int slright) {
		rightSlider = slright;
		
	}

	@Override
	public int getSliderLeft() {
		return leftSlider;
	}

	@Override
	public int getSliderRight() {
		return rightSlider;
	}

}
