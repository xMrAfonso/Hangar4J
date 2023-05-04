# Hangar4J

A Java wrapper to easily access PaperMC's [Hangar API](https://hangar.papermc.io/api-docs).
This wrapper is still in development, so more features will be added in the future.

At the moment, you can retrieve the following information:
* Projects
* Versions
* Users

*Disclaimer: This wrapper is not affiliated with PaperMC or Hangar in any way.*
## How to Use
Firstly, you should initialize a ```HangarClient``` instance with your API key. You can get an API key from your Hangar profile.
If you don't contain an API key, you can still use the wrapper, but you will share api limits with other users using the public API.

The methods below are in the ```HangarClient``` class and will return their respective objects.

### Initializing HangarClient
Using an API key and a custom user agent (recommended):
```java
HangarClient HangarClient("API_KEY", "USER_AGENT")
```
Without an API key and using public api and sharing its limits with other users (not recommended):
```java
HangarClient HangarClient("USER_AGENT")
```

### Projects
Get a project by its author and slug
```java
Project getProject(String author, String slug)
```    
Get a list of projects
```java
Projects getProjects(boolean orderWithRelevance, int limit, int offset)
Projects getProjects(int limit, int offset)
```
Get the total amount of projects
```java
int getTotalProjectCount()
```

### Versions
Get a list of versions of a project
```java
Versions getVersions(String author, String slug)
Versions getVersions(HangarProject project)
```
Get a specific version of a project
```java
Version getVersion(String author, String slug, String version)
Version getVersion(HangarProject project, String version)
```
### Users
Get a user by their username
```java
User getUser(String user)
```
## Installation

The latest version can be found in the releases tab on the right.

### Maven
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

#### Gradle
```kt
repositories {
    maven("https://jitpack.io")
}
        
dependencies {
    implementation("com.github.xmrafonso:hangar4j:VERSION")
}
```  

## Contributing

Contributions are always welcome. Please open a pull request or an issue if you have any ideas or suggestions.

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/xMrAfonso/Hangar4J/blob/main/LICENSE) file for details.