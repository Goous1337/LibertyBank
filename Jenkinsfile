final String TEST_CONTAINER_IMAGE = 'maven:3.8.7-openjdk-18-slim'
final String GIT_REPO_URL = 'https://git.astondevs.ru/aston/liberty-bank/liberty-bank-aqa-web-and-api.git'
final String GIT_CREDS_ID = 'gitlab-aston'

pipeline {
	agent any
	environment {
		INNER_CONTAINER_WORK_DIR = "/usr/src/myapp"
	}
	stages {
		stage('Clone SCM') {
			steps {
				echo "env.GIT_BRANCH: ${env.GIT_BRANCH}"
				git branch: env.GIT_BRANCH.replaceFirst('origin/', ''),  credentialsId: GIT_CREDS_ID, url: GIT_REPO_URL
			}
		}
		stage('Run tests') {
			agent {
        		docker { 
					image TEST_CONTAINER_IMAGE
					args "-w ${INNER_CONTAINER_WORK_DIR}"
					reuseNode true
				}
			}
				steps {
					sh 'mvn clean test'
					sh 'ls -al'
				}
    	}
		stage('Collect Allure report') {
			steps {
				allure([
					includeProperties: false,
					reportBuildPolicy: 'ALWAYS',
					results: [[path: 'api_target_allure-results']]
				])
			}
		}
    }
	post {
		cleanup { 
			cleanWs()
		}	
	}	
}