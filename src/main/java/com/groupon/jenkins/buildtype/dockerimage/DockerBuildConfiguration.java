/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014, Groupon, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.groupon.jenkins.buildtype.dockerimage;

import com.groupon.jenkins.buildtype.install_packages.buildconfiguration.configvalue.ListOrSingleValue;
import com.groupon.jenkins.buildtype.install_packages.buildconfiguration.configvalue.MapValue;
import com.groupon.jenkins.buildtype.install_packages.buildconfiguration.configvalue.StringValue;
import com.groupon.jenkins.buildtype.util.config.Config;
import com.groupon.jenkins.buildtype.util.shell.ShellCommands;
import java.util.Map;

public class DockerBuildConfiguration {
    private Config config;

    public DockerBuildConfiguration(Map config) {
        this.config = new Config(config, "image", StringValue.class, "env", MapValue.class, "script", ListOrSingleValue.class);
    }

    public ShellCommands toShellCommands() {
        ShellCommands shellCommands = new ShellCommands();

        DockerCommandBuilder runCommand = DockerCommandBuilder.dockerCommand("run")
                .flag("rm")
                .flag("sig-proxy=true")
                .flag("v", "`pwd`:/var/project")
                .flag("w", "/var/project")
                .flag("u", "`id -u`")
                .args(getImageName(), getRunCommand());

        //exportEnvVars(runCommand, getEnvVars());

        return shellCommands;
    }

    private String getRunCommand() {
        return config.get("script",String.class);
    }

    private String getImageName() {
      return (String) config.get("image",String.class);
    }


    private void exportEnvVars(DockerCommandBuilder runCommand, Map<String, String> envVars) {
        for (Map.Entry<String, String> var : envVars.entrySet()) {
            runCommand.flag("e", String.format("\"%s=%s\"", var.getKey(), var.getValue()));
        }
    }
}