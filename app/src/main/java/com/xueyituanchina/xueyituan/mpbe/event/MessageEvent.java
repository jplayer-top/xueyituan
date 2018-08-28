package com.xueyituanchina.xueyituan.mpbe.event;

/**
 * Created by Administrator on 2018/8/28.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageEvent {
    public String preText, key;

    public MessageEvent(String preText, String key) {
        this.key = key;
        this.preText = preText;
    }
}
