package com.zhang.dto;

/**
 * Created by zhenghua.zhang on 2017/11/16.
 */
public class Select2Dto {
    private Long id;
    private String text;
    private Object other;

    public Select2Dto(Long id, String text, Object other) {
        this.id = id;
        this.text = text;
        this.other = other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public Object getOther() {

        return other;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
