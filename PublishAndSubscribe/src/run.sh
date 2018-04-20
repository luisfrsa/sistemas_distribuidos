echo -e "Init-------\n";
rm build/*.class;
echo -e "rm build/\n";
javac *.java -d build/;
echo -e "Build\n";
cd build/;
echo -e "RMIC\n";
rmic IntermediateService && rmic PublisherService && rmic SubscriberService;
echo -e "Start server....\n";
java Intermediate &
 java Publisher &
 java Subscriber;
cd ..;
echo -e "End\n";