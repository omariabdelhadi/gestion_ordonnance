pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = "abd1230"
    }

    stages {
        stage('Patient Service') {
//             when { changeset "patient-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/patient-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('patient-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Auth Service') {
//             when { changeset "auth-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/auth-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('auth-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Medicament Service') {
//             when { changeset "medicament-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/medicament-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('medicament-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Ordonnance Service') {
//             when { changeset "ordonnance-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/ordonnance-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('ordonnance-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Ordonnance Bot') {
//             when { changeset "ordonnance-bot/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/ordonnance-bot"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('ordonnance-bot') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Config Service') {
            //when { changeset "config-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/config-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('config-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Discovery Server') {
           // when { changeset "discovery-server/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/discovery-server"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('discovery-server') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Gateway Server') {
           // when { changeset "gateway-server/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/gateway-server"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('gateway-server') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Analytics Service') {
           // when { changeset "analytics-service/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/analytics-service"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('analytics-service') {
                        sh 'chmod +x mvnw'
                        sh './mvnw -B clean package'
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Angular App') {
           // when { changeset "projet-angulare/**" }
            steps {
                script {
                    def img = "${DOCKER_REGISTRY}/projet-angulare"
                    def tag = "${env.BUILD_NUMBER}"
                    dir('projet-angulare') {
                        sh "docker build -t ${img}:${tag} -t ${img}:latest ."
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                            sh 'echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin'
                            sh "docker push ${img}:${tag}"
                            sh "docker push ${img}:latest"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }

        stage('Deploy') {
  /*           when {
                anyOf {
                    changeset "patient-service *//**"
                    changeset "auth-service *//**"
                    changeset "medicament-service *//**"
                    changeset "ordonnance-service *//**"
                    changeset "ordonnance-bot *//**"
                    changeset "config-service *//**"
                    changeset "discovery-server *//**"
                    changeset "gateway-server *//**"
                    changeset "analytics-service *//**"
                    changeset "projet-angulare *//**"
                }
            } */
            steps {
                sh 'docker compose down --remove-orphans || true'
                sh 'docker compose pull'
                sh 'docker compose up -d --remove-orphans'
            }
        }
    }
}