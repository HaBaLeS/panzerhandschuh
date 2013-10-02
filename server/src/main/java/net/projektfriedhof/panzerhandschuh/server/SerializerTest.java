package net.projektfriedhof.panzerhandschuh.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class SerializerTest {
	
	static final int SIZE = 1000;
	
	static final int listSize = 1;
	
	
	Random r = new Random();
	
	
	public static void main(String[] args) throws Exception {
	
//		System.out.println("GEnerating " + SIZE + "x" + SIZE+"x" +listSize+" testdata thingies :" + SIZE*SIZE*listSize);
//		
//		
//		List list = new ArrayList(listSize);
//		
//		for(int x = 0; x<listSize;x++){
//			GameFieldDef[][] subMap = new GameFieldDef[SIZE][SIZE];
//			for(int i=0;i<SIZE;i++){
//				for(int j=0;j<SIZE;j++){
//					GameFieldDef d = new GameFieldDef();
//					subMap[i][j]=d;
//				}
//			}
//			list.add(subMap);
//		}
//		
//		System.out.println("Writing Data " + list.size());
//		
//		Kryo k = new Kryo();
//		OutputStream gzipOut = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("/tmp/submap.bin.gz")) );
//		Output mapData = new Output(gzipOut);
//		
//		k.writeObject(mapData, list);
//		mapData.flush();
//		mapData.close();
//		
//		System.out.println("Done");
//		
//		File f = new File("/tmp/submap.bin.gz");
//		System.out.println(f.length()/1024);
//		
//		serializeToGZIP(list, f1);
//		serializeRAW(list, f2);
//		printResults()
		
		
		new SerializerTest().run();
		
	}





	private void run() throws Exception{
		for(int i=1;i<=1000*1000*10;i=i*10){
			System.out.println("\nTesting With:" + i + " Elements");
			File outFile = new File("/tmp/Testdata_" + i + "_elems.bin");
			File outGZFile = new File("/tmp/Testdata_" + i + "_elems.bin.gz");
			outFile.delete();
			outGZFile.delete();

			GameFieldDef[] map = new GameFieldDef[i];
			for(int x=0;x<i;x++){
				map[x] = new GameFieldDef();
				map[x].x=x;
				map[x].y=i+x;
				map[x].overlay=r.nextInt(200);
				map[x].owner=(short)r.nextInt(5000);
				map[x].fieldType=(short)r.nextInt(100);
			}
			
			Kryo k = new Kryo();
			Output out = new Output(new FileOutputStream(outFile));
			Output outgz = new Output(new GZIPOutputStream(new FileOutputStream(outGZFile)));
			
			k.writeObject(out,map);
			k.writeObject(outgz, map);
			
			out.flush();
			out.close();
			
			outgz.flush();
			outgz.close();
			
			System.out.println("\t unzipped: " + outFile.length()/1024 + " kb");
			System.out.println("\t gipped  : " + outGZFile.length()/1024 +" kb");
			
			
		}
		
	}
	

}
