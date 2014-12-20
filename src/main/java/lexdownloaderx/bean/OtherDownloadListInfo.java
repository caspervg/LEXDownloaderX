package lexdownloaderx.bean;

import javafx.beans.property.BooleanProperty;
import lexdownloaderx.cleanitol.CleanitolItem;
import org.controlsfx.dialog.Dialogs;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

public class OtherDownloadListInfo implements DownloadListInfo {

    private String name;
    private String author;
    private int id;
    private String version;
    private Date updated;
    private URL url;
    private BooleanProperty check;

    public OtherDownloadListInfo(CleanitolItem item) {
        this.name = item.getFileName();
        this.author = "N/A";
        this.id = item.getId();
        this.version = "N/A";
        this.updated = new Date();
        this.url = item.getUrl();
    }

    public URL getUrl() {
        return url;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean getCheck() {
        return false;
    }

    @Override
    public void onDoubleClick() {
        try {
            Desktop.getDesktop().browse(url.toURI());
        }  catch (URISyntaxException | IOException e) {
            Dialogs.create()
                .title("Failed to show download page")
                .masthead("Exception Encountered")
                .showException(e);
        }
    }
}
