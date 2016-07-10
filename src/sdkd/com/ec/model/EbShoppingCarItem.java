package sdkd.com.ec.model;

/**
 * Created by HellCrow on 2016/7/9.
 */
public class EbShoppingCarItem {
    public int getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(int esp_id) {
        this.esp_id = esp_id;
    }

    public EbProduct getEbProduct() {
        return ebProduct;
    }

    public void setEbProduct(EbProduct ebProduct) {
        this.ebProduct = ebProduct;
    }

    public int getEsh_quantity() {
        return esh_quantity;
    }

    public void setEsh_quantity(int esh_quantity) {
        this.esh_quantity = esh_quantity;
    }

    private int esp_id;
    private int esh_quantity;
    private EbProduct ebProduct;
}
