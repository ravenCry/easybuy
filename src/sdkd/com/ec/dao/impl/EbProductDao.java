package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/6.
 */
public class EbProductDao extends BaseDao {
    public List<EbProduct> getProduct(String sql,List<String> params) {
        List<EbProduct> productsList = new ArrayList<EbProduct>();
        //String sql = "select * from ebproduct limit 0,8";
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbProduct product = new EbProduct();
                product.setId(rs.getInt("ep_id"));
                product.setName(rs.getString("ep_name"));
                product.setPrice(rs.getDouble("ep_price"));
                product.setDescription(rs.getString("ep_description"));
                product.setStock(rs.getString("ep_stock"));
                product.setDiscount(rs.getInt("ep_discount"));
                product.setCid(rs.getInt("epc_id"));
                product.setCcid(rs.getInt("epc_child_id"));
                product.setFilename(rs.getString("ep_file_name"));
                product.setView(rs.getInt("ep_view"));
                //添加到集合中
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebproduct(ep_name,ep_decription,ep_price" +
                "ep_stock,epc_id,epc_child_id"+
        "ep_file_name,ep_view,ep_discount) values(?,?,?,?,?,?,?,?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebUser set ep_name=? and ep_decription=? and " +
                "ep_price=? and ep_stock=? and epc_id=? and epc_child_id=?" +
                "ep_file_name=? and ep_view=? and ep_discount=? "+
                "where eb_user_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String eb_user_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from ebproduct where ep_id=?";
        this.exeucteModify(sql,params);
    }
}
