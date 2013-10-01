package net.projektfriedhof.panzerhandschuh.server;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class SerializerTest {
	
	static final int SIZE = 20;
	
	static final int listSize = 1;
	
	public static void main(String[] args) throws Exception {
	
		System.out.println("GEnerating " + SIZE + "x" + SIZE+"x" +listSize+" testdata thingies :" + SIZE*SIZE*listSize);
		
		
		List list = new ArrayList(listSize);
		
		for(int x = 0; x<listSize;x++){
			GameFieldDef[][] subMap = new GameFieldDef[SIZE][SIZE];
			for(int i=0;i<SIZE;i++){
				for(int j=0;j<SIZE;j++){
					GameFieldDef d = new GameFieldDef();
					subMap[i][j]=d;
				}
			}
			list.add(subMap);
		}
		
		System.out.println("Writing Data " + list.size());
		
		Kryo k = new Kryo();
		
		Output mapData = new Output(new BufferedOutputStream(new FileOutputStream("/tmp/submap.bin")));
		
		k.writeObject(mapData, list);
		mapData.flush();
		mapData.close();
		
		System.out.println("Done");
	}
	

}
