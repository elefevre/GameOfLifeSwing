import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameOfLifeTest {
	@Test
	public void aSingleLivingCellOnTheBoardDies() {
		assertFalse(gameWith(1, 1).step().isCellAlive(1, 1));
	}

	@Test
	public void singleLivingCellsOnTheBoardDie() {
		assertFalse(gameWith(1, 1, 3, 3, 5, 5, 1, 5).step().isCellAlive(1, 1));
		assertFalse(gameWith(1, 1, 3, 3, 5, 5, 1, 5).step().isCellAlive(3, 3));
		assertFalse(gameWith(1, 1, 3, 3, 5, 5, 1, 5).step().isCellAlive(5, 5));
		assertFalse(gameWith(1, 1, 3, 3, 5, 5, 1, 5).step().isCellAlive(1, 5));
	}

	@Test
	public void liveCellWith2NeighboursLivesOn() {
		assertTrue(gameWith(1, 1, 2, 1, 1, 2).step().isCellAlive(1, 1));
		assertTrue(gameWith(1, 1, 0, 1, 1, 0).step().isCellAlive(1, 1));
		assertTrue(gameWith(1, 1, 0, 0, 2, 2).step().isCellAlive(1, 1));
	}

	@Test
	public void liveCellWith3NeighboursLivesOn() {
		assertTrue(gameWith(1, 1, 2, 1, 1, 2, 1, 0).step().isCellAlive(1, 1));
		assertTrue(gameWith(1, 1, 0, 1, 1, 0, 2, 1).step().isCellAlive(1, 1));
		assertTrue(gameWith(1, 1, 0, 0, 2, 2, 0, 1).step().isCellAlive(1, 1));
	}

	@Test
	public void deadCellWith3NeighboursBecomesAlive() {
		assertTrue(gameWith(2, 1, 1, 2, 1, 0).step().isCellAlive(1, 1));
		assertTrue(gameWith(0, 1, 1, 0, 2, 1).step().isCellAlive(1, 1));
		assertTrue(gameWith(0, 0, 2, 2, 0, 1).step().isCellAlive(1, 1));
	}

	@Test
	public void liveCellsWithMoreThan3NeighboursDie() {
		assertFalse(gameWith(1, 1, 0, 0, 0, 1, 0, 2, 1, 0).step().isCellAlive(
				1, 1));
		assertFalse(gameWith(1, 1, 0, 0, 0, 1, 0, 2, 1, 0, 1, 2, 2, 0, 2, 1, 2,
				2).step().isCellAlive(1, 1));
	}

	@Test
	public void supportsInitialLiveCells() {
		GameOfLife life = new GameOfLife();

		life.setInitialLiveCells(new long[][] { { 0, 0 }, { 2, 4 } });

		assertTrue(life.isCellAlive(0, 0));
		assertTrue(life.isCellAlive(2, 4));
	}

	@Test
	public void initialLiveCellsRemovePreviousLiveCells() {
		GameOfLife life = new GameOfLife();

		life.setInitialLiveCells(new long[][] { { 0, 0 } });
		life.setInitialLiveCells(new long[][] { { 2, 4 } });

		assertFalse(life.isCellAlive(0, 0));
		assertTrue(life.isCellAlive(2, 4));
	}

	@Test
	public void returnsAllLiveCells() {
		GameOfLife life = new GameOfLife();

		life.setInitialLiveCells(new long[][] { { 0, 0 }, { 2, 4 } });

		assertTrue(life.allLiveCells().contains(
				new GameOfLife.Coordinates(0, 0)));
		assertTrue(life.allLiveCells().contains(
				new GameOfLife.Coordinates(2, 4)));
	}

	private GameOfLife gameWith(long... coordinates) {
		GameOfLife life = new GameOfLife();
		long[][] coordinatesAsArray = new long[coordinates.length / 2][2];
		for (int i = 0; i < coordinates.length; i = i + 2) {
			coordinatesAsArray[i / 2] = new long[] { coordinates[i],
					coordinates[i + 1] };
		}

		life.setInitialLiveCells(coordinatesAsArray);

		return life;
	}

}
