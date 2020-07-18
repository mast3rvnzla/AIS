package es.codeurjc.ais.tictactoe;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;



class TestDobles extends TicTacToeGame{
	
	TicTacToeGame juego;
	Player pl1, pl2;
	Connection cn1, cn2;

	//beforeEach
	@BeforeEach
	public void inicializar(){
		
		juego = new TicTacToeGame();
		cn1 = mock(Connection.class);
		cn2 = mock(Connection.class);
		juego.addConnection(cn1);
		juego.addConnection(cn2);
		pl1 = new Player(0,"1","Ricardo");
		pl2 = new Player(1,"2","Julio");
		juego.addPlayer(pl1);
		juego.addPlayer(pl2);
	}
	
	@Test
	void primerJugadorGana() {
		verify(cn1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(cn2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		assertEquals(true,juego.checkTurn(0)); // Comprobacion de que es turno del primer jugador
		juego.mark(0);
		assertEquals(false,juego.checkTurn(0)); // Comprobacion de que el turno ha cambiado despues de invocar al metodo mark
		juego.mark(2);
		assertEquals(true,juego.checkTurn(0));	
		juego.mark(3);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(4);
		assertEquals(true,juego.checkTurn(0));
		juego.mark(6);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(cn1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = argument.getValue();
		assertEquals(pl1, event.player);	
	}
	
	@Test
	void primerJugadorPierde() {
		verify(cn1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(cn2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		assertEquals(true,juego.checkTurn(0)); // Comprobacion de que es turno del primer jugador
		juego.mark(1);
		assertEquals(false,juego.checkTurn(0)); // Comprobacion de que el turno ha cambiado despues de invocar al metodo mark
		juego.mark(0);
		assertEquals(true,juego.checkTurn(0));	
		juego.mark(2);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(3);
		assertEquals(true,juego.checkTurn(0));
		juego.mark(8);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(6);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(cn1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = argument.getValue();
		assertEquals(pl2, event.player);	
	}
	
	@Test
	void Empate() {
		verify(cn1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(cn2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		assertEquals(true,juego.checkTurn(0)); // Comprobacion de que es turno del primer jugador
		juego.mark(0);
		assertEquals(false,juego.checkTurn(0)); // Comprobacion de que el turno ha cambiado despues de invocar al metodo mark
		juego.mark(2);
		assertEquals(true,juego.checkTurn(0));	
		juego.mark(1);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(3);
		assertEquals(true,juego.checkTurn(0));
		juego.mark(5);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(4);
		assertEquals(true,juego.checkTurn(0));
		juego.mark(6);
		assertEquals(true,juego.checkTurn(1));
		juego.mark(7);
		assertEquals(true,juego.checkTurn(0));
		juego.mark(8);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(cn1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = argument.getValue();
		assertEquals(null, event);	
	}
}
