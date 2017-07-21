package com.turbovnc.vncviewer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class ButtonListener implements MouseListener {
	Border raised = new BevelBorder(BevelBorder.RAISED);
	Border lowered = new BevelBorder(BevelBorder.LOWERED);
	Border inactive = BorderFactory.createEmptyBorder(2, 2, 2, 2);
	;
	AbstractButton b;

	public ButtonListener(javax.swing.AbstractButton button) {
		b = button;
	}

	public void mousePressed(MouseEvent e) {
		if (!b.isEnabled())
			return;
		if (b instanceof javax.swing.JToggleButton) {
			b.setBorder((b.isSelected() ? inactive : lowered));
			b.setBorder(lowered);
			b.setBorderPainted((b.isSelected() ? false : true));
		} else {
			b.setBorder(lowered);
			b.setBorderPainted(true);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (!b.isEnabled())
			return;
		if (b instanceof javax.swing.JButton) {
			b.setBorder(inactive);
			b.setBorderPainted(false);
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (!b.isEnabled())
			return;
		if (b instanceof javax.swing.JToggleButton && b.isSelected())
			return;
		b.setBorder(raised);
		b.setBorderPainted(true);
	}

	public void mouseExited(MouseEvent e) {
		if (!b.isEnabled())
			return;
		if (b instanceof javax.swing.JToggleButton && b.isSelected())
			return;
		//b.setBorder(inactive);
		b.setBorderPainted(false);
	}
}