pipeline {
    agent any
    stages {
        stage("初始化环境变量") {
            steps {
                script {
                    groupName="${env.GIT_URL.drop(30).split("/")[0]}"
                    projectName="${env.GIT_URL.drop(30).split('/')[1][0..-5]}"
                    env['groupName']="$groupName"
                    env['projectName']="$projectName"
                    env['versionShell']="\$(grep -E \"<version>.+?</version>\" pom.xml | sed -n '2p'|sed 's/<version>//'|sed 's/<\\/version>//'|sed 's/ //g')"
                    env['targetVersion'] =env.branch=="dev"||env.branch==~/^f_.*_dev$/?"dev":env.branch=="test"||env.branch==~/^f_.*_test$/?"test":env.versionShell
                    env['branchConfig']=env.branch=="dev"||env.branch==~/^f_.*_dev$/?"dev":env.branch=="test"||env.branch==~/^f_.*_test$/?"test":env.branch
                }
                sh 'printenv'
            }
        }

        stage('拉取部署代码') {
            steps {
                checkout([
                    $class           : 'GitSCM',
                    branches         : [[name: '*/main']],
                    extensions       : [
                        [$class: 'RelativeTargetDirectory', relativeTargetDir: 'docker'],
                    ],
                    userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://gitlab.fengchaoit.com/basic/buildscripts.git']]
                ])
                sh "echo '拷贝Dockerfile文件'"
                sh "cp ./docker/backend/Dockerfile ."
				sh "echo '拷贝Docker默认启动docker-entrypoint.sh脚本'"
				sh "cp ./docker/backend/docker-entrypoint.sh ."
            }
        }

        stage('项目打包为镜像') {
            steps {
                sh "docker build --build-arg PROJECTCODE=$projectName --build-arg PROJECTVERSION=$targetVersion -t $projectName:$targetVersion ."
            }
        }

		stage('推送镜像到镜像仓库') {
			steps {
                sh "echo '改镜像标签'"
                sh "docker tag $projectName:$targetVersion harbor.fengchaoit.com/$groupName/$projectName:$targetVersion"
				sh "echo '镜像入库'"
                sh "docker push harbor.fengchaoit.com/$groupName/$projectName:$targetVersion"
            }
		}

		stage('保存项目镜像到project文件夹') {
		    steps {
		        sh "mkdir -p project"
		        sh "docker save $projectName:$targetVersion -o ${env.projectName}_${env.targetVersion}.tar"
		        sh "tar zcf ${env.projectName}_${env.targetVersion}.tar.gz ${env.projectName}_${targetVersion}.tar"
		        sh "cp *.txt *.tar.gz project/"
		        sh "chmod +x docker/backend/apprun.sh"
		        sh "cp docker/backend/apprun.sh project/"
                sh "sed -i \"s/^PROJECT_NAME.*/PROJECT_NAME=${env.projectName}/g\" project/apprun.sh"
                sh "sed -i \"s/^IMAGE_NAME.*/IMAGE_NAME=${env.projectName}/g\" project/apprun.sh"
		        script {
		            if(!Boolean.parseBoolean(copysh)) {
		                sh "rm -rf project/apprun.sh"
		            }
		        }

		    }
		}

		stage('保存到发布版本目录'){
		    when {
                expression { env.branch == "main" }
            }
            steps {
                sh "mkdir -p /projects/$groupName/$targetVersion/$projectName"
                sh "rm -f /projects/$groupName/$targetVersion/$projectName/*"
                sh "cp project/*  /projects/$groupName/$targetVersion/$projectName/"
            }
		}

		stage('部署项目到服务器'){
		    when {
		        expression { env.branch == "dev" || env.branch == "test" || env.branch =~ /(^f_.*_dev$)|(^fix_.*_test$)/  }
		    }
		    steps {
		        sshPublisher(
		            continueOnError: false,
		            failOnError: true,
		            publishers: [
		                sshPublisherDesc(
		                    configName: env.branchConfig,
		                    transfers: [
		                        sshTransfer(
		                            cleanRemote: false,
		                            excludes: '',
		                            execCommand: [
		                                "echo 定位到项目位置",
		                                "cd /usr/local/fengchaoit/WorkSpace/$projectName",
		                                "echo 授予启动脚本权限",
		                                "chmod +x ./apprun.sh",
		                                "echo 停止正在运行服务",
		                                "./apprun.sh remove",
		                                "echo 启动新构建服务",
		                                "./apprun.sh restart"
		                            ].join('\n'),
		                            execTimeout: 120000,
		                            flatten: false,
		                            makeEmptyDirs: false,
		                            noDefaultExcludes: false,
		                            patternSeparator: '[, ]+',
		                            remoteDirectory: "/usr/local/fengchaoit/WorkSpace/$projectName",
		                            remoteDirectorySDF: false,
		                            removePrefix: 'project',
		                            sourceFiles: 'project/*'
		                        )
		                    ],
		                    usePromotionTimestamp: false,
		                    useWorkspaceInPromotion: false,
		                    verbose: true
		                )
		            ]
		        )
		    }
		}

		stage('清理') {
		    steps {
		        sh "echo '删除原始镜像'"
                sh "docker rmi $projectName:$targetVersion"
		    }
		}
    }
    post {
        success {
            script {
                if(env.branch == "main") {
                    sh "curl -X POST -H \"Content-Type: application/json\" -d '{\"msg_type\":\"interactive\",\"card\":{\"config\":{},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"column_set\",\"flex_mode\":\"none\",\"horizontal_spacing\":\"default\",\"background_style\":\"default\",\"columns\":[{\"tag\":\"column\",\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目${env.branch}分支打包成功，请及时获取备份\",\"text_size\":\"normal\",\"text_align\":\"left\",\"text_color\":\"default\"},\"icon\":{\"tag\":\"standard_icon\",\"token\":\"announce_filled\",\"color\":\"grey\"}}],\"width\":\"weighted\",\"weight\":1}]}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"版本构建成功通知\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目打包成功\"},\"template\":\"green\",\"ud_icon\":{\"tag\":\"standard_icon\",\"token\":\"msgcard-rectangle_outlined\"}}}}}}' https://open.feishu.cn/open-apis/bot/v2/hook/ecad4435-131f-4d49-ab2b-d12e95c9b245"
                } else if(env.branch == "dev" || env.branch == "test" || env.branch =~ /(^f_.*_dev$)|(^fix_.*_test$)/) {
                    sh "curl -X POST -H \"Content-Type: application/json\" -d '{\"msg_type\":\"interactive\",\"card\":{\"config\":{},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"column_set\",\"flex_mode\":\"none\",\"horizontal_spacing\":\"default\",\"background_style\":\"default\",\"columns\":[{\"tag\":\"column\",\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目${env.branch}分支打包成功且已部署到${env.branchConfig}环境，请及时查看验证\",\"text_size\":\"normal\",\"text_align\":\"left\",\"text_color\":\"default\"},\"icon\":{\"tag\":\"standard_icon\",\"token\":\"announce_filled\",\"color\":\"grey\"}}],\"width\":\"weighted\",\"weight\":1}]}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"版本构建成功通知\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目部署成功\"},\"template\":\"green\",\"ud_icon\":{\"tag\":\"standard_icon\",\"token\":\"msgcard-rectangle_outlined\"}}}}}}' https://open.feishu.cn/open-apis/bot/v2/hook/ecad4435-131f-4d49-ab2b-d12e95c9b245"
                } else {
                    sh "curl -X POST -H \"Content-Type: application/json\" -d '{\"msg_type\":\"interactive\",\"card\":{\"config\":{},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"column_set\",\"flex_mode\":\"none\",\"horizontal_spacing\":\"default\",\"background_style\":\"default\",\"columns\":[{\"tag\":\"column\",\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目${env.branch}分支构建成功，非有效分支无法进行远程部署，请及时检查\",\"text_size\":\"normal\",\"text_align\":\"left\",\"text_color\":\"default\"},\"icon\":{\"tag\":\"standard_icon\",\"token\":\"announce_filled\",\"color\":\"grey\"}}],\"width\":\"weighted\",\"weight\":1}]}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"版本构建成功通知\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目构建成功\"},\"template\":\"green\",\"ud_icon\":{\"tag\":\"standard_icon\",\"token\":\"msgcard-rectangle_outlined\"}}}}}}' https://open.feishu.cn/open-apis/bot/v2/hook/ecad4435-131f-4d49-ab2b-d12e95c9b245"
                }
            }
        }
        failure {
            sh "curl -X POST -H \"Content-Type: application/json\" -d '{\"msg_type\":\"interactive\",\"card\":{\"config\":{},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"column_set\",\"flex_mode\":\"none\",\"horizontal_spacing\":\"default\",\"background_style\":\"default\",\"columns\":[{\"tag\":\"column\",\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目${env.branch}分支构建失败，请及时查看失败原因并修复\",\"text_size\":\"normal\",\"text_align\":\"left\",\"text_color\":\"default\"},\"icon\":{\"tag\":\"standard_icon\",\"token\":\"announce_filled\",\"color\":\"grey\"}}],\"width\":\"weighted\",\"weight\":1}]}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"项目构建失败通知\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目构建失败\"},\"template\":\"red\",\"ud_icon\":{\"tag\":\"standard_icon\",\"token\":\"close_outlined\"}}}}}}' https://open.feishu.cn/open-apis/bot/v2/hook/ecad4435-131f-4d49-ab2b-d12e95c9b245"
        }
        aborted {
            sh "curl -X POST -H \"Content-Type: application/json\" -d '{\"msg_type\":\"interactive\",\"card\":{\"config\":{},\"i18n_elements\":{\"zh_cn\":[{\"tag\":\"column_set\",\"flex_mode\":\"none\",\"horizontal_spacing\":\"default\",\"background_style\":\"default\",\"columns\":[{\"tag\":\"column\",\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目${env.branch}分支构建未成功，请及时查看失败原因并修复\",\"text_size\":\"normal\",\"text_align\":\"left\",\"text_color\":\"default\"},\"icon\":{\"tag\":\"standard_icon\",\"token\":\"announce_filled\",\"color\":\"grey\"}}],\"width\":\"weighted\",\"weight\":1}]}]},\"i18n_header\":{\"zh_cn\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"项目构建未成功通知\"},\"subtitle\":{\"tag\":\"plain_text\",\"content\":\"${projectName}项目构建未成功\"},\"template\":\"orange\",\"ud_icon\":{\"tag\":\"standard_icon\",\"token\":\"warning_outlined\"}}}}}}' https://open.feishu.cn/open-apis/bot/v2/hook/ecad4435-131f-4d49-ab2b-d12e95c9b245"
        }
        cleanup {
            sh "echo '清理无用空镜像'"
            sh "docker image prune -f"
            sh "var=\$(docker ps -a -q --filter \"status=exited\");if [ ! -z \"\$var\"];then docker rm \$var; fi"
            cleanWs()
        }
    }
}
