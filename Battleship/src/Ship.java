import java.util.ArrayList;
public class Ship {
private int size;
ArrayList<Coordinate> coods;
	public Ship(int size) {
		this.size = size;
		this.coods = new ArrayList<Coordinate>();
		this.coods.ensureCapacity(size);
	}
	public void addPosition(Coordinate c) {
		if(this.coods.size()==this.size)
			return;
		else
			this.coods.add(c);
	}
}
