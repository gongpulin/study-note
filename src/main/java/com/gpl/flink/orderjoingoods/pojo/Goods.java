package com.gpl.flink.orderjoingoods.pojo;


/**
 * @author gongpulin
 * date 2020-05-18
 */


public class Goods {
    private int goodsId;
    private String goodsName;
    private boolean isRemove;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }
}
