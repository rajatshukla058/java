pipeline{
    agent any
    // environment {
    //     PATH = "$PATH:/opt/apache-maven-3.8.2/bin"
    // }
    stages{
    //    stage('git'){
    //         steps{
    //             git credentialsId: '93005e4c-b144-43a9-b814-4bf5f161df38', url: 'https://github.com/rajatshukla058/java.git'
    //         }
    //      }
          

       stage('Build'){
            steps{
                sh 'mvn clean install'
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
       stage("building dockerfile..........."){
            steps{
               
               sh ('/tmp/remove_container.sh')
               sh "docker rmi 1125431058/webapp:latest"
               sh "docker build -t 1125431058/webapp:latest ."
               
               
             }
        }
    //     stage('Docker Push') {
    // //   agent any
    //   steps {
    //     withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
    //       sh "docker login -u ${env.dockerhubUser} -p ${env.dockerhubPassword}"
    //       sh 'docker push 1125431058/webapp:latest'
    //     }
    //   }
    //   stage('Deploy')
    //   steps {
    //     sh "docker run -d  -p 8081:8080 --name tomcat 1125431058/webapp:latest"
    //   }
    // }


       
    }
}

