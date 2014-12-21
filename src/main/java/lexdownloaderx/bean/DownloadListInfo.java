package lexdownloaderx.bean;

import javafx.beans.property.BooleanProperty;

public interface DownloadListInfo {
    public void onDoubleClick();
    public int getId();
    public String getName();
    public boolean getCheck();
    public BooleanProperty checkProperty();
}
