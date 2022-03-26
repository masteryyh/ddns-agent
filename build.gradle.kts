plugins {
    id("java")
}

group = "win.minaandyyh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}