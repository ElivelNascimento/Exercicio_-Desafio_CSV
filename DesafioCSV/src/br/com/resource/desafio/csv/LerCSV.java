package br.com.resource.desafio.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LerCSV {
	public static void main(String[] args) throws Exception {

		String pathArquivo = "C:\\Users\\resource\\eclipse-workspace\\DesafioCSV\\Operacoes.csv";
		String pathArquivo2 = "C:\\Users\\resource\\eclipse-workspace\\DesafioCSV\\DadosMercado.csv";
		lerArquivoCSV(pathArquivo, pathArquivo2);
	}

	private static void lerArquivoCSV(String pathArquivo, String pathArquivo2) throws Exception {

		BufferedReader conteudoCSV = null;
		String linha = "";
		String csvSeparadorCampos = ";";
		List<Saida> saidas = new ArrayList<Saida>();

		try {
			conteudoCSV = new BufferedReader(new FileReader(pathArquivo));
			List<Operacao> listaoperacao = new ArrayList<Operacao>();
			while ((linha = conteudoCSV.readLine()) != null) {
				String[] arquivo = linha.split(csvSeparadorCampos);

				if (!arquivo[0].equals("CD_OPERACAO")) {
					Operacao item = new Operacao();

					  item.setCd_operacao(Double.parseDouble(arquivo[0]));
					  DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					  LocalDate dateIni = LocalDate.parse(arquivo[1], dateformatter);					  
					  item.setDt_inicio(dateIni);
					  LocalDate dateFim = LocalDate.parse(arquivo[2], dateformatter);
					  item.setDt_fim(dateFim);
					  item.setNm_empresa(arquivo[3]);
					  item.setNm_mesa(arquivo[4]);
					  item.setNm_estrategia(arquivo[5]);
					  item.setNm_centralizador(arquivo[6]);
					  item.setNm_gestor(arquivo[7]); 
					  item.setNm_subgestor(arquivo[8]);
					  item.setNm_subproduto(arquivo[9]);
					  item.setNm_caracteristica(arquivo[10]);
					  item.setCd_ativo(arquivo[11]);
					  item.setQuantidade(Float.parseFloat(arquivo[12].replace(',', '.')));
					  item.setId_preco(Integer.parseInt(arquivo[13]));
					  listaoperacao.add(item);
					 
					System.out.println("CD_OPERACAO = " + item.getCd_operacao() 
							+ " , Data inicio = "+ item.getDt_inicio() 
							+ " , Data fim = " + item.getDt_fim() 
							+ " , Numero da empresa = "+ item.getNm_empresa()
							+ " , Numero da mesa = " + item.getNm_mesa()
							+ " , Numero da estrategia = " + item.getNm_estrategia()
							+ " , Numero centralizador = " + item.getNm_centralizador()
							+ " , Numero gestor = " + item.getNm_gestor()
							+ " , Sub gestor = " + item.getNm_subgestor()
							+ " , Sub produto = "+ item.getNm_subproduto() 
							+ " , Caracteristica = " + item.getNm_caracteristica()
							+ " , CD ativo = " + item.getCd_ativo()
							+ " , Quantidade = " + item.getQuantidade()
							+ " , Preco = " + item.getId_preco());

					
				} 
			}
			
			conteudoCSV = null;
			List<Preco> listapreco = new ArrayList<Preco>();
			conteudoCSV = new BufferedReader(new FileReader(pathArquivo2));
			while ((linha = conteudoCSV.readLine()) != null) 
			{ 
				String[] arquivo2 =	linha.split(csvSeparadorCampos); 
				if (!arquivo2[0].equals("ID_PRECO")) 
				{				
					  Preco preco = new Preco(); 
					  preco.setId_preco(Integer.parseInt(arquivo2[0]));
					  preco.setNu_prazo_dias_corridos(Integer.parseInt(arquivo2[1]));
					  preco.setVl_preco(Float.parseFloat(arquivo2[2].replace(',', '.')));
					  listapreco.add(preco);
					  
					  System.out.println("ID preco = " + preco.getId_preco() +
					  " , Prazo dia corrido = "+ preco.getNu_prazo_dias_corridos() +
					  " , VL preco = " + preco.getVl_preco());
					  
				} 
			 }
			
			for (Operacao item : listaoperacao) {

				Long dias = item.getDt_inicio().until(item.getDt_fim(), ChronoUnit.DAYS);

				Preco precoEncontrado = listapreco.stream()
					.filter(preco -> preco.getId_preco().equals(item.getId_preco()))
					.filter(preco -> preco.getNu_prazo_dias_corridos() == Integer.parseInt(dias.toString()))
					.findAny().orElse(null);
				
				
				Float valorPreco = 0f;
				if(precoEncontrado == null)
					valorPreco = 0f;
				else
					valorPreco = precoEncontrado.getVl_preco();

				Float TotalItem = item.getQuantidade() * valorPreco;
				Saida saida = new Saida();
				saida.setNm_subproduto(item.getNm_subproduto());
				saida.setResultado(TotalItem);				
				saidas.add(saida);
				
			}

			criarArquivoCSV("C:\\Users\\resource\\eclipse-workspace\\DesafioCSV\\Resultado_"
					+ System.currentTimeMillis() + ".csv", saidas);

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado:  \n" + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBounds: \n" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Erro: \n " + e.getMessage());
		} finally {
			if (conteudoCSV != null) {
				try {
					conteudoCSV.close();
				} catch (Exception e) {
					System.out.println("IO Erro: \n" + e.getMessage());
				}
			}
		}
	}

	private static void criarArquivoCSV(String filePath, List<Saida> saidas) {
		
		System.out.println("Gerando arquivo CSV");
		FileWriter fileWriter = null;
		try {
			 fileWriter = new FileWriter(filePath);
			
			fileWriter.append("nm_suproduto, Resultado \n");
			
			for(int i = 0; i < saidas.size();i++) {
				fileWriter.append(saidas.get(i).getNm_subproduto());
				fileWriter.append(",");
				fileWriter.append(saidas.get(i).getResultado().toString());				
				fileWriter.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				System.out.println("Arquivo gerado com sucesso");
			} catch (Exception e2) {
				System.out.println("Erro ao gerar arquivo");
				e2.printStackTrace();
			}
		}
	}
}
