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
}
