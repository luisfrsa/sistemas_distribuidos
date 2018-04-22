echo -e "Starting RMI Registry....\n";
cd build/;
echo -e "RMI Registry has been started\n"
rmiregistry;
cd ../;
echo -e "Is being closed....\n";
sleep 3;
