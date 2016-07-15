package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户DAO
 * Created by SDUST-132 on 2016/7/5.
 */
public class EbUserDao extends BaseDao {
    public EbUser match(String username, String password)
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
        List<EbUser> newsList = new ArrayList<EbUser>();
        String sql = "select * from ebuser";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbUser news = new EbUser();
                news.setId(rs.getInt("eb_user_id"));
                news.setName(rs.getString("eb_user_name"));
                news.setSex(rs.getString("eu_sex"));
                news.setBirthday(rs.getDate("eu_birthday"));
                news.setIdCode(rs.getString("eu_identity_code"));
                news.setEmail(rs.getString("eu_email"));
                news.setMobile(rs.getString("eu_mobile"));
                news.setAddress(rs.getString("eb_address"));
                news.setStatus(rs.getInt("eu_status"));
                news.setPassword(rs.getString("eu_password"));


                //添加到集合中
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebuser(eb_user_name,eu_password,eu_sex," +
                "eu_birthday,eu_mobile,eb_address,eu_status) values(?,?,?,?,?,?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebuser set eb_user_name=? , eu_password=? , " +
                "eu_sex=? , eu_birthday=? , eu_mobile=? , eb_address=? , eu_status=? " +
                "where eb_user_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String eb_user_id)
    {
        List<String> params=new ArrayList<String>();
        params.add(eb_user_id);
        String sql="delete from ebuser where eb_user_id=?";
        this.exeucteModify(sql,params);
    }
}
