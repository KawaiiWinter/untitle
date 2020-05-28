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
//    implementation("org.slf4j:slf4j-simple:1.8.0-beta2") exposed-jdbc
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.exposed:exposed-core:0.25.1")
//    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.25.1")

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