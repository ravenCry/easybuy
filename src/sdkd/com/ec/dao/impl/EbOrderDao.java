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
}
