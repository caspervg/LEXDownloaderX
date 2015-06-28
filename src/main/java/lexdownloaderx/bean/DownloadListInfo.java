package lexdownloaderx.bean;

import javafx.beans.property.BooleanProperty;

public interface DownloadListInfo {
    void onDoubleClick();
    int getId();
    String getName();
    boolean getCheck();
    BooleanProperty checkProperty();
}
