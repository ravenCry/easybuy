package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbShoppingCarItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/8.
 */
public class EbShoppingCarDao extends BaseDao{
    public List<EbShoppingCarItem> getShoppingCarByUserId(String userId)
    {
        List<EbShoppingCarItem> shoppingCarItems=new ArrayList<EbShoppingCarItem>();
        List<String> params=new ArrayList<String>();
        params.add(userId);
        String sql="select ebshoppingcar.esp_id,ebshoppingcar.esh_quantity,ebproduct.ep_id,ebproduct.ep_name,ebproduct.ep_description,ebproduct.ep_price,ebproduct.ep_stock,ebproduct.epc_id," +
                "ebproduct.epc_child_id,ebproduct.ep_file_name,ebproduct.ep_view,ebproduct.ep_discount " +
                "from ebshoppingcar,ebuser,ebproduct where ebuser.eb_user_id=? and ebuser.eb_user_id=ebshoppingcar" +
                ".eb_user_id and ebshoppingcar.ep_id=ebproduct.ep_id";
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbProduct ebProduct=new EbProduct();
                EbShoppingCarItem ebShoppingCarItem=new EbShoppingCarItem();
                ebProduct.setId(rs.getInt("ep_id"));
                ebProduct.setName(rs.getString("ep_name"));
                ebProduct.setDescription(rs.getString("ep_description"));
                ebProduct.setPrice(rs.getDouble("ep_price"));
                ebProduct.setStock(rs.getString("ep_stock"));
                ebProduct.setCid(rs.getInt("epc_id"));
                ebProduct.setCcid(rs.getInt("epc_child_id"));
                ebProduct.setFilename(rs.getString("ep_file_name"));
                ebProduct.setView(rs.getInt("ep_view"));
                ebProduct.setDiscount(rs.getInt("ep_discount"));
                ebShoppingCarItem.setEsp_id(rs.getInt("esp_id"));
                ebShoppingCarItem.setEsh_quantity(rs.getInt("esh_quantity"));
                ebShoppingCarItem.setEbProduct(ebProduct);
                shoppingCarItems.add(ebShoppingCarItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCarItems;
    }
    public void insertProductInMyShoppingCar(String productId,String userId)
    {
        List<String> params=new ArrayList<String>();
        params.add(productId);
        params.add(userId);
        String sql="update ebshoppingcar set esh_quantity = esh_quantity + 1 where ep_id = ? and eb_user_id = ?";
        int modifyRows = this.exeucteModify(sql,params);
        if(modifyRows==0)
        {
            sql="insert into ebshoppingcar(ep_id,eb_user_id,esh_quantity) values (?,?,1)";
            this.exeucteModify(sql,params);
        }
    }
    public void updateProductInMyShoppingCar(String item_id,String quantity)
    {
        List<String> params=new ArrayList<String>();
        params.add(quantity);
        params.add(item_id);
        String sql="update ebshoppingcar set esh_quantity = ? where esp_id = ?";
        this.exeucteModify(sql,params);
    }
    public void deleteProductInMyShoppingCar(String item_id)
    {
        List<String> params=new ArrayList<String>();
        params.add(item_id);
        String sql="delete from ebshoppingcar where esp_id = ?";
        this.exeucteModify(sql,params);
    }
}
