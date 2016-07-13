package sdkd.com.ec.model;

/**
 * Created by HellCrow on 2016/7/12.
 */
public class EbOrder {
    public int getEo_id() {
        return eo_id;
    }

    public void setEo_id(int eo_id) {
        this.eo_id = eo_id;
    }

    public EbUser getEbUser() {
        return ebUser;
    }

    public void setEbUser(EbUser ebUser) {
        this.ebUser = ebUser;
    }

    public double getEo_cost() {
        return eo_cost;
    }

    public void setEo_cost(double eo_cost) {
        this.eo_cost = eo_cost;
    }

    public String getEo_status() {
        return eo_status;
    }

    public void setEo_status(String eo_status) {
        this.eo_status = eo_status;
    }

    public String getEo_type() {
        return eo_type;
    }

    public void setEo_type(String eo_type) {
        this.eo_type = eo_type;
    }
    int eo_id;
    EbUser ebUser;
    double eo_cost;
    String eo_status;
    String eo_type;

    public String getEb_user_name() {
        return eb_user_name;
    }

    public void setEb_user_name(String eb_user_name) {
        this.eb_user_name = eb_user_name;
    }

    String eb_user_name;

    public String getEb_address() {
        return eb_address;
    }

    public void setEb_address(String eb_address) {
        this.eb_address = eb_address;
    }

    String eb_address;
}
