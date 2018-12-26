
package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewProject {

    @SerializedName("msg")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
