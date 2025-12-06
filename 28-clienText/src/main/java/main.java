import java.util.Scanner;

import lombok.extern.log4j.Log4j2;
import services.services;

/*
 * 
 */

@Log4j2
public class main extends services {

	public static void main(String[] args) {

		int op = 0;
		Scanner scn = new Scanner(System.in);

		do {

			System.out.println("selecione a opcao desejada:\n1-adicionar anime \n2-listar todos\n3-procurar nome de anime \n0-sair");

			op = scn.nextInt();
			switch (op) {
				case 1: {
					System.out.println("vc selecionol a opao 1,\n qual qual anime deseja adicionar : ");
					scn.nextLine();
					addAnime(scn.nextLine());
					break;
				}
				case 2: {
					scn.nextLine();
					findAll(scn);
					break;
				}
				case 3:{
					scn.nextLine();
					String name=scn.nextLine();
					System.out.println("vc deseja todos os nomes q comtenhao estes caracteres:? true ou false:");
					boolean comtem=scn.nextBoolean();
					scn.nextLine();
					services.findByName(name,comtem,scn);
					break;
				}
			}
		} while (op != 0);

	}

}
