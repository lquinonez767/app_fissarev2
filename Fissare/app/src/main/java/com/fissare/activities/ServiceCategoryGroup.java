package com.fissare.activities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 31/01/18.
 */

public class ServiceCategoryGroup {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public ServiceCategoryGroup(String string) {
        this.string = string;
    }
}
