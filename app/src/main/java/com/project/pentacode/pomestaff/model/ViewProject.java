
package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewProject {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("method")
    @Expose
    private String method;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ViewProject withHref(String href) {
        this.href = href;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ViewProject withMethod(String method) {
        this.method = method;
        return this;
    }

}
