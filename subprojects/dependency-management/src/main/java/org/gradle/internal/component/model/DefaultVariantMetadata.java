/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.internal.component.model;

import org.gradle.api.Describable;
import org.gradle.api.internal.attributes.AttributeContainerInternal;

import java.util.Set;

public class DefaultVariantMetadata implements VariantMetadata {
    private final Describable displayName;
    private final AttributeContainerInternal attributes;
    private final Set<? extends ComponentArtifactMetadata> artifacts;

    public DefaultVariantMetadata(Describable displayName, AttributeContainerInternal attributes, Set<? extends ComponentArtifactMetadata> artifacts) {
        this.displayName = displayName;
        this.attributes = attributes;
        this.artifacts = artifacts;
    }

    @Override
    public Describable asDescribable() {
        return displayName;
    }

    @Override
    public AttributeContainerInternal getAttributes() {
        return attributes;
    }

    @Override
    public Set<? extends ComponentArtifactMetadata> getArtifacts() {
        return artifacts;
    }
}
