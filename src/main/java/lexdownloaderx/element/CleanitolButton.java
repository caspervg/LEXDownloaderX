package lexdownloaderx.element;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import lexdownloaderx.LEXDownloaderModel;
import lexdownloaderx.bean.LEXDownloadListInfo;
import lexdownloaderx.bean.OtherDownloadListInfo;
import lexdownloaderx.cleanitol.CleanitolItem;
import lexdownloaderx.cleanitol.CleanitolParser;
import lexdownloaderx.service.LotService;
import net.caspervg.lex4j.bean.Lot;
import org.controlsfx.dialog.Dialogs;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CleanitolButton extends Button implements EventHandler<ActionEvent>, InvalidationListener {

    private LEXDownloaderModel model;

    public CleanitolButton() {
        setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the Cleanitol file to add..");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Future<List<CleanitolItem>> future = CleanitolParser.parseCleanitol(file.toPath());

            try {
                model.setProgress(0);
                List<CleanitolItem> items = future.get();
                final int numItems = items.size();
                final int[] numDone = {0};
                for(CleanitolItem item : items) {
                    if (item.getId() > 0) {
                        LotService service = new LotService();
                        service.setId(item.getId());
                        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                            @Override
                            public void handle(WorkerStateEvent workerStateEvent) {
                                Lot lot = (Lot) workerStateEvent.getSource().getValue();
                                model.getCleanitolList().add(new LEXDownloadListInfo(lot.getId(),
                                        lot.getName(),
                                        lot.getAuthor(),
                                        lot.getVersion(),
                                        lot.getUpdated()));
                                model.setProgress(++numDone[0] / (double) numItems);
                            }
                        });
                        service.start();
                    } else {
                        model.getCleanitolList().add(new OtherDownloadListInfo(item));
                        model.setProgress(++numDone[0] / (double) numItems);
                    }

                }
            } catch (InterruptedException | ExecutionException e) {
                Dialogs.create()
                        .title("Failed to parse Cleanitol file")
                        .masthead("Exception encountered")
                        .showException(e);
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

    @Override
    public void invalidated(Observable observable) {
        if (model.getUser() == null) {
            this.setDisable(true);
        } else {
            this.setDisable(false);
        }
    }
}
