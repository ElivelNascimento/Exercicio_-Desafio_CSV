package br.com.resource.desafio.csv;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Operacao {

	private double cd_operacao;
	private LocalDate dt_inicio;
	private LocalDate dt_fim;
	private String nm_empresa;
	private String nm_mesa;
	private String nm_estrategia;
	private String nm_centralizador;
	private String nm_gestor;
	private String nm_subgestor;
	private String nm_subproduto;
	private String nm_caracteristica;
	private String cd_ativo;
	private Float quantidade;
	private int id_preco;
	
	
	
	
	public double getCd_operacao() {
		return cd_operacao;
	}
	public void setCd_operacao(double cd_operacao) {
		this.cd_operacao = cd_operacao;
	}
	
	public LocalDate getDt_inicio() {
		return dt_inicio;
	}
	public void setDt_inicio(LocalDate dt_inicio) {
		this.dt_inicio = dt_inicio;
	}
	public LocalDate getDt_fim() {
		return dt_fim;
	}
	public void setDt_fim(LocalDate dt_fim) {
		this.dt_fim = dt_fim;
	}
	public String getNm_empresa() {
		return nm_empresa;
	}
	public void setNm_empresa(String nm_empresa) {
		this.nm_empresa = nm_empresa;
	}
	public String getNm_mesa() {
		return nm_mesa;
	}
	public void setNm_mesa(String nm_mesa) {
		this.nm_mesa = nm_mesa;
	}
	public String getNm_estrategia() {
		return nm_estrategia;
	}
	public void setNm_estrategia(String nm_estrategia) {
		this.nm_estrategia = nm_estrategia;
	}
	public String getNm_centralizador() {
		return nm_centralizador;
	}
	public void setNm_centralizador(String nm_centralizador) {
		this.nm_centralizador = nm_centralizador;
	}
	public String getNm_gestor() {
		return nm_gestor;
	}
	public void setNm_gestor(String nm_gestor) {
		this.nm_gestor = nm_gestor;
	}
	public String getNm_subgestor() {
		return nm_subgestor;
	}
	public void setNm_subgestor(String nm_subgestor) {
		this.nm_subgestor = nm_subgestor;
	}
	public String getNm_subproduto() {
		return nm_subproduto;
	}
	public void setNm_subproduto(String nm_subproduto) {
		this.nm_subproduto = nm_subproduto;
	}
	public String getNm_caracteristica() {
		return nm_caracteristica;
	}
	public void setNm_caracteristica(String nm_caracteristica) {
		this.nm_caracteristica = nm_caracteristica;
	}
	
	public String getCd_ativo() {
		return cd_ativo;
	}
	public void setCd_ativo(String cd_ativo) {
		this.cd_ativo = cd_ativo;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
	public int getId_preco() {
		return id_preco;
	}
	public void setId_preco(int id_preco) {
		this.id_preco = id_preco;
	}
	
}
