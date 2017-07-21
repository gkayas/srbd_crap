package com.turbovnc.vncviewer;

import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.turbovnc.rfb.Keysyms;

public class BottomToolbar extends JToolBar implements ActionListener {

    static final String[] buttonNames = {"Menu", "Home", "Back"};
    protected CodeGenerator codeGenerator;
    static final String[] home_icons = {
    	"icons/Menu.png",
    	"icons/Home.png",
    	"icons/Back.png",
    };
    long homeStartTime, homeEndTime;


    private final ClassLoader cl = getClass().getClassLoader();
    static Toolkit tk = Toolkit.getDefaultToolkit();
    private CConn cc;

	public BottomToolbar(CConn cc_, CodeGenerator cG) {
		super();
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		cc = cc_;
		this.codeGenerator = cG;

		if(ReadConfigUtil.getProfileName().equals("Wearable")){
			JButton button = new JButton();
			setImageToButton(button, home_icons[2], 156, 20);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cc.writeKeyEvent(Keysyms.XF86XK_Back, true);
				    cc.writeKeyEvent(Keysyms.XF86XK_Back, false);

				    if(!codeGenerator.isCodeRunning()) {
				    	codeGenerator.pressBack();
				    }
				}
			});
			add(button);
		}else{
			for (String name : buttonNames) {
				JButton button = new JButton();
				if(name.equals("Home")) {
					setImageToButton(button, home_icons[1], 156, 20);

					button.addMouseListener(new MouseAdapter() {
						@Override
				        public void mousePressed(MouseEvent e) {
							cc.writeKeyEvent(Keysyms.XF86XK_Home, true);
							homeStartTime = System.currentTimeMillis();
				        }

						@Override
				        public void mouseReleased(MouseEvent e) {
							homeEndTime = System.currentTimeMillis();
							cc.writeKeyEvent(Keysyms.XF86XK_Home, false);

							if(!codeGenerator.isCodeRunning()) {
								if(homeEndTime - homeStartTime < 500) {
									codeGenerator.pressHome();
								} else {
									codeGenerator.longPressHome();
								}

							}
				        }
					});

				} else if(name.equals("Menu")) {
					setImageToButton(button, home_icons[0], 156, 20);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							cc.writeKeyEvent(Keysyms.XF86XK_Menu, true);
						    cc.writeKeyEvent(Keysyms.XF86XK_Menu, false);

						    if(!codeGenerator.isCodeRunning()) {
						    	codeGenerator.pressMenu();
						    }
						}
					});
				}else if(name.equals("Back")) {
					setImageToButton(button, home_icons[2], 156, 20);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							cc.writeKeyEvent(Keysyms.XF86XK_Back, true);
						    cc.writeKeyEvent(Keysyms.XF86XK_Back, false);

						    if(!codeGenerator.isCodeRunning()) {
						    	codeGenerator.pressBack();
						    }
						}
					});
				}

				add(button);
			}
		}
	}

  private void setImageToButton(JButton button, String imgPath, int W, int H) {
    	Image toolbarImage = new ImageIcon(cl.getResource(imgPath)).getImage();
    	BufferedImage bi = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(toolbarImage, 0, 0, W, H, null);
		setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
		setFloatable(false);
		setBorder(new EmptyBorder(1, 2, 1, 0));
		ImageIcon icon = new ImageIcon(tk.createImage(bi.getSource()));
    	button.setIcon(icon);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		 cc.writeKeyEvent(Keysyms.XF86XK_Back, true);
	     cc.writeKeyEvent(Keysyms.XF86XK_Back, false);
	}

//	class HomeButtonActionListener implements
}
