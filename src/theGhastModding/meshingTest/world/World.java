package theGhastModding.meshingTest.world;

import java.util.Random;

import theGhastModding.meshingTest.world.blocks.Block;

public class World {
	
	private Chunk[] chunks;
	private int width;
	private int height;
	private int depth;
	private int chunkWidth;
	private int chunkHeight;
	private int chunkDepth;
	
	public static final int DEFAULT_WIDTH = 128;
	public static final int DEFAULT_HEIGHT = 128;
	public static final int DEFAULT_DEPTH = 128;
	
	public World(int width, int height, int depth) {
		this.width = width - (width % Chunk.CHUNK_WIDTH);
		this.height = height - (height % Chunk.CHUNK_HEIGHT);
		this.depth = depth - (depth % Chunk.CHUNK_DEPTH);
		this.chunkWidth = this.width / Chunk.CHUNK_WIDTH;
		this.chunkHeight = this.height / Chunk.CHUNK_HEIGHT;
		this.chunkDepth = this.depth / Chunk.CHUNK_DEPTH;
		chunks = new Chunk[chunkWidth * chunkHeight * chunkDepth];
		for(int i = 0; i < chunkWidth; i++) {
			for(int j = 0; j < chunkHeight; j++) {
				for(int k = 0; k < chunkDepth; k++) {
					chunks[i * (chunkHeight * chunkDepth) + j * chunkDepth + k] = new Chunk(i, j, k);
				}
			}
		}
	}
	
	public boolean setBlock(int x, int y, int z, int block) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth) return false;
		Chunk c = getChunkAt(x, y, z);
		c.setBlock(x - Chunk.CHUNK_WIDTH * c.getChunkx(), y - Chunk.CHUNK_HEIGHT * c.getChunky(), z - Chunk.CHUNK_DEPTH * c.getChunkz(), block);
		return true;
	}
	
	public int getBlock(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth) return 0;
		Chunk c = getChunkAt(x, y, z);
		return c.getBlock(x - Chunk.CHUNK_WIDTH * c.getChunkx(), y - Chunk.CHUNK_HEIGHT * c.getChunky(), z - Chunk.CHUNK_DEPTH * c.getChunkz());
	}
	
	private Chunk getChunk(int chunkx, int chunky, int chunkz) {
		if(chunkx < 0 || chunky >= chunkWidth || chunky < 0 || chunky >= chunkHeight || chunkz < 0 || chunkz >= chunkDepth) return null;
		return chunks[chunkx * (chunkHeight * chunkDepth) + chunky * chunkDepth + chunkz];
	}
	
	private Chunk getChunkAt(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth) return null;
		return getChunk(x / Chunk.CHUNK_WIDTH, y / Chunk.CHUNK_HEIGHT, z / Chunk.CHUNK_DEPTH);
	}
	
	public void generate() {
		int blockCount = 0;
		Random r = new Random();
		for(int i = 0; i < depth; i++) {
			for(int j = 0; j < width; j++) {
				for(int k = 0; k < 60; k++) {
					setBlock(j, k, i, Block.stone.getBlockID()); 
					blockCount++;
					if(k == 59 && r.nextInt(10) == 0) {
						setBlock(j, k + 1, i, Block.grass.getBlockID());
						blockCount++;
					}
				}
			}
		}
		System.out.println(Integer.toString(blockCount) + " blocks in world");
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDepth() {
		return depth;
	}
	
}