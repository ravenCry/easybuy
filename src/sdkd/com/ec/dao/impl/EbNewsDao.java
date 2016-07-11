package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDUST-132 on 2016/7/6.
 */
public class EbNewsDao extends BaseDao {
    public List<EbNews> getNews(){
        List<EbNews> newsList = new ArrayList<EbNews>();
        String sql = "select * from ebnews order by en_create_time desc limit 0,7";
        try {
            ResultSet rs = this.executeSearch(sql,null);
            while (rs.next()){
                EbNews news = new EbNews();
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                //添加到集合中
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public EbNews getNewsById(int id)
    {
        EbNews news=new EbNews();
        String sql = "select * from ebnews where en_id=?";
        List<String> params=new ArrayList<String>();
        try {
            params.add(id+"");
            ResultSet rs = this.executeSearch(sql,params);
            while (rs.next()){
                news.setEnId(rs.getInt("en_id"));
                news.setEnTitle(rs.getString("en_title"));
                news.setEnContent(rs.getString("en_content"));
                //添加到集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }
    public void insert(List<String> params)
    {
        String sql="insert into ebnews(en_title,en_content"+
                "en_create_time ) values(?,?,?)";
        this.exeucteModify(sql,params);
    }
    public void update(List<String> params)
    {
        String sql="update ebnews set en_title=? and en_content=? and " +
                "en_create_time" +
                "where en_id=?";
        this.exeucteModify(sql,params);
    }
    public void delete(String en_id)
    {
        List<String> params=new ArrayList<String>();
        String sql="delete from ebnews where en_id=?";
        this.exeucteModify(sql,params);
    }
}
