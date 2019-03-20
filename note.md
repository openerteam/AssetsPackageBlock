mvn deploy:deploy-file -DgroupId=cn.team.block -DartifactId=assets -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/magicbeans/Develop/Explore/Server/Block/AssetsPackageBlock/target/assets-1.0.0.jar -Durl=http://maven.magic-beans.cn/nexus/content/repositories/thirdparty -DpomFile=/Users/magicbeans/Develop/Explore/Server/Block/AssetsPackageBlock/pom.xml -DrepositoryId=thirdparty


mvn clean package -Dmaven.test.skip=true 