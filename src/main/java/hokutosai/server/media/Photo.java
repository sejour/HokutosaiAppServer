package hokutosai.server.media;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Getter;

import org.springframework.web.multipart.MultipartFile;

public class Photo implements Media {

	private BufferedImage image;

	@Getter
	private String extension;

	public Photo(MultipartFile media, String ext) throws IOException {
		this.image = ImageIO.read(media.getInputStream());
		this.extension = ext;
	}

	public Photo(BufferedImage image, String ext) {
		this.image = image;
		this.extension = ext;
	}

	public Photo resize(double rate) {
		int newWidth = (int)(rate * this.image.getWidth());
		int newHeight = (int)(rate * this.image.getHeight());

		BufferedImage newImage = new BufferedImage(newWidth, newHeight, this.image.getType());
		Graphics2D g2d = newImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

		g2d.drawImage(image, 0, 0, newWidth, newHeight, null);

		return new Photo(newImage, this.extension);
	}

	public void write(BufferedOutputStream stream) throws IOException {
    	ImageIO.write(image, this.extension, stream);
	}

	public String getMediaType() {
		return "photo";
	}

	public int getWidth() {
		return this.image.getWidth();
	}

	public int getHeight() {
		return this.image.getHeight();
	}

	public int getTotalResolution() {
		return this.image.getWidth() * this.image.getHeight();
	}

}
