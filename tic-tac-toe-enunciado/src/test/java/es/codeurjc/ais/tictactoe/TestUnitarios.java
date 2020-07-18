package es.codeurjc.ais.tictactoe;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//mvn test
class TestUnitarios {
	
	private Board instance;
	private int lineaGanadora[];
	private int lineaObtenida[];
	
	@BeforeEach
	void inicializarTablero() {
		instance = new Board();
		lineaGanadora = new int[3];
		lineaObtenida = new int[3];
		lineaGanadora[0] = 0;
		lineaGanadora[1] = 3;
		lineaGanadora[2] = 6;
	}

	@Test
	void primerJugadorGana() {
		
		//Valores para las celdas
		instance.getCell(0).value = "jugador1";
		instance.getCell(0).active = false;
		instance.getCell(2).value = "jugador2";
		instance.getCell(2).active = false;
		instance.getCell(3).value = "jugador1";
		instance.getCell(3).active = false;
		instance.getCell(4).value = "jugador2";
		instance.getCell(4).active = false;
		instance.getCell(6).value = "jugador1";
		instance.getCell(6).active = false;
		lineaObtenida = instance.getCellsIfWinner("jugador1");
		
		// Aserciones
		assertEquals(false, instance.checkDraw());
		assertArrayEquals(lineaGanadora,lineaObtenida);
	}
	
	@Test
	void primerJugadorPierde() {
		
		//Valores para las celdas
		instance.getCell(1).value = "jugador1";
		instance.getCell(1).active = false;
		instance.getCell(0).value = "jugador2";
		instance.getCell(0).active = false;
		instance.getCell(2).value = "jugador1";
		instance.getCell(2).active = false;
		instance.getCell(3).value = "jugador2";
		instance.getCell(3).active = false;
		instance.getCell(8).value = "jugador1";
		instance.getCell(8).active = false;
		instance.getCell(6).value = "jugador2";
		instance.getCell(6).active = false;
		
		lineaObtenida = instance.getCellsIfWinner("jugador2");
		
		// Aserciones
		assertEquals(false, instance.checkDraw());
		assertArrayEquals(lineaGanadora,lineaObtenida);
	}
	
	@Test
	void empate() {
		
		//Valores para las celdas
		instance.getCell(0).value = "jugador1";
		instance.getCell(0).active = false;
		instance.getCell(2).value = "jugador2";
		instance.getCell(2).active = false;
		instance.getCell(1).value = "jugador1";
		instance.getCell(1).active = false;
		instance.getCell(3).value = "jugador2";
		instance.getCell(3).active = false;
		instance.getCell(5).value = "jugador1";
		instance.getCell(5).active = false;
		instance.getCell(4).value = "jugador2";
		instance.getCell(4).active = false;
		instance.getCell(6).value = "jugador1";
		instance.getCell(6).active = false;
		instance.getCell(7).value = "jugador2";
		instance.getCell(7).active = false;
		instance.getCell(8).value = "jugador1";
		instance.getCell(8).active = false;
		
		// Aserciones
		assertEquals(true,instance.checkDraw());
		assertEquals(null,instance.getCellsIfWinner("jugador1"));
		assertEquals(null,instance.getCellsIfWinner("jugador2"));
	}
}
