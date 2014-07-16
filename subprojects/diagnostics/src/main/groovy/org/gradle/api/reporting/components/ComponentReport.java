/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.reporting.components;

import org.gradle.api.DefaultTask;
import org.gradle.api.Incubating;
import org.gradle.api.Project;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.reporting.components.internal.ComponentReportRenderer;
import org.gradle.api.tasks.TaskAction;
import org.gradle.language.base.ProjectSourceSet;
import org.gradle.logging.StyledTextOutput;
import org.gradle.logging.StyledTextOutputFactory;
import org.gradle.runtime.base.BinaryContainer;
import org.gradle.runtime.base.ProjectComponent;
import org.gradle.runtime.base.ProjectComponentContainer;

import javax.inject.Inject;
import java.util.Collections;

/**
 * Displays some details about the software components produced by the project.
 */
@Incubating
public class ComponentReport extends DefaultTask {
    @Inject
    protected StyledTextOutputFactory getTextOutputFactory() {
        throw new UnsupportedOperationException();
    }

    @Inject
    protected FileResolver getFileResolver() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void report() {
        Project project = getProject();

        StyledTextOutput textOutput = getTextOutputFactory().create(ComponentReport.class);
        ComponentReportRenderer renderer = new ComponentReportRenderer(getFileResolver());
        renderer.setOutput(textOutput);

        renderer.startProject(project);

        ProjectComponentContainer components = project.getExtensions().findByType(ProjectComponentContainer.class);
        if (components != null) {
            renderer.renderComponents(components);
        } else {
            renderer.renderComponents(Collections.<ProjectComponent>emptyList());
        }

        ProjectSourceSet sourceSets = project.getExtensions().findByType(ProjectSourceSet.class);
        if (sourceSets != null) {
            renderer.renderSourceSets(sourceSets);
        }
        BinaryContainer binaries = project.getExtensions().findByType(BinaryContainer.class);
        if (binaries != null) {
            renderer.renderBinaries(binaries);
        }

        renderer.completeProject(project);
        renderer.complete();
    }
}
