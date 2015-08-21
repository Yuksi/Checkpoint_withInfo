package checkpoint;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackPanel extends JPanel {
	private BufferedImage originalImage = null;
	private Image image = null;
	public BackPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				formComponentResized(evt);
			}
		});
	}

	private void formComponentResized(ComponentEvent evt) {		// Реакция на изменение размеров панели - изменение размера изображения.
		int w = this.getWidth();
		int h = this.getHeight();
		if ((originalImage != null) && (w > 0) && (h > 0)) {
			image = originalImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
			this.repaint();
		}
	}
	
	public BufferedImage getImage() {
		return originalImage;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public void setImageFile(File imageFile) {
		try {
			if (imageFile == null) {
				originalImage = null;
			}
			BufferedImage bi = ImageIO.read(imageFile);
			originalImage = bi;
		} catch (IOException ex) {
			System.err.println("Error image");
			ex.printStackTrace();
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		if (image != null) {					// Рисуем картинку
			g.drawImage(image, 0, 0, null);
		}
		super.paintChildren(g);			// Рисуем подкомпоненты.
		super.paintBorder(g);
	}
}
