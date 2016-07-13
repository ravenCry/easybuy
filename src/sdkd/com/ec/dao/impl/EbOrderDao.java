package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/12.
 */
public class EbOrderDao extends BaseDao{
    public void insertOrder(EbUser ebUser,double eo_cost,String eo_status,String eo_type)
    {
        String sql="insert into eborder(eb_user_id,eb_user_name,eo_cost,eo_status,eo_type) values(?,?,?,?,?)";
        List<String> params=new ArrayList<String>();
        params.add(ebUser.getId()+"");
        params.add(ebUser.getName());
        params.add(eo_cost+"");
        params.add(eo_status);
        params.add(eo_type);
        this.exeucteModify(sql,params);
    }
    public List<EbOrder> getOrder(){
        List<EbOrder> orderList = new ArrayList<EbOrder>();
        String sql = "select * from eborder";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbOrder order= new EbOrder();
                order.setEo_id(rs.getInt("eo_id"));
                order.setEb_user_name(rs.getString("eb_user_name"));
                order.setEo_cost(rs.getDouble("eo_cost"));
                order.setEo_status(rs.getString("eo_status"));
                order.setEo_type(rs.getString("eo_type"));


                //添加到集合中
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public EbOrder getOrderRecent()
    {
        EbOrder ebOrder=new EbOrder();
        String sql="select max(eo_id) as recentId from eborder";
        ResultSet rs = this.executeSearch(sql,null);
        try {
            while (rs.next()){
                //添加到集合中
                ebOrder.setEo_id(rs.getInt("recentId"));
                ebOrder.setEo_type(rs.getString("eo_type"));
                ebOrder.setEo_cost(rs.getDouble("eo_cost"));
                ebOrder.setEo_status(rs.getString("eo_status"));
            }
        } catch (SQLException e) {

        }
        return ebOrder;
    }
    public void insert(List<String> params)
    {
        String sql="insert into eborder(eo_id,eb_user_id"+
                " ed_user_name) values(?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update eborde set eb_user_id=? , eb_user_name=?  " +
                "where eo_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String eo_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from eborder where eo_id=?";
        this.exeucteModify(sql,params);
    }
}
