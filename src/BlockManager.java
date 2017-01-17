
public class BlockManager {

	private Block[] UNC_BLOCKS;
	private Block[] KENT_BLOCKS;
	private Block[] KANSAS_BLOCKS;
	private int SIZE;
	
	
	public BlockManager(int number, int size) {
		UNC_BLOCKS = new Block[number];
		KENT_BLOCKS = new Block[number];
		KANSAS_BLOCKS = new Block[number];
		SIZE = size;
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
		Double number = x/width;
		int index = number.intValue();
		if (blockList[index].checkHits() == 0) {
			return null;
		}
		blockList[index].wasHit();
		return blockList[index];
	}
	
	
}
