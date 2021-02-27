package planetbound.iu.graph;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class Resources {
    public static InputStream getResourceFile(String name) {
        InputStream in = Resources.class.getResourceAsStream(name);
        return in;
    }
    
    public static File getResourceSom(String name) {
        URL in = Resources.class.getResource(name);
        File file = new File(in.getPath());
        return file;
    }
}//Resources