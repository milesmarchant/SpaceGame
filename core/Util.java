package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class Util {
	
	//long[] indexes:
	//0: iteration number
	//1: total time
	//2: current iteration start time
	static HashMap<String, long[]> profiles = new HashMap<String, long[]>();
	private static String profileReport = "";
	
	private static HashMap<String, Integer> periodicReports = new HashMap<String, Integer>();
	
	private static Graphics2D debuggerGraphics; 
	
	//top level log, does not have any entries
	static Log logs = new Log();
	
	//periodic print statement, not very useful
	public static void periodicPrint(String s, String id, int period){
		Integer counter = periodicReports.getOrDefault(id, 0);
		if(counter%period == 0){
			System.out.println(s);
		}
		periodicReports.put(id, counter+1);
	}
	
	//profiler
	public static void startProfile(String profile){
		long[] value = profiles.getOrDefault(profile, new long[3]);
		value[0]++;
		value[2] = System.nanoTime();
		profiles.put(profile, value);
	}
	
	public static void endProfile(String profile){
		long[] value = profiles.getOrDefault(profile, new long[]{0, 0, System.nanoTime()});
		value[1] += System.nanoTime() - value[2];
		profiles.put(profile, value);
	}
	
	public static void reportProfiles(){
		profileReport = "Report of profiles:\n";
		profiles.forEach(new BiConsumer<String, long[]>(){

			@Override
			public void accept(String name, long[] data) {
				profileReport += "\tProfile: "+name+"\n"
						+ "\t\tTimes Run: "+data[0]+"\n"
						+ "\t\tTotal Time: "+(data[1]/1_000_000.0)+"ms\n"
						+ "\t\tAverage Time: "+(data[1]/data[0]/1_000_000.0)+"ms\n";
			}		
		});
		System.out.print(profileReport);
	}
	
	//Logging
	/**
	 * Adds and entry to the top level log
	 * 
	 * @param entry
	 */
	public static void addEntry(String entry){
		logs.addEntry(entry);
	}
	public static Log openLog(String name){
		return logs.openLog(name);
	}
	
	public static void reportLogs(){
		System.out.println("Top Level " + logs.reportLogs(0));
	}
	
	static class Log{
		
		ArrayList<String> entries = new ArrayList<String>();
		HashMap<String, Log> logs = new HashMap<String, Log>();
		String report = "";
		String indent = "";
		
		static String t = "|   ";

		public Log(){
		}
		
		//entries are in the order that they were added
		public void addEntry(String entry){
			entries.add(entry);
		}
		
		public ArrayList<String> getEntries(){
			return entries;
		}
		
		public Log openLog(String name){
			logs.putIfAbsent(name, new Log());
			return logs.get(name);
		}
		
		public HashMap<String, Log> getLogs(){
			return logs;
		}
		
		public String reportLogs(int numTabs){
			for(int i = 0; i < numTabs; i ++){
				indent += t;
			}
			report += indent + "Entries:\n";
			int entryNumber = 0;
			for(String s: entries){
				entryNumber++;
				report += t + indent + "Entry #" + entryNumber + ": " + s + "\n";
			}
			report += indent + "Logs:\n";
			logs.forEach(new BiConsumer<String, Log>(){
				@Override
				public void accept(String s, Log l) {
					report += t + indent + "Log name: " + s + "\n";
					report += l.reportLogs(numTabs+2);
				}
			});
			return report;
		}
		
	}
	
	//Misc
	
	public static boolean contains(int[] arr, int key){
		for(int i : arr){
			if(i == key){
				return true;
			}
		}
		return false;
	}
	
	private static ArrayList<DrawCommand> draws = new ArrayList<DrawCommand>();

	public static synchronized void passGraphics(Graphics2D g) {
		debuggerGraphics = g;
	}
	
	public static synchronized Graphics2D getGraphics(){
		return debuggerGraphics;
	}
	
	public static synchronized void addDrawCommand(DrawCommand dc){
		draws.add(dc);
	}
	
	public static interface DrawCommand{
		public void draw(Graphics2D g);
	}
	
	public static synchronized void draw(){
		for(DrawCommand dc: draws){
			dc.draw(debuggerGraphics);
		}
		draws.clear();
	}

}
