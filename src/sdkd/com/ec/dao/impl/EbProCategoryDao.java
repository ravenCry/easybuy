package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProCategory;
import java.util.*;
import java.sql.*;
/**
 * Created by HellCrow on 2016/7/7.
 */
public class EbProCategoryDao extends BaseDao{
    public List<EbProCategory> getProCategory()
    {
        List<EbProCategory> epcList=new ArrayList<EbProCategory>();
        String sql = "select * from ebprocategory";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbProCategory proCategory = new EbProCategory();
                proCategory.setId(rs.getInt("epc_id"));
                proCategory.setName(rs.getString("epc_name"));
                String pid=rs.getString("epc_parent_id");
                if(pid==null)
                    proCategory.setParentId(-1);
                else
                    proCategory.setParentId(Integer.parseInt(pid));
                epcList.add(proCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return epcList;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebprocategory(epc_name,epc_parent_id" +
                ") values(?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebprocategory set epc_name=? and epc_parent_id=?  " +
                 "where epc_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String epc_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from ebprocategory where epc_id=?";
        this.exeucteModify(sql,params);
    }
}
