package sdkd.com.ec.model;

/**
 * Created by HellCrow on 2016/7/12.
 */
public class EbOrderDetail {
    public int getEod_id() {
        return eod_id;
    }

    public void setEod_id(int eod_id) {
        this.eod_id = eod_id;
    }

    public EbOrder getEbOrder() {
        return ebOrder;
    }

    public void setEbOrder(EbOrder ebOrder) {
        this.ebOrder = ebOrder;
    }

    public int getEod_quantity() {
        return eod_quantity;
    }

    public void setEod_quantity(int eod_quantity) {
        this.eod_quantity = eod_quantity;
    }

    public EbProduct getEbProduct() {
        return ebProduct;
    }

    public void setEbProduct(EbProduct ebProduct) {
        this.ebProduct = ebProduct;
    }

    int eod_id;
    EbOrder ebOrder;
    int eod_quantity;
    EbProduct ebProduct;
}
