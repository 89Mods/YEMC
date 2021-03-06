package theGhastModding.meshingTest.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JOptionPane;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class MeshingTest {
	
	public static final String NAME = "TGM's Minecraft Clone";
	public static final String VERSION = "mcc_05202020";
	
	public static void main(String[] args){
		
		if(args.length > 0 && args[0].equals("-r")) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("genSettings_rng.txt")));
				Random rng = new Random();
				bw.write(Integer.toString(rng.nextInt(128) + 1));
				bw.newLine();
				int ht = rng.nextInt(300) + 1;
				bw.write(Integer.toString(ht));
				bw.newLine();
				bw.write(Integer.toString(ht + rng.nextInt(350 - ht) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(200) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(200) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(200) + 1));
				bw.newLine();
				bw.write("350");
				bw.newLine();
				bw.write(Integer.toString(14) + 2);
				bw.newLine();
				bw.write(Double.toString(rng.nextDouble() * 2.0 + 1.0));
				bw.newLine();
				bw.write(Double.toString(Math.min(0.95, Math.max(0.05D, rng.nextDouble()))));
				bw.newLine();
				bw.write(Integer.toString(14) + 2);
				bw.newLine();
				bw.write(Double.toString(rng.nextDouble() * 2.0 + 1.0));
				bw.newLine();
				bw.write(Double.toString(Math.min(0.95, Math.max(0.05D, rng.nextDouble()))));
				bw.newLine();
				bw.write(Integer.toString(14) + 2);
				bw.newLine();
				bw.write(Double.toString(rng.nextDouble() * 2.0 + 1.0));
				bw.newLine();
				bw.write(Double.toString(Math.min(0.95, Math.max(0.05D, rng.nextDouble()))));
				bw.newLine();
				bw.write(Double.toString(rng.nextDouble() * 0.15 + 0.45));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.write(Integer.toString(rng.nextInt(100) + 1));
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.err.println("Error: ");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Done.");
			System.exit(0);
		}
		
		int width = 1280;
		int height = 720;
		
		double spawnx = 32;
		double spawny = 32;
		if(args.length > 1) {
			spawnx = Double.parseDouble(args[0]);
			spawny = Double.parseDouble(args[1]);
		}
		
		if(!GLFW.glfwInit()){
			JOptionPane.showMessageDialog(null, "Error initializing GLFW", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		long window = GLFW.glfwCreateWindow(width, height, NAME, 0, 0);
		if(window == 0){
			JOptionPane.showMessageDialog(null, "Error creating window", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);
		GLFW.glfwShowWindow(window);
		
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		
		MainGameLoop loop = new MainGameLoop(window, spawnx, spawny);
		loop.start();
	}
	
}