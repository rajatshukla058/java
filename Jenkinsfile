pipeline{
    // 
    agent 
    {
    docker {
        image 'maven:3.8.1-adoptopenjdk-11'
        // label 'my-defined-label'
        args  '-v /tmp:/tmp'
    }
}
//     tools {
//   maven 'test'
// //   git 'Default'
// }

    stages{
        stage("A"){
            steps{
                echo "========executing A========"
                dir "/var/lib/jenkins/workspace/test2/"
                sh "mvn clean install"
                
            }
            post{
                always{
                    echo "========always====== =="
                }
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
                }
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}

 