package com.stefanini.hackathon.rest;

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

@Path("/pessoa")
public class PessoaAPI {

	public PessoaAPI() {
		
	}
	
	@Inject
	Repositorio repositorio;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultar() {
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@GET
	@Path("/{cpf}")
	public Response consultar(@PathParam("cpf") String cpf) {
		return Response.ok(repositorio.getMapPessoa().get(cpf)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Pessoa pessoa) {
		repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		System.out.println(pessoa.getNome());
		return Response.ok(repositorio.getMapPessoa().get(pessoa.getCpf())).build();
	}
	
//	@DELETE
//	@Path("/{cpf}")
//	public Response excluir(@PathParam("cpf") String cpf) {
//		repositorio.getMapPessoa().remove(cpf);
//		return Response.ok(repositorio.getMapPessoa().get(cpf)).build();
//	}
	
	@DELETE
	public Response excluir2(@QueryParam("cpf") String cpf) {
		repositorio.getMapPessoa().remove(cpf);
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@PUT
	@Path("/{cpf}")
	public Response alterar(Pessoa pessoa, @PathParam("cpf") String cpf) {
		repositorio.getMapPessoa().put(cpf, pessoa);
		return Response.ok(repositorio.getMapPessoa()).build();
	}
	
	@GET
	@Path("/{cpf}/{id}")
	public Response associar(@PathParam("cpf") String cpf, @PathParam("id") Integer id) {
		repositorio.getMapPessoa().get(cpf).setConta(repositorio.getMapConta().get(id));
		return Response.ok(repositorio.getMapPessoa().get(cpf)).build();
	}

}
