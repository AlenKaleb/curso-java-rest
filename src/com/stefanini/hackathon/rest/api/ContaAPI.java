package com.stefanini.hackathon.rest.api;

import java.util.ArrayList;

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

import com.stefanini.hackathon.rest.entidades.Conta;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/conta")
public class ContaAPI {

	public ContaAPI() {
		
	}
	
	@Inject
	Repositorio repositorio;
	
	@GET
	public Response consultar() {
		return Response.ok(repositorio.getMapConta()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response consultar(@PathParam("id") Integer id) {
		return Response.ok(repositorio.getMapConta().get(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(ArrayList<Conta> contas) {
		contas.forEach(conta -> repositorio.getMapConta().put(conta.getId(), conta));
		return Response.ok(repositorio.getMapConta()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Conta conta) {
		repositorio.getMapConta().put(conta.getId(), conta);
		System.out.println(conta.getId());
		return Response.ok(repositorio.getMapConta().get(conta.getId())).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir() {
		
		return Response.ok().build();
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Integer id) {
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
