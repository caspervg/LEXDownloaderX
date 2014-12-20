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

package lexdownloaderx.bean;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lexdownloaderx.service.LotService;
import net.caspervg.lex4j.bean.Lot;

import java.util.Date;

/**
 * Contains the required information for a table download list item row
 */
public class LEXDownloadListInfo implements DownloadListInfo {

    private String name;
    private String author;
    private int id;
    private String version;
    private Date updated;
    private BooleanProperty check;

    public LEXDownloadListInfo(int i, String n, String a, String v, Date u) {
        this.id = i;
        this.name = n;
        this.author = a;
        this.check = new SimpleBooleanProperty(true);
        this.version = "v. " + v;
        this.updated = u;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version =  "v. " + version;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean getCheck() {
        return check.get();
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check.set(check);
    }

    @Override
    public void onDoubleClick() {
        LotService service = new LotService();
        service.setId(this.id);
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
