# Demo Spring Boot Project for Jar2Exe

## Introduction
After a spring program is encrypted by Jar2Exe, 2 features will fail:

1. Any kind of `component-scan` will fail:

	Program could not automatically find and instantiate a `@Service` or `@Controller` or `@Mapper` bean. It could not find mapper xml file according to a wildcard such as `classpath:/mapper/*.xml`.

2. Any kind of `@annotation` will fail:

	Such as `@Autowired` or `@Value` will not work.

## Solution
Because of the incompatibility of `spring` and `encryption`, we choose to encrypt part of the program, and keep the `spring` part un-encrypted:

* `@Service` class: we split a service class into a `parent class` and a `child class`:

	* `parent class` is the real service class with business code to be encrypted. It has no annotations.
	* `child class` extends the `parent class`, with `@Service` to be auto-scaned, with `@Autowired` to be injected to by spring.

* `@Mapper` class: they are interfaces, we need not encrypt them.

* `mapper/xxx.xml`: there are sql statements, if we want to encrypt, we need to declare them one by one explicitly.

* `@Controller` class: usually we need not encrypt. If we want, do like `@Service` class does.

* `/static/` and `/templates/`, these files are referred by their names, encyption compatible, encrypt if we want.

* `/application.properties`, this file is used by its name, encryption compatible.

## Project Structure
In order to be partially encrypted, we split a spring-boot project into 3 projects:

* `demo-web` is the original spring-boot project, keep un-encrypted. `@Controller` and `@Service` classes are in it.

* `demo-core` is to be encrypted. `parent class` of `@Service` are in this project. This project is a dependency of `demo-web`.

* `demo-share` contains `@Mapper` classes, keep un-encrypted. In order to let `@Mapper` classes accessible in `demo-core` during developing, as a dependency of `demo-core`.

## Encrypt using Jar2Exe
Run `mvn build` at the parent directory, then we will get the result `/demo-web/target/demo-web-0.0.1-SNAPSHOT.jar`:

1. First, please download the most recent Jar2Exe `v2.2.4` or above for v2.2, `v2.5.2` or above for v2.5.

2. unzip the `demo-web-0.0.1-SNAPSHOT.jar`, we will get:

		demo-web-0.0.1-SNAPSHOT
		├── BOOT-INF
		│   ├── classes
		│   │   └── ………
		│   └── lib
		│       ├── ………
		│       ├── demo-core-0.0.1-SNAPSHOT.jar
		│       ├── demo-share-0.0.1-SNAPSHOT.jar
		│       └── ………
		├── META-INF
		│   ├── MANIFEST.MF
		│   └── ………
		└── org
		    └── springframework
		        └── boot
		            └── loader
		                └── ………
	because Jar2Exe has provided a ClassLoader, so we only need those files under `/BOOT-INF/` and ignore other files.
	
3. Pack the contents under `/BOOT-INF/classes/` into a zip or jar file:

		cd BOOT-INF/classes/
		jar cvf ../classes.jar *
		
4. Run Jar2Exe:

	* At the step 1, choose `/BOOT-INF/lib/demo-core-....jar` as the main jar.

	* At the step 3, input `com.regexlab.j2e.demo.DemoWebApplication` as the main class, ignore the warning when we click `Next`.

	* At the step 4, we check the `Encrypt and hide`.

	* At the step 5, we add `BOOT-INF/classes.jar` and all `BOOT-INF/lib/*` files as dependencies. Keep `../classes.jar` and `demo-share-....jar` **NOT** protected. As to other jar files, we need NOT to enable `Protect` because it will slow down the startup of program if we choose more jars to be protected.
