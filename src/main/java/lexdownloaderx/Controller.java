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

package lexdownloaderx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.core.DoubleClickableLotCellFactory;

public class Controller {

    @FXML
    public TableView<DownloadListInfo> tableView;
    @FXML
    private TableColumn<DownloadListInfo, String> nameColumn;
    @FXML
    private TableColumn<DownloadListInfo, String> authorColumn;
    @FXML
    private TableColumn<DownloadListInfo, String> versionColumn;
    @FXML
    private TableColumn<DownloadListInfo, Boolean> checkColumn;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("author"));
        versionColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("version"));
        checkColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, Boolean>("check"));

        checkColumn.setCellFactory(new Callback<TableColumn<DownloadListInfo, Boolean>, TableCell<DownloadListInfo, Boolean>>() {
            @Override
            public TableCell<DownloadListInfo, Boolean> call(TableColumn<DownloadListInfo, Boolean> downloadListInfoBooleanTableColumn) {
                final TableCell<DownloadListInfo, Boolean> cell = new TableCell<DownloadListInfo, Boolean>() {
                    @Override
                    public void updateItem(final Boolean item, boolean empty) {
                        if (item == null) return;

                        super.updateItem(item, empty);
                        if (! isEmpty()) {
                            final DownloadListInfo info = getTableView().getItems().get(getIndex());
                            CheckBox checkBox = new CheckBox();
                            if (info.getId() > 0) {
                                checkBox.selectedProperty().bindBidirectional(info.checkProperty());
                                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                                        info.checkProperty().setValue(newVal);
                                    }
                                });
                                setDisable(false);
                            } else {
                                setDisable(true);
                            }
                            setGraphic(checkBox);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        nameColumn.setCellFactory(new DoubleClickableLotCellFactory<DownloadListInfo, String>());
    }
}
