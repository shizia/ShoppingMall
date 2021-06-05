package com.cn.subject;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<String,CartItem> items = new LinkedHashMap<String,CartItem>();

    //添加
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加过此商品， 如果已添加， 则数量累加， 总金额更新， 如果没有添加过， 直接放到集合中即可
        CartItem item = items.get(cartItem.getId());

        if(item == null){
            // 之前没添加过此商品
            items.put(cartItem.getId(), cartItem);
        }else{
            // 已经 添加过的情况
            item.setCount(item.getCount() + 1);  // 数量累加
            item.setTotalPrice(item.getPrice()*item.getCount());  //更新总金额

        }
    }

    //删除
    public void deleteItem(String id){
        items.remove(id);
    }

    //清空
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(String id,Integer count){

        CartItem cartItem = items.get(id);

        if(cartItem != null){
            // 不为空代表购物车中有这个商品，把这个商品的数量更改为传入的参数
            cartItem.setCount(count);
            //更新总金额
            cartItem.setTotalPrice(cartItem.getPrice()*cartItem.getCount());
        }

    }
    
    
    

    public Integer getTotalCount() {
        //我们通过每次调用获取totalCount方法是，
        Integer totalCount = 0;
        for(Map.Entry<String,CartItem> entry : items.entrySet()){
           totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }



    public double getTotalPrice() {
        double totalPrice = 0;
        for(Map.Entry<String,CartItem> entry : items.entrySet()){
            totalPrice =totalPrice+entry.getValue().getTotalPrice();
        }

        return totalPrice;
    }



    public Map<String, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<String, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
