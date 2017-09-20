package io.goldammer.jenkins.dataHolder;

import hudson.model.InvisibleAction;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

/**
 * Created by Christian Goldammer on 18.09.17.
 */

@ExportedBean(defaultVisibility = 2)
public class DataHolderAction extends InvisibleAction {

    private String name;
    private Object value;

    @Exported
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exported(name = "value")
    public Object getExportedValue(){
        return value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
