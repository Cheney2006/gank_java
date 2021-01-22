# gank_java
apply plugin: 'maven'

def mavenCommitId = rootProject.ext.mavenCommitId
uploadArchives {
    repositories.mavenDeployer {
        if (mavenCommitId != null && !"".equals(mavenCommitId)) {
            String lastCommitId = mavenCommitId.toString().substring(0, 8)
            snapshotRepository(url: "http://nexus.os.adc.com/nexus/content/repositories/snapshots/") {
                authentication(userName: "cc", password: "cc")
                android.libraryVariants.all { variant ->
                    def buildType = "${variant.buildType.name}"
                    println "buildType is = $buildType"
                    def flavors = variant.productFlavors
                    def mavenName = "${flavors[0].name}"
                    println "mavenName is = $mavenName"
                    def aarName = "${flavors[0].name}-${variant.buildType.name}.aar"
                    println "aarName is = $aarName"
                    def aarPath = "$buildDir/outputs/aar/${project.name}-$aarName"
                    artifacts {
                        if (buildType.contains("release")) {
                            println "aarPath is = $aarPath"
                            archives file: file(aarPath)
                        }
                    }
                    addFilter(mavenName) { artifact, file ->
                        println file.name + "=$aarPath"
                        //选取产物
                        aarPath.contains(file.name)
                    }

                    pom(mavenName).groupId = "com.cheney.accountsdk"
                    pom(mavenName).artifactId = "cheney-$mavenName"
                    pom(mavenName).version = "${allModuleVersion}-${lastCommitId}-SNAPSHOT"
                }
            }
        }
    }
}
