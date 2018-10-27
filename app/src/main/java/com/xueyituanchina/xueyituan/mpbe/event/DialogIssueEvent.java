package com.xueyituanchina.xueyituan.mpbe.event;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.event
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogIssueEvent {
    public String desc;
    public float rating;
    public boolean checked;

    public DialogIssueEvent(String desc, float rating, boolean checked) {
        this.checked = checked;
        this.rating = rating;
        this.desc = desc;
    }
}
