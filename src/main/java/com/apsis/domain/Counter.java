package com.apsis.domain;

/**
 * Created by dinesh on 19/03/17.
 */
public class Counter {

    private String name;

    private Integer value;
    
    public Counter(){
    	
    }

    public Counter(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
