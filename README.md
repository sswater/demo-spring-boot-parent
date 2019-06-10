# Demo Spring Boot Project for Jar2Exe

## Introduction
After a spring program is encrypted by Jar2Exe, 2 features will fail:

1. Any kind of `component-scan` will fail:

	Program could not automatically find and instantiate a `@Service` or `@Controller` or `@Mapper` bean. It could not find mapper xml file according to a wildcard such as `classpath:/mapper/*.xml`.

2. Any kind of `@annotation` will fail:

	Such as `@Autowired` or `@Value` will not work.
	
But these 2 features are strongly suggested by spring and spring-boot. Without these 2 features, we need to do a lot more configurations. So, because `spring` and `encryption` are incompatible, we are not going encrypt the whole spring-boot project, we can encrypt the core business part only.

## Solution
