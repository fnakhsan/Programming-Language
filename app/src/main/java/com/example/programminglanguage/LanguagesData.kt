package com.example.programminglanguage

object LanguagesData {
    private val languageNames = arrayOf("C",
        "Java",
        "C++",
        "C#",
        "Ruby",
        "Rust",
        "JavaScript",
        "PHP",
        "Python",
        "Kotlin",
        "Swift",
        "Dart",
    )

    private val languageDetails = arrayOf("C is a general-purpose, procedural computer programming language supporting structured programming, lexical variable scope, and recursion, with a static type system. By design, C provides constructs that map efficiently to typical machine instructions. It has found lasting use in applications previously coded in assembly language. Such applications include operating systems and various application software for computer architectures that range from supercomputers to PLCs and embedded systems. C was created in 1972 by Dennis Ritchie for the Unix Operating System at Bell Telephone Laboratories.",
        "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle) and released in 1995 as a core component of Sun Microsystems' Java platform.",
        "C++ is a general-purpose programming language created by Bjarne Stroustrup in 1985 as an extension of the C programming language, or \"C with Classes\". The language has expanded significantly over time, and modern C++ now has object-oriented, generic, and functional features in addition to facilities for low-level memory manipulation. It is almost always implemented as a compiled language, and many vendors provide C++ compilers, including the Free Software Foundation, LLVM, Microsoft, Intel, Oracle, and IBM, so it is available on many platforms. C++ was designed with an orientation toward system programming and embedded, resource-constrained software and large systems, with performance, efficiency, and flexibility of use as its design highlights. C++ has also been found useful in many other contexts, with key strengths being software infrastructure and resource-constrained applications, including desktop applications, video games, servers (e.g. e-commerce, web search, or databases), and performance-critical applications (e.g. telephone switches or space probes).",
        "C# is a general-purpose, multi-paradigm programming language encompassing static typing, strong typing, lexically scoped, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines. C# was developed around 2000 by Microsoft as part of its .NET initiative and later approved as an international standard by Ecma (ECMA-334) in 2002 and ISO (ISO/IEC 23270) in 2003. It was designed by Anders Hejlsberg, and its development team is currently led by Mads Torgersen, being one of the programming languages designed for the Common Language Infrastructure (CLI). The most recent version is 9.0, which was released in 2020 in .NET 5.0 and included in Visual Studio 2019 version 16.8.",
        "Ruby is an interpreted, high-level, general-purpose programming language. It was designed and developed in the mid-1990s by Yukihiro \"Matz\" Matsumoto in Japan. Ruby is dynamically typed and uses garbage collection and just-in-time compilation. It supports multiple programming paradigms, including procedural, object-oriented, and functional programming. According to the creator, Ruby was influenced by Perl, Smalltalk, Eiffel, Ada, BASIC, and Lisp. Matsumoto has said that Ruby is designed for programmer productivity and fun, following the principles of good user interface design. At a Google Tech Talk in 2008 Matsumoto further stated, \"I hope to see Ruby help every programmer in the world to be productive, and to enjoy programming, and to be happy. That is the primary purpose of Ruby language.\" Ruby is said to follow the principle of least astonishment (POLA), meaning that the language should behave in such a way as to minimize confusion for experienced users.",
        "Rust is a multi-paradigm, high-level, general-purpose programming language designed for performance and safety, especially safe concurrency. Rust is syntactically similar to C++, but can guarantee memory safety by using a borrow checker to validate references. Rust achieves memory safety without garbage collection, and reference counting is optional. Rust was originally designed by Graydon Hoare at Mozilla Research, with contributions from Dave Herman, Brendan Eich, and others. The designers refined the language while writing the Servo layout or browser engine, and the Rust compiler. It has gained increasing use in industry, and Microsoft has been experimenting with the language for secure and safety-critical software components. Its development is supervised by the Rust Foundation, a group consisting of Amazon Web Services, Google, Huawei, Microsoft and Mozilla.",
        "JavaScript often abbreviated as JS, is a programming language that conforms to the ECMAScript specification. JavaScript is high-level, often just-in-time compiled, and multi-paradigm. It has curly-bracket syntax, dynamic typing, prototype-based object-orientation, and first-class functions. Alongside HTML and CSS, JavaScript is one of the core technologies of the World Wide Web. Over 97% of websites use it client-side for web page behavior, often incorporating third-party libraries. All major web browsers have a dedicated JavaScript engine to execute the code on the user's device. As a multi-paradigm language, JavaScript supports event-driven, functional, and imperative programming styles. It has application programming interfaces (APIs) for working with text, dates, regular expressions, standard data structures, and the Document Object Model (DOM). JavaScript engines were originally used only in web browsers, but they are now core components of other software systems, most notably servers and a variety of applications.",
        "PHP is a general-purpose scripting language geared towards web development. It was originally created by Danish-Canadian programmer Rasmus Lerdorf in 1994. The PHP reference implementation is now produced by The PHP Group. PHP originally stood for Personal Home Page, but it now stands for the recursive initialism PHP: Hypertext Preprocessor. PHP code is usually processed on a web server by a PHP interpreter implemented as a module, a daemon or as a Common Gateway Interface (CGI) executable. On a web server, the result of the interpreted and executed PHP code – which may be any type of data, such as generated HTML or binary image data – would form the whole or part of an HTTP response. Various web template systems, web content management systems, and web frameworks exist which can be employed to orchestrate or facilitate the generation of that response. Additionally, PHP can be used for many programming tasks outside of the web context, such as standalone graphical applications and robotic drone control. PHP code can also be directly executed from the command line.",
        "Python is an interpreted high-level general-purpose programming language. Python's design philosophy emphasizes code readability with its notable use of significant indentation. Its language constructs as well as its object-oriented approach aim to help programmers write clear, logical code for small and large-scale projects. Python is dynamically-typed and garbage-collected. It supports multiple programming paradigms, including structured (particularly, procedural), object-oriented and functional programming. Python is often described as a \"batteries included\" language due to its comprehensive standard library. Guido van Rossum began working on Python in the late 1980s, as a successor to the ABC programming language, and first released it in 1991 as Python 0.9.0. Python consistently ranks as one of the most popular programming languages.",
        "Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise. Kotlin mainly targets the JVM, but also compiles to JavaScript (e.g., for frontend web applications using React) or native code (via LLVM); e.g., for native iOS apps sharing business logic with Android apps. Language development costs are borne by JetBrains, while the Kotlin Foundation protects the Kotlin trademark. On 7 May 2019, Google announced that the Kotlin programming language is now its preferred language for Android app developers. Since the release of Android Studio 3.0 in October 2017, Kotlin has been included as an alternative to the standard Java compiler.",
        "Swift is a general-purpose, multi-paradigm, compiled programming language developed by Apple Inc. and the open-source community. First released in 2014, Swift was developed as a replacement for Apple's earlier programming language Objective-C, as Objective-C had been largely unchanged since the early 1980s and lacked modern language features. Swift works with Apple's Cocoa and Cocoa Touch frameworks, and a key aspect of Swift's design was the ability to interoperate with the huge body of existing Objective-C code developed for Apple products over the previous decades. It is built with the open source LLVM compiler framework and has been included in Xcode since version 6, released in 2014. On Apple platforms, it uses the Objective-C runtime library which allows C, Objective-C, C++ and Swift code to run within one program. Version 2.2 was made open-source software under the Apache License 2.0 on December 3, 2015, for Apple's platforms and Linux.",
        "Dart is a programming language designed for client development, such as for the web and mobile apps. It is developed by Google and can also be used to build server and desktop applications. Dart is an object-oriented, class-based, garbage-collected language with C-style syntax. Dart can compile to either native code or JavaScript. It supports interfaces, mixins, abstract classes, reified generics, and type inference. Dart was unveiled at the GOTO conference in Aarhus, Denmark, October 10–12, 2011. The project was founded by Lars Bak and Kasper Lund. Dart 1.0 was released on November 14, 2013. Dart is a descendant of the ALGOL language family, alongside C, Java, C#, JavaScript, and other." )

    private val languageImages = intArrayOf(
        R.drawable.c,
        R.drawable.java,
        R.drawable.cpp,
        R.drawable.csharp,
        R.drawable.ruby,
        R.drawable.rust,
        R.drawable.javascript,
        R.drawable.php,
        R.drawable.python,
        R.drawable.kotlin,
        R.drawable.swift,
        R.drawable.dart)

    val listData: ArrayList<Language>
        get() {
            val list = arrayListOf<Language>()
            for (position in languageNames.indices) {
                val language = Language()
                language.name = languageNames[position]
                language.developer = languageDeveloper[position]
                language.paradigm = languageParadigm[position]
                language.detail = languageDetails[position]
                language.photo = languageImages[position]
                list.add(language)
            }
            return list
        }

    private val languageParadigm = arrayOf("Multi-paradigm: imperative (procedural), structured.",
        "Multi-paradigm: generic, object-oriented (class-based), functional, imperative, reflective.",
        "Multi-paradigm: procedural, functional, object-oriented, generic, modular.",
        "Multi-paradigm: structured, imperative, object-oriented, event-driven, task-driven, functional, generic, reflective, concurrent.",
        "Multi-paradigm: functional, imperative, object-oriented, reflective.",
        "Multi-paradigm: concurrent, functional, generic, imperative, structured.",
        "Multi-paradigm: event-driven, functional, imperative.",
        "Multi-paradigm: imperative, functional, object-oriented, procedural, reflective.",
        "Multi-paradigm: object-oriented, procedural (imperative), functional, structured, reflective.",
        "Multi-paradigm: object-oriented, functional, imperative, block structured, declarative, generic, reflective, concurrent.",
        "Multi-paradigm: protocol-oriented, object-oriented, functional, imperative, block structured, declarative.",
        "Multi-paradigm: functional, imperative, object-oriented, reflective."
    )

    private val languageDeveloper = arrayOf("ANSI C",
        "Oracle Corporation",
        "ISO/IEC",
        "Microsoft",
        "Yukihiro Matsumoto",
        "The Rust Foundation",
        "ECMA International",
        "The PHP Development Team, Zend Technologies",
        "Python Software Foundation",
        "JetBrains",
        "Apple Inc. and open-source contributors",
        "Google"
    )

}