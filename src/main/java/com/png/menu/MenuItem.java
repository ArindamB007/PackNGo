package com.png.menu;

import org.springframework.stereotype.Component;

public class MenuItem extends Menu{
    private String linkURL;
    private String dataId;
    private String icon;
    private String iconClass;
    private Boolean showLabel;
    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public Boolean getShowLabel() {
        return showLabel;
    }

    public void setShowLabel(Boolean showLabel) {
        this.showLabel = showLabel;
    }
}
