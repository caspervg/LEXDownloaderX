package lexdownloaderx.core;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

/**
 * Factory that creates JavaFX TableCells with a checkbox
 * @param <S> Checkbox input object (for example: <code>DownloadListInfo</code>)
 * @param <T> Checkbox output value (for example: <code>Boolean</code>)
 */
public class CheckBoxTableCellFactory<S, T> extends TableCellFactory<S, T> {

    @Override
    protected TableCell<S, T> createTableCell(TableColumn<S, T> column) {
        return new CheckBoxTableCell<>();
    }
}
