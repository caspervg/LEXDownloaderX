package lexdownloaderx.element;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import lexdownloaderx.LEXDownloaderModel;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.bean.LEXDownloadListInfo;
import net.caspervg.lex4j.bean.DownloadListItem;

/**
 * Table containing the download list elements
 */
public class LEXDownloadListTableView extends TableView<DownloadListInfo> implements InvalidationListener {

    private LEXDownloaderModel model;
    private ObservableList<DownloadListInfo> list = FXCollections.observableArrayList();

    public LEXDownloadListTableView() {
        super();
        this.setItems(list);
    }

    @Override
    public void invalidated(Observable observable) {
        if (model.getDownloadList() != null) {
            if (model.getDownloadList().size() + model.getCleanitolList().size() == list.size()) {
                return;
            }

            list.clear();
            for (DownloadListItem item : model.getDownloadList()) {
                DownloadListItem.DownloadListItemLot lot = item.getLot();

                LEXDownloadListInfo info = new LEXDownloadListInfo(lot.getId(), lot.getName(), lot.getAuthor(), lot.getVersion(), lot.getUpdated());
                list.add(info);
            }
            for (DownloadListInfo info : model.getCleanitolList()) {
                list.add(info);
            }
        }
    }

    public LEXDownloaderModel getModel() {
        return model;
    }

    public void setModel(LEXDownloaderModel model) {
        this.model = model;
        model.addListener(this);
    }
}
