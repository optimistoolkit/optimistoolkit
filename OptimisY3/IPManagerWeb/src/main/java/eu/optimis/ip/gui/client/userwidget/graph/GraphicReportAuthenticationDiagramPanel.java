/**
 * Copyright (C) 2010-2013 Barcelona Supercomputing Center
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version. This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with this library; if not, write
 * to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 */
package eu.optimis.ip.gui.client.userwidget.graph;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class GraphicReportAuthenticationDiagramPanel extends ContentPanel {

    private LabelToolItem labelToolItem = new LabelToolItem();

    public GraphicReportAuthenticationDiagramPanel() {
        setHeading("Welcome to the OPTIMIS Infrastructure Provider website");
        setLayout(new FitLayout());
        ToolBar toolBar = new ToolBar();
        toolBar.add(labelToolItem);
        setTopComponent(toolBar);
    }

    public void setSubmissionText(String text) {
        labelToolItem.setLabel(text);
    }
}
