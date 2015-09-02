package mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TakePicture {

	public static void takePic() {
		try {
			Process proc = Runtime.getRuntime().exec("streamer -f jpeg -o /home/dinoop/theif.jpeg -s 1280x960"); //Whatever you want to execute
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				System.out.println(read.readLine());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void main(String args[]) throws IOException{
		takePic();
	}
}
