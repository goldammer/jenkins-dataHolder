package io.goldammer.jenkins.dataHolder;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Created by Christian Goldammer on 18.09.17.
 */
public class DataHolderBuilder extends Builder implements SimpleBuildStep {

    private @CheckForNull String name;
    private @CheckForNull Object value;

    @DataBoundConstructor
    public DataHolderBuilder(){
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath filePath, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
        listener.getLogger().println("DataHolderBuilder name="+name+", value="+String.valueOf(value));

        DataHolderAction dataAction = new DataHolderAction();
        dataAction.setName(name);
        dataAction.setValue(value);
        run.addAction(dataAction);
    }

    @CheckForNull
    public String getName() {
        return name;
    }

    @DataBoundSetter
    public void setName(@CheckForNull String name) {
        this.name = name;
    }

    @CheckForNull
    public Object getValue() {
        return value;
    }

    @DataBoundSetter
    public void setValue(@CheckForNull Object value) {
        this.value = value;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    @Extension
    @Symbol("dataHolder")
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public DescriptorImpl() {
            load();
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDisplayName() {
            return "DataHolder";
        }

        @Override
        public Builder newInstance(@Nullable StaplerRequest req, @Nonnull JSONObject formData) throws FormException {
            DataHolderBuilder dataHolderBuilder = new DataHolderBuilder();
            dataHolderBuilder.setName(String.valueOf(formData.get("name")));
            dataHolderBuilder.setValue(formData.get("value"));
            return dataHolderBuilder;
        }
    }

}
