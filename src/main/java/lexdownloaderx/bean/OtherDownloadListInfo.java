package lexdownloaderx.bean;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
        this.check = new SimpleBooleanProperty(false);
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

    public BooleanProperty checkProperty() {
        return check;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtherDownloadListInfo that = (OtherDownloadListInfo) o;

        if (id != that.id) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
