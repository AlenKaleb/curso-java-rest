package com.stefanini.hackathon.rest.api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackathon.rest.entidades.Pessoa;
import com.stefanini.hackathon.rest.exceptions.NegocioException;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/pessoa")
public class PessoaAPI {

	public PessoaAPI() {
		
	}
	
	@Inject
	Repositorio repositorio;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultar() throws NegocioException {
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@GET
	@Path("/{cpf}")
	public Response consultar(@PathParam("cpf") String cpf) throws NegocioException {
		Pessoa pessoa;
		if(repositorio.getMapPessoa().get(cpf) == null) {
			throw new NegocioException("Pessoa nao encontrada");
		}else {
			pessoa = repositorio.getMapPessoa().get(cpf);
		}
		
		return Response.ok(pessoa).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Pessoa pessoa) throws NegocioException {
		if(repositorio.getMapPessoa().get(pessoa.getCpf()) != null) {
			throw new NegocioException("Pessoa ja inserida!");
		}else {
			repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		}
		return Response.ok(repositorio.getMapPessoa().get(pessoa.getCpf())).build();
	}
	
//	@DELETE
//	@Path("/{cpf}")
//	public Response excluir(@PathParam("cpf") String cpf) {
//		repositorio.getMapPessoa().remove(cpf);
//		return Response.ok(repositorio.getMapPessoa().get(cpf)).build();
//	}
	
	@DELETE
	public Response excluir2(@QueryParam("cpf") String cpf) throws NegocioException {
		if(repositorio.getMapPessoa().get(cpf) == null) {
			throw new NegocioException("Pessoa nao encontrada.");
		}else {
			repositorio.getMapPessoa().remove(cpf);
		}
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@PUT
	@Path("/{cpf}")
	public Response alterar(Pessoa pessoa, @PathParam("cpf") String cpf) throws NegocioException {
		if(repositorio.getMapPessoa().get(cpf) == null) {
			throw new NegocioException("Pessoa nao encontrada");
		}else {
			repositorio.getMapPessoa().put(cpf, pessoa);
		}
		
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@GET
	@Path("/{cpf}/{id}")
	public Response associar(@PathParam("cpf") String cpf, @PathParam("id") Integer id) {
		repositorio.getMapPessoa().get(cpf).setConta(repositorio.getMapConta().get(id));
		return Response.ok(repositorio.getMapPessoa().get(cpf)).build();
	}

}
