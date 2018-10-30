(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{169:function(e,t,s){"use strict";s.r(t);var a=s(0),i=Object(a.a)({},function(){this.$createElement;this._self._c;return this._m(0)},[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"content"},[s("h1",{attrs:{id:"reproducible-builds"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#reproducible-builds","aria-hidden":"true"}},[e._v("#")]),e._v(" Reproducible Builds")]),e._v(" "),s("p",[e._v("Because JAR files contain the timestamp of the included files, it is often difficult to create reproducible builds\nfrom a source commit that results in a hash identical file.\nGradle supports reproducible JAR creation by setting the timestamps of included files to a consistent value.\nShadow includes support for overriding file timestamps starting in version 4.0.0. By default, Shadow will preserve\nthe file timestamps when creating the Shadow JAR. To set timestamps to a consistent value (1980/1/1 00:00:00),\nset the "),s("code",[e._v("preserveFileTimestamps")]),e._v(" property to "),s("code",[e._v("false")]),e._v(" on the "),s("code",[e._v("ShadowJar")]),e._v(" task.")]),e._v(" "),s("div",{staticClass:"language-groovy extra-class"},[s("pre",{pre:!0,attrs:{class:"language-groovy"}},[s("code",[s("span",{attrs:{class:"token comment"}},[e._v("// Reset file timestamps")]),e._v("\nshadowJar "),s("span",{attrs:{class:"token punctuation"}},[e._v("{")]),e._v("\n  preserveFileTimestamps "),s("span",{attrs:{class:"token operator"}},[e._v("=")]),e._v(" "),s("span",{attrs:{class:"token boolean"}},[e._v("false")]),e._v("\n"),s("span",{attrs:{class:"token punctuation"}},[e._v("}")]),e._v("\n")])])])])}],!1,null,null,null);i.options.__file="README.md";t.default=i.exports}}]);