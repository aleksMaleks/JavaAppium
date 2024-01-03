emulator @and81
sh /Users/aleks/Desktop/JavaAppium/ci-scripts/runemulator.sh
mvn -Dtest=TestSuite test
mvn -Dtest=SearchTests#testVerifyTextSearchField test