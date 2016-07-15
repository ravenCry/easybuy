package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProduct;

import java.sql.*;
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
        String sql="insert into ebproduct(ep_name,ep_price," +
                "ep_stock,epc_id,epc_child_id," +
                "ep_file_name,ep_view,ep_discount) values(?,?,?,?,?,?,0,0)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebproduct set ep_name=?,"
                +"ep_price=?,ep_stock=?,epc_id=?,epc_child_id=?,"
                +"ep_file_name=? "
                +"where ep_id=?";
        this.exeucteModify(sql,params);
    }
    public void update(EbProduct ebProduct)
    {
        List<String> params=new ArrayList<String>();
        params.add(ebProduct.getName());
        params.add(ebProduct.getDescription());
        params.add(ebProduct.getPrice()+"");
        params.add(ebProduct.getStock());
        params.add(ebProduct.getCid()+"");
        params.add(ebProduct.getCcid()+"");
        params.add(ebProduct.getFilename());
        params.add(ebProduct.getView()+"");
        params.add(ebProduct.getDiscount()+"");
        params.add(ebProduct.getId()+"");
        String sql="update ebproduct set ep_name=?,"
            +"ep_price=?,ep_stock=?,epc_id=?,epc_child_id=?,"
            +"ep_file_name=?"
        +"where ep_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String ep_id)
    {
        List<String> params=new ArrayList<String>();
        params.add(ep_id);
        String sql="delete from ebproduct where ep_id=?";
        this.exeucteModify(sql,params);
    }
    public List<EbProduct> getProductPager(int pageIndex,int pageSize,String epc_child_id){
        //pageIndex 1 2 3 4 5 6
        List<EbProduct> productList = new ArrayList<EbProduct>();
        String sql="select * from ebproduct where epc_child_id=? limit ?,?";
        ResultSet rs = null;
        try
        {
            Connection con = this.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            int start = (pageIndex * pageSize)-pageSize;
            int col1=1;
            int col2=2;
            int col3=3;
            ps.setString(col1,epc_child_id);
            ps.setInt(col2,start);
            ps.setInt(col3,pageSize);
            rs = ps.executeQuery();
            try {
                while(rs.next()){
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
                    productList.add(product);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return productList;
    }
    public int getProductCount(){
        int count = 0;
        String sql = "select count(ep_id) from ebproduct";
        ResultSet rs = this.executeSearch(sql,null);
        try {
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
