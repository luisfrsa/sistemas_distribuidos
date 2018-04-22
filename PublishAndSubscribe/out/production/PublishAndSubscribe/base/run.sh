echo -e "Init-------\n";
rm build/*.class;
echo -e "rm build/\n";
javac *.java -d build/;
echo -e "Build\n";
cd build/;
echo -e "RMIC\n";
rmic UsuarioService && rmic APIGatewayService && rmic ProdutoService;
echo -e "Start server....\n";
java UsuarioServer &
 java ProdutoServer &
 java APIGateway &
 java ClientUsuario &
 java ClientProduto;
cd ..;
echo -e "End\n";