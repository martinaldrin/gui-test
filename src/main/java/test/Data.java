package test;

public class Data {
	String id;
	int x;
	int y;
	int type;

	Data(String input) {
		String[] dataTypes = input.split(";");
		String id = dataTypes[0].split("=")[1];
		String x = dataTypes[1].split("=")[1];
		String y = dataTypes[2].split("=")[1];
		String type = dataTypes[3].split("=")[1];
		this.id = id;
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.type = Integer.parseInt(type);
	}

	public String getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", x=" + x + ", y=" + y + ", type=" + type + "]";
	}
}
