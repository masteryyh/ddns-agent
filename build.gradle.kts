plugins {
    id("java")
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.1")
    implementation("com.google.guava:guava:31.1-jre")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate.validator:hibernate-validator:7.0.4.Final")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    // Dependency Injection
    implementation("org.springframework:spring-context:5.3.18")
    implementation("org.springframework:spring-core:5.3.18")
    implementation("org.springframework:spring-beans:5.3.18")

    // JDBC drivers and connection pool
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("hikari-cp:hikari-cp:2.14.0")

    // Logging
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.apache.logging.log4j:log4j-api:2.17.2")
    implementation("org.slf4j:slf4j-api:1.7.36")
}

tasks.getByName<Test>("test") {
    useTestNG()
}