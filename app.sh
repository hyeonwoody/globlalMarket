BASEDIR=$(dirname $0)
echo "Script location ${BASEDIR}"
path=${BASEDIR}

javaPort='11000'
reactPort='3000'

react_on()
{
	nohup npm start --prefix ${path}/src/main/frontend >> ./react.logh &
}

java_on()
{
	nohup java -jar ./build/libs/globalMarket-0.0.1-SNAPSHOT.jar >> ./java.logh &
}

killa()
{
	echo "killing java"
	`netstat -lntp | grep :$javaPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
	echo "killing react"
	`netstat -lntp | grep :$reactPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
}

allow_port()
{
	ufw allow 3000
	ufw allow 13000
}

delete_allow_port()
{
	ufw delete allow 3000
	ufw delete allow 13000
}

build_all()
{
	nohup ./gradlew build --exclude-task test > ${path}/java.build.logh &
	nohup npm install --prefix ${path}/src/main/frontend > ${path}/react.build.logh &
}

remove_log()
{
	nohup rm -rf ${path}/*.logh
}

if [ $# -eq 0 ]; then
	javaProcess=`netstat -lntp | grep :$javaPort | awk '{ print $7 }' | cut -d '/' -f1`
	reactProcess=`netstat -lntp | grep :$reactPort | awk '{ print $7 }' | cut -d '/' -f1`
	if [ -z "$javaProcess" ]; then
		echo "java on"
		java_on
	else
		echo "killing"
		killa
		echo "java on"
		java_on
	fi
else 
	case "$1" in
		clean)
			delete_allow_port
			killa
			remove_log
			;;
		delete_allow)
			delete_allow_port
			;;
		allow)
			allow_port
			;;
		build)
			build_all
			;;
		start)
			allow_port
			java_on
			react_on
			;;
		stop)
			killa
			;;
		java)
			echo "java on"
			java_on
			;;
		react)
			echo "react on"
			react_on
			;;
		*)
			echo "nothing"
			;;
	esac
fi
