package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbWord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/14.
 */
public class EbWordDao extends BaseDao{
    public List<EbWord> selectAll()
    {
        List<EbWord> ebWords=new ArrayList<EbWord>();
        String sql="select * from ebword";
        try {
            ResultSet rs=this.executeSearch(sql,null);
            while (rs.next()){
                //添加到集合中
                EbWord ebWord=new EbWord();
                ebWord.setEw_id(rs.getInt("ew_id"));
                ebWord.setEw_name(rs.getString("ew_name"));
                ebWord.setEw_content(rs.getString("ew_content"));
                ebWord.setEw_creat_time(rs.getDate("ew_creat_time"));
                ebWord.setEw_reply(rs.getString("ew_reply"));
                ebWord.setEw_reply_time(rs.getDate("ew_reply_time"));
                ebWords.add(ebWord);
                System.out.println(ebWord.getEw_id());
            }
        } catch (SQLException e) {

        }
        return  ebWords;
    }
    public void delete(String ew_id)
    {
        List<String> params=new ArrayList<String>();
        params.add(ew_id);
        String sql="delete from ebword where ew_id=?";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebword set ew_reply=?,ew_reply_time=? where ew_id=?";
        this.exeucteModify(sql,params);
    }
    public void insert(List<String> params)
    {
        String sql="insert ebword(ew_name,ew_content,ew_creat_time) values(?,?,?)";
        this.exeucteModify(sql,params);
    }
}
