package org.p0gram3r.playground;

import java.util.ArrayList;
import java.util.List;

public class Albums {
    public String title;
    public String message;

    // both will result in "[]"
    // public String[] errors = new String[] {};
    public List<String> errors = new ArrayList<String>();

    public String total;
    public int total_pages;
    public int page;
    public String limit;

    List<Dataset> dataset = new ArrayList<Dataset>();
}
