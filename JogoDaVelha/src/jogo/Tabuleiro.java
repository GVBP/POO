package jogo;

import javax.swing.JOptionPane;

public class Tabuleiro {

	String[][] itens = new String [3][3];
	int jogadorVez = 0;
	boolean endGame = false;
	
	public void startGame() {
		carga();
		do {
			mostraJogo();
			mostraJogador();
			escolheItem();
			limpaConsole();
		} while(endGame != true);
	}
	
	public void carga() {
		int posicao = 1;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				itens[i][k] = Integer.toString(posicao);
				posicao++;
			}
		}
	}
	
	public void limpaConsole() {
		System.out.println("\033[H\033[2J");  
	    System.out.flush();
	}
	
	public void mostraJogo() {
		String linha = "";
		System.out.println("  Jogador 1: X ");
		System.out.println("  Jogador 2: O ");
		System.out.println("  Jogo da Velha ");
		
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if (k < 2) {
					linha = linha + "   " + itens[i][k] + "  |";
				} else {
					linha = linha + "   " + itens[i][k] + "  |";
					System.out.println(linha);
					linha = "";
				}
			}
		}
	}
	
	public boolean validaNumeroDigitado(int numeroEscolhido) {
		if (numeroEscolhido >= 1 && numeroEscolhido <= 9)
			return true;
		System.out.println("Escolha um número entre 1..9");
		return false;
	}
	
	public void escolheItem() {
		int numeroEscolhido = 0;
		boolean validador = true;
		do {
			numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Escolha um campo de 1..9"));
			if (validaNumeroDigitado(numeroEscolhido)) {
				if (marcaItem(numeroEscolhido)) {
					System.out.println("Escolha um campo que ainda não foi selecionado..");
				} else {
					validador = false;
				}
			}
		} while (validador);
		if (ganhador()) {
			limpaConsole();
			mostraJogo();
            mostraVencedor();
            this.endGame = true;
        } else if (verificaVelha()) {
        	limpaConsole();
        	mostraJogo();
        	System.out.println("#Velha");
            this.endGame = true;
        } else {
            this.jogadorVez = (this.jogadorVez == 0) ? 1 : 0;
        }
	}
	
	public boolean marcaItem(int numeroEscolhido) {
		int aux = 0, coluna = 0, linha = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				aux++;
				if (numeroEscolhido == aux) {
					coluna = i;
					linha = j;
				}
			}
		}
		if (itens[coluna][linha] != "X" && itens[coluna][linha] != "O") {
            if (jogadorVez == 0) {
            	itens[coluna][linha] = "X";
            } else {
            	itens[coluna][linha] = "O";
            }
            return false;
        }
		return true;
	}
	
	public boolean ganhador() {
        if (verificaLinha()) {
            return true;
        } else if (verificaColuna()) {
            return true;
        } else if (verificaDiagonal()) {
            return true;
        } else {
            return false;
        }
	}
	
	public void mostraVencedor() {
		String mensagemGanhador = (jogadorVez == 0) ? "GANHADOR - JOGADOR X" : "GANHADOR - JOGADOR O";
		System.out.println(mensagemGanhador);
	}
	
	public void mostraJogador() {
		String mensagemJogadorVez = (jogadorVez == 0) ? "Vez do jogador X" : "Vez do jogador O";
		System.out.println(mensagemJogadorVez);
	}
	
	public boolean verificaVelha() {
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
            	if (itens[l][m] != "X" && itens[l][m] != "O") {
            		return false;
                }
            }
        }
        return true;
	}
	
	public boolean verificaLinha() {
		for (int l = 0; l < 3; l++) {
            if (itens[0][l] != "" && itens[1][l] != "" && itens[2][l] != "") {
                if (itens[0][l] == itens[1][l] && itens[1][l] == itens[2][l])
                    return true;
            }
        }
        return false;
	}
	
	public boolean verificaColuna() {
		for (int l = 0; l < 3; l++) {
            if (itens[l][0] != "" && itens[l][1] != "" && itens[l][2] != "") {
                if (itens[l][0] == itens[l][1] && itens[l][1] == itens[l][2])
                    return true;
            }
        }
        return false;
	}
	
	public boolean verificaDiagonal() {
		if (itens[0][0] != "" && itens[1][1] != "" && itens[2][2] != "") {
            if (itens[0][0] == itens[1][1] && itens[1][1] == itens[2][2])
                return true;
        }
        if (itens[0][2] != "" && itens[1][1] != "" && itens[2][0] != "") {
            if (itens[0][2] == itens[1][1] && itens[1][1] == itens[2][0])
                return true;
        }
        return false;
	}
}
