/*
The MIT License (MIT)

Copyright (c) 2014, Groupon, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.groupon.jenkins.dynamic.organizationcontainer;

import hudson.model.Descriptor.FormException;
import hudson.model.Item;
import hudson.model.TopLevelItem;
import hudson.model.View;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.export.Exported;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;

public class AllListView extends View {

    private final OrganizationContainer organizationContainer;

    public AllListView(final OrganizationContainer organizationContainer) {
        super("All", organizationContainer);
        this.organizationContainer = organizationContainer;
    }

    @Override
    @Exported(name = "jobs")
    public Collection getItems() {
        return this.organizationContainer.getItems();
    }

    @Override
    public boolean contains(final TopLevelItem item) {
        return true;
    }

    @Override
    public void onJobRenamed(final Item item, final String oldName, final String newName) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void submit(final StaplerRequest req) throws IOException, ServletException, FormException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Item doCreateItem(final StaplerRequest req, final StaplerResponse rsp) throws IOException, ServletException {
        throw new UnsupportedOperationException();
    }

}
