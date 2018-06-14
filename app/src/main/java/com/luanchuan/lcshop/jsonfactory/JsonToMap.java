package com.luanchuan.lcshop.jsonfactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by good on 2018/6/1.
 */

/**
 * 解析json
//@param string 要解析的字符串
 * @return
 */
//返回一个ArrayList<Map<String ,Object>>数组
public class JsonToMap {
    public static ArrayList<Map<String ,Object>> getArrJson(String string){
        ArrayList<Map<String, Object>> mapArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONObject jsonArray = jsonObject.getJSONObject("data");
            JSONArray listjsonarrya = jsonArray.getJSONArray("list");
            for (int i = 0; i < listjsonarrya.length(); i++) {
                JSONObject jsonObject1 = listjsonarrya.getJSONObject(i);
                Map<String, Object> map = new HashMap<>();
                map.put("goods_id", jsonObject1.getString("goods_id"));//ID
                map.put("goods_pic", jsonObject1.getString("goods_pic"));//商品名字
                map.put("goods_title", jsonObject1.getString("goods_title"));//商品ID
                map.put("goods_short_title", jsonObject1.getString("goods_short_title"));//分类ID
                map.put("goods_cat", jsonObject1.getString("goods_cat"));//分类名字
                map.put("goods_price", jsonObject1.getDouble("goods_price"));//商品URL
                map.put("goods_sales", jsonObject1.getString("goods_sales"));//商品图片
                map.put("goods_introduce", jsonObject1.getString("goods_introduce"));//在线售价
                map.put("seller_id", jsonObject1.getString("seller_id"));//劵后售价
                map.put("coupon_id", jsonObject1.getString("coupon_id"));//优惠券价格
                map.put("coupon_price", jsonObject1.getDouble("coupon_price"));//月销量
                mapArrayList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

return mapArrayList;

    }
}
