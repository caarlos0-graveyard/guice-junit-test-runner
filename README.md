# guice-junit-test-runner [![Build Status](https://travis-ci.org/caarlos0/guice-junit-test-runner.svg?branch=master)](https://travis-ci.org/caarlos0/guice-junit-test-runner)

A very simple artifact, with no dependencies besides JUnit and Guice,
which is intended to make it easier to test your Guice code.

## Usage

Add the dependency to your `pom.xml`:

```xml
<dependency>
	<groupId>com.carlosbecker</groupId>
	<artifactId>guice-junit-test-runner</artifactId>
	<version>1.0.0</version>
</dependency>
```

Write your tests:

```java
@RunWith(GuiceTestRunner.class)
@GuiceModules(MyModule.class)
public class MyTest {
	@Inject
	private Something something;

	@Test
	public void testItWorks() throws Exception {
		assertThat(something.doSomething(), notNullValue());
	}
}
```

And that's it!