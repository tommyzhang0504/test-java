package com.market.item.pojo;

import com.market.pojo.TbItem;

public class Item extends TbItem {

    public Item(TbItem tbItem){
        //初始化属性
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages(){
        if (this.getImage() !=null && !this.getImage().equals("")){
            return this.getImage().split(",");
        }
        return null;
    }

}
