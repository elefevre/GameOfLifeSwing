import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GameOfLife {
	private final Set<Coordinates> liveCells = new HashSet<Coordinates>();

	public GameOfLife step() {
		Set<Coordinates> newLiveCells = new HashSet<Coordinates>();
		Set<Coordinates> cellsNextToALiveCell = new HashSet<Coordinates>();

		for (Coordinates coordinates : liveCells) {
			int numberOfNeighbors = countLiveNeighbors(coordinates);

			if ((numberOfNeighbors == 2)
					&& isCellAlive(coordinates.x, coordinates.y))
				turnCellAlive(newLiveCells, coordinates);

			cellsNextToALiveCell.add(new Coordinates(coordinates.x + 1,
					coordinates.y + 1));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x + 1,
					coordinates.y + 0));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x + 1,
					coordinates.y - 1));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x - 1,
					coordinates.y + 1));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x - 1,
					coordinates.y + 0));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x - 1,
					coordinates.y - 1));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x + 0,
					coordinates.y + 1));
			cellsNextToALiveCell.add(new Coordinates(coordinates.x + 0,
					coordinates.y - 1));
		}
		for (Coordinates coordinates : cellsNextToALiveCell) {
			int numberOfNeighbors = countLiveNeighbors(coordinates);

			if (numberOfNeighbors == 3)
				turnCellAlive(newLiveCells, coordinates);
		}

		replaceWithContentOf(liveCells, newLiveCells);

		return this;
	}

	private int countLiveNeighbors(Coordinates coordinates) {
		int count = 0;
		count += isCellAlive(coordinates.x + 1, coordinates.y + 1) ? 1 : 0;
		count += isCellAlive(coordinates.x + 1, coordinates.y + 0) ? 1 : 0;
		count += isCellAlive(coordinates.x + 1, coordinates.y - 1) ? 1 : 0;
		count += isCellAlive(coordinates.x - 1, coordinates.y + 1) ? 1 : 0;
		count += isCellAlive(coordinates.x - 1, coordinates.y + 0) ? 1 : 0;
		count += isCellAlive(coordinates.x - 1, coordinates.y - 1) ? 1 : 0;
		count += isCellAlive(coordinates.x + 0, coordinates.y + 1) ? 1 : 0;
		count += isCellAlive(coordinates.x + 0, coordinates.y - 1) ? 1 : 0;
		return count;
	}

	private void replaceWithContentOf(Set<Coordinates> target,
			Set<Coordinates> origin) {
		target.clear();
		target.addAll(origin);
	}

	public boolean isCellAlive(Coordinates coordinates) {
		return isCellAlive(liveCells, coordinates);
	}

	private boolean isCellAlive(Set<Coordinates> cells, Coordinates coordinates) {
		return cells.contains(coordinates);
	}

	public boolean isCellAlive(long x, long y) {
		return isCellAlive(new Coordinates(x, y));
	}

	private void turnCellAlive(Set<Coordinates> cells, Coordinates coordinates) {
		cells.add(coordinates);
	}

	private void turnCellAlive(long x, long y) {
		turnCellAlive(liveCells, new Coordinates(x, y));
	}

	public static class Coordinates {
		long x;
		long y;

		public Coordinates(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (x ^ (x >>> 32));
			result = prime * result + (int) (y ^ (y >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinates other = (Coordinates) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	public void setInitialLiveCells(long[][] liveCells) {
		for (int i = 0; i < liveCells.length; i++) {
			long[] coordinates = liveCells[i];
			turnCellAlive(coordinates[0], coordinates[1]);
		}
	}

	public Collection<Coordinates> allLiveCells() {
		return liveCells;
	}

}
