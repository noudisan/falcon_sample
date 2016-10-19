package com.zhiyi.falcon.gateway.result;

/**
 *
 */
public class DeliveryProcessSettleResult {

    private String deliveryType;
    private Integer count;
    private String price;

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
