# Hangar4J ![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/xMrAfonso/Hangar4J/gradle-test.yml?branch=main) [![](https://jitpack.io/v/xMrAfonso/Hangar4J.svg)](https://jitpack.io/#xMrAfonso/Hangar4J?style=for-the-badge)

A Java wrapper to easily access PaperMC's [Hangar API](https://hangar.papermc.io/api-docs).
This wrapper is still in development, so more features will be added in the future.

At the moment, you can retrieve the following information:
* Projects
* Versions
* Users

*Disclaimer: This wrapper is not affiliated with PaperMC or Hangar in any way.*
## How to Use
Initialize a ```HangarClient``` instance with your API key. You can get an [API key here](https://hangar.papermc.io/auth/settings/api-keys).
If you don't contain an API key, you can still use the wrapper, but you will share api limits with other users using the public API.

It's also important to note that this wrapper only supports Java 17 or higher.

### Documentation
The javadocs with all the information you need can be found [here](https://javadoc.jitpack.io/com/github/xmrafonso/hangar4j/1.2.2/javadoc/).

### Basic Example
This example contains a very simple class responsible for returning the statistics of a project.
The class will also initialize the ```HangarClient``` instance.

```java
// imports

public class HangarExample {
    private final HangarClient hangarClient;

    // Can be initialized in several ways, this is one of them.
    public HangarExample() {
        this.hangarClient = new HangarClient("API_KEY", "USER_AGENT");
    }

    // Blocking call in this case, up to you to decide if you want to use non-blocking or blocking.
    // You may also use the HangarProject object if you already have it in most methods.
    public int getProjectDownloads(String author, String slug) {
        return hangarClient.getProject(author, slug).join().stats().downloads();
    }
}
```    

## Installation

The latest version can be found in the releases tab on the right.
#### Gradle
```kt
repositories {
    maven("https://jitpack.io")
}
        
dependencies {
    implementation("com.github.xmrafonso:hangar4j:VERSION")
}
```

#### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```  
```xml
<dependency>
    <groupId>com.github.xmrafonso</groupId>
    <artifactId>hangar4j</artifactId>
    <version>VERSION</version>
</dependency>
```

## Contributing

Contributions are always welcome. Please open a pull request or an issue if you have any ideas or suggestions.

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/xMrAfonso/Hangar4J/blob/main/LICENSE) file for details.