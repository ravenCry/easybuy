package sdkd.com.ec.model;

import java.util.Date;

/**
 * Created by HellCrow on 2016/7/14.
 */
public class EbWord {
    public int getEw_id() {
        return ew_id;
    }

    public void setEw_id(int ew_id) {
        this.ew_id = ew_id;
    }

    public String getEw_name() {
        return ew_name;
    }

    public void setEw_name(String ew_name) {
        this.ew_name = ew_name;
    }

    public String getEw_content() {
        return ew_content;
    }

    public void setEw_content(String ew_content) {
        this.ew_content = ew_content;
    }

    public Date getEw_creat_time() {
        return ew_creat_time;
    }

    public void setEw_creat_time(Date ew_creat_time) {
        this.ew_creat_time = ew_creat_time;
    }

    public String getEw_reply() {
        return ew_reply;
    }

    public void setEw_reply(String ew_reply) {
        this.ew_reply = ew_reply;
    }

    public Date getEw_reply_time() {
        return ew_reply_time;
    }

    public void setEw_reply_time(Date ew_reply_time) {
        this.ew_reply_time = ew_reply_time;
    }

    int ew_id;
    String ew_name;
    String ew_content;
    Date ew_creat_time;
    String ew_reply;
    Date ew_reply_time;
}
