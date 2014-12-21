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

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import lexdownloaderx.LEXDownloaderModel;
import lexdownloaderx.service.DownloadListService;
import lexdownloaderx.service.UserLoginService;

import java.util.HashSet;
import java.util.List;

/**
 * Button to log in the user
 */
public class LEXLoginButton extends Button implements EventHandler<ActionEvent> {

    private LEXDownloaderModel model;

    public LEXLoginButton() {
        setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        model.setProgress(-1);
        final Auth auth = new Auth(model.getUsername(), model.getPassword());

        UserLoginService userLoginService = new UserLoginService();
        userLoginService.setAuth(auth);
        userLoginService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                final User user = (User) t.getSource().getValue();
                model.setUser(user);

                DownloadListService downloadListService = new DownloadListService();
                downloadListService.setAuth(auth);
                downloadListService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        List<DownloadListItem> list = (List<DownloadListItem>) t.getSource().getValue();
                        model.setDownloadList(new HashSet<>(list));
                        model.setProgress(1.0);
                        model.setAttentionColor(Color.GREEN);
                        if (list.size() < 1) {
                            // There are no files to download!
                            model.setAttentionText(String.format("Welcome, %s. You have no files that are queued for downloading.", user.getUsername()));
                        } else {
                            // There are some files that the user could download
                            model.setAttentionText(String.format("Welcome, %s. Please select the files you want to download.", user.getUsername()));
                        }
                    }
                });

                downloadListService.start();
            }
        });

        userLoginService.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("Wrong credentials!");
                model.setProgress(0);
            }
        });

        userLoginService.start();
    }

    public LEXDownloaderModel getModel() {
        return model;
    }

    public void setModel(LEXDownloaderModel model) {
        this.model = model;
    }
}
