package com.zju.pojo;

/**
 * @author godfu
 * @Date:2022/3/18-18:05
 */

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
    /*private Integer totalCount;
    private BigDecimal totalPrice;*/
    /**
     * key是商品编号，value是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 获取总数量
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * 获取总金额
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount()+
                ", totalPrice=" + getTotalPrice()+
                ", items=" + items +
                '}';
    }

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否添加过此商品，如果已经添加，则数量累加，总金额更新；如果没有，直接放到集合中
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            //购物车没有添加过商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //已经添加过该商品
            //商品数量累加
            item.setCount(item.getCount()+1);
            //总金额更新
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        //删除指定key
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count){
        //先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            //修改商品数量
            cartItem.setCount(count);
            //更新总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

}
