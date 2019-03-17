package com.cloud.microblog.gateway.auth;

import java.util.ArrayList;
import java.util.List;

public abstract  class AbsAppPath {

    protected List<String>  paths =  new ArrayList<String>();
    protected String name;


    public abstract  void setPaths(List<String> paths);
    public abstract void setName(String name);

    public abstract List<String> getPaths();

    public abstract String getName();
}
