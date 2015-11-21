package io.github.thunderbots.lightning.utility;

public enum Alliance {
	
	BLUE (1),
	RED (-1);
	
	private final int side;
	
	Alliance(int side) {
		this.side = side;
	}
	
	public int getSide() {
		return this.side;
	}

}
