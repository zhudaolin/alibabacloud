package com.zdl.config.dbconfig;

import java.util.Arrays;

public enum DsTypeEnum {
    DS_MASTER(1,"master"),
    DS_SLAVE(2,"slave");

    DsTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DsTypeEnum fromCode(int code){
        return   Arrays.stream(DsTypeEnum.values()).filter(o->o.getCode()==code).findFirst().orElse(null);
    }
}
