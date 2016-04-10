package hokutosai.server.io;

public class FileUtil {

	public static String getExtension(String fileName) throws StringIndexOutOfBoundsException{
	    int idx = fileName.lastIndexOf(".");
	    return fileName.substring(idx).substring(1);
	}

}
