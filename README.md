# WordPress REST API for Java

A Java client of the WordPress REST API.

## Getting Started
```bash
git clone https://github.com/sirrop/java-wpapi.git
```

## Basic Usage
```java
WordPressApi api = WordPressApi.discover("uri you want to send requests").get();
WPAPI2 v2 = api.namespace("wp/v2");
v2.posts()
    .get()
    .thenAccept(System.out::println);
```
