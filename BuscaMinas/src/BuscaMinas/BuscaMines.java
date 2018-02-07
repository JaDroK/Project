package BuscaMinas;
import java.util.Scanner;
import java.util.Random;

public class BuscaMines {
	static Scanner reader = new Scanner(System.in);
	static Random rnd = new Random();
	public static void main(String[] args) {
		int opcio = 0, MAX = 0, i = 0, j = 0;
		boolean acabar = true;
		MostrarOpcio();
		opcio = TriarDificul();
		MAX = ContQuad(opcio);
		int[][] QuadMostra = new int[MAX][MAX];
		int[][] QuadPriv = new int[MAX][MAX];
		OmplirQuadBomb(opcio, QuadPriv, QuadMostra, MAX);
		do {
			
			TriarPos();
			for(i=0;i<MAX;i++) {
				for(j=0;j<MAX;j++) {
					System.out.print(QuadPriv[i][j]);
				}
				System.out.println();;
			}
			acabar = Tornar();
		}while(!acabar);
		
		
		
	}
	
	// Metode ContQuad(opcio);
	/**
	 * Defineix la quantitat d'espais te el nostre tauler
	 * @param opcio Valor que s'utilitza com a referencia per definir el MAX en aquest metode
	 * @return<ul>
	 * 		<li>Opcio == 1</li>
	 * 			Defineix que el MAX és 6
	 * 		<li>Opcio == 2</li>
	 * 			Defineix que el MAX és 12
	 * 		<li>Opcio == 3</li>
	 * 			Defineix que el MAX és 24
	 * 		</ul>
	 */
	private static int ContQuad(int opcio) {
		int MAX = 0;
		System.out.println(opcio);
		switch(opcio){
		case 1:
			MAX = 6; // Principiante
			System.out.println("Tablero: " + MAX + " x " + MAX);
		case 2:
			MAX = 12; // Amateur
			System.out.println("Tablero: " + MAX + " x " + MAX);
		case 3:
			MAX = 24; // Professional
			System.out.println("Tablero: " + MAX + " x " + MAX);
	}
		
		return MAX;
	}
	
	//MostrarOpcio();
	/**
	 * Mostra les diferents opcions de nivell que tenim dins del joc
	 * 1 == Principiante
	 * 2 == Amateur
	 * 3 == Professional
	 * 0 == Sortir del programa
	 */
	private static void MostrarOpcio() {
		MissatgeSpace("[1] - Principiante");
		MissatgeSpace("[2] - Amateur");
		MissatgeSpace("[3] - Professinal");
		MissatgeSpace("[0] - Sortir");
	}
	//TriarDificul();
	/**
	 * Permet a l'usuari triar la dificultad del joc.
	 * @return<ul>
	 * 			<li>opcio == 1 El nivell triat es principiante</li>
	 * 			<li>opcio == 2 El nivell triat es amateur</li>
	 * 			<li>opcio == 3 El nivell triat es professional</li>
	 * 			<ul>
	 */
	private static int TriarDificul() {
		int opcio = 0;
		boolean check = false; //Defineix si l'opcio introduida es correcte o no.
		
	do {	
		try {
			MostraMissatge("Introdueix el nivell de joc:"); //Demanem a l'usuari que introdueixi un nivell de joc.
			opcio = reader.nextInt();
			if(opcio >= 1 && opcio <= 3) {
				check = true; 
			}
		}
			catch (Exception e){
				check = false;
				System.out.println("Error en l'opció introduida");
				reader.nextLine();
			}
	}while(!check);
		if(opcio == 1) { 
			MissatgeSpace("Has escogido la dificultad principiante con: "); //
		}else if (opcio == 2) {
			MissatgeSpace("Has escogido la dificultad amateur con: ");
		}else if (opcio == 3) {
			MissatgeSpace("Has escogido la dificultad professional con: ");
		} //Mostra la mida del tauler i la quantitat de bombes dins del tauler.
		
		return opcio;
	}
	//OmplirQuadBomb(opcio, quadPriv, quadMostra, MAX);
	/**
	 * 
	 * @param opcio Defineix el número de bombes
	 * @param quadPriv És el quadrat que no es mostra a l'usuari
	 * @param quadMostra Es el quadrat que es mostra a l'usuari
	 * @param MAX Mides del tauler 
	 */
	private static void OmplirQuadBomb(int opcio, int[][] quadPriv, int[][] quadMostra, int MAX) {
		// Omplir bombes
		int bomb = 0;
		int i = 0, j = 0;
		switch(opcio){
			case 1:
			bomb = 6; // Nivel Principiante
			case 2:
			bomb = 12; // Nivel Amateur
			case 3:
			bomb = 24; // Nivel Professional
		}
		System.out.println("Bombas:" + bomb);
			
		for(i=0;i<MAX;i++) {
			for(j=0;j<MAX;j++) {
				quadPriv[i][j] = 0;
			}
		}
		do {
			i = rnd.nextInt(MAX);
			j = rnd.nextInt(MAX);
			
			if (quadPriv[i][j] != 9) {
				quadPriv[i][j] = 9; // Bomba es = 9
				bomb--;
				// 1 Posición arriba
				if(quadPriv[i+1][j] != 9 && (i != 0 && i != MAX)&&(j != 0 && i != MAX)) {
					if(quadPriv[i+1][j] != 0) {
						quadPriv[i+1][j]++;
					}else {
					quadPriv[i+1][j] = 1;
					}
				}
				// 1 Posición abajo
				if(quadPriv[i-1][j] != 9 || i == 0 && (i != 0 && i != MAX)&&(j != 0 && j != MAX)) {
					if(quadPriv[i-1][j] != 0) {
						quadPriv[i-1][j]++;
					}else {
					quadPriv[i-1][j] = 1;	
					}
				}
				// 1 Posición derecha 1 arriba
				if(quadPriv[i+1][j+1] != 9 && (i != 0 && i != MAX)&&(j != 0 && j != MAX)) {
					if(quadPriv[i+1][j+1] != 0) {
						quadPriv[i+1][j+1]++;
					}else {
					quadPriv[i+1][j+1] = 1;
					}
				}
				// 1 Posición derecha 1 abajo
				if(quadPriv[i-1][j+1] != 9 && (i != 0 && i != MAX)&&(j != 0 && j != MAX)) {
					if(quadPriv[i-1][j+1] != 0) {
						quadPriv[i-1][j+1]++;
					}else {
					quadPriv[i-1][j+1] = 1;
					}
				}
				// 1 Posición izquierda 1 abajo
				if(quadPriv[i-1][j-1] != 9 && (i != 0 && i != MAX)&&(j != 0 && j != MAX)) {
					if(quadPriv[i-1][j-1] != 0) {
						quadPriv[i-1][j-1]++;
					}else {
					quadPriv[i-1][j-1] = 1;
					}
				}
				// 1 Posición izquierda 1 arriba
				if(quadPriv[i+1][j-1] != 9 && (i != 0 && i != MAX)&&(j != 0 && j != MAX)) {
					if(quadPriv[i+1][j-1] != 0) {
						quadPriv[i+1][j-1]++;
					}else {
					quadPriv[i+1][j-1] = 1;
					}
				}
			}
			
			
		}while(bomb > 0);
		
		//Omplir Quadrat a mostrar
		for(i=0;i<MAX;i++) {
			for(j=0;j<MAX;j++) {
				quadMostra[i][j] = 0;
			}
		}
		
	}
	private static void TriarPos() {
		int i = 0, j = 0;
		
		
	}
	private static boolean Tornar() {
		
		return false;
	}

	public static void MostraMissatge (String string) {
		System.out.print(string.toString());
	}
	public static void MissatgeSpace (String string) {
		System.out.println(string.toString());
	}

}
