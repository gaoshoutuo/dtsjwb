package com.zjdt.dtsjwb.Bean;

/**
 * Created by 71568 on 2018/4/3.
 */

public class MenuBean {
    private String menuname;
    private int menuIconRid;
    private boolean haveRp;

    public MenuBean(String menuname, int menuIconRid,boolean isHave) {
        this.menuname = menuname;
        this.menuIconRid = menuIconRid;
        this.haveRp=isHave;
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
    public MenuBean() {

    }

    public boolean isHaveRp() {
        return haveRp;
    }

    public void setHaveRp(boolean haveRp) {
        this.haveRp = haveRp;
    }
}
