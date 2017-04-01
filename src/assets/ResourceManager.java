package assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class ResourceManager {
	public static InputStream getResourceAsStream(String dir) {
		return ResourceManager.class.getResourceAsStream(dir);
	}

	public static BufferedImage getImage(String dir) {
		try {
			return ImageIO.read(getResourceAsStream(dir));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
