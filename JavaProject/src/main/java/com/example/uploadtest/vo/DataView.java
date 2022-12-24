package com.example.uploadtest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jun
 * @date 2022/12/1 10:16
 * @description DataView
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataView {


    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataView(Long count, Object data){
        this.count = count;
        this.data = data;
    }

    public DataView(Object data){
        this.data = data;
    }


}
