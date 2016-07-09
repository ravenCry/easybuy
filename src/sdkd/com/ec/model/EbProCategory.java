package sdkd.com.ec.model;

/**
 * Created by HellCrow on 2016/7/7.
 */
public class EbProCategory {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    private int id;
    private String name;
    private int parentId;
}
