package com.zjdt.dtsjwb.Bean;

/**
 * Created by 71568 on 2018/4/3.
 */

public class MenuBean {
    String menuname;
    int menuIconRid;

    public MenuBean(String menuname, int menuIconRid) {
        this.menuname = menuname;
        this.menuIconRid = menuIconRid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getMenuIconRid() {
        return menuIconRid;
    }

    public void setMenuIconRid(int menuIconRid) {
        this.menuIconRid = menuIconRid;
    }
}
