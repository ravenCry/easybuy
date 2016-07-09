package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户DAO
 * Created by SDUST-132 on 2016/7/5.
 */
public class EbUserDao extends BaseDao{
    public EbUser match(String username,String password)
    {
        EbUser ebUser=new EbUser();
        String sql="select * from ebuser  where eb_user_name = ? and eu_password = ?";
        List<String> params = new ArrayList<String>();
        params.add(username);
        params.add(password);
        ResultSet rs = this.executeSearch(sql, params);
        try {
            while (rs.next()){
                //添加到集合中
                ebUser.setId(rs.getInt("eb_user_id"));
                ebUser.setName(rs.getString("eb_user_name"));
                ebUser.setPassword(rs.getString("eu_password"));
            }
        } catch (SQLException e) {

        }
        return ebUser;
    }
}
