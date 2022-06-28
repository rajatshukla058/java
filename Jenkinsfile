pipeline{
    agent any
    
    
    stages{

        stage("build"){
            steps{
               sh "mvn clean install -DskipTests"
            }
        }
        stage('SonarQube analysis') {
//    def scannerHome = tool 'SonarScanner 4.0';
            steps{
                withSonarQubeEnv('Version 8.9.8') { 
        // If you have configured more than one global server connection, you can specify its name
//      sh "${scannerHome}/bin/sonar-scanner"
                sh "mvn sonar:sonar"
            }
        }
        }
        stage("building dockerfile..........."){
            steps{
               
               sh ('/tmp/remove_container.sh')
               sh "docker rmi 1125431058/webapp:latest"
               sh "docker build -t 1125431058/webapp:latest ."
               sh "docker run -d  -p 8081:8080 --name tomcat 1125431058/webapp:latest"
               
            }
        }
        stage('Docker Push') {
    //   agent any
            steps {
               withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
               sh "docker login -u ${env.dockerhubUser} -p ${env.dockerhubPassword}"
               sh 'docker push 1125431058/webapp:latest'
        }
      }
    }
}
}
