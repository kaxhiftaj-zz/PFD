package com.techease.pfd.Controller;

/**
 * Created by Adam Noor on 08-Dec-17.
 */

public class CoupanListModel {

    private String CoupanCode;
    private String CoupanDate;
    private String DiscountNo;
    private String DiscountType;
    private String CoupanId;

    public String getCoupanCode() {
        return CoupanCode;
    }

    public void setCoupanCode(String coupanCode) {
        CoupanCode = coupanCode;
    }

    public String getCoupanDate() {
        return CoupanDate;
    }

    public void setCoupanDate(String coupanDate) {
        CoupanDate = coupanDate;
    }

    public String getDiscountNo() {
        return DiscountNo;
    }

    public void setDiscountNo(String discountNo) {
        DiscountNo = discountNo;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public String getDiscountId() {
        return CoupanId;
    }

    public void setDiscountId(String discountId) {
        CoupanId = discountId;
    }


}
