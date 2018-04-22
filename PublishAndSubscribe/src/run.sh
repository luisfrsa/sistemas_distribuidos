echo -e "Initing Publish and Subscriber.....\n";

echo -e "Cleaning build/ folder...\n";
rm build/*.class;
echo -e "build/ cleaned\n";

echo -e "Compiling *.java, saving on build/...\n";
javac *.java -d build/;
sleep 0.5;
echo -e "*.java has been compiled\n";

echo -e "Entering in build/ folder \n";
cd build/;

echo -e "RMIC all Services....\n";
rmic IntermediateService &&  rmic SubscriberService;
sleep 0.5;
echo -e "RMIC all Services has been Done\n";
sleep 0.5;

echo -e "------------------------------------------\n";
echo -e "--------Starting all intermediates--------\n";
echo -e "Starting Intermediate i1....\n";
start "C:\Program Files\Git\git-bash.exe" java Intermediate i1;
echo -e "Intermediate i1 started\n";
sleep 0.5;

echo -e "Starting Intermediate i2....\n";
start "C:\Program Files\Git\git-bash.exe"  java Intermediate i2;
echo -e "Intermediate i2 started\n";
sleep 0.5;

echo -e "Starting Intermediate i3....\n";
start "C:\Program Files\Git\git-bash.exe" java Intermediate i3;
echo -e "Intermediate i3 started\n";
echo -e "----All intermediates has been started----\n";
echo -e "------------------------------------------\n";
sleep 0.5;

echo -e "------------------------------------------\n";
echo -e "--------Starting all Subscribers--------\n";
echo -e "Starting Subscribers a1....\n";
start "C:\Program Files\Git\git-bash.exe" java Subscriber a1;
echo -e "Subscribers a1 started\n";
sleep 0.5;
echo -e "Starting Subscribers a2....\n";
start "C:\Program Files\Git\git-bash.exe"  java Subscriber a2;
echo -e "Subscribers a2 started\n";
sleep 0.5;
echo -e "Starting Subscribers a3....\n";
start "C:\Program Files\Git\git-bash.exe" java Subscriber a3;
echo -e "Subscribers a3 started\n";
echo -e "----All Subscribers has been started----\n";
echo -e "------------------------------------------\n";
sleep 2;

echo -e "------------------------------------------\n";
echo -e "---------------Starting Main--------------\n";
start "C:\Program Files\Git\git-bash.exe" java Main;
echo -e "-----------Main has Been started----------\n";
sleep 5;

echo -e "------------------------------------------\n";
echo -e "---------------Starting Publishers--------\n";
echo -e "Starting java Publishers P1 a1....\n";
start "C:\Program Files\Git\git-bash.exe" java Publisher P1 i1 a1;
echo -e "java Publishers P1 a1 started\n";
sleep 0.5;
echo -e "Starting java Publishers P2 a3....\n";
start "C:\Program Files\Git\git-bash.exe" java Publisher P2 i3 a3;
echo -e "java Publishers P2 a3 started\n";
sleep 0.5;
echo -e "-----------All Publishers has Been started----------\n";
sleep 3600;