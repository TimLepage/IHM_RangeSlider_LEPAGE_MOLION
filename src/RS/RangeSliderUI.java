package RS;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * UI delegate for the RangeSlider component. RangeSliderUI paints two thumbs,
 * one for the lower value and one for the upper value.
 */
/*
 * Based on https://github.com/ernieyu/Swing-range-slider and simplified 
 */
class RangeSliderUI extends BasicSliderUI {

	/** Location and size of thumb for upper value. */
	private Rectangle upperThumbRect;
	/** Indicator that determines whether upper thumb is selected. */
	private boolean upperThumbSelected;

	/** Indicator that determines whether lower thumb is being dragged. */
	private boolean lowerDragging;
	/** Indicator that determines whether upper thumb is being dragged. */
	private boolean upperDragging;

	/**
	 * Constructs a RangeSliderUI for the specified slider component.
	 * 
	 * @param b
	 *            RangeSlider
	 */
	public RangeSliderUI(RangeSlider b) {
		super(b);
	}

	/**
	 * Installs this UI delegate on the specified component.
	 */
	@Override
	public void installUI(JComponent c) {
		upperThumbRect = new Rectangle();
		super.installUI(c);
	}

	/**
	 * Creates a listener to handle track events in the specified slider.
	 */
	@Override
	protected TrackListener createTrackListener(JSlider slider) {
		return new RangeTrackListener();
	}

	/**
	 * Creates a listener to handle change events in the specified slider.
	 */
	@Override
	protected ChangeListener createChangeListener(JSlider slider) {
		return new ChangeHandler();
	}

	/**
	 * Updates the dimensions for both thumbs.
	 */
	@Override
	protected void calculateThumbSize() {
		// Call superclass method for lower thumb size.
		super.calculateThumbSize();

		// Set upper thumb size.
		upperThumbRect.setSize(thumbRect.width, thumbRect.height);
	}

	/**
	 * Updates the locations for both thumbs.
	 */
	@Override
	protected void calculateThumbLocation() {
		// Call superclass method for lower thumb location.
		super.calculateThumbLocation();

		// Calculate upper thumb location. The thumb is centered over its
		// value on the track.
		int upperPosition = xPositionForValue(slider.getValue() + slider.getExtent());
		upperThumbRect.x = upperPosition - (upperThumbRect.width / 2);
		upperThumbRect.y = trackRect.y;
	}

	/**
	 * Returns the size of a thumb.
	 */
	@Override
	protected Dimension getThumbSize() {
		return new Dimension(12, 12);
	}

	/**
	 * Paints the slider. The selected thumb is always painted on top of the
	 * other thumb.
	 */
	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);

		Rectangle clipRect = g.getClipBounds();
		if (upperThumbSelected) {
			// Paint lower thumb first, then upper thumb.
			if (clipRect.intersects(thumbRect)) {
				paintLowerThumb(g);
			}
			if (clipRect.intersects(upperThumbRect)) {
				paintUpperThumb(g);
			}

		} else {
			// Paint upper thumb first, then lower thumb.
			if (clipRect.intersects(upperThumbRect)) {
				paintUpperThumb(g);
			}
			if (clipRect.intersects(thumbRect)) {
				paintLowerThumb(g);
			}
		}
	}


	/**
	 * Overrides superclass method to do nothing. Thumb painting is handled
	 * within the <code>paint()</code> method.
	 */
	@Override
	public void paintThumb(Graphics g) {
		// Do nothing.
	}

	/**
	 * Paints the thumb for the lower value using the specified graphics object.
	 */
	private void paintLowerThumb(Graphics g) {
		Rectangle knobBounds = thumbRect;
		int w = knobBounds.width;
		int h = knobBounds.height;

		// Create graphics copy.
		Graphics2D g2d = (Graphics2D) g.create();

		// Create default thumb shape.
		Shape thumbShape = createThumbShape(w - 1, h - 1);

		// Draw thumb.
		g2d.translate(knobBounds.x, knobBounds.y);

		g2d.fill(thumbShape);

		g2d.draw(thumbShape);

	}

	/**
	 * Paints the thumb for the upper value using the specified graphics object.
	 */
	private void paintUpperThumb(Graphics g) {
		Rectangle knobBounds = upperThumbRect;
		int w = knobBounds.width;
		int h = knobBounds.height;

		// Create graphics copy.
		Graphics2D g2d = (Graphics2D) g.create();

		// Create default thumb shape.
		Shape thumbShape = createThumbShape(w - 1, h - 1);

		// Draw thumb.
		g2d.translate(knobBounds.x, knobBounds.y);
		g2d.fill(thumbShape);
		g2d.draw(thumbShape);
	}

	/**
	 * Returns a Shape representing a thumb.
	 */
	private Shape createThumbShape(int width, int height) {
		// Use circular shape.
		Rectangle shape = new Rectangle(0, 0, width, height);
		return shape;
	}

	/**
	 * Sets the location of the upper thumb, and repaints the slider. This is
	 * called when the upper thumb is dragged to repaint the slider. The
	 * <code>setThumbLocation()</code> method performs the same task for the
	 * lower thumb.
	 */
	private void setUpperThumbLocation(int x, int y) {
		Rectangle upperUnionRect = new Rectangle();
		upperUnionRect.setBounds(upperThumbRect);

		upperThumbRect.setLocation(x, y);

		slider.repaint(upperUnionRect.x, upperUnionRect.y, upperUnionRect.width, upperUnionRect.height);
	}

	/**
	 * Listener to handle model change events. This calculates the thumb
	 * locations and repaints the slider if the value change is not caused by
	 * dragging a thumb.
	 */
	public class ChangeHandler implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			if (!lowerDragging && !upperDragging) {
				calculateThumbLocation();
				slider.repaint();
			}
		}
	}

	/**
	 * Listener to handle mouse movements in the slider track.
	 */
	public class RangeTrackListener extends TrackListener {

		@Override
		public void mousePressed(MouseEvent e) {
			if (!slider.isEnabled()) {
				return;
			}

			currentMouseX = e.getX();
			currentMouseY = e.getY();

			// Determine which thumb is pressed. If the upper thumb is
			// selected (last one dragged), then check its position first;
			// otherwise check the position of the lower thumb first.
			boolean lowerPressed = false;
			boolean upperPressed = false;
			if (upperThumbSelected || slider.getMinimum() == slider.getValue()) {
				if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
					upperPressed = true;
				} else if (thumbRect.contains(currentMouseX, currentMouseY)) {
					lowerPressed = true;
				}
			} else {
				if (thumbRect.contains(currentMouseX, currentMouseY)) {
					lowerPressed = true;
				} else if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
					upperPressed = true;
				}
			}

			// Handle lower thumb pressed.
			if (lowerPressed) {

				offset = currentMouseX - thumbRect.x;

				upperThumbSelected = false;
				lowerDragging = true;
				return;
			}
			lowerDragging = false;

			// Handle upper thumb pressed.
			if (upperPressed) {
				offset = currentMouseX - upperThumbRect.x;
				upperThumbSelected = true;
				upperDragging = true;
				return;
			}
			upperDragging = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			lowerDragging = false;
			upperDragging = false;
			slider.setValueIsAdjusting(false);
			super.mouseReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!slider.isEnabled()) {
				return;
			}

			currentMouseX = e.getX();
			currentMouseY = e.getY();

			if (lowerDragging) {
				slider.setValueIsAdjusting(true);
				moveLowerThumb();

			} else if (upperDragging) {
				slider.setValueIsAdjusting(true);
				moveUpperThumb();
			}
		}

		@Override
		public boolean shouldScroll(int direction) {
			return false;
		}

		/**
		 * Moves the location of the lower thumb, and sets its corresponding
		 * value in the slider.
		 */
		private void moveLowerThumb() {
			int thumbMiddle = 0;

			int halfThumbWidth = thumbRect.width / 2;
			int thumbLeft = currentMouseX - offset;
			int trackLeft = trackRect.x;
			int trackRight = trackRect.x + (trackRect.width - 1);
			int hMax = xPositionForValue(slider.getValue() + slider.getExtent());

			// Apply bounds to thumb position.
			if (drawInverted()) {
				trackLeft = hMax;
			} else {
				trackRight = hMax;
			}
			thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
			thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth);

			setThumbLocation(thumbLeft, thumbRect.y);

			// Update slider value.
			thumbMiddle = thumbLeft + halfThumbWidth;
			slider.setValue(valueForXPosition(thumbMiddle));
		}

		/**
		 * Moves the location of the upper thumb, and sets its corresponding
		 * value in the slider.
		 */
		private void moveUpperThumb() {
			int thumbMiddle = 0;

			int halfThumbWidth = thumbRect.width / 2;
			int thumbLeft = currentMouseX - offset;
			int trackLeft = trackRect.x;
			int trackRight = trackRect.x + (trackRect.width - 1);
			int hMin = xPositionForValue(slider.getValue());

			// Apply bounds to thumb position.
			if (drawInverted()) {
				trackRight = hMin;
			} else {
				trackLeft = hMin;
			}
			thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
			thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth);

			setUpperThumbLocation(thumbLeft, thumbRect.y);

			// Update slider extent.
			thumbMiddle = thumbLeft + halfThumbWidth;
			slider.setExtent(valueForXPosition(thumbMiddle) - slider.getValue());

		}
	}
}