package lexdownloaderx.core;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.caspervg.lex4j.bean.Lot;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.service.LotService;

/**
 * JavaFX TableCell that has a doubleclick action attached to it
 * @param <S> Checkbox input object (for example: <code>DownloadListInfo</code>)
 * @param <T> Checkbox output value (for example: <code>Boolean</code>)
 */
public class DoubleClickableLotCellFactory<S, T> extends TableCellFactory<S, T> {


    private class DoubleClickableTableCell<S, T> extends TextFieldTableCell<S, T> implements EventHandler<MouseEvent> {

        public DoubleClickableTableCell() {
            setOnMouseClicked(DoubleClickableTableCell.this);
        }

        @Override
        public void handle(MouseEvent t) {
            if (t.getClickCount() > 1) {
                int lotId = ((DownloadListInfo) (((DoubleClickableTableCell) t.getSource()).getTableRow().getItem())).getId();

                LotService service = new LotService();
                service.setId(lotId);
                service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        final Lot lot = (Lot) event.getSource().getValue();
                        openLotInfo(lot);
                    }

                    private void openLotInfo(Lot lot) {
                        String url = "http://sc4devotion.com/csxlex/" + lot.getImages().get("primary");
                        Stage stage = new Stage();
                        stage.setScene(new Scene(new Group(new ImageView(new Image(url)))));
                        stage.sizeToScene();
                        stage.setTitle(lot.getName());
                        stage.show();
                    }
                });
                service.start();

            }
        }
    }

    @Override
    protected TableCell<S, T> createTableCell(TableColumn<S, T> column) {
        return new DoubleClickableTableCell<>();
    }
}