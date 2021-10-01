package com.flight.seats;

import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		if(args.length != 2) throw new Exception("input is invalid");
		
		String seats = args[0];//"[ [3,2], [4,3], [2,3], [3,5] ]";
		String persons = args[1];//"30";
		int[][] availableSeats = parsingInputs(seats);
		int totalSeats = getTotalSeats(availableSeats);
		int waitinginQueue = Integer.parseInt(persons);
		if(totalSeats < waitinginQueue) {
			throw new Exception("Seats is not available for waiting seat");
		}
		int aisleSeatsCount = getAisleSeatsCount(availableSeats);
		int windowSeatsCount = getWindowSeatsCount(availableSeats);
		fillArrangSeat(availableSeats, waitinginQueue, aisleSeatsCount, windowSeatsCount);
	}
	
	private static int getTotalSeats(int[][] availableSeats) throws Exception {
		int total = 0;
		for(int[] lines : availableSeats) {
			total += lines[0] * lines[1];
		}
		return total;
	}
	
	private static int[][] parsingInputs(String seats) throws Exception {
		try {
			seats = seats.replaceAll(" ", "");
			String[] arrays = seats.split("],");
			int[][] mainarray = new int[arrays.length][2];
			for(int i=0;i<arrays.length;i++) {
				String value = arrays[i].replaceAll("\\[", "").replaceAll("]", "");
				String[] values = value.split(",");
				if(values.length == 2) {
					mainarray[i][0] = Integer.parseInt(values[0]);
					mainarray[i][1] = Integer.parseInt(values[1]);
					if(mainarray[i][0] < 1 || mainarray[i][0] < 1) {
						throw new Exception("Seats 2D input is invalid");
					}
				}
			}
			return mainarray;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Seats 2D input is invalid");
		}
	}
	
	
	private static void fillArrangSeat(int[][] availableSeats, int peoples,
			int aisleSeatsCount, int windowSeatsCount) throws Exception {
		int availableAisleSeatsCount = peoples >= aisleSeatsCount?aisleSeatsCount:peoples;
		peoples = peoples - availableAisleSeatsCount;
		int availableWindowSeatsCount = peoples >= windowSeatsCount?windowSeatsCount:peoples;
		peoples = peoples - availableWindowSeatsCount;
		int availableOtherSeatsCount = peoples;
		int seatNumber = 0;
		int filledAisleSeatNumber = 0;
		int filledWindowSeatNumber = 0;
		int filledAisleSeats = 0;
		int filledWindowSeats = availableAisleSeatsCount;
		int filledOtherSeats = filledWindowSeats + availableWindowSeatsCount;
		List<int[][]> list = new LinkedList<int[][]>();
		int maxlength = 0;
		for(int i=0 ; i<availableSeats.length ; i++) {
			System.out.println("");
			list.add(new int[availableSeats[i][1]][availableSeats[i][0]]);
			maxlength = availableSeats[i][1]>maxlength?availableSeats[i][1]:maxlength;
		}
		for(int i=0;i<maxlength;i++) {
			System.out.println(" ");
			for( int a =0 ; a < list.size();a++) {
				availableSeats = list.get(a);
				System.out.print("    ");
				if(i >= availableSeats.length ) {
					System.out.print("   ");
					for(int m=0;m<availableSeats[0].length;m++) {

						System.out.print("  ");
					}

					continue;
				}
				for( int b =0 ; b < availableSeats[i].length;b++) {
					System.out.print(" ");
					if(((a == 0 && b == 0 ) || (a == list.size()-1 && b==availableSeats[availableSeats.length-1].length-1)) && availableWindowSeatsCount >= filledWindowSeatNumber) {
						filledWindowSeatNumber++;
						filledWindowSeats++;
						System.out.print(filledWindowSeats>9?filledWindowSeats:"0"+filledWindowSeats);
					} else if((b == 0 || b==availableSeats[i].length-1) && availableAisleSeatsCount >= filledAisleSeatNumber) {
							filledAisleSeatNumber++;
							filledAisleSeats++;
							System.out.print(filledAisleSeats>9?filledAisleSeats:"0"+filledAisleSeats);
					} else if(availableOtherSeatsCount >= seatNumber) {
							seatNumber++;
							filledOtherSeats++;
							System.out.print(filledOtherSeats>9?filledOtherSeats:"0"+filledOtherSeats);
					} else {
						System.out.print("  ");
					}
					
				}
			}
		}
	}
	
	private static int getAisleSeatsCount(int[][] availableSeats) throws Exception {
		int totalSeats = 0;
		if(availableSeats.length == 0) {
			throw new Exception("Seats 2D input is invalid");
		}
		if(availableSeats.length == 1) {
				return 0;
		}
		for(int i=0 ; i< availableSeats.length ;  i++) {
			if(i == 0 || i == availableSeats.length-1) {
				totalSeats += availableSeats[i][1];
			} else {				
				totalSeats += availableSeats[i][1] * 2;
			}
		}
		return totalSeats;
	}
	
	private static int getWindowSeatsCount(int[][] availableSeats) throws Exception {
		if(availableSeats.length == 0) {
			throw new Exception("Seats 2D input is invalid");
		}
		if(availableSeats.length == 1) {
			if(availableSeats[0].length == 1) {
				return availableSeats[0][0];
			}
		}
		return availableSeats[0][1] + availableSeats[availableSeats.length-1][1];
	}
	



}

class Seat {
	private String name;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
