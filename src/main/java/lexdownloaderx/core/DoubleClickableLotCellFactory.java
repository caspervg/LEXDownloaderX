package lexdownloaderx.core;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import lexdownloaderx.bean.DownloadListInfo;

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
                ((DownloadListInfo) (((DoubleClickableTableCell) t.getSource()).getTableRow().getItem())).onDoubleClick();
            }
        }
    }

    @Override
    protected TableCell<S, T> createTableCell(TableColumn<S, T> column) {
        return new DoubleClickableTableCell<>();
    }
}