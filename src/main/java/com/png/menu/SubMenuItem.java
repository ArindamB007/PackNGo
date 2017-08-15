package com.png.menu;

import java.util.ArrayList;

public class SubMenuItem extends Menu{
    private String type;
    ArrayList<Menu> submenuItems;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Menu> getSubmenuItems() {
        return submenuItems;
    }

    public void setSubmenuItems(ArrayList<Menu> submenuItems) {
        this.submenuItems = submenuItems;
    }
}
