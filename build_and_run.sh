# shellcheck disable=SC2046

build_all_java_classes() {
  javac $(find . -name "*.java")
}

run_main_class() {
  java -cp ./src/main/java Main "port $1 number-of-threads $2"
}

display_help() {
  echo "Usage: $0 [option....] {-b|-r|-p|-t|-h}" >&2
  echo " -b, --build        compile all java classes. Do this before running."
  echo " -r, --run          run the main class."
  echo " -p, --port         the port this server should run in. Default is 8080"
  echo " -t, --threads      the number of threads the executor class should have. Default is 20"
  echo " -h, --help         displays help"
}

BUILD=false
RUN=false
PORT=8080
THREADS=20
while :; do
    case $1 in
      -b|--build) BUILD=true
      ;;
      -r|--run) RUN=true
      ;;
      -p|--port) PORT=$2
      shift
      ;;
      -t|--threads) THREADS=$2
      shift
      ;;
      -h|--help) display_help
      exit
      ;;
      *) break
    esac
    shift
done
if [ $BUILD = true ]; then
  build_all_java_classes
fi
if [ $RUN = true ]; then
  run_main_class $PORT $THREADS
fi