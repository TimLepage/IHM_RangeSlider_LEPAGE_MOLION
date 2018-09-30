package RS;

import javax.swing.JSlider;

/*
 * Based on https://github.com/ernieyu/Swing-range-slider
 * Simplified and adapted to _RangeSlider interface
 */
public class RangeSlider extends JSlider implements _RangeSlider {

	/**
	 * Constructs a RangeSlider with default minimum and maximum values of 0 and
	 * 100.
	 */
	public RangeSlider(int min, int max) {
		setOrientation(HORIZONTAL);
        this.setMinimum(min);
        this.setMaximum(max);
	}


	/**
	 * Overrides the superclass method to install the UI delegate to draw two
	 * thumbs.
	 */
	@Override
	public void updateUI() {
		setUI(new RangeSliderUI(this));
		// Update UI for slider labels. This must be called after updating the
		// UI of the slider. Refer to JSlider.updateUI().
		updateLabelUIs();
	}

	/**
	 * Returns the lower value in the range.
	 */
	@Override
	public int getSliderLeft() {
		return super.getValue();
	}

	@Override
	public void setValue(int value){
		setSliderLeft(value);
	}
	
	/**
	 * Sets the lower value in the range.
	 */
	@Override
	public void setSliderLeft(int value) {
		int oldValue = getValue();

		// Compute new value and extent to maintain upper value.
		int oldExtent = getExtent();
		int newValue = Math.min(Math.max(getMinimum(), value), oldValue + oldExtent);
		int newExtent = oldExtent + oldValue - newValue;
		
		// Set new value and extent, and fire a single change event.
		getModel().setRangeProperties(newValue, newExtent, getMinimum(), getMaximum(), getValueIsAdjusting());
	}

	/**
	 * Returns the upper value in the range.
	 */
	public int getSliderRight() {
		return getValue() + getExtent();
	}

	/**
	 * Sets the upper value in the range.
	 */
	public void setSliderRight(int value) {
		
		// Compute new extent.
		int lowerValue = getValue();
		int newExtent = Math.min(Math.max(0, value - lowerValue), getMaximum() - lowerValue);
		
		// Set extent to set upper value.
		setExtent(newExtent);
	}
}