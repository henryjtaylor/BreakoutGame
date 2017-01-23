
public class BlockManager {

	private Block[] UNC_BLOCKS;
	private Block[] KENT_BLOCKS;
	private Block[] KANSAS_BLOCKS;
	private int SIZE;
	private double BALL_WIDTH;
	
	
	public BlockManager(int number, int size, double width) {
		UNC_BLOCKS = new Block[number];
		KENT_BLOCKS = new Block[number];
		KANSAS_BLOCKS = new Block[number];
		SIZE = size;
		BALL_WIDTH = width;
	}
	
	public void addBrick(int position, Block block) {
		if (block.checkHits() == 3) {
			UNC_BLOCKS[position] = block;
		} else if (block.checkHits() == 2) {
			KENT_BLOCKS[position]= block;
		} else {
			KANSAS_BLOCKS[position] = block;
		}
	}
	
	public Block checkBricks(double x, double y) {
		Block hitBlock = null;
		if (y >= KANSAS_BLOCKS[0].getY() && y <= KANSAS_BLOCKS[0].getY() + KANSAS_BLOCKS[0].getFitHeight()) {
			hitBlock = checkCollision(x, y, KANSAS_BLOCKS);
		} else if (y >= KENT_BLOCKS[0].getY() && y <= KENT_BLOCKS[0].getY() + KENT_BLOCKS[0].getFitHeight()) {
			hitBlock = checkCollision(x,y, KENT_BLOCKS);
		} else if (y >= UNC_BLOCKS[0].getY() && y <= UNC_BLOCKS[0].getY() + UNC_BLOCKS[0].getFitHeight()) {
			hitBlock = checkCollision(x,y,UNC_BLOCKS);
		}
		return hitBlock;
	}

	
	private Block checkCollision(double x, double y, Block[] blockList) {
		double width = SIZE/blockList.length;
		Double firstIndex = x/width;
		Double secondIndex = (x+BALL_WIDTH)/width;
		int indexOne = firstIndex.intValue();
		int indexTwo = secondIndex.intValue();
		if (blockList[indexOne].checkHits() == 0) {
			if (blockList[indexTwo].checkHits() != 0) {
				blockList[indexTwo].wasHit();
				return blockList[indexTwo];
			} else {
				return null;
			}
		}
		blockList[indexOne].wasHit();
		return blockList[indexOne];
	}
	
	
}
