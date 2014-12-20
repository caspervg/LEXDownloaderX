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

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lexdownloaderx.bean.DownloadListInfo;
import lexdownloaderx.core.Model;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the LEX Downloader X
 */
public class LEXDownloaderModel extends Model {

    private String username;
    private String password;
    private User user;
    private double progress;
    private List<DownloadListItem> downloadList;
    private List<DownloadListInfo> cleanitolList;
    private File directory;
    private Paint attentionColor;
    private String attentionText;

    public LEXDownloaderModel() {
        attentionColor = Color.BLACK;
        attentionText = "Welcome to the SC4D LEX Downloader X.";
        cleanitolList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        fireInvalidationEvent();
    }

    public List<DownloadListItem> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(List<DownloadListItem> downloadList) {
        this.downloadList = downloadList;

        fireInvalidationEvent();
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
        fireInvalidationEvent();
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
        fireInvalidationEvent();
    }

    public Paint getAttentionColor() {
        return attentionColor;
    }

    public void setAttentionColor(Paint attentionColor) {
        this.attentionColor = attentionColor;
    }

    public String getAttentionText() {
        return attentionText;
    }

    public void setAttentionText(String attentionText) {
        this.attentionText = attentionText;
        fireInvalidationEvent();
    }

    public List<DownloadListInfo> getCleanitolList() {
        return cleanitolList;
    }

    public void setCleanitolList(List<DownloadListInfo> cleanitolList) {
        this.cleanitolList = cleanitolList;
        fireInvalidationEvent();
    }
}
