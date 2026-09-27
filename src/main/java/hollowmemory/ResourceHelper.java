package hollowmemory;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.scene.image.Image;

public final class ResourceHelper {

    private ResourceHelper() {
    }

    public static URL resource(String absolutePath) {
        URL resource = ResourceHelper.class.getResource(absolutePath);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + absolutePath);
        }
        return resource;
    }

    public static Image image(String absolutePath) throws FileNotFoundException {
        URL resource = ResourceHelper.class.getResource(absolutePath);
        if (resource == null) {
            throw new FileNotFoundException("Resource not found: " + absolutePath);
        }
        return new Image(resource.toExternalForm());
    }
}
