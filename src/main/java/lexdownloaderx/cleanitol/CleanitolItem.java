package lexdownloaderx.cleanitol;

import java.net.URL;

public class CleanitolItem {
    private String fileName;
    private URL url;
    private int id;

    private CleanitolItem() {}

    public CleanitolItem(CleanitolItemBuilder builder) {
        this.fileName = builder.fileName;
        this.url = builder.url;
        this.id = builder.id;
    }

    public String getFileName() {
        return fileName;
    }

    public URL getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}
