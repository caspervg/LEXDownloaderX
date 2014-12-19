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
import javafx.scene.control.TextField;
import lexdownloaderx.LEXDownloaderModel;

/**
 * Field for download directory
 */
public class LEXDirectoryField extends TextField implements InvalidationListener {

    private LEXDownloaderModel model;

    public LEXDirectoryField() {
        this.setDisabled(true);
    }

    @Override
    public void invalidated(Observable observable) {
        if (model.getDirectory() != null) {
            this.setText(model.getDirectory().getAbsolutePath());
        }
    }

    public void setModel(LEXDownloaderModel model) {
        this.model = model;
        model.addListener(this);
    }

    public LEXDownloaderModel getModel() {
        return this.model;
    }
}
