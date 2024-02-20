pipeline {
    agent {
        label 'master'
    }
    environment{
        GIT_REPO_URL = 'https://git.astondevs.ru/aston/liberty-bank/liberty-bank-aqa-web-and-api.git'
        GIT_CREDS_ID = 'gitlab-aston'
        INNER_CONTAINER_WORK_DIR = "/usr/src/myapp"
    }
    stages{
//         stage('Copy GIT') {
//             steps{
//                 git branch: GIT_BRANCH, credentialsId: GIT_CREDS_ID, url: GIT_REPO_URL
//             }
//         }
        stage('Run tests') {
            agent {
                docker {
                    image "${TEST_CONTAINER_IMAGE}"
                    args "-w ${INNER_CONTAINER_WORK_DIR}"
                    reuseNode true
                }
            }
                steps {
                    sh 'mvn clean test -Dgroups=${TEST_TAGS} sonar:sonar'
                    sh 'ls -al'
                }
        }
        stage('Collect Allure report') {
            steps {
                allure([
                    includeProperties: false,
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
