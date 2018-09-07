package RS;

import javax.swing.event.ChangeListener;

public interface _RangeSlider {
	
	/**
	 * Adds a ChangeListener to the model's listener list
	 * @param x the ChangeListener to add
	 */
	public void addChangeListenerRight(ChangeListener clr);
	
	public void addChangeListenerLeft(ChangeListener cll);
	
	public void removeChangeListener(ChangeListener cl);
	
	public void setMin(int min);
	
	public void setMax(int max);
	
	public void setSliderLeft(int slleft);
	
	public void setSliderRight(int slright);
	
	public int getMin();
	
	public int getMax();
	
	public int getSliderLeft();
	
	public int getSliderRight();
		
}
