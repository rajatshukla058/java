// // pipeline{
// //     // 
// //     agent 
// //     {
// //     docker {
// //         image 'maven:3.8.1-adoptopenjdk-11'
// //         // label 'my-defined-label'
// //         args  '-v /tmp:/tmp'
// //     }
// //     }   
// // //     tools {
// // //   maven 'test'
// // // //   git 'Default'
// // // }

// //     stages{
// //         stage("A"){
// //             steps{
// //                 echo "========executing A========"
// //                 dir "/var/lib/jenkins/workspace/test2/"
// //                 sh "mvn clean install"
                
// //             }
// //             post{
// //                 always{
// //                     echo "========always====== =="
// //                 }
// //                 success{
// //                     echo "========A executed successfully========"
// //                 }
// //                 failure{
// //                     echo "========A execution failed========"
// //                 }
// //             }
// //         }
// //     }
// //     post{
// //         always{
// //             echo "========always========"
// //         }
// //         success{
// //             echo "========pipeline executed successfully ========"
// //         }
// //         failure{
// //             echo "========pipeline execution failed========"
// //         }
// //     }
// // }




// pipeline{
//     agent any
    
    
//     stages{

//         stage("build"){
//             steps{
//                sh "mvn clean install -DskipTests"
//             }
//         }
//         stage("building dockerfile..........."){
//             steps{
               
//                sh ('/tmp/remove_container.sh')
//                sh "docker rmi 1125431058/webapp:latest"
//                sh "docker build -t 1125431058/webapp:latest ."
//                sh "docker run -d  -p 8081:8080 --name tomcat 1125431058/webapp:latest"
               
//             }
//         }
//         stage('Docker Push') {
//     //   agent any
//       steps {
//         withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
//           sh "docker login -u ${env.dockerhubUser} -p ${env.dockerhubPassword}"
//           sh 'docker push 1125431058/webapp:latest'
//         }
//       }
//     }
// }
// }


pipeline{
    agent any
    // environment {
    //     PATH = "$PATH:/opt/apache-maven-3.8.2/bin"
    // }
    stages{
//        stage('GetCode'){
//             steps{
//                 sh 'git clone https://github.com/rajatshukla058/java.git'
//             }
//          }        
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
        stage('Docker Push') {
    //   agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
          sh "docker login -u ${env.dockerhubUser} -p ${env.dockerhubPassword}"
          sh 'docker push 1125431058/webapp:latest'
        }
      }
      stage('Deploy')
      step {
        sh "docker run -d  -p 8081:8080 --name tomcat 1125431058/webapp:latest"
      }
        }


       
    }
}
