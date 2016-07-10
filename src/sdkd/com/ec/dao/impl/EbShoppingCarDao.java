package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/8.
 */
public class EbShoppingCarDao extends BaseDao{
    public List<EbProduct> getShoppingCarByUserId(String userId)
    {
        List<EbProduct> shoppingCarProducts=new ArrayList<EbProduct>();
        List<String> params=new ArrayList<String>();
        params.add(userId);
        String sql="select * from ebshoppingcar,ebuser,ebproduct where ebuser.eb_user_id=? and ebuser.eb_user_id=ebshoppingcar" +
                ".eb_user_id and ebshoppingcar.ep_id=ebproduct.ep_id";
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbProduct ebProduct=new EbProduct();
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
                shoppingCarProducts.add(ebProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCarProducts;
    }
}
