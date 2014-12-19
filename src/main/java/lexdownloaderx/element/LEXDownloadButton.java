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

package lexdownloaderx.element;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import net.caspervg.lex4j.auth.Auth;
import lexdownloaderx.Controller;
import lexdownloaderx.LEXDownloaderModel;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.service.LotDownloadService;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 23/11/13
 * Time: 22:34
 */
public class LEXDownloadButton extends Button implements EventHandler<ActionEvent>, InvalidationListener {

    private LEXDownloaderModel model;
    private boolean downloadAll;

    public LEXDownloadButton() {
        setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        final List<DownloadListInfo> dlList = Controller.tableModel;
        final double[] count = {0,0};
        File dir = model.getDirectory();
        model.setProgress(-1);
        model.setAttentionColor(Color.GREEN);
        model.setAttentionText("Downloading the files you requested..");

        for (DownloadListInfo dlItem : dlList) {
            if (downloadAll || dlItem.getCheck()) {
                count[1]++;
            }
        }

        for (final DownloadListInfo dlItem : dlList) {
            if (downloadAll || dlItem.getCheck()) {
                LotDownloadService service = new LotDownloadService();
                service.setAuth(new Auth(model.getUsername(), model.getPassword()));
                service.setId(dlItem.getId());
                service.setDirectory(dir);

                service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent workerStateEvent) {
                        count[0]++;
                        model.setProgress(count[0]/count[1]);
                        model.setAttentionColor(Color.GREEN);
                        model.setAttentionText(dlItem.getName() + " has been downloaded succesfully..");
                        dlList.remove(dlItem);
                    }
                });

                service.setOnFailed(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent workerStateEvent) {
                        count[0]++;
                        model.setProgress(count[0]/count[1]);
                        model.setAttentionColor(Color.RED);
                        model.setAttentionText("Downloading " + dlItem.getName() + " failed..");
                    }
                });

                service.start();
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

    public boolean isDownloadAll() {
        return downloadAll;
    }

    public void setDownloadAll(boolean downloadAll) {
        this.downloadAll = downloadAll;
    }

    @Override
    public void invalidated(Observable observable) {
        File dir = model.getDirectory();
        if (dir != null) {
            this.setDisabled(false);
            this.setDisable(false);
        } else {
            this.setDisabled(true);
            this.setDisable(true);
        }
    }

}
