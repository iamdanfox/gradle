/*
 * Copyright 2010 the original author or authors.
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
package org.gradle.plugins.idea.configurer

/**
 * @author Szczepan Faber, @date 03.03.11
 */
public class ModuleNameDeduper {

    def dedupeModuleNames(Collection names) {
        def allNames = []
        names.each { moduleName ->
            def n = moduleName.moduleName
            def candidates = moduleName.candidateNames as Queue
            while (allNames.contains(n)) {
                def nextCandidate = candidates.poll()
                if (!nextCandidate) { break }
                n = nextCandidate
            }
            allNames.add(n)
            moduleName.moduleName = n
        }
    }
}
