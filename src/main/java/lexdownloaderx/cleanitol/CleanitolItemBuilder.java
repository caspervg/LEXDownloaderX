package lexdownloaderx.cleanitol;

import java.net.URL;

public class CleanitolItemBuilder {
    protected String fileName;
    protected URL url;
    protected int id;

    public CleanitolItemBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CleanitolItemBuilder Url(URL url) {
        this.url = url;
        return this;
    }

    public CleanitolItemBuilder id(int id) {
        this.id = id;
        return this;
    }

    public CleanitolItem build() {
        return new CleanitolItem(this);
    }
}
