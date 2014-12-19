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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.core.CheckBoxTableCellFactory;
import lexdownloaderx.core.DoubleClickableLotCellFactory;

public class Controller {

    @FXML
    private TableView<DownloadListInfo> tableView;
    @FXML
    private TableColumn<DownloadListInfo, String> nameColumn;
    @FXML
    private TableColumn<DownloadListInfo, String> authorColumn;
    @FXML
    private TableColumn<DownloadListInfo, String> versionColumn;
    @FXML
    private TableColumn<DownloadListInfo, Boolean> checkColumn;

    public static ObservableList<DownloadListInfo> tableModel = FXCollections.observableArrayList();


    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("author"));
        versionColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, String>("version"));
        checkColumn.setCellValueFactory(new PropertyValueFactory<DownloadListInfo, Boolean>("check"));

        checkColumn.setCellFactory(new CheckBoxTableCellFactory<DownloadListInfo, Boolean>());
        nameColumn.setCellFactory(new DoubleClickableLotCellFactory<DownloadListInfo, String>());
    }
}
