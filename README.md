# java2c

A transpiler to convert Java to C source.

Obviously, this isn't perfect. Currently, it's designed to use a subset of java - a dialect.

## Why?
Modern IDES, particularly JetBrains' IntelliJ, make for highly efficient coding. Leveraging one of these for a large C project, has, to date, been hard. There are plugins, but they're difficult and cumbersome to use. Additionally, doing OO in C is hard, yet the benefits are very tangible. Java has an excellent syntax for OO, and modern compilers can catch and discover a myriad of problems and warnings that a C developer could only dream about.

There have been other transpilers in this vain. The most able in Vala's, which uses the GObject system. The downside to Vala is the need for first class IDE support, and to learn a new language syntax.
