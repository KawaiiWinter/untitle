plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "io.github.kawaiiwinter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mariadb.jdbc:mariadb-java-client:2.1.2")
    implementation("com.google.guava:guava:22.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("net.dv8tion:JDA:4.1.1_153")
    implementation("com.discord4j:discord4j-core:3.1.0.RC1")
    implementation("org.slf4j:slf4j-api:1.7.25")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}