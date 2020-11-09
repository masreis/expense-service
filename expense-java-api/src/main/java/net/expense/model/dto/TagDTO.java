package net.expense.model.dto;

import java.io.Serializable;

public class TagDTO implements Serializable {

    private static final long serialVersionUID = -7559575742303599215L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
