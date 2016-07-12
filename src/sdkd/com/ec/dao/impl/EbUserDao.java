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
                ebUser.setStatus(rs.getInt("eu_status"));
            }
        } catch (SQLException e) {

        }
        return ebUser;
    }
    public List<EbUser> getUser(){
        List<EbUser> userList=new ArrayList<EbUser>();
        String sql = "select * from ebuser";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbUser user = new EbUser();
                user.setId(rs.getInt("eb_user_id"));
                user.setName(rs.getString("eb_user_name"));
                user.setSex(rs.getString("eu_sex"));
                user.setBirthday(rs.getDate("eu_birthday"));
                user.setIdCode(rs.getString("eu_identity_code"));
                user.setEmail(rs.getString("eu_email"));
                user.setMobile(rs.getString("eu_mobile"));
                user.setAddress(rs.getString("eb_address"));
                user.setStatus(rs.getInt("eu_status"));


                //添加到集合中
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebUser(eb_user_name,eu_password,eu_sex" +
                "eu_birthday,eu_mobile,eu_address) values(?,?,?,?,?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebUser set eb_user_name=? and eu_password=? and " +
                "eu_sex=? and eu_birthday=? and eu_mobile=? and eu_address=?" +
                "where eb_user_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String eb_user_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from ebUser where eb_user_id=?";
        this.exeucteModify(sql,params);
    }
}
