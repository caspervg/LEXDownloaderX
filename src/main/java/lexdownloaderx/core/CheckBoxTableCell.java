/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Casper Van Gheluwe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package lexdownloaderx.core;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;

/**
 * JavaFX TableCell with a checkbox
 * @param <S> Checkbox input object (for example: <code>DownloadListInfo</code>)
 * @param <T> Checkbox output value (for example: <code>Boolean</code>)
 */
public class CheckBoxTableCell<S, T> extends TableCell<S, T> {

    private final CheckBox checkBox;
    private ObservableValue<T> ov;
    private S ses;

    public CheckBoxTableCell() {
        this.checkBox = new CheckBox();
        this.checkBox.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
        setGraphic(checkBox);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(checkBox);
            if (ov instanceof BooleanProperty) {
                checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
            }

            ov = getTableColumn().getCellObservableValue(getIndex());

            if (ov instanceof BooleanProperty) {
                checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
            }
        }
    }
}
