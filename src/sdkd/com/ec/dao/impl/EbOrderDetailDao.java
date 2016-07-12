package sdkd.com.ec.dao.impl;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbOrder;
import sdkd.com.ec.model.EbProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HellCrow on 2016/7/12.
 */
public class EbOrderDetailDao extends BaseDao {
    public void insertOrderDetail(EbOrder ebOrder, EbProduct ebProduct, int quantity)
    {
        String sql="insert into eborderdetail(eo_id,ep_id,eod_quantity,eod_cost) values(?,?,?,?)";
        List<String> params=new ArrayList<String>();
        params.add(ebOrder.getEo_id()+"");
        params.add(ebProduct.getId()+"");
        params.add(quantity+"");
        params.add(quantity*ebProduct.getPrice()+"");
        this.exeucteModify(sql,params);
    }
}
