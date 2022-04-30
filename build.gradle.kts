plugins {
    id("java")
    id("io.freefair.lombok") version "6.4.3"
}

group = "win.minaandyyh"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Common libraries
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
    implementation("com.google.guava:guava:31.1-jre")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate.validator:hibernate-validator:7.0.4.Final")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.apache.tomcat.embed:tomcat-embed-el:10.0.20")

    // Dependency Injection
    implementation("org.springframework:spring-context:5.3.19")
    implementation("org.springframework:spring-core:5.3.19")
    implementation("org.springframework:spring-beans:5.3.19")

    // Logging
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.apache.logging.log4j:log4j-api:2.17.2")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")

    // hutool
    implementation("cn.hutool:hutool-all:5.8.0.M2")

    // Test framework
    testImplementation("org.testng:testng:7.5")
    testImplementation("org.springframework:spring-test:5.3.19")
}

tasks.getByName<Test>("test") {
    useTestNG()
}