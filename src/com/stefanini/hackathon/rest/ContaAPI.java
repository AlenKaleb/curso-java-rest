package com.stefanini.hackathon.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ContaAPI {

public ContaAPI() {
		
	}
	
	@Inject
	Repositorio repositorio;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultar() {
		return Response.ok(repositorio.getMapConta()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Conta conta) {
		repositorio.getMapConta().put(conta.getIdConta(), conta);
		System.out.println(conta.getIdConta());
		return Response.ok(repositorio.getMapConta().get(conta.getIdConta())).build();
	}
	
	@DELETE
	@Path("/{idConta}")
	public Response excluir(@PathParam("idConta") Integer id) {
		repositorio.getMapConta().remove(id);
		return Response.ok(repositorio.getMapConta().get(id)).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response alterar(Conta conta, @PathParam("id") Integer id) {
		repositorio.getMapConta().put(id, conta);
		return Response.ok(repositorio.getMapConta().get(id)).build();
	}
	
}
