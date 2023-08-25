package com.jpmc.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.jpmc.exercise.SubarrayDivision;

public class SubarrayTest {

	private SubarrayDivision obj;

	@Test
	public void nullList() {
		obj = new SubarrayDivision(null);
		assertFalse(obj.validate());
	}

	@Test
	public void emptyList() {
		obj = new SubarrayDivision(Arrays.asList());
		assertFalse(obj.validate());
	}

	@Test
	public void minListSize() {
		obj = new SubarrayDivision(Arrays.asList(1, 2));
		assertFalse(obj.validate());
	}

	@Test
	public void minimalList() {
		obj = new SubarrayDivision(Arrays.asList(4, 5, 6));
		assertTrue(obj.validate());
		obj.compute();
		assertArrayEquals(new Integer[] { 4 }, obj.getP1().toArray());
		assertEquals(Integer.valueOf(4), obj.getS1());
		assertArrayEquals(new Integer[] { 5 }, obj.getP2().toArray());
		assertEquals(Integer.valueOf(5), obj.getS2());
		assertArrayEquals(new Integer[] { 6 }, obj.getP3().toArray());
		assertEquals(Integer.valueOf(6), obj.getS3());
		assertEquals(Integer.valueOf(7), obj.getMin());
	}

	@Test
	public void positiveList() {
		obj = new SubarrayDivision(Arrays.asList(2, 3, 1, 7, 8, 9, 6, 5));
		assertTrue(obj.validate());
		obj.compute();
		assertArrayEquals(new Integer[] { 2, 3, 1, 7, 8 }, obj.getP1().toArray());
		assertEquals(Integer.valueOf(21), obj.getS1());
		assertArrayEquals(new Integer[] { 9 }, obj.getP2().toArray());
		assertEquals(Integer.valueOf(9), obj.getS2());
		assertArrayEquals(new Integer[] { 6, 5 }, obj.getP3().toArray());
		assertEquals(Integer.valueOf(11), obj.getS3());
		assertEquals(Integer.valueOf(1), obj.getMin());
	}

	@Test
	public void negativeList() {
		obj = new SubarrayDivision(Arrays.asList(-4, -2, -1, -6, -7, -3, -5, -8));
		assertTrue(obj.validate());
		obj.compute();
		assertArrayEquals(new Integer[] { -4, -2, -1, -6, -7 }, obj.getP1().toArray());
		assertEquals(Integer.valueOf(-20), obj.getS1());
		assertArrayEquals(new Integer[] { -3 }, obj.getP2().toArray());
		assertEquals(Integer.valueOf(-3), obj.getS2());
		assertArrayEquals(new Integer[] { -5, -8 }, obj.getP3().toArray());
		assertEquals(Integer.valueOf(-13), obj.getS3());
		assertEquals(Integer.valueOf(4), obj.getMin());
	}

	@Test
	public void mixList() {
		obj = new SubarrayDivision(Arrays.asList(1, -2, 3, -4, 5, -6, 7, -8, 9));
		assertTrue(obj.validate());
		obj.compute();
		assertArrayEquals(new Integer[] { 1, -2, 3 }, obj.getP1().toArray());
		assertEquals(Integer.valueOf(2), obj.getS1());
		assertArrayEquals(new Integer[] { -4 }, obj.getP2().toArray());
		assertEquals(Integer.valueOf(-4), obj.getS2());
		assertArrayEquals(new Integer[] { 5, -6, 7, -8, 9 }, obj.getP3().toArray());
		assertEquals(Integer.valueOf(7), obj.getS3());
		assertEquals(Integer.valueOf(1), obj.getMin());
	}
}
