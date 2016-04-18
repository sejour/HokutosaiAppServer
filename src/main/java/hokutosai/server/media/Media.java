package hokutosai.server.media;

import java.io.BufferedOutputStream;
import java.io.IOException;

public interface Media {

	public String getMediaType();

	public String getExtension();

	public void write(BufferedOutputStream stream) throws IOException;

}
