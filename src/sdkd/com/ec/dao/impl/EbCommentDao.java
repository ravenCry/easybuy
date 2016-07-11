package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbComment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/7.
 */
public class EbCommentDao extends BaseDao{
    public List<EbComment> getComment(String sql, List<String> params) {
        List<EbComment> commentList = new ArrayList<EbComment>();
        //String sql = "select * from ebproduct limit 0,8";
        try {
            ResultSet rs = this.executeSearch(sql, params);
            while (rs.next()) {
                EbComment comment = new EbComment();
                comment.setId(rs.getInt("ec_id"));
                comment.setContent(rs.getString("ec_content"));
                comment.setReply(rs.getString("ec_reply"));
                comment.setCreateTime(rs.getDate("ec_create_time"));
                comment.setReplyTime(rs.getDate("ec_reply_time"));
                comment.setUsername(rs.getString("eb_user_name"));
                //添加到集合中
                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebcomment(ec_reply,ec_content" +
                "ec_create_time,ec_reply_time,eb_user_name) values(?,?,?,?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebcomment set ec_reply=? and ec_content=? and " +
                "ec_create_time=? and ec_reply_time=? and eb_user_name=? and eu_address=?" +
                "where ec_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String ec_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from ebcomment where ec_id=?";
        this.exeucteModify(sql,params);
    }
}
