(window.webpackJsonp=window.webpackJsonp||[]).push([[38],{319:function(t,a,n){"use strict";n.r(a);var e=n(14),s=Object(e.a)({},(function(){var t=this,a=t._self._c;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"using-shadow-to-package-gradle-plugins"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#using-shadow-to-package-gradle-plugins"}},[t._v("#")]),t._v(" Using Shadow to Package Gradle Plugins")]),t._v(" "),a("p",[t._v("In some scenarios, writing a Gradle plugin can be problematic because your plugin may depend on a version that\nconflicts with the same dependency provided by the Gradle runtime. If this is the case, then you can utilize Shadow\nto relocate your dependencies to a different package name to avoid the collision.")]),t._v(" "),a("p",[t._v("Configuring the relocation has always been possible, but the build author is required to know all the package names\nbeforehand. As of Shadow v8.1.0, automatic package relocation can be enabled by setting the "),a("code",[t._v("enabledRelocation")]),t._v("\nand "),a("code",[t._v("relocationPrefix")]),t._v(" settings on any "),a("code",[t._v("ShadowJar")]),t._v(" task.")]),t._v(" "),a("p",[t._v("A simple Gradle plugin can use this feature by applying the "),a("code",[t._v("shadow")]),t._v(" plugin and configuring the "),a("code",[t._v("shadowJar")]),t._v(" task for relocation.")]),t._v(" "),a("div",{staticClass:"language-groovy no-plugins extra-class"},[a("pre",{pre:!0,attrs:{class:"language-groovy"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("import")]),t._v(" com"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("github"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("jengelman"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("gradle"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("plugins"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("shadow"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("tasks"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("ShadowJar\n\nplugins "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n  id "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'com.gradleup.shadow'")]),t._v(" version "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'8.3.4'")]),t._v("\n  id "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'java'")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n\ndependencies "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    shadow "),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("localGroovy")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v("\n    shadow "),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("gradleApi")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v("\n\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'org.jdom:jdom2:2.0.6'")]),t._v("\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'org.ow2.asm:asm:6.0'")]),t._v("\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'org.ow2.asm:asm-commons:6.0'")]),t._v("\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'commons-io:commons-io:2.4'")]),t._v("\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'org.apache.ant:ant:1.9.4'")]),t._v("\n    implementation "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'org.codehaus.plexus:plexus-utils:2.0.6'")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n\ntasks"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("named")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'shadowJar'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" com"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("github"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("jengelman"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("gradle"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("plugins"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("shadow"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("tasks"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("ShadowJar"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    enableRelocation "),a("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("true")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),a("p",[t._v("Note that the "),a("code",[t._v("localGroovy()")]),t._v(" and "),a("code",[t._v("gradleApi()")]),t._v(" dependencies are added to the "),a("code",[t._v("shadow")]),t._v(" configuration instead of the\nnormal "),a("code",[t._v("compile")]),t._v(" configuration. These 2 dependencies are provided by Gradle to compile your project but are ultimately\nprovided by the Gradle runtime when executing the plugin. Thus, it is "),a("strong",[t._v("not")]),t._v(" advisable to bundle these dependencies\nwith your plugin.")]),t._v(" "),a("h2",{attrs:{id:"publishing-shadowed-gradle-plugins"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#publishing-shadowed-gradle-plugins"}},[t._v("#")]),t._v(" Publishing shadowed Gradle plugins")]),t._v(" "),a("p",[t._v("The Gradle Publish Plugin introduced support for plugins packaged with Shadow in version 1.0.0.\nStarting with this version, plugin projects that apply both Shadow and the Gradle Plugin Publish plugin will be\nautomatically configured to publish the output of the "),a("code",[t._v("shadowJar")]),t._v(" tasks as the consumable artifact for the plugin.\nSee the "),a("a",{attrs:{href:"https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html#shadow_dependencies",target:"_blank",rel:"noopener noreferrer"}},[t._v("Gradle Plugin Publish docs"),a("OutboundLink")],1),t._v(" for details.")]),t._v(" "),a("h2",{attrs:{id:"automatic-package-relocation-with-shadow-prior-to-v8-1-0"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#automatic-package-relocation-with-shadow-prior-to-v8-1-0"}},[t._v("#")]),t._v(" Automatic package relocation with Shadow prior to v8.1.0")]),t._v(" "),a("p",[t._v("Prior to Shadow v8.1.0, Shadow handled this by introducing a new task type "),a("code",[t._v("ConfigureShadowRelocation")]),t._v(".\nTasks of this type are configured to target an instance of a "),a("code",[t._v("ShadowJar")]),t._v(" task and run immediately before it.")]),t._v(" "),a("p",[t._v("The "),a("code",[t._v("ConfigureShadowRelocation")]),t._v(" task, scans the dependencies from the configurations specified on the associated\n"),a("code",[t._v("ShadowJar")]),t._v(" task and collects the package names contained within them. It then configures relocation for these\npackages using the specified "),a("code",[t._v("prefix")]),t._v(" on the associated "),a("code",[t._v("ShadowJar")]),t._v(" task.")]),t._v(" "),a("p",[t._v("While this is useful for developing Gradle plugins, nothing about the "),a("code",[t._v("ConfigureShadowRelocation")]),t._v(" task is tied to\nGradle projects. It can be used for standard Java or Groovy projects.")])])}),[],!1,null,null,null);a.default=s.exports}}]);