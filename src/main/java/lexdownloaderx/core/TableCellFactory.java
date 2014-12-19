package lexdownloaderx.core;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Factory that creates a JavaFX TableCell
 * @param <S>
 */
public abstract class TableCellFactory<S, T> implements Callback<TableColumn<S,T>, TableCell<S,T>> {

    @Override
    public final TableCell<S, T> call(TableColumn<S, T> p) {
        return createTableCell(p);
    }

    protected abstract TableCell<S, T> createTableCell (TableColumn<S,T> column);
}