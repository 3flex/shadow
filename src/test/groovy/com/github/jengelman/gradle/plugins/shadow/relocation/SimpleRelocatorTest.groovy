/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.jengelman.gradle.plugins.shadow.relocation

import com.github.jengelman.gradle.plugins.shadow.ShadowStats
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

/**
 * Test for {@link SimpleRelocator}.
 *
 * @author Benjamin Bentmann
 * @version $Id: SimpleRelocatorTest.java 1342979 2012-05-26 22:05:45Z bimargulies $
 *
 * Modified from org.apache.maven.plugins.shade.relocation.SimpleRelocatorTest.java
 *
 * Modifications
 * @author John Engelman
 */
class SimpleRelocatorTest {

    private static final def objectFactory = ProjectBuilder.builder().build().objects
    private static ShadowStats stats

    @BeforeEach
    void setUp() {
        stats = new ShadowStats()
    }

    @Test
    void testCanRelocatePath() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "org.foo", null)
        assertEquals(true, relocator.canRelocatePath("org/foo/Class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/Class.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/bar/Class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/bar/Class.class"))
        assertEquals(false, relocator.canRelocatePath("com/foo/bar/Class"))
        assertEquals(false, relocator.canRelocatePath("com/foo/bar/Class.class"))
        assertEquals(false, relocator.canRelocatePath("org/Foo/Class"))
        assertEquals(false, relocator.canRelocatePath("org/Foo/Class.class"))

        // Verify paths starting with '/'
        assertEquals(false, relocator.canRelocatePath("/org/Foo/Class"))
        assertEquals(false, relocator.canRelocatePath("/org/Foo/Class.class"))

        relocator = new SimpleRelocator(objectFactory, "org.foo", null, null,
            List.of("org.foo.Excluded", "org.foo.public.*", "org.foo.recurse.**", "org.foo.Public*Stuff"))
        assertEquals(true, relocator.canRelocatePath("org/foo/Class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/Class.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/excluded"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Excluded"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Excluded.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/public"))
        assertEquals(false, relocator.canRelocatePath("org/foo/public/Class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/public/Class.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/public/sub"))
        assertEquals(true, relocator.canRelocatePath("org/foo/public/sub/Class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/publicRELOC/Class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/PrivateStuff"))
        assertEquals(true, relocator.canRelocatePath("org/foo/PrivateStuff.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/PublicStuff"))
        assertEquals(false, relocator.canRelocatePath("org/foo/PublicStuff.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/PublicUtilStuff"))
        assertEquals(false, relocator.canRelocatePath("org/foo/PublicUtilStuff.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse/Class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse/Class.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse/sub"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse/sub/Class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/recurse/sub/Class.class"))

        // Verify edge cases
        relocator = new SimpleRelocator(objectFactory, "org.f", null)
        assertEquals(false, relocator.canRelocatePath(""))       // Empty path
        assertEquals(false, relocator.canRelocatePath(".class")) // only .class
        assertEquals(false, relocator.canRelocatePath("te"))     // shorter than path pattern
        assertEquals(false, relocator.canRelocatePath("test"))   // shorter than path pattern with /
        assertEquals(true, relocator.canRelocatePath("org/f"))   // equal to path pattern
        assertEquals(true, relocator.canRelocatePath("/org/f"))  // equal to path pattern with /
    }

    @Test
    void testCanRelocatePathWithRegex() {
        SimpleRelocator relocator

        // Include with Regex
        relocator = new SimpleRelocator(objectFactory, "org.foo", null, Collections.singletonList("%regex[org/foo/R(\\\$.*)?\$]"), null)
        assertEquals(true, relocator.canRelocatePath("org/foo/R.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/R\$string.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/R\$layout.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Recording/R.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Recording.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/bar/R\$string.class"))
        assertEquals(false, relocator.canRelocatePath("org/R.class"))
        assertEquals(false, relocator.canRelocatePath("org/R\$string.class"))

        // Exclude with Regex
        relocator = new SimpleRelocator(objectFactory, "org.foo", null)
        relocator.exclude("%regex[org/foo/.*Factory[0-9].*]")
        assertEquals(true, relocator.canRelocatePath("org/foo/Factory.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/FooFactoryMain.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/BarFactory.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Factory0.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/FooFactory1Main.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/BarFactory2.class"))

        // Include with Regex and normal pattern
        relocator = new SimpleRelocator(objectFactory, "org.foo", null,
            List.of("%regex[org/foo/.*Factory[0-9].*]", "org.foo.public.*"))
        assertEquals(true, relocator.canRelocatePath("org/foo/Factory1.class"))
        assertEquals(true, relocator.canRelocatePath("org/foo/public/Bar.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/Factory.class"))
        assertEquals(false, relocator.canRelocatePath("org/foo/R.class"))
    }

    @Test
    void testCanRelocateClass() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "org.foo", null)
        assertEquals(true, relocator.canRelocateClass("org.foo.Class"))
        assertEquals(true, relocator.canRelocateClass("org.foo.bar.Class"))
        assertEquals(false, relocator.canRelocateClass("com.foo.bar.Class"))
        assertEquals(false, relocator.canRelocateClass("org.Foo.Class"))

        relocator = new SimpleRelocator(objectFactory, "org.foo", null, null,
            List.of("org.foo.Excluded", "org.foo.public.*", "org.foo.recurse.**", "org.foo.Public*Stuff"))
        assertEquals(true, relocator.canRelocateClass("org.foo.Class"))
        assertEquals(true, relocator.canRelocateClass("org.foo.excluded"))
        assertEquals(false, relocator.canRelocateClass("org.foo.Excluded"))
        assertEquals(false, relocator.canRelocateClass("org.foo.public"))
        assertEquals(false, relocator.canRelocateClass("org.foo.public.Class"))
        assertEquals(false, relocator.canRelocateClass("org.foo.public.sub"))
        assertEquals(true, relocator.canRelocateClass("org.foo.public.sub.Class"))
        assertEquals(true, relocator.canRelocateClass("org.foo.publicRELOC.Class"))
        assertEquals(true, relocator.canRelocateClass("org.foo.PrivateStuff"))
        assertEquals(false, relocator.canRelocateClass("org.foo.PublicStuff"))
        assertEquals(false, relocator.canRelocateClass("org.foo.PublicUtilStuff"))
        assertEquals(false, relocator.canRelocateClass("org.foo.recurse"))
        assertEquals(false, relocator.canRelocateClass("org.foo.recurse.Class"))
        assertEquals(false, relocator.canRelocateClass("org.foo.recurse.sub"))
        assertEquals(false, relocator.canRelocateClass("org.foo.recurse.sub.Class"))
    }

    @Test
    void testCanRelocateRawString() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "org/foo", null, null, null, true)
        assertEquals(true, relocator.canRelocatePath("(I)org/foo/bar/Class"))

        relocator = new SimpleRelocator(objectFactory, "^META-INF/org.foo.xml\$", null, null, null, true)
        assertEquals(true, relocator.canRelocatePath("META-INF/org.foo.xml"))
    }

    //MSHADE-119, make sure that the easy part of this works.
    @Test
    void testCanRelocateAbsClassPath() {
        SimpleRelocator relocator = new SimpleRelocator(objectFactory, "org.apache.velocity", "org.apache.momentum")
        assertEquals("/org/apache/momentum/mass.properties", relocator.relocatePath(pathContext("/org/apache/velocity/mass.properties")))

    }

    @Test
    void testRelocatePath() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "org.foo", null)
        assertEquals("hidden/org/foo/bar/Class.class", relocator.relocatePath(pathContext("org/foo/bar/Class.class")))

        relocator = new SimpleRelocator(objectFactory, "org.foo", "private.stuff")
        assertEquals("private/stuff/bar/Class.class", relocator.relocatePath(pathContext("org/foo/bar/Class.class")))
    }

    @Test
    void testRelocateClass() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "org.foo", null)
        assertEquals("hidden.org.foo.bar.Class", relocator.relocateClass(classContext("org.foo.bar.Class")))

        relocator = new SimpleRelocator(objectFactory, "org.foo", "private.stuff")
        assertEquals("private.stuff.bar.Class", relocator.relocateClass(classContext("org.foo.bar.Class")))
    }

    @Test
    void testRelocateRawString() {
        SimpleRelocator relocator

        relocator = new SimpleRelocator(objectFactory, "Lorg/foo", "Lhidden/org/foo", null, null, true)
        assertEquals("(I)Lhidden/org/foo/bar/Class", relocator.relocatePath(pathContext("(I)Lorg/foo/bar/Class")))

        relocator = new SimpleRelocator(objectFactory, "^META-INF/org.foo.xml\$", "META-INF/hidden.org.foo.xml", null, null, true)
        assertEquals("META-INF/hidden.org.foo.xml", relocator.relocatePath(pathContext("META-INF/org.foo.xml")))
    }

    protected static RelocatePathContext pathContext(String path) {
        return RelocatePathContext.builder().path(path).stats(stats).build()
    }

    protected static RelocateClassContext classContext(String className) {
        return RelocateClassContext.builder().className(className).stats(stats).build()
    }
}
