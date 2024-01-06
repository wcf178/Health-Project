package com.bigdata.pojo;

import java.io.Serializable;

public class GroupToItem implements Serializable {
    private int checkGroupId;
    private int[] checkItemId;
    private String code;

    public int getCheckGroupId() {
        return checkGroupId;
    }

    public void setCheckGroupId(int checkGroupId) {
        this.checkGroupId = checkGroupId;
    }

    public int[] getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(int[] checkItemId) {
        this.checkItemId = checkItemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void printIds(){
        for (int id:
             checkItemId) {
            System.out.println(id);

        }
    }
}
