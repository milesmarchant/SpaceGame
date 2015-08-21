package core;

public class Main {
	
	public static void main(String args[]){
		Util.addEntry("Test");
		Util.addEntry("Test2");
		Util.addEntry("Test3");
		
		Util.openLog("Test Log");
		
		Util.openLog("Test Log").addEntry("Inner Test 1");
		Util.openLog("Test Log").addEntry("Inner Test 2");
		Util.openLog("Test Log").addEntry("Inner Test 3");

		Util.openLog("Test Log").openLog("Inner Log");
		
		Util.openLog("Test Log 2");
		
		Util.openLog("Test Log 2").openLog("Inner Log 2").addEntry("Inner inner entry");

		
		new Game();
	}

}
